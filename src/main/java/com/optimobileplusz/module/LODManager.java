package com.optimobileplusz.module;

import com.optimobileplusz.client.monitor.FpsMonitor;
import com.optimobileplusz.config.OptiMobileConfig;
import com.optimobileplusz.core.OptimizationState;
import net.minecraft.entity.Entity;
import net.minecraft.client.MinecraftClient;

public class LODManager {

    public static void update(OptimizationState state) {
        if (!OptiMobileConfig.lodEnabled) {
            return;
        }

        // A Minecraft.method_1551() helyett a hivatalos getInstance()
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.world == null) {
            return;
        }

        int fps = FpsMonitor.getFPS();
        double scale = 1.0;
        if (fps < 30) {
            scale = 0.6;
        } else if (fps < 50) {
            scale = 0.8;
        }

        // field_1690 -> options, method_42517 -> getEntityDistanceScaling, method_41748 -> setValue
        client.options.getEntityDistanceScaling().setValue(Math.max(4.0, 10.0 * scale));
    }

    public static boolean shouldSimplifyEntity(Entity entity) {
        if (!OptiMobileConfig.lodEnabled) {
            return false;
        }

        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.player == null) {
            return false;
        }

        // method_5649 -> squaredDistanceTo, method_23317 -> getX(), etc.
        double distance = client.player.squaredDistanceTo(entity.getX(), entity.getY(), entity.getZ());
        return distance > 64.0 * 64.0;
    }
}
