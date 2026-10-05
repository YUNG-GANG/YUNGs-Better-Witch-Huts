package com.yungnickyoung.minecraft.betterwitchhuts.module;

import com.mojang.serialization.MapCodec;
import com.yungnickyoung.minecraft.betterwitchhuts.BetterWitchHutsCommon;
import com.yungnickyoung.minecraft.betterwitchhuts.world.processor.*;
import com.yungnickyoung.minecraft.yungsapi.api.autoregister.AutoRegister;

@AutoRegister(BetterWitchHutsCommon.MOD_ID)
public class StructureProcessorTypeModule {
    @AutoRegister("leg_processor")
    public static MapCodec<LegProcessor> LEG_PROCESSOR = LegProcessor.CODEC;

    @AutoRegister("fence_leg_processor")
    public static MapCodec<FenceLegProcessor> FENCE_LEG_PROCESSOR = FenceLegProcessor.CODEC;

    @AutoRegister("witch_circle_processor")
    public static MapCodec<WitchCircleProcessor> WITCH_CIRCLE_PROCESSOR = WitchCircleProcessor.CODEC;

    @AutoRegister("brewing_stand_processor")
    public static MapCodec<BrewingStandProcessor> BREWING_STAND_PROCESSOR = BrewingStandProcessor.CODEC;

    @AutoRegister("potted_mushroom_processor")
    public static MapCodec<PottedMushroomProcessor> POTTED_MUSHROOM_PROCESSOR = PottedMushroomProcessor.CODEC;
}
