package com.optimobileplusz.module;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.CloudRenderMode;
import com.optimobileplusz.core.OptimizationState;

public class FastRenderModule {
    public static void update(OptimizationState state) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.options == null || client.world == null) return;

        // Felhők kikapcsolása a jobb GPU teljesítményért
        client.options.getCloudRenderMode().setValue(CloudRenderMode.OFF);
        
        // [TÖRÖLVE] client.options.getViewDistance().setValue(4);
        // Kivettük a fix korlátozást, így az OptiCore szabadon állíthatja az FPS szerint!
    }
}