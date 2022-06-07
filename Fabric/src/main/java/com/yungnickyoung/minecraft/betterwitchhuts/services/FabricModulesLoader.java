package com.yungnickyoung.minecraft.betterwitchhuts.services;

import com.yungnickyoung.minecraft.betterwitchhuts.module.*;

public class FabricModulesLoader implements IModulesLoader {
    @Override
    public void loadModules() {
        ConfigModuleFabric.init();
        TagModuleFabric.init();
        StructureProcessorModuleFabric.init();
        StructureFeatureModuleFabric.init();
    }
}
