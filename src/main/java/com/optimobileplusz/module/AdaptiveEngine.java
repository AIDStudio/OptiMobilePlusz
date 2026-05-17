package com.optimobileplusz.module;

import com.optimobileplusz.core.Log;
import com.optimobileplusz.core.OptimizationState;

public class AdaptiveEngine {

    private static OptimizationState lastState;

    public static void update(OptimizationState state) {

        if (state == lastState) {
            return;
        }

        lastState = state;

        Log.info("Optimization State -> " + state);
    }
}