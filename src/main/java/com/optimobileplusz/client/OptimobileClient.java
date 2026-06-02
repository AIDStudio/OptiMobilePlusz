package com.optimobileplusz.client;

import com.optimobileplusz.client.event.OptiClientTickEvents;
import com.optimobileplusz.core.Log;
import com.optimobileplusz.core.OptiCore;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import com.mojang.blaze3d.platform.InputConstants;
import org.lwjgl.glfw.GLFW;

public class OptimobileClient implements ClientModInitializer {

    public static KeyMapping zoomKey;

    @Override
    public void onInitializeClient() {
        
        com.optimobileplusz.config.OptiMobileConfig.load();
        
        // Ez a megoldás: A KeyBinding.Category enum-on keresztül hivatkozunk a kategóriára.
        // Az 1.21.1-es Yarn mappingben ez a legbiztosabb módja a Category objektum átadásának.
        zoomKey = KeyBindingHelper.registerKeyBinding(new KeyMapping(
                "key.optimobileplusz.zoom", 
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_C, 
                KeyMapping.Category.MISC // <--- Ez itt a belső Enum hivatkozás
        ));
        
        OptiClientTickEvents.register();
        Log.info("OptiMobilePlusz kliens oldali funkciok betöltve!");
        OptiCore.initialize();
    }
}
