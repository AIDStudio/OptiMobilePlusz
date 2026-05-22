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

        if (OptiMobileConfig.frameBudgetEnabled) {
            // Az újabb mappingekben a mező neve simán 'graphics'
            client.options.graphics.setValue(GraphicsMode.FAST);
        } else {
            client.options.graphics.setValue(GraphicsMode.FANCY);
        }
    }
}
