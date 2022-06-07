package com.yungnickyoung.minecraft.betterwitchhuts.world.structure;

import com.yungnickyoung.minecraft.yungsapi.api.YungJigsawConfig;
import com.yungnickyoung.minecraft.yungsapi.api.YungJigsawManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.QuartPos;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.structure.PoolElementStructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;

import java.util.Optional;
import java.util.Random;

public class WitchHutStructure extends StructureFeature<YungJigsawConfig> {
    @Override
    public GenerationStep.Decoration step() {
        return GenerationStep.Decoration.SURFACE_STRUCTURES;
    }

    public WitchHutStructure() {
        super(YungJigsawConfig.CODEC, context -> {
            // Random
            WorldgenRandom worldgenRandom = new WorldgenRandom(new LegacyRandomSource(0L));
            worldgenRandom.setLargeFeatureSeed(context.seed(), context.chunkPos().x, context.chunkPos().z);
            BlockPos startPos = new BlockPos(context.chunkPos().getMiddleBlockX(), 1, context.chunkPos().getMiddleBlockZ());

            // Only generate if location is valid
            if (!checkLocation(context, startPos, worldgenRandom)) {
                return Optional.empty();
            }

            return YungJigsawManager.assembleJigsawStructure(
                    context,
                    PoolElementStructurePiece::new,
                    startPos,
                    false,
                    true,
                    80);
        });
    }

    private static boolean checkLocation(PieceGeneratorSupplier.Context<YungJigsawConfig> context, BlockPos startPos, Random random) {
        return context.validBiome().test(context.chunkGenerator().getNoiseBiome(
                        QuartPos.fromBlock(startPos.getX()),
                        QuartPos.fromBlock(startPos.getY()),
                        QuartPos.fromBlock(startPos.getZ())));
    }
}
