package com.optimobileplusz.module;

import com.optimobileplusz.config.OptiMobileConfig;
import net.minecraft.Minecraft;

public class HudOverlayManager {

    public static boolean shouldRenderOverlay() {
        return OptiMobileConfig.showDetailedOverlay;
    }

    public static String getOverlayText() {
        Minecraft client = Minecraft.method_1551();
        if (client == null) {
            return "";
        }

        int fps = client.method_47599();
        int particleRate = ParticleLimiter.getParticleMultiplier();
        boolean budget = FrameBudgetManager.isBudgetActive();
        return String.format("FPS: %d  Budget: %s  Particles: %d%%", fps, budget ? "ON" : "OFF", particleRate);
    }
}
