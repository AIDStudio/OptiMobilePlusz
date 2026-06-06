package com.optimobileplusz.core;

public class TickLimiter {

    private static int tickCounter = 0;

    public static void init() {}

    // Mobil optimalizáció: minden 2. tick heavy logic skip
    public static boolean shouldSkipHeavyTick() {
        tickCounter++;
        return (tickCounter % 2 == 0);
    }
}