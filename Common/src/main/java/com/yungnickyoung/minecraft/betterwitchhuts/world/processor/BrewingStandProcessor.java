package com.yungnickyoung.minecraft.betterwitchhuts.world.processor;

import com.mojang.serialization.Codec;
import com.yungnickyoung.minecraft.betterwitchhuts.module.StructureProcessorModule;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class BrewingStandProcessor extends StructureProcessor {
    public static final BrewingStandProcessor INSTANCE = new BrewingStandProcessor();
    public static final Codec<BrewingStandProcessor> CODEC = Codec.unit(() -> INSTANCE);

    @Override
    public StructureTemplate.StructureBlockInfo processBlock(LevelReader levelReader,
                                                             BlockPos jigsawPiecePos,
                                                             BlockPos jigsawPieceBottomCenterPos,
                                                             StructureTemplate.StructureBlockInfo blockInfoLocal,
                                                             StructureTemplate.StructureBlockInfo blockInfoGlobal,
                                                             StructurePlaceSettings structurePlacementData) {
        if (blockInfoGlobal.state.getBlock() == Blocks.BREWING_STAND) {
            Random random = structurePlacementData.getRandom(blockInfoGlobal.pos);
            CompoundTag tag = blockInfoGlobal.nbt;
            ListTag itemsListTag = tag.getList("Items", 10);
            populateItemsList(itemsListTag, random);
            blockInfoGlobal = new StructureTemplate.StructureBlockInfo(blockInfoGlobal.pos, blockInfoGlobal.state, tag);
        }
        return blockInfoGlobal;
    }

    protected StructureProcessorType<?> getType() {
        return StructureProcessorModule.BREWING_STAND_PROCESSOR;
    }

    private void populateItemsList(ListTag itemsListTag, Random random) {
        int n = random.nextInt(5);
        switch (n) {
            case 0 -> addBrewingRecipe(itemsListTag, "minecraft:glistering_melon_slice", "minecraft:healing", random);
            case 1 -> addBrewingRecipe(itemsListTag, "minecraft:sugar", "minecraft:swiftness", random);
            case 2 -> addBrewingRecipe(itemsListTag, "minecraft:pufferfish", "minecraft:water_breathing", random);
            case 3 -> addBrewingRecipe(itemsListTag, "minecraft:golden_carrot", "minecraft:night_vision", random);
            case 4 -> addBrewingRecipe(itemsListTag, "minecraft:phantom_membrane", "minecraft:slow_falling", random);
        }
    }

    private void addBrewingRecipe(ListTag itemsListTag, String inputItemId, String outputPotionId, Random random) {
        // Input item
        itemsListTag.add(Util.make(new CompoundTag(), itemTag -> {
            putInputItem(itemTag, inputItemId, (byte) (random.nextInt(4) + 2));
        }));

        // Output item(s)
        itemsListTag.add(Util.make(new CompoundTag(), itemTag -> {
            putPotionInSlot(itemTag, (byte) 1, outputPotionId);
            if (random.nextFloat() < .5f) putPotionInSlot(itemTag, (byte) 0, outputPotionId);
            if (random.nextFloat() < .5f) putPotionInSlot(itemTag, (byte) 2, outputPotionId);
        }));
    }

    private void putInputItem(CompoundTag itemTag, String itemId, byte count) {
        itemTag.putByte("Slot", (byte) 3);
        itemTag.putString("id", itemId);
        itemTag.putByte("Count", count);
    }

    private void putPotionInSlot(CompoundTag itemTag, byte slot, String potionId) {
        itemTag.putByte("Slot", slot);
        itemTag.putString("id", "minecraft:potion");
        itemTag.putByte("Count", (byte) 1);
        itemTag.put("tag", Util.make(new CompoundTag(), potionTag -> {
            potionTag.putString("Potion", potionId);
        }));
    }
}