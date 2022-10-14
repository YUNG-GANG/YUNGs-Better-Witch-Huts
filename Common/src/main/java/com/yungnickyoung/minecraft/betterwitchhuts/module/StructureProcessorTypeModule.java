package com.yungnickyoung.minecraft.betterwitchhuts.module;

import com.yungnickyoung.minecraft.betterwitchhuts.BetterWitchHutsCommon;
import com.yungnickyoung.minecraft.betterwitchhuts.world.processor.*;
import com.yungnickyoung.minecraft.yungsapi.api.autoregister.AutoRegister;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;

@AutoRegister(BetterWitchHutsCommon.MOD_ID)
public class StructureProcessorTypeModule {
    @AutoRegister("waterlog_processor")
    public static StructureProcessorType<WaterlogProcessor> WATERLOG_PROCESSOR = () -> WaterlogProcessor.CODEC;

    @AutoRegister("leg_processor")
    public static StructureProcessorType<LegProcessor> LEG_PROCESSOR = () -> LegProcessor.CODEC;

    @AutoRegister("fence_leg_processor")
    public static StructureProcessorType<FenceLegProcessor> FENCE_LEG_PROCESSOR = () -> FenceLegProcessor.CODEC;

    @AutoRegister("witch_circle_processor")
    public static StructureProcessorType<WitchCircleProcessor> WITCH_CIRCLE_PROCESSOR = () -> WitchCircleProcessor.CODEC;

    @AutoRegister("brewing_stand_processor")
    public static StructureProcessorType<BrewingStandProcessor> BREWING_STAND_PROCESSOR = () -> BrewingStandProcessor.CODEC;

    @AutoRegister("potted_mushroom_processor")
    public static StructureProcessorType<PottedMushroomProcessor> POTTED_MUSHROOM_PROCESSOR = () -> PottedMushroomProcessor.CODEC;
}
