package com.optimobileplusz.module;

import com.optimobileplusz.config.OptiMobileConfig;
import net.minecraft.client.MinecraftClient; // Frissített import

public class HudOverlayManager {

    public static boolean shouldRenderOverlay() {
        return OptiMobileConfig.showDetailedOverlay;
    }

    public static String getOverlayText() {
        // A Minecraft.method_1551() helyett a hivatalos getInstance() használandó
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null) {
            return "";
        }

        // A method_47599() obfuszkált név helyett a hivatalos getCurrentFps() használandó
        int fps = client.getCurrentFps();
        int particleRate = ParticleLimiter.getParticleMultiplier();
        boolean budget = FrameBudgetManager.isBudgetActive();
        
        return String.format("FPS: %d  Budget: %s  Particles: %d%%", fps, budget ? "ON" : "OFF", particleRate);
    }
}
