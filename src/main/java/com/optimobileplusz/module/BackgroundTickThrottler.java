package com.optimobileplusz.module;

import com.optimobileplusz.config.OptiMobileConfig;
import net.minecraft.client.Minecraft;

public class BackgroundTickThrottler {

    public static boolean shouldSkipHeavyTick() {
        if (!OptiMobileConfig.backgroundTickThrottleEnabled) {
            return false;
        }

        Minecraft client = Minecraft.getInstance();
        if (client == null) {
            return false;
        }

        if (!client.isWindowFocused() && client.world != null) {
            return true;
        }

        return false;
    }
}
