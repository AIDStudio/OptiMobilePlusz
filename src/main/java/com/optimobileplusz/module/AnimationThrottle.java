package com.optimobileplusz.module;

import com.optimobileplusz.config.OptiMobileConfig;
import com.optimobileplusz.core.OptimizationState;
import net.minecraft.client.MinecraftClient; // Frissített import

public class AnimationThrottle {

    public static boolean shouldSkipAnimatedSprites(OptimizationState state) {
        if (!OptiMobileConfig.throttledAnimations) {
            return false;
        }

        MinecraftClient client = MinecraftClient.getInstance();
        // A 'player' és 'world' a modern Mojang elnevezések
        if (client == null || client.player == null || client.world == null) {
            return false;
        }

        // A worldTime a world.getTime() hívással érhető el a modern mappingben
        long worldTime = client.world.getTime();

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
