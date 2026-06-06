package com.optimobileplusz.client.monitor;

import net.minecraft.Minecraft;


public class FpsMonitor {

    private static int currentFPS = 0;

    public static void update() {
        // A MinecraftClient.getInstance() használata kliensoldali kód
        currentFPS = Minecraft.method_1551().method_47599();
    }

    public static int getFPS() {
        return currentFPS;
    }
}
