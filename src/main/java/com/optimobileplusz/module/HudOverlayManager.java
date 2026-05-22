package com.optimobileplusz.module;

import com.optimobileplusz.config.OptiMobileConfig;
import net.minecraft.client.MinecraftClient;

public class HudOverlayManager {

    public static boolean shouldRenderOverlay() {
        return OptiMobileConfig.showDetailedOverlay;
    }

    public static String getOverlayText() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null) {
            return "";
        }

        int fps = client.getCurrentFps();
        int particleRate = ParticleLimiter.getParticleMultiplier();
        boolean budget = FrameBudgetManager.isBudgetActive();
        return String.format("FPS: %d  Budget: %s  Particles: %d%%", fps, budget ? "ON" : "OFF", particleRate);
    }
}
