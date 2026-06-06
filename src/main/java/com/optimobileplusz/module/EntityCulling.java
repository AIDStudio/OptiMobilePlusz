package com.optimobileplusz.module;

import com.optimobileplusz.config.OptiMobileConfig;
import com.optimobileplusz.core.OptimizationState;
import com.optimobileplusz.module.FrameBudgetManager;
import net.minecraft.Entity;
import net.minecraft.Minecraft;

public class EntityCulling {

    private static OptimizationState currentState = OptimizationState.EXTREME;

    public static void update(OptimizationState state) {
        currentState = state;
    }

    public static boolean shouldRender(Entity entity) {
        if (!OptiMobileConfig.smartEntityCulling) {
            return true;
        }

        Minecraft client = Minecraft.method_1551();
        if (client == null || client.field_1724 == null || entity == client.field_1724) {
            return true;
        }

        double distanceSq = client.field_1724.method_5649(entity.method_23317(), entity.method_23318(), entity.method_23321());
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
