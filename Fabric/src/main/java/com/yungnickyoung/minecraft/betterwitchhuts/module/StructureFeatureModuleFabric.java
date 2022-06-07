package com.yungnickyoung.minecraft.betterwitchhuts.module;

import com.yungnickyoung.minecraft.betterwitchhuts.BetterWitchHutsCommon;
import com.yungnickyoung.minecraft.betterwitchhuts.world.structure.WitchCircleStructure;
import com.yungnickyoung.minecraft.betterwitchhuts.world.structure.WitchHutStructure;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public class StructureFeatureModuleFabric {
    public static void init() {
        StructureFeatureModule.WITCH_HUT = register("witch_hut", new WitchHutStructure());
        StructureFeatureModule.WITCH_CIRCLE = register("witch_circle", new WitchCircleStructure());
    }

    private static <FC extends FeatureConfiguration> StructureFeature<FC> register(String name, StructureFeature<FC> structureFeature) {
        return Registry.register(Registry.STRUCTURE_FEATURE, new ResourceLocation(BetterWitchHutsCommon.MOD_ID, name), structureFeature);
    }
}
