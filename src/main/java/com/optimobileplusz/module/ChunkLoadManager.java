package com.optimobileplusz.module;

import com.optimobileplusz.config.OptiMobileConfig;
import com.optimobileplusz.core.OptimizationState;
import net.minecraft.Minecraft;

public class ChunkLoadManager {

    public static void update(OptimizationState state) {
        if (!OptiMobileConfig.adaptiveChunkLoadingEnabled) {
            return;
        }

        Minecraft client = Minecraft.method_1551();
        if (client == null || client.field_1724 == null || client.field_1687 == null) {
            return;
        }

        double speed = client.field_1724.method_18798().method_1027();

        if (speed > 0.05) {
            int distance = (state == OptimizationState.EXTREME) ? 4 : (state == OptimizationState.PERFORMANCE ? 5 : 6);
            client.field_1690.method_42510().method_41748(distance);
        }
    }

    public static boolean shouldThrottleChunkLoad() {
        return OptiMobileConfig.adaptiveChunkLoadingEnabled;
    }
}
