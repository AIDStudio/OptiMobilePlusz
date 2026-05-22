package com.optimobileplusz.module;

import com.optimobileplusz.config.OptiMobileConfig;
import com.optimobileplusz.core.Log;
import com.optimobileplusz.core.OptimizationState;
import com.optimobileplusz.client.monitor.FpsMonitor;

public class FrameBudgetManager {

    private static boolean activeBudget = false;
    private static long lastSwitchTime = 0;

    public static void update(OptimizationState state) {
        if (!OptiMobileConfig.frameBudgetEnabled) {
            if (activeBudget) {
                activeBudget = false;
                Log.info("FrameBudget mode disabled.");
            }
            return;
        }

        int fps = FpsMonitor.getFPS();
        boolean shouldActivate = fps < 40 || state == OptimizationState.EXTREME;

        if (shouldActivate != activeBudget) {
            activeBudget = shouldActivate;
            lastSwitchTime = System.currentTimeMillis();
            Log.info("FrameBudget mode " + (activeBudget ? "ENGAGED" : "DISENGAGED") + " (FPS=" + fps + ", state=" + state + ")");
        }
    }

    public static boolean isBudgetActive() {
        return activeBudget;
    }

    public static int getMaxParticleChance(int originalChance) {
        if (!activeBudget) {
            return originalChance;
        }
        return Math.min(originalChance, 20);
    }

    public static boolean shouldStrongThrottleEffects() {
        return activeBudget;
    }
}