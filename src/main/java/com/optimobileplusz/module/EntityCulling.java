package com.optimobileplusz.module;

import com.optimobileplusz.core.OptimizationState;
import net.minecraft.client.MinecraftClient;

public class EntityCulling {
    public static void update(OptimizationState state) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.world == null) return;
    }
}