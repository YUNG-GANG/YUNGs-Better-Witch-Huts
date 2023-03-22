package com.yungnickyoung.minecraft.betterwitchhuts.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name="betterwitchhuts-fabric-1_19_4")
public class BWHConfigFabric implements ConfigData {
    @ConfigEntry.Category("Better Witch Huts")
    @ConfigEntry.Gui.TransitiveObject
    public ConfigGeneralFabric general = new ConfigGeneralFabric();
}
