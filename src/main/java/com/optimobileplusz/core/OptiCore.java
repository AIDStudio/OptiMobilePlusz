package com.optimobileplusz.core;

import com.optimobileplusz.module.*;
import net.minecraft.client.MinecraftClient; // Frissített import

public class OptiCore {
    private static OptimizationState currentState = OptimizationState.EXTREME;

    public static void initialize() {
        TickLimiter.init();
    }

    public static void update() {
        currentState = AdaptiveEngine.update(currentState);

        // Optimalizációk futtatása
        RenderThrottle.update(currentState);
        ParticleLimiter.update(currentState);
        FrameBudgetManager.update(currentState);
        EntityCulling.update(currentState);
        LODManager.update(currentState);
        ChunkLoadManager.update(currentState);
        TextureStreamingManager.update(currentState);
        SceneSimplifier.update();
        GpuProfileManager.update(currentState);
        ThermalProtection.update(currentState);
        TelemetryManager.update();
    }

    /**
     * Megmondja, hogy animáljuk-e a textúrát a távolság alapján.
     */
    public static boolean shouldThrottleAnimation(double blockX, double blockY, double blockZ) {
        // A method_1551() helyett a getInstance() használandó
        MinecraftClient client = MinecraftClient.getInstance();
        
        // A field_1724 helyett a 'player' mezőt használjuk
        if (client == null || client.player == null) return false;

        // Távolság négyzetének kiszámítása
        double distanceSq = client.player.squaredDistanceTo(blockX, blockY, blockZ);
        double maxDistance = (currentState == OptimizationState.EXTREME) ? 256.0 : 1024.0;

        return distanceSq > maxDistance;
    }

    public static OptimizationState getState() {
        return currentState;
    }
}
