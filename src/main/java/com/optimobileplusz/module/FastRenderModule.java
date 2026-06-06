package com.optimobileplusz.module;

import com.optimobileplusz.core.OptimizationState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.CloudMode;

public class FastRenderModule {
    public static void update(OptimizationState state) {
        // 1. A Minecraft.method_1551() helyett a hivatalos getInstance() használandó
        MinecraftClient client = MinecraftClient.getInstance();
        
        // 2. A mezőnevek modernizálása: field_1690 -> options, field_1687 -> world
        if (client == null || client.options == null || client.world == null) return;

        // 3. Felhők kikapcsolása a jobb GPU teljesítményért
        // A method_42528() -> getCloudRenderMode()
        // A class_4063.field_18162 (OFF) -> CloudMode.OFF
        client.options.getCloudRenderMode().setValue(CloudMode.OFF);
        
        // [TÖRÖLVE] client.options.getViewDistance().setValue(4);
        // Kivettük a fix korlátozást, így az OptiCore szabadon állíthatja az FPS szerint!
    }
}
