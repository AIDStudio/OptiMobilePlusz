package com.optimobileplusz.module;

import com.optimobileplusz.config.OptiMobileConfig;
import com.optimobileplusz.core.OptimizationState;
import net.minecraft.Minecraft;

public class AnimationThrottle {

    public static boolean shouldSkipAnimatedSprites(OptimizationState state) {
        if (!OptiMobileConfig.throttledAnimations) {
            return false;
        }

        Minecraft client = Minecraft.method_1551();
        if (client == null || client.field_1724 == null || client.field_1687 == null) {
            return false;
        }

        long worldTime = client.field_1687.method_75260();

        switch (state) {
            case EXTREME:
                return (worldTime % 2 == 0);
            case PERFORMANCE:
                return (worldTime % 3 != 0);
            case BALANCED:
                return (worldTime % 4 == 0);
            case ULTRA:
            default:
                return false;
        }
    }
}
