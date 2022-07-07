package com.yungnickyoung.minecraft.betterwitchhuts.services;

import com.yungnickyoung.minecraft.betterwitchhuts.module.StructureProcessorTypeModule;

public interface IModulesLoader {
    default void loadModules() {
        StructureProcessorTypeModule.init();
    }
}
