package com.yungnickyoung.minecraft.betterwitchhuts.module;

import com.yungnickyoung.minecraft.betterwitchhuts.world.processor.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;

public class StructureProcessorModule {
    public static StructureProcessorType<WaterlogProcessor> WATERLOG_PROCESSOR = () -> WaterlogProcessor.CODEC;
    public static StructureProcessorType<LegProcessor> LEG_PROCESSOR = () -> LegProcessor.CODEC;
    public static StructureProcessorType<FenceLegProcessor> FENCE_LEG_PROCESSOR = () -> FenceLegProcessor.CODEC;
    public static StructureProcessorType<WitchCircleProcessor> WITCH_CIRCLE_PROCESSOR = () -> WitchCircleProcessor.CODEC;
    public static StructureProcessorType<BrewingStandProcessor> BREWING_STAND_PROCESSOR = () -> BrewingStandProcessor.CODEC;
    public static StructureProcessorType<PottedMushroomProcessor> POTTED_MUSHROOM_PROCESSOR = () -> PottedMushroomProcessor.CODEC;
}
