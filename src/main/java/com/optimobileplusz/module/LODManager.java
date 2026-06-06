package com.optimobileplusz.module;

import com.optimobileplusz.client.monitor.FpsMonitor;
import com.optimobileplusz.config.OptiMobileConfig;
import com.optimobileplusz.core.OptimizationState;
import net.minecraft.Entity;
import net.minecraft.Minecraft;

public class LODManager {

    public static void update(OptimizationState state) {
        if (!OptiMobileConfig.lodEnabled) {
            return;
        }

        Minecraft client = Minecraft.method_1551();
        if (client == null || client.field_1687 == null) {
            return;
        }

        int fps = FpsMonitor.getFPS();
        double scale = 1.0;
        if (fps < 30) {
            scale = 0.6;
        } else if (fps < 50) {
            scale = 0.8;
        }

        client.field_1690.method_42517().method_41748(Math.max(4.0, 10.0 * scale));
    }

    public static boolean shouldSimplifyEntity(Entity entity) {
        if (!OptiMobileConfig.lodEnabled) {
            return false;
        }

        Minecraft client = Minecraft.method_1551();
        if (client == null || client.field_1724 == null) {
            return false;
        }

        double distance = client.field_1724.method_5649(entity.method_23317(), entity.method_23318(), entity.method_23321());
        return distance > 64.0 * 64.0;
    }
}
