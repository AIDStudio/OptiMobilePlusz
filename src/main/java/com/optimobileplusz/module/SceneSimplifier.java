package com.optimobileplusz.module;

import com.optimobileplusz.config.OptiMobileConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GraphicsMode;
import java.lang.reflect.Field;

public class SceneSimplifier {

    public static void update() {
        if (!OptiMobileConfig.sceneSimplifierEnabled) return;

        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.options == null) return;

        GraphicsMode target = OptiMobileConfig.frameBudgetEnabled ? GraphicsMode.FAST : GraphicsMode.FANCY;

        try {
            // Megkeressük a GameOptions osztályban azt a mezőt, ami GraphicsMode típusú
            for (Field field : client.options.getClass().getFields()) {
                if (field.getType() == GraphicsMode.class) {
                    // Megtaláltuk a mezőt (pl. graphicsMode vagy graphics), most beállítjuk
                    field.set(client.options, target);
                    return; // Kész vagyunk
                }
            }
        } catch (Exception e) {
            // Ha valami hiba történik, nem omlik össze a játék
        }
    }
}
