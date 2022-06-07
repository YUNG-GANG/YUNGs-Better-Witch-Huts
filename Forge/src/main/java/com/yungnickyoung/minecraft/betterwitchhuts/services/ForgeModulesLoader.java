package com.yungnickyoung.minecraft.betterwitchhuts.services;

import com.yungnickyoung.minecraft.betterwitchhuts.module.*;

public class ForgeModulesLoader implements IModulesLoader {
    @Override
    public void loadModules() {
        ConfigModuleForge.init();
        TagModuleForge.init();
        StructureProcessorModuleForge.init();
        StructureFeatureModuleForge.init();
    }
}
