package com.optimobileplusz.module;

import com.optimobileplusz.config.OptiMobileConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GraphicsMode;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class SceneSimplifier {
    
    // Gyorsítótár (cache), hogy a Reflection csak egyszer fusson le, és megmaradjon a max sebesség
    private static boolean resolved = false;
    private static Field directEnumField = null;
    private static Field simpleOptionField = null;
    private static Method simpleOptionGetter = null;
    private static Method setValueMethod = null;

    public static void update() {
        if (!OptiMobileConfig.sceneSimplifierEnabled) {
            return;
        }

        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.options == null) {
            return;
        }

        GraphicsMode targetMode = OptiMobileConfig.frameBudgetEnabled ? GraphicsMode.FAST : GraphicsMode.FANCY;

        // Ha még nem kerestük meg a pontos változót, most megtesszük
        if (!resolved) {
            resolveMapping(client.options);
            resolved = true;
        }

        // Érték beállítása a megtalált és cache-elt útvonalon
        try {
            if (directEnumField != null) {
                directEnumField.set(client.options, targetMode);
            } else if (simpleOptionField != null) {
                Object optionObj = simpleOptionField.get(client.options);
                if (setValueMethod != null) setValueMethod.invoke(optionObj, targetMode);
            } else if (simpleOptionGetter != null) {
                Object optionObj = simpleOptionGetter.invoke(client.options);
                if (setValueMethod != null) setValueMethod.invoke(optionObj, targetMode);
            }
        } catch (Exception e) {
            // Csendes kivételkezelés, hogy ne spamkelje tele a logokat játék közben
        }
    }

    private static void resolveMapping(Object options) {
        try {
            // 1. Esély: Régebbi verziók, ahol a mező közvetlenül egy GraphicsMode enum
            for (Field f : options.getClass().getFields()) {
                if (f.getType() == GraphicsMode.class) {
                    directEnumField = f;
                    return;
                }
            }

            // 2. Esély: Újabb verziók, ahol a beállítás egy SimpleOption burkolóban van mezőként
            for (Field f : options.getClass().getFields()) {
                if (f.getType().getSimpleName().equals("SimpleOption") && f.getName().toLowerCase().contains("graphics")) {
                    simpleOptionField = f;
                    Object optionObj = f.get(options);
                    setValueMethod = optionObj.getClass().getMethod("setValue", Object.class);
                    return;
                }
            }

            // 3. Esély: Legújabb verziók, ahol a SimpleOption objektumot egy getter metódus adja vissza
            for (Method m : options.getClass().getMethods()) {
                if (m.getReturnType().getSimpleName().equals("SimpleOption") && m.getName().toLowerCase().contains("graphics")) {
                    simpleOptionGetter = m;
                    Object optionObj = m.invoke(options);
                    setValueMethod = optionObj.getClass().getMethod("setValue", Object.class);
                    return;
                }
            }
        } catch (Exception e) {
            System.out.println("[OptiMobilePlusz] Nem sikerult automatikusan feloldani a grafikai modot.");
        }
    }
}
