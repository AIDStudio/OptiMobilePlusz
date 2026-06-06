package com.optimobileplusz.client.monitor;

import net.minecraft.client.MinecraftClient;

public class FpsMonitor {

    private static int currentFPS = 0;

    public static void update() {
        // A MinecraftClient.getInstance() a modern, hivatalos módszer
        // a kliens példány elérésére.
        currentFPS = MinecraftClient.getInstance().getCurrentFps();
    }

    public static int getFPS() {
        return currentFPS;
    }
}
