package com.yungnickyoung.minecraft.betterwitchhuts.world.processor;

import com.mojang.serialization.MapCodec;
import com.yungnickyoung.minecraft.betterwitchhuts.module.StructureProcessorTypeModule;
import com.yungnickyoung.minecraft.yungsapi.api.world.randomize.BlockStateRandomizer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;




public class WitchCircleProcessor implements StructureProcessor {
    public static final WitchCircleProcessor INSTANCE = new WitchCircleProcessor();
    public static final MapCodec<WitchCircleProcessor> CODEC = MapCodec.unit(() -> INSTANCE);

    private static final BlockStateRandomizer BRICKS_RANDOMIZER = new BlockStateRandomizer(Blocks.STONE_BRICKS.defaultBlockState())
            .addBlock(Blocks.MOSSY_STONE_BRICKS.defaultBlockState(), 0.6f)
            .addBlock(Blocks.CRACKED_STONE_BRICKS.defaultBlockState(), 0.1f);

    private static final BlockStateRandomizer STONE_RANDOMIZER = new BlockStateRandomizer(Blocks.COBBLESTONE.defaultBlockState())
            .addBlock(Blocks.MOSSY_COBBLESTONE.defaultBlockState(), 0.6f)
            .addBlock(Blocks.COARSE_DIRT.defaultBlockState(), 0.1f);

    private static final BlockStateRandomizer STAIRS_RANDOMIZER = new BlockStateRandomizer(Blocks.STONE_BRICK_STAIRS.defaultBlockState())
            .addBlock(Blocks.MOSSY_STONE_BRICK_STAIRS.defaultBlockState(), 0.6f);

    @Override
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader levelReader,
                                                             BlockPos jigsawPiecePos,
                                                             BlockPos jigsawPieceBottomCenterPos,
                                                             BlockPos pivotPos,
                                                             StructureTemplate.StructureBlockInfo blockInfo,
                                                             StructurePlaceSettings structurePlacementData) {
        RandomSource randomSource = structurePlacementData.getRandom(blockInfo.pos());

        if (blockInfo.state().getBlock() == Blocks.STONE_BRICKS) {
            blockInfo = new StructureTemplate.StructureBlockInfo(blockInfo.pos(), BRICKS_RANDOMIZER.get(randomSource), blockInfo.nbt());
        } else if (blockInfo.state().getBlock() == Blocks.MOSSY_COBBLESTONE) {
            blockInfo = new StructureTemplate.StructureBlockInfo(blockInfo.pos(), STONE_RANDOMIZER.get(randomSource), blockInfo.nbt());
        } else if (blockInfo.state().getBlock() == Blocks.STONE_BRICK_STAIRS) {
            blockInfo = new StructureTemplate.StructureBlockInfo(blockInfo.pos(), STAIRS_RANDOMIZER.get(randomSource), blockInfo.nbt());
        } else if (blockInfo.state().getBlock() == Blocks.STAINED_GLASS.pick(DyeColor.GRAY)) {
            if (levelReader instanceof WorldGenRegion worldGenRegion && !worldGenRegion.getCenter().equals(ChunkPos.containing(blockInfo.pos()))) {
                return blockInfo;
            }

            blockInfo = new StructureTemplate.StructureBlockInfo(blockInfo.pos(), BRICKS_RANDOMIZER.get(randomSource), blockInfo.nbt());
            BlockPos.MutableBlockPos mutable = blockInfo.pos().mutable().move(Direction.DOWN);
            BlockState currBlockState = levelReader.getBlockState(mutable);

            while (mutable.getY() > levelReader.getMinY()
                    && mutable.getY() < levelReader.getMaxY()
                    && (currBlockState.isAir() || !levelReader.getFluidState(mutable).isEmpty())) {
                levelReader.getChunk(mutable).setBlockState(mutable, BRICKS_RANDOMIZER.get(randomSource));
                mutable.move(Direction.DOWN);
                currBlockState = levelReader.getBlockState(mutable);
            }
        }

        return blockInfo;
    }

    @Override
    public MapCodec<? extends StructureProcessor> codec() {
        return StructureProcessorTypeModule.WITCH_CIRCLE_PROCESSOR;
    }
}