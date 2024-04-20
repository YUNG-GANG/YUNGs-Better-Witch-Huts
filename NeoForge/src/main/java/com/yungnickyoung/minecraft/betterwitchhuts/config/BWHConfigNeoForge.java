package com.yungnickyoung.minecraft.betterwitchhuts.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class BWHConfigNeoForge {
    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec SPEC;

    public static final ConfigGeneralNeoForge general;

    static {
        BUILDER.push("YUNG's Better Witch Huts");

        general = new ConfigGeneralNeoForge(BUILDER);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}