package com.optimobileplusz.module;

import com.optimobileplusz.config.OptiMobileConfig;
import com.optimobileplusz.core.OptimizationState;
import net.minecraft.client.Minecraft;

public class ChunkLoadManager {

    public static void update(OptimizationState state) {
        if (!OptiMobileConfig.adaptiveChunkLoadingEnabled) {
            return;
        }

        Minecraft client = Minecraft.getInstance();
        if (client == null || client.player == null || client.world == null) {
            return;
        }

        double speed = client.player.getVelocity().lengthSquared();

        if (speed > 0.05) {
            int distance = (state == OptimizationState.EXTREME) ? 4 : (state == OptimizationState.PERFORMANCE ? 5 : 6);
            client.options.getSimulationDistance().setValue(distance);
        }
    }

    public static boolean shouldThrottleChunkLoad() {
        return OptiMobileConfig.adaptiveChunkLoadingEnabled;
    }
}
