package com.optimobileplusz.module;

import com.optimobileplusz.core.OptimizationState;
import net.minecraft.Minecraft;
import net.minecraft.class_4063;

public class FastRenderModule {
    public static void update(OptimizationState state) {
        Minecraft client = Minecraft.method_1551();
        if (client == null || client.field_1690 == null || client.field_1687 == null) return;

        // Felhők kikapcsolása a jobb GPU teljesítményért
        client.field_1690.method_42528().method_41748(class_4063.field_18162);
        
        // [TÖRÖLVE] client.options.getViewDistance().setValue(4);
        // Kivettük a fix korlátozást, így az OptiCore szabadon állíthatja az FPS szerint!
    }
}