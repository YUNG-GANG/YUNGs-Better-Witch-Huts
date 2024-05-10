package com.yungnickyoung.minecraft.betterwitchhuts.module;

import com.yungnickyoung.minecraft.betterwitchhuts.BetterWitchHutsCommon;
import com.yungnickyoung.minecraft.betterwitchhuts.BetterWitchHutsNeoForge;
import com.yungnickyoung.minecraft.betterwitchhuts.config.BWHConfigNeoForge;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.level.LevelEvent;

public class ConfigModuleNeoForge {
    public static void init() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, BWHConfigNeoForge.SPEC, "betterwitchhuts-neoforge-1_20_4.toml");
        NeoForge.EVENT_BUS.addListener(ConfigModuleNeoForge::onWorldLoad);
        BetterWitchHutsNeoForge.loadingContextEventBus.addListener(ConfigModuleNeoForge::onConfigChange);
    }

    private static void onWorldLoad(LevelEvent.Load event) {
        bakeConfig();
    }

    private static void onConfigChange(ModConfigEvent event) {
        if (event.getConfig().getSpec() == BWHConfigNeoForge.SPEC) {
            bakeConfig();
        }
    }
    private static void bakeConfig() {
        BetterWitchHutsCommon.CONFIG.general.disableVanillaWitchHuts = BWHConfigNeoForge.general.disableVanillaWitchHuts.get();
    }
}
