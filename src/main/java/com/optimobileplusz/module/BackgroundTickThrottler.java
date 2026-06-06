package com.optimobileplusz.module;

import com.optimobileplusz.config.OptiMobileConfig;
import net.minecraft.client.MinecraftClient;

public class BackgroundTickThrottler {

    public static boolean shouldSkipHeavyTick() {
        if (!OptiMobileConfig.backgroundTickThrottleEnabled) {
            return false;
        }

        // A Minecraft.method_1551() helyett a hivatalos getInstance() használandó
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null) {
            return false;
        }

        // isWindowFocused() a hivatalos metódus neve a method_1569() helyett.
        // A client.world a hivatalos név a field_1687 helyett.
        if (!client.isWindowFocused() && client.world != null) {
            return true;
        }

        return false;
    }
}
