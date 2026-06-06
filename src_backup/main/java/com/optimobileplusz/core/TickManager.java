package com.optimobileplusz.core;

public class TickManager {

    private static long tickCounter = 0;

    public static void tick() {

        tickCounter++;
    }

    public static long getTicks() {

        return tickCounter;
    }
}