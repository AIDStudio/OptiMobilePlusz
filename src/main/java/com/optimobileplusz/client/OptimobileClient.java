package com.optimobileplusz.client;

import com.optimobileplusz.client.event.OptiClientTickEvents;
import com.optimobileplusz.core.Log;
import com.optimobileplusz.core.OptiCore;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class OptimobileClient implements ClientModInitializer {

    public static KeyBinding zoomKey;

    @Override
    public void onInitializeClient() {
        
        com.optimobileplusz.config.OptiMobileConfig.load();
        
        // Hivatalos Mojang mapping szerinti KeyBinding regisztráció
        // Az InputUtil.Type.KEYSYM a modern szabvány a billentyűk kezelésére
        zoomKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.optimobileplusz.zoom",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_C,
                "category.optimobileplusz.general" 
        ));
        
        // Az események regisztrálása
        OptiClientTickEvents.register();
        
        Log.info("OptiMobilePlusz kliens oldali funkciók betöltve!");
        OptiCore.initialize();
    }
}
