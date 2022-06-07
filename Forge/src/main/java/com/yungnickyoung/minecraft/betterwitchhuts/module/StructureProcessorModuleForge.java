package com.yungnickyoung.minecraft.betterwitchhuts.module;

import com.yungnickyoung.minecraft.betterwitchhuts.BetterWitchHutsCommon;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class StructureProcessorModuleForge {
    public static void init() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(StructureProcessorModuleForge::commonSetup);
    }

    private static void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            register("waterlog_processor", StructureProcessorModule.WATERLOG_PROCESSOR);
            register("leg_processor", StructureProcessorModule.LEG_PROCESSOR);
            register("fence_leg_processor", StructureProcessorModule.FENCE_LEG_PROCESSOR);
            register("witch_circle_processor", StructureProcessorModule.WITCH_CIRCLE_PROCESSOR);
            register("brewing_stand_processor", StructureProcessorModule.BREWING_STAND_PROCESSOR);
            register("potted_mushroom_processor", StructureProcessorModule.POTTED_MUSHROOM_PROCESSOR);
        });
    }

    private static <P extends StructureProcessor> StructureProcessorType<P> register(String name, StructureProcessorType<P> processorType) {
        return Registry.register(Registry.STRUCTURE_PROCESSOR, new ResourceLocation(BetterWitchHutsCommon.MOD_ID, name), processorType);
    }
}
