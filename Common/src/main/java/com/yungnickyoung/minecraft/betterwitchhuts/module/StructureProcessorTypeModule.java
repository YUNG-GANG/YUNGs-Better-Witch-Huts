package com.yungnickyoung.minecraft.betterwitchhuts.module;

import com.yungnickyoung.minecraft.betterwitchhuts.BetterWitchHutsCommon;
import com.yungnickyoung.minecraft.betterwitchhuts.services.Services;
import com.yungnickyoung.minecraft.betterwitchhuts.world.processor.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;

public class StructureProcessorTypeModule {
    public static StructureProcessorType<WaterlogProcessor> WATERLOG_PROCESSOR = () -> WaterlogProcessor.CODEC;
    public static StructureProcessorType<LegProcessor> LEG_PROCESSOR = () -> LegProcessor.CODEC;
    public static StructureProcessorType<FenceLegProcessor> FENCE_LEG_PROCESSOR = () -> FenceLegProcessor.CODEC;
    public static StructureProcessorType<WitchCircleProcessor> WITCH_CIRCLE_PROCESSOR = () -> WitchCircleProcessor.CODEC;
    public static StructureProcessorType<BrewingStandProcessor> BREWING_STAND_PROCESSOR = () -> BrewingStandProcessor.CODEC;
    public static StructureProcessorType<PottedMushroomProcessor> POTTED_MUSHROOM_PROCESSOR = () -> PottedMushroomProcessor.CODEC;

    public static void init() {
        register("waterlog_processor", StructureProcessorTypeModule.WATERLOG_PROCESSOR);
        register("leg_processor", StructureProcessorTypeModule.LEG_PROCESSOR);
        register("fence_leg_processor", StructureProcessorTypeModule.FENCE_LEG_PROCESSOR);
        register("witch_circle_processor", StructureProcessorTypeModule.WITCH_CIRCLE_PROCESSOR);
        register("brewing_stand_processor", StructureProcessorTypeModule.BREWING_STAND_PROCESSOR);
        register("potted_mushroom_processor", StructureProcessorTypeModule.POTTED_MUSHROOM_PROCESSOR);

    }

    private static void register(String name, StructureProcessorType<?> processorType) {
        Services.REGISTRY.registerStructureProcessorType(new ResourceLocation(BetterWitchHutsCommon.MOD_ID, name), processorType);
    }
}
