package com.yungnickyoung.minecraft.betterwitchhuts.module;

import com.yungnickyoung.minecraft.betterwitchhuts.BetterWitchHutsCommon;
import com.yungnickyoung.minecraft.betterwitchhuts.config.BWHConfigFabric;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.minecraft.world.InteractionResult;

public class ConfigModuleFabric {
    public static void init() {
        AutoConfig.register(BWHConfigFabric.class, Toml4jConfigSerializer::new);
        AutoConfig.getConfigHolder(BWHConfigFabric.class).registerSaveListener(ConfigModuleFabric::bakeConfig);
        AutoConfig.getConfigHolder(BWHConfigFabric.class).registerLoadListener(ConfigModuleFabric::bakeConfig);
        bakeConfig(AutoConfig.getConfigHolder(BWHConfigFabric.class).get());
    }

    private static InteractionResult bakeConfig(ConfigHolder<BWHConfigFabric> configHolder, BWHConfigFabric configFabric) {
        bakeConfig(configFabric);
        return InteractionResult.SUCCESS;
    }

    private static void bakeConfig(BWHConfigFabric configFabric) {
        BetterWitchHutsCommon.CONFIG.general.disableVanillaWitchHuts = configFabric.general.disableVanillaWitchHuts;
    }
}
