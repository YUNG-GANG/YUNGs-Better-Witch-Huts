package com.yungnickyoung.minecraft.betterwitchhuts;

import com.yungnickyoung.minecraft.betterwitchhuts.module.ConfigModuleNeoForge;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(BetterWitchHutsCommon.MOD_ID)
public class BetterWitchHutsNeoForge {
    public static IEventBus loadingContextEventBus;

    public BetterWitchHutsNeoForge(IEventBus eventBus, ModContainer container) {
        BetterWitchHutsNeoForge.loadingContextEventBus = eventBus;

        BetterWitchHutsCommon.init();
        ConfigModuleNeoForge.init(container);
    }
}
