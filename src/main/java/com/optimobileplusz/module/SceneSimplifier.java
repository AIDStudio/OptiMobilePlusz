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
            // Próbáljuk a getGraphics() metódussal
            client.options.getGraphics().setValue(GraphicsMode.FAST);
        } else {
            client.options.getGraphics().setValue(GraphicsMode.FANCY);
        }
    }
}
