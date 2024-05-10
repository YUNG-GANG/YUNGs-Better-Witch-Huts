package com.yungnickyoung.minecraft.betterwitchhuts;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(BetterWitchHutsCommon.MOD_ID)
public class BetterWitchHutsNeoForge {
    public static IEventBus loadingContextEventBus;

    public BetterWitchHutsNeoForge(IEventBus eventBus) {
        BetterWitchHutsNeoForge.loadingContextEventBus = eventBus;

        BetterWitchHutsCommon.init();
    }
}
