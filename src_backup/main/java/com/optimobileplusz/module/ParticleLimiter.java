package com.optimobileplusz.module;

import com.optimobileplusz.core.OptimizationState;

public class ParticleLimiter {
    // Százalékos arány: 100 = összes részecske megjelenik, 10 = csak a 10%-uk
    private static int particleMultiplier = 100;

    public static void update(OptimizationState state) {
        switch (state) {
            case ULTRA:
                particleMultiplier = 100;
                break;
            case BALANCED:
                particleMultiplier = 70;
                break;
            case PERFORMANCE:
                particleMultiplier = 40;
                break;
            case EXTREME:
                particleMultiplier = 15; // EXTREME módban drasztikusan vágjuk le a Poco GPU-ja miatt
                break;
        }
    }

    public static int getParticleMultiplier() {
        return particleMultiplier;
    }
}