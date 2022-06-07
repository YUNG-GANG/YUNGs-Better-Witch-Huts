package com.yungnickyoung.minecraft.betterwitchhuts;

import net.fabricmc.api.ModInitializer;

public class BetterWitchHutsFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        BetterWitchHutsCommon.init();
    }
}
