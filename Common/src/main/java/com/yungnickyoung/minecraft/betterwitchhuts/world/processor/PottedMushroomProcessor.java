package com.yungnickyoung.minecraft.betterwitchhuts.world.processor;

import com.mojang.serialization.MapCodec;
import com.yungnickyoung.minecraft.betterwitchhuts.module.StructureProcessorTypeModule;
import com.yungnickyoung.minecraft.yungsapi.api.world.randomize.BlockStateRandomizer;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class PottedMushroomProcessor extends StructureProcessor {
    public static final PottedMushroomProcessor INSTANCE = new PottedMushroomProcessor();
    public static final MapCodec<PottedMushroomProcessor> CODEC = MapCodec.unit(() -> INSTANCE);

    private static final BlockStateRandomizer RANDOMIZER = new BlockStateRandomizer(Blocks.POTTED_RED_MUSHROOM.defaultBlockState())
            .addBlock(Blocks.POTTED_BROWN_MUSHROOM.defaultBlockState(), .2f)
            .addBlock(Blocks.POTTED_CORNFLOWER.defaultBlockState(), .1f)
            .addBlock(Blocks.POTTED_CACTUS.defaultBlockState(), .1f)
            .addBlock(Blocks.POTTED_DEAD_BUSH.defaultBlockState(), .1f)
            .addBlock(Blocks.POTTED_FERN.defaultBlockState(), .1f)
            .addBlock(Blocks.POTTED_AZALEA.defaultBlockState(), .1f);

    @Override
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader levelReader,
                                                             BlockPos jigsawPiecePos,
                                                             BlockPos jigsawPieceBottomCenterPos,
                                                             StructureTemplate.StructureBlockInfo blockInfoLocal,
                                                             StructureTemplate.StructureBlockInfo blockInfoGlobal,
                                                             StructurePlaceSettings structurePlacementData) {
        if (blockInfoGlobal.state().getBlock() == Blocks.POTTED_RED_MUSHROOM) {
            RandomSource randomSource = structurePlacementData.getRandom(blockInfoGlobal.pos());
            blockInfoGlobal = new StructureTemplate.StructureBlockInfo(blockInfoGlobal.pos(), RANDOMIZER.get(randomSource), null);
        }
        return blockInfoGlobal;
    }

    protected StructureProcessorType<?> getType() {
        return StructureProcessorTypeModule.POTTED_MUSHROOM_PROCESSOR;
    }
}