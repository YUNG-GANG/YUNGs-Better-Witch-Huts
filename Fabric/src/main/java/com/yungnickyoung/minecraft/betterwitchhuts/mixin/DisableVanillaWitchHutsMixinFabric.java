package com.yungnickyoung.minecraft.betterwitchhuts.mixin;

import com.yungnickyoung.minecraft.betterwitchhuts.BetterWitchHutsCommon;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.SectionPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureFeatureManager;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChunkGenerator.class)
public class DisableVanillaWitchHutsMixinFabric {
    @Inject(method = "tryGenerateStructure", at = @At(value = "HEAD"), cancellable = true)
    void disableVanillaPyramids(StructureSet.StructureSelectionEntry structureSetEntry,
                                   StructureFeatureManager structureFeatureManager,
                                   RegistryAccess registryAccess,
                                   StructureManager structureManager,
                                   long seed,
                                   ChunkAccess chunkAccess,
                                   ChunkPos chunkPos,
                                   SectionPos sectionPos,
                                   CallbackInfoReturnable<Boolean> cir) {
        if (BetterWitchHutsCommon.CONFIG.general.disableVanillaWitchHuts
                && Registry.STRUCTURE_FEATURE.getKey(structureSetEntry.structure().value().feature).equals(new ResourceLocation("swamp_hut"))) {
            cir.setReturnValue(false);
        }
    }
}
