package com.optimobileplusz.module;

import com.optimobileplusz.config.OptiMobileConfig;
import com.optimobileplusz.core.OptimizationState;
import com.optimobileplusz.module.FrameBudgetManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;

public class EntityCulling {

    private static OptimizationState currentState = OptimizationState.EXTREME;

    public static void update(OptimizationState state) {
        currentState = state;
    }

    public static boolean shouldRender(Entity entity) {
        if (!OptiMobileConfig.smartEntityCulling) {
            return true;
        }

        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.player == null || entity == client.player) {
            return true;
        }

        double distanceSq = client.player.squaredDistanceTo(entity.getX(), entity.getY(), entity.getZ());
        double limit;

        switch (currentState) {
            case EXTREME:
                limit = 64.0 * 64.0;
                break;
            case PERFORMANCE:
                limit = 96.0 * 96.0;
                break;
            case BALANCED:
                limit = 128.0 * 128.0;
                break;
            case ULTRA:
            default:
                limit = 192.0 * 192.0;
                break;
        }

        if (FrameBudgetManager.isBudgetActive()) {
            limit *= 0.55;
        }

        return distanceSq <= limit;
    }
}
