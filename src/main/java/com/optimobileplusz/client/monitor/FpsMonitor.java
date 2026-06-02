package com.optimobileplusz.client.monitor;

import net.minecraft.client.Minecraft; // Yarn alatt Minecraft a neve!


public class FpsMonitor {

    private static int currentFPS = 0;

    public static void update() {
        // A Minecraft.getInstance() használata kliensoldali kód
        currentFPS = Minecraft.getInstance().getCurrentFps();
    }

    public static int getFPS() {
        return currentFPS;
    }
}
