package com.optimobileplusz.client.monitor;

import net.minecraft.client.MinecraftClient; // Yarn alatt MinecraftClient a neve!


public class FpsMonitor {

    private static int currentFPS = 0;

    public static void update() {
        // A MinecraftClient.getInstance() használata kliensoldali kód
        currentFPS = MinecraftClient.getInstance().getCurrentFps();
    }

    public static int getFPS() {
        return currentFPS;
    }
}
