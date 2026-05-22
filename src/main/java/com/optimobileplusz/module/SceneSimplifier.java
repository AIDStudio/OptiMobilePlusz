package com.optimobileplusz.module;

import com.optimobileplusz.config.OptiMobileConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GraphicsMode;

public class SceneSimplifier {

    public static void update() {
        if (!OptiMobileConfig.sceneSimplifierEnabled) {
            return;
        }

        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.options == null) {
            return;
        }

        // A 1.21-es mappingekben a mező neve pontosan: graphicsMode
        // Mivel ez egy SimpleOption<GraphicsMode>, a .set() metódust kell használnunk
        if (OptiMobileConfig.frameBudgetEnabled) {
            client.options.graphicsMode.set(GraphicsMode.FAST);
        } else {
            client.options.graphicsMode.set(GraphicsMode.FANCY);
        }
    }
}
