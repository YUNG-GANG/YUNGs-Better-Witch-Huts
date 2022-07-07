package com.yungnickyoung.minecraft.betterwitchhuts.services;

import com.yungnickyoung.minecraft.betterwitchhuts.module.ConfigModuleFabric;

public class FabricModulesLoader implements IModulesLoader {
    @Override
    public void loadModules() {
        IModulesLoader.super.loadModules(); // Load common modules
        ConfigModuleFabric.init();
    }
}
