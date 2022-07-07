package com.yungnickyoung.minecraft.betterwitchhuts.services;

import com.yungnickyoung.minecraft.betterwitchhuts.module.*;

public class ForgeModulesLoader implements IModulesLoader {
    @Override
    public void loadModules() {
        IModulesLoader.super.loadModules();
        ConfigModuleForge.init();
        StructureProcessorTypeModuleForge.init();
    }
}
