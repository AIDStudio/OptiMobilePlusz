package com.optimobileplusz.module;

import com.optimobileplusz.config.OptiMobileConfig;
import com.optimobileplusz.core.OptimizationState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.SoundInstance;

public class SoundBudgetManager {

    public static boolean shouldSkipSound(SoundInstance sound) {
        if (!OptiMobileConfig.soundBudgetEnabled) {
            return false;
        }

        if (sound == null) {
            return false;
        }

        if (sound.getCategory() != null && sound.getCategory().equals(net.minecraft.sound.SoundCategory.MASTER)) {
            return false;
        }

        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.player == null) {
            return false;
        }

        double volume = sound.getVolume();
        double distance = client.player.squaredDistanceTo(sound.getX(), sound.getY(), sound.getZ());
        if (distance > 64.0 * 64.0 && volume < 0.7) {
            return true;
        }

        return false;
    }

    public static boolean isEnabled() {
        return OptiMobileConfig.soundBudgetEnabled;
    }
}
