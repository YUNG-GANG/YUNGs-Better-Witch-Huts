package com.yungnickyoung.minecraft.betterwitchhuts.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ConfigGeneralForge {
    public final ForgeConfigSpec.ConfigValue<Boolean> disableVanillaWitchHuts;

    public ConfigGeneralForge(final ForgeConfigSpec.Builder BUILDER) {
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

