package com.optimobileplusz;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OptimobilePlusz implements ModInitializer {
    public static final String MOD_ID = "optimobileplusz";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("OptiElitePlusz inicializalva (Common)!");
    }
}
