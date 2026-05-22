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

        // 1.21-ben a GameOptions-ben a grafika már nem mező, hanem egy objektum, 
        // ami a .getValue() és .setValue() metódusokat használja.
        // A mező neve a Yarn mappingben 1.21-nél: graphicsMode
        
        if (OptiMobileConfig.frameBudgetEnabled) {
            client.options.getGraphicsMode().setValue(GraphicsMode.FAST);
        } else {
            client.options.getGraphicsMode().setValue(GraphicsMode.FANCY);
        }
    }
}
