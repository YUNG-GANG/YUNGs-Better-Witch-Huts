package com.yungnickyoung.minecraft.betterwitchhuts.mixin;

import com.yungnickyoung.minecraft.betterwitchhuts.BetterWitchHutsCommon;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.levelgen.feature.ConfiguredStructureFeature;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Cat.class)
public abstract class CatMixin extends TamableAnimal {
    private static final ResourceLocation hutResourceLocation = new ResourceLocation(BetterWitchHutsCommon.MOD_ID, "witch_hut");
    private static final ResourceLocation circleResourceLocation = new ResourceLocation(BetterWitchHutsCommon.MOD_ID, "witch_circle");

    @Shadow
    public abstract void setCatType(int type);

    protected CatMixin(EntityType<? extends TamableAnimal> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "finalizeSpawn", at = @At("RETURN"), cancellable = true)
    public void betterwitchhuts_finalizeSpawn(ServerLevelAccessor levelAccessor, DifficultyInstance difficulty, MobSpawnType mobSpawnType, SpawnGroupData spawnGroupData, CompoundTag tag, CallbackInfoReturnable<SpawnGroupData> cir) {
        spawnGroupData = super.finalizeSpawn(levelAccessor, difficulty, mobSpawnType, spawnGroupData, tag);

        ResourceKey<ConfiguredStructureFeature<?, ?>> hutKey = ResourceKey.create(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY, hutResourceLocation);
        ResourceKey<ConfiguredStructureFeature<?, ?>> circleKey = ResourceKey.create(Registry.CONFIGURED_STRUCTURE_FEATURE_REGISTRY, circleResourceLocation);
        StructureStart structureStart1 = ((ServerLevel) this.level).structureFeatureManager().getStructureWithPieceAt(this.blockPosition(), hutKey);
        StructureStart structureStart2 = ((ServerLevel) this.level).structureFeatureManager().getStructureWithPieceAt(this.blockPosition(), circleKey);
        if (structureStart1.isValid() || structureStart2.isValid()) {
            this.setCatType(10);
            this.setPersistenceRequired();
        }

        cir.setReturnValue(spawnGroupData);
    }
}
