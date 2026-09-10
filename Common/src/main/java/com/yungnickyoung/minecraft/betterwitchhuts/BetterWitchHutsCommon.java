package com.yungnickyoung.minecraft.betterwitchhuts;

import com.yungnickyoung.minecraft.betterwitchhuts.module.ConfigModule;
import com.yungnickyoung.minecraft.betterwitchhuts.services.Services;
import com.yungnickyoung.minecraft.yungsapi.api.YungAutoRegister;
import com.yungnickyoung.minecraft.yungsapi.api.world.structure.locate.LocateReplacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.BuiltinStructures;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BetterWitchHutsCommon {
    public static final String MOD_ID = "betterwitchhuts";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    public static final ConfigModule CONFIG = new ConfigModule();

    public static void init() {
        YungAutoRegister.scanPackageForAnnotations("com.yungnickyoung.minecraft.betterwitchhuts.module");
        Services.MODULES.loadModules();

        LocateReplacer.register(BuiltinStructures.SWAMP_HUT,
                ResourceKey.create(Registries.STRUCTURE, Identifier.fromNamespaceAndPath(MOD_ID, "witch_hut")),
                () -> CONFIG.general.disableVanillaWitchHuts);
    }
}
