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




public class PottedMushroomProcessor implements StructureProcessor {
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
                                                             BlockPos pivotPos,
                                                             StructureTemplate.StructureBlockInfo blockInfo,
                                                             StructurePlaceSettings structurePlacementData) {
        if (blockInfo.state().getBlock() == Blocks.POTTED_RED_MUSHROOM) {
            RandomSource randomSource = structurePlacementData.getRandom(blockInfo.pos());
            blockInfo = new StructureTemplate.StructureBlockInfo(blockInfo.pos(), RANDOMIZER.get(randomSource), null);
        }
        return blockInfo;
    }

    @Override
    public MapCodec<? extends StructureProcessor> codec() {
        return StructureProcessorTypeModule.POTTED_MUSHROOM_PROCESSOR;
    }
}