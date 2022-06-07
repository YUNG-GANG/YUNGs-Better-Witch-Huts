package com.yungnickyoung.minecraft.betterwitchhuts.module;

import com.yungnickyoung.minecraft.betterwitchhuts.BetterWitchHutsCommon;
import com.yungnickyoung.minecraft.betterwitchhuts.world.structure.WitchCircleStructure;
import com.yungnickyoung.minecraft.betterwitchhuts.world.structure.WitchHutStructure;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;

public class StructureFeatureModuleForge {
    public static void init() {
        FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(StructureFeature.class, StructureFeatureModuleForge::registerStructures);
    }

    private static void registerStructures(RegistryEvent.Register<StructureFeature<?>> event) {
        IForgeRegistry<StructureFeature<?>> registry = event.getRegistry();
        StructureFeatureModule.WITCH_HUT = register(registry, "witch_hut", new WitchHutStructure());
        StructureFeatureModule.WITCH_CIRCLE = register(registry, "witch_circle", new WitchCircleStructure());
    }

    private static <FC extends FeatureConfiguration> StructureFeature<FC> register(IForgeRegistry<StructureFeature<?>> registry, String name, StructureFeature<FC> structureFeature) {
        structureFeature.setRegistryName(new ResourceLocation(BetterWitchHutsCommon.MOD_ID, name));
        registry.register(structureFeature);
        return structureFeature;
    }
}
