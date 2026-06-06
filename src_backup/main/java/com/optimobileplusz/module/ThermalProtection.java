package com.optimobileplusz.module;

import com.optimobileplusz.core.Log;
import com.optimobileplusz.core.OptimizationState;

public class ThermalProtection {

    private static long lastWarning = 0;

    public static void update(OptimizationState state) {

        if (state == OptimizationState.EXTREME) {

            long now = System.currentTimeMillis();

            if (now - lastWarning > 10000) {

                lastWarning = now;

                Log.warn("Device thermal risk detected.");
            }
        }
    }
}