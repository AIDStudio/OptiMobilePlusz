package com.optimobileplusz.module;

import com.optimobileplusz.config.OptiMobileConfig;
import com.optimobileplusz.core.OptimizationState;
import net.minecraft.client.MinecraftClient;

public class ChunkLoadManager {

    public static void update(OptimizationState state) {
        if (!OptiMobileConfig.adaptiveChunkLoadingEnabled) {
            return;
        }

        // 1. A Minecraft.method_1551() helyett a hivatalos getInstance() használandó
        MinecraftClient client = MinecraftClient.getInstance();
        
        // 2. A field_1724 -> player, a field_1687 -> world (a modern Mojang nevek)
        if (client == null || client.player == null || client.world == null) {
            return;
        }

        // 3. A velocity lekérése (method_18798() -> getVelocity(), method_1027() -> length())
        double speed = client.player.getVelocity().length();

        if (speed > 0.05) {
            int distance = (state == OptimizationState.EXTREME) ? 4 : (state == OptimizationState.PERFORMANCE ? 5 : 6);
            
            // 4. A viewDistance beállítása (field_1690 -> options)
            client.options.getViewDistance().setValue(distance);
        }
    }

    public static boolean shouldThrottleChunkLoad() {
        return OptiMobileConfig.adaptiveChunkLoadingEnabled;
    }
}
