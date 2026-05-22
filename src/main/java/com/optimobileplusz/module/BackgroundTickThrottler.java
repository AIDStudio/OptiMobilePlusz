package com.optimobileplusz.module;

import com.optimobileplusz.config.OptiMobileConfig;
import net.minecraft.client.MinecraftClient;

public class BackgroundTickThrottler {

    public static boolean shouldSkipHeavyTick() {
        if (!OptiMobileConfig.backgroundTickThrottleEnabled) {
            return false;
        }

        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null) {
            return false;
        }

        if (!client.isWindowFocused() && client.world != null) {
            return true;
        }

        return false;
    }
}
