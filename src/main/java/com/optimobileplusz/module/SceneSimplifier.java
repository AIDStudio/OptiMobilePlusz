package com.optimobileplusz.module;

import com.optimobileplusz.config.OptiMobileConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.GraphicsPreset;
import java.lang.reflect.Field;

public class SceneSimplifier {

    public static void update() {
        if (!OptiMobileConfig.sceneSimplifierEnabled) return;

        Minecraft client = Minecraft.getInstance();
        if (client == null || client.options == null) return;

        GraphicsPreset target = OptiMobileConfig.frameBudgetEnabled ? GraphicsPreset.FAST : GraphicsPreset.FANCY;

        try {
            // Megkeressük a Options osztályban azt a mezőt, ami GraphicsPreset típusú
            for (Field field : client.options.getClass().getFields()) {
                if (field.getType() == GraphicsPreset.class) {
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
