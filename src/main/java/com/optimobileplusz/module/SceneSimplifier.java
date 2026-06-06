package com.optimobileplusz.module;

import com.optimobileplusz.config.OptiMobileConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GraphicsMode; // Hivatalos enum

public class SceneSimplifier {

    public static void update() {
        if (!OptiMobileConfig.sceneSimplifierEnabled) return;

        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.options == null) return;

        // A frameBudget alapján választjuk ki a grafikát:
        // FAST (gyors) vagy FANCY (szép)
        GraphicsMode target = OptiMobileConfig.frameBudgetEnabled ? GraphicsMode.FAST : GraphicsMode.FANCY;

        // A reflexiót lecseréltük a hivatalos API-ra
        client.options.getGraphicsMode().setValue(target);
    }
}
