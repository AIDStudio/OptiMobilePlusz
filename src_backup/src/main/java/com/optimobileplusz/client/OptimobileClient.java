package com.optimobileplusz.client;

import com.optimobileplusz.client.event.OptiClientTickEvents;
import com.optimobileplusz.core.Log;
import com.optimobileplusz.core.OptiCore;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.KeyBinding;
import net.minecraft.MinecraftClient;
import org.lwjgl.glfw.GLFW;

public class OptimobileClient implements ClientModInitializer {

    public static KeyBinding zoomKey;

    @Override
    public void onInitializeClient() {
        
        com.optimobileplusz.config.OptiMobileConfig.load();
        
        // Ez a megoldás: A KeyBinding.Category enum-on keresztül hivatkozunk a kategóriára.
        // Az 1.21.1-es Yarn mappingben ez a legbiztosabb módja a Category objektum átadásának.
        zoomKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.optimobileplusz.zoom", 
                MinecraftClient.class_307.field_1668,
                GLFW.GLFW_KEY_C, 
                KeyBinding.class_11900.field_62556 // <--- Ez itt a belső Enum hivatkozás
        ));
        
        OptiClientTickEvents.register();
        Log.info("OptiMobilePlusz kliens oldali funkciok betöltve!");
        OptiCore.initialize();
    }
}
