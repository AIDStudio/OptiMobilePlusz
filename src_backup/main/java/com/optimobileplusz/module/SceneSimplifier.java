package com.optimobileplusz.module;

import com.optimobileplusz.config.OptiMobileConfig;
import java.lang.reflect.Field;
import net.minecraft.Minecraft;
import net.minecraft.class_5365;

public class SceneSimplifier {

    public static void update() {
        if (!OptiMobileConfig.sceneSimplifierEnabled) return;

        Minecraft client = Minecraft.method_1551();
        if (client == null || client.field_1690 == null) return;

        class_5365 target = OptiMobileConfig.frameBudgetEnabled ? class_5365.field_25427 : class_5365.field_25428;

        try {
            // Megkeressük a GameOptions osztályban azt a mezőt, ami GraphicsMode típusú
            for (Field field : client.field_1690.getClass().getFields()) {
                if (field.getType() == class_5365.class) {
                    // Megtaláltuk a mezőt (pl. graphicsMode vagy graphics), most beállítjuk
                    field.set(client.field_1690, target);
                    return; // Kész vagyunk
                }
            }
        } catch (Exception e) {
            // Ha valami hiba történik, nem omlik össze a játék
        }
    }
}
