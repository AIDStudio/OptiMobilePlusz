package com.optimobileplusz.module;

import com.optimobileplusz.config.OptiMobileConfig;
import com.optimobileplusz.core.OptimizationState;
import net.minecraft.entity.Entity; // Frissített import
import net.minecraft.client.MinecraftClient; // Frissített import

public class EntityCulling {

    private static OptimizationState currentState = OptimizationState.EXTREME;

    public static void update(OptimizationState state) {
        currentState = state;
    }

    public static boolean shouldRender(Entity entity) {
        if (!OptiMobileConfig.smartEntityCulling) {
            return true;
        }

        // 1. A Minecraft.method_1551() helyett a hivatalos getInstance() használandó
        MinecraftClient client = MinecraftClient.getInstance();
        
        // 2. A field_1724 helyett a 'player' mezőt használjuk
        if (client == null || client.player == null || entity == client.player) {
            return true;
        }

        // 3. A squaredDistanceTo() a hivatalos metódus név a method_5649 helyett.
        // Az entity.getX(), getY(), getZ() a hivatalos nevek a method_23317() stb. helyett.
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
