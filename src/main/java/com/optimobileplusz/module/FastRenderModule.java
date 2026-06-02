package com.optimobileplusz.module;

import net.minecraft.client.Minecraft;
import net.minecraft.client.CloudStatus;
import com.optimobileplusz.core.OptimizationState;

public class FastRenderModule {
    public static void update(OptimizationState state) {
        Minecraft client = Minecraft.getInstance();
        if (client == null || client.options == null || client.world == null) return;

        // Felhők kikapcsolása a jobb GPU teljesítményért
        client.options.getCloudRenderMode().setValue(CloudStatus.OFF);
        
        // [TÖRÖLVE] client.options.getViewDistance().setValue(4);
        // Kivettük a fix korlátozást, így az OptiCore szabadon állíthatja az FPS szerint!
    }
}