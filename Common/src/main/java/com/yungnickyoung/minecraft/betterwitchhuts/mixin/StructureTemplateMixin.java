package com.yungnickyoung.minecraft.betterwitchhuts.mixin;

import com.yungnickyoung.minecraft.betterwitchhuts.mixin.accessor.StructureProcessorAccessor;
import com.yungnickyoung.minecraft.betterwitchhuts.module.StructureProcessorTypeModule;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Makes it so a block's waterlogged state is not based solely on the presence of water at the block's position.
 *
 * @author TelepathicGrunt
 */
@Mixin(StructureTemplate.class)
public class StructureTemplateMixin {
    @Inject(method = "placeInWorld", at = @At(value = "HEAD"))
    private void betterwitchhuts_preventAutoWaterlogging(ServerLevelAccessor serverLevelAccessor, BlockPos blockPos1, BlockPos blockPos2, StructurePlaceSettings structurePlaceSettings, RandomSource randomSource, int flag, CallbackInfoReturnable<Boolean> cir) {
        if (structurePlaceSettings.getProcessors()
                .stream()
                .anyMatch(processor -> ((StructureProcessorAccessor)processor).callGetType() == StructureProcessorTypeModule.WATERLOG_PROCESSOR)) {
            structurePlaceSettings.setKeepLiquids(false);
        }
    }
}
