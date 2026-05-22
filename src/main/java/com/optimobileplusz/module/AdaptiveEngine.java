package com.optimobileplusz.module;

import com.optimobileplusz.client.monitor.FpsMonitor;
import com.optimobileplusz.core.Log;
import com.optimobileplusz.core.OptimizationState;

public class AdaptiveEngine {

    private static OptimizationState lastState;

    public static OptimizationState update(OptimizationState state) {
        int fps = FpsMonitor.getFPS();
        OptimizationState nextState = state;

        if (fps < 25) {
            nextState = OptimizationState.EXTREME;
        } else if (fps < 40) {
            nextState = OptimizationState.PERFORMANCE;
        } else if (fps < 55) {
            nextState = OptimizationState.BALANCED;
        } else {
            nextState = OptimizationState.ULTRA;
        }

        if (nextState != lastState) {
            lastState = nextState;
            Log.info("Adaptive Engine selected state: " + nextState + " (FPS=" + fps + ")");
        }

        return nextState;
    }
}