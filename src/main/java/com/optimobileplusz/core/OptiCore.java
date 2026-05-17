package com.optimobileplusz.core;

import com.optimobileplusz.module.*;
import net.minecraft.client.MinecraftClient;

public class OptiCore {
    private static OptimizationState currentState = OptimizationState.EXTREME;

    public static void initialize() {
    }

    public static void update() {
        // Alapvető optimalizációk futtatása minden tickben
        RenderThrottle.update(currentState);
        ParticleLimiter.update(currentState);
        
        // A dinamikus látótávolság logika teljes egészében eltávolítva,
        // hogy ne állítgassa a játékot a hátad mögött.
    }

    /**
     * Megmondja, hogy egy adott pozíció túl messze van-e ahhoz, hogy animáljuk a textúráját.
     * Ha EXTREME módban vagyunk, a távolság limit 16 blokk (1 chunk), egyébként 32 blokk.
     */
    public static boolean shouldThrottleAnimation(double blockX, double blockY, double blockZ) {
        MinecraftClient client = MinecraftClient.getInstance();
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