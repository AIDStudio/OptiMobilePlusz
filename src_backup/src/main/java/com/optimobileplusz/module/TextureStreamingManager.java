package com.optimobileplusz.module;

import com.optimobileplusz.config.OptiMobileConfig;
import com.optimobileplusz.core.Log;
import com.optimobileplusz.core.OptimizationState;
import net.minecraft.Minecraft;

public class TextureStreamingManager {

    private static boolean throttled = false;

    public static void update(OptimizationState state) {
        if (!OptiMobileConfig.asyncTextureStreamingEnabled) {
            throttled = false;
            return;
        }

        Minecraft client = Minecraft.method_1551();
        if (client == null) {
            return;
        }

        boolean shouldThrottle = state == OptimizationState.EXTREME || state == OptimizationState.PERFORMANCE;
        if (shouldThrottle != throttled) {
            throttled = shouldThrottle;
            Log.info("Async texture streaming " + (throttled ? "throttled" : "normal"));
        }
    }

    public static boolean isThrottled() {
        return throttled;
    }
}
