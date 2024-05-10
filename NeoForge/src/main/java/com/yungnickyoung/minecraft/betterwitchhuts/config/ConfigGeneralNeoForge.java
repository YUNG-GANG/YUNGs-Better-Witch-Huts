package com.yungnickyoung.minecraft.betterwitchhuts.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ConfigGeneralNeoForge {
    public final ModConfigSpec.ConfigValue<Boolean> disableVanillaWitchHuts;

    public ConfigGeneralNeoForge(final ModConfigSpec.Builder BUILDER) {
        BUILDER
                .comment(
                        """
                                ##########################################################################################################
                                # General settings.
                                ##########################################################################################################""")
                .push("General");

        disableVanillaWitchHuts = BUILDER
                .comment(
                        """
                                Whether or not vanilla witch huts should be disabled.
                                Default: true""".indent(1))
                .worldRestart()
                .define("Disable Vanilla Witch Huts", true);

        BUILDER.pop();
    }
}

