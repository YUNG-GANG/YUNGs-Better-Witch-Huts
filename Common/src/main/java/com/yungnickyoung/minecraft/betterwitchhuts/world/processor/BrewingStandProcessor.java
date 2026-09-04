package com.yungnickyoung.minecraft.betterwitchhuts.world.processor;

import com.mojang.serialization.MapCodec;
import com.yungnickyoung.minecraft.betterwitchhuts.module.StructureProcessorTypeModule;
import net.minecraft.util.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;




public class BrewingStandProcessor implements StructureProcessor {
    public static final BrewingStandProcessor INSTANCE = new BrewingStandProcessor();
    public static final MapCodec<BrewingStandProcessor> CODEC = MapCodec.unit(() -> INSTANCE);

    @Override
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader levelReader,
                                                             BlockPos jigsawPiecePos,
                                                             BlockPos jigsawPieceBottomCenterPos,
                                                             BlockPos pivotPos,
                                                             StructureTemplate.StructureBlockInfo blockInfo,
                                                             StructurePlaceSettings structurePlacementData) {
        if (blockInfo.state().getBlock() == Blocks.BREWING_STAND) {
            RandomSource randomSource = structurePlacementData.getRandom(blockInfo.pos());
            CompoundTag tag = blockInfo.nbt();
            ListTag itemsListTag = tag.getListOrEmpty("Items");
            populateItemsList(itemsListTag, randomSource);
            blockInfo = new StructureTemplate.StructureBlockInfo(blockInfo.pos(), blockInfo.state(), tag);
        }
        return blockInfo;
    }

    @Override
    public MapCodec<? extends StructureProcessor> codec() {
        return StructureProcessorTypeModule.BREWING_STAND_PROCESSOR;
    }

    private void populateItemsList(ListTag itemsListTag, RandomSource randomSource) {
        int n = randomSource.nextInt(5);
        switch (n) {
            case 0 -> addBrewingRecipe(itemsListTag, "minecraft:glistering_melon_slice", "minecraft:healing", randomSource);
            case 1 -> addBrewingRecipe(itemsListTag, "minecraft:sugar", "minecraft:swiftness", randomSource);
            case 2 -> addBrewingRecipe(itemsListTag, "minecraft:pufferfish", "minecraft:water_breathing", randomSource);
            case 3 -> addBrewingRecipe(itemsListTag, "minecraft:golden_carrot", "minecraft:night_vision", randomSource);
            case 4 -> addBrewingRecipe(itemsListTag, "minecraft:phantom_membrane", "minecraft:slow_falling", randomSource);
        }
    }

    private void addBrewingRecipe(ListTag itemsListTag, String inputItemId, String outputPotionId, RandomSource randomSource) {
        // Input item
        itemsListTag.add(Util.make(new CompoundTag(), itemTag -> {
            putInputItem(itemTag, inputItemId, randomSource.nextInt(4) + 2);
        }));

        // Guaranteed output item
        itemsListTag.add(Util.make(new CompoundTag(), itemTag -> putPotionInSlot(itemTag, (byte) 1, outputPotionId)));

        // Bonus output item
        if (randomSource.nextFloat() < 0.5f) {
            int bonusSlot = randomSource.nextBoolean() ? 0 : 2;
            itemsListTag.add(Util.make(new CompoundTag(), itemTag -> putPotionInSlot(itemTag, (byte) bonusSlot, outputPotionId)));
        }
    }

    private void putInputItem(CompoundTag itemTag, String itemId, int count) {
        itemTag.putByte("Slot", (byte) 3);
        itemTag.putString("id", itemId);
        itemTag.putInt("count", count);
    }

    private void putPotionInSlot(CompoundTag itemTag, byte slot, String potionId) {
        itemTag.putByte("Slot", slot);
        itemTag.putString("id", "minecraft:potion");
        itemTag.putInt("count", 1);
        itemTag.put("components", Util.make(new CompoundTag(), componentsTag -> {
            componentsTag.put("minecraft:potion_contents", Util.make(new CompoundTag(), potionContentsTag -> {
                potionContentsTag.putString("potion", potionId);
            }));
        }));
    }
}