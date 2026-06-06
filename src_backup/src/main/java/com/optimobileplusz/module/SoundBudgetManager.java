package com.optimobileplusz.module;

import com.optimobileplusz.config.OptiMobileConfig;
import com.optimobileplusz.core.OptimizationState;
import net.minecraft.class_1113;
import net.minecraft.Minecraft;

public class SoundBudgetManager {

    public static boolean shouldSkipSound(class_1113 sound) {
        if (!OptiMobileConfig.soundBudgetEnabled) {
            return false;
        }

        if (sound == null) {
            return false;
        }

        if (sound.method_4774() != null && sound.method_4774().equals(net.minecraft.class_3419.field_15250)) {
            return false;
        }

        Minecraft client = Minecraft.method_1551();
        if (client == null || client.field_1724 == null) {
            return false;
        }

        double volume = sound.method_4781();
        double distance = client.field_1724.method_5649(sound.method_4784(), sound.method_4779(), sound.method_4778());
        if (distance > 64.0 * 64.0 && volume < 0.7) {
            return true;
        }

        return false;
    }

    public static boolean isEnabled() {
        return OptiMobileConfig.soundBudgetEnabled;
    }
}
