package com.optimobileplusz.core;

import com.optimobileplusz.module.AdaptiveEngine;
import com.optimobileplusz.module.ChunkLoadManager;
import com.optimobileplusz.module.EntityCulling;
import com.optimobileplusz.module.FrameBudgetManager;
import com.optimobileplusz.module.GpuProfileManager;
import com.optimobileplusz.module.LODManager;
import com.optimobileplusz.module.ParticleLimiter;
import com.optimobileplusz.module.RenderThrottle;
import com.optimobileplusz.module.SceneSimplifier;
import com.optimobileplusz.module.TextureStreamingManager;
import com.optimobileplusz.module.TelemetryManager;
import com.optimobileplusz.module.ThermalProtection;
import net.minecraft.client.Minecraft;

public class OptiCore {
    private static OptimizationState currentState = OptimizationState.EXTREME;

    public static void initialize() {
        TickLimiter.init();
    }

    public static void update() {
        currentState = AdaptiveEngine.update(currentState);

        // Alapvető optimalizációk futtatása minden tickben
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
     * Megmondja, hogy egy adott pozíció túl messze van-e ahhoz, hogy animáljuk a textúráját.
     * Ha EXTREME módban vagyunk, a távolság limit 16 blokk (1 chunk), egyébként 32 blokk.
     */
    public static boolean shouldThrottleAnimation(double blockX, double blockY, double blockZ) {
        Minecraft client = Minecraft.getInstance();
        if (client == null || client.player == null) return false;

        // Távolság négyzetének kiszámítása a hatékonyság érdekében
        double distanceSq = client.player.squaredDistanceTo(blockX, blockY, blockZ);
        double maxDistance = (currentState == OptimizationState.EXTREME) ? 256.0 : 1024.0;

        return distanceSq > maxDistance;
    }

    public static OptimizationState getState() {
        return currentState;
    }

    public static void setState(OptimizationState state) {
        currentState = state;
    }
}