package com.optimobileplusz.module;

import com.optimobileplusz.config.OptiMobileConfig;
import net.minecraft.Minecraft;

public class BackgroundTickThrottler {

    public static boolean shouldSkipHeavyTick() {
        if (!OptiMobileConfig.backgroundTickThrottleEnabled) {
            return false;
        }

        Minecraft client = Minecraft.method_1551();
        if (client == null) {
            return false;
        }

        if (!client.method_1569() && client.field_1687 != null) {
            return true;
        }

        return false;
    }
}
