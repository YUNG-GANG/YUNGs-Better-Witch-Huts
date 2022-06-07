package com.yungnickyoung.minecraft.betterwitchhuts.module;

import com.yungnickyoung.minecraft.betterwitchhuts.BetterWitchHutsCommon;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;

public class TagModuleFabric {
    public static void init() {
        TagModule.HAS_BETTER_WITCH_HUT = TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(BetterWitchHutsCommon.MOD_ID, "has_better_witch_hut"));
    }
}
