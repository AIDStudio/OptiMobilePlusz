package com.optimobileplusz.module;

import com.optimobileplusz.config.OptiMobileConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.SoundInstance; // Hivatalos SoundInstance
import net.minecraft.sound.SoundCategory;      // Hivatalos kategóriák

public class SoundBudgetManager {

    public static boolean shouldSkipSound(SoundInstance sound) {
        if (!OptiMobileConfig.soundBudgetEnabled) {
            return false;
        }

        if (sound == null) {
            return false;
        }

        // A zenei hangokat (Music) ne szűrjük ki
        if (sound.getCategory() != null && sound.getCategory().equals(SoundCategory.MUSIC)) {
            return false;
        }

        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.player == null) {
            return false;
        }

        // A hangerő és távolság alapján történő szűrés
        double volume = sound.getVolume();
        // squaredDistanceTo a hivatalos metódus név
        double distance = client.player.squaredDistanceTo(sound.getX(), sound.getY(), sound.getZ());
        
        // Ha túl messze van és halk, kihagyjuk
        if (distance > 64.0 * 64.0 && volume < 0.7) {
            return true;
        }

        return false;
    }

    public static boolean isEnabled() {
        return OptiMobileConfig.soundBudgetEnabled;
    }
}
