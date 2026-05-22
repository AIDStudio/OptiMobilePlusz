package com.optimobileplusz.module;

import com.optimobileplusz.config.OptiMobileConfig;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class PresetManager {

    private static final Path PRESET_FILE = FabricLoader.getInstance().getConfigDir().resolve("optimobileplusz-presets.properties");

    public static void applyPreset(String name) {
        switch (name.toLowerCase()) {
            case "battery":
                OptiMobileConfig.fastClouds = false;
                OptiMobileConfig.particlesEnabled = false;
                OptiMobileConfig.frameBudgetEnabled = true;
                OptiMobileConfig.smartEntityCulling = true;
                OptiMobileConfig.throttledAnimations = true;
                OptiMobileConfig.sceneSimplifierEnabled = true;
                OptiMobileConfig.gpuProfileEnabled = true;
                OptiMobileConfig.backgroundTickThrottleEnabled = true;
                OptiMobileConfig.soundBudgetEnabled = true;
                OptiMobileConfig.telemetryEnabled = false;
                break;
            case "competition":
                OptiMobileConfig.fastClouds = true;
                OptiMobileConfig.particlesEnabled = false;
                OptiMobileConfig.frameBudgetEnabled = true;
                OptiMobileConfig.smartEntityCulling = true;
                OptiMobileConfig.throttledAnimations = true;
                OptiMobileConfig.sceneSimplifierEnabled = true;
                OptiMobileConfig.gpuProfileEnabled = true;
                OptiMobileConfig.backgroundTickThrottleEnabled = false;
                OptiMobileConfig.soundBudgetEnabled = true;
                OptiMobileConfig.telemetryEnabled = true;
                break;
            case "balanced":
            default:
                OptiMobileConfig.fastClouds = true;
                OptiMobileConfig.particlesEnabled = true;
                OptiMobileConfig.frameBudgetEnabled = false;
                OptiMobileConfig.smartEntityCulling = true;
                OptiMobileConfig.throttledAnimations = false;
                OptiMobileConfig.sceneSimplifierEnabled = false;
                OptiMobileConfig.gpuProfileEnabled = true;
                OptiMobileConfig.backgroundTickThrottleEnabled = true;
                OptiMobileConfig.soundBudgetEnabled = false;
                OptiMobileConfig.telemetryEnabled = false;
                break;
        }
    }

    public static void saveCurrentPreset(String name) {
        Properties prop = new Properties();
        prop.setProperty("preset.name", name);
        prop.setProperty("fastClouds", String.valueOf(OptiMobileConfig.fastClouds));
        prop.setProperty("particlesEnabled", String.valueOf(OptiMobileConfig.particlesEnabled));
        prop.setProperty("showFps", String.valueOf(OptiMobileConfig.showFps));
        prop.setProperty("frameBudgetEnabled", String.valueOf(OptiMobileConfig.frameBudgetEnabled));
        prop.setProperty("smartEntityCulling", String.valueOf(OptiMobileConfig.smartEntityCulling));
        prop.setProperty("throttledAnimations", String.valueOf(OptiMobileConfig.throttledAnimations));
        prop.setProperty("sceneSimplifierEnabled", String.valueOf(OptiMobileConfig.sceneSimplifierEnabled));
        prop.setProperty("gpuProfileEnabled", String.valueOf(OptiMobileConfig.gpuProfileEnabled));
        prop.setProperty("backgroundTickThrottleEnabled", String.valueOf(OptiMobileConfig.backgroundTickThrottleEnabled));
        prop.setProperty("soundBudgetEnabled", String.valueOf(OptiMobileConfig.soundBudgetEnabled));
        prop.setProperty("telemetryEnabled", String.valueOf(OptiMobileConfig.telemetryEnabled));

        try {
            Files.createDirectories(PRESET_FILE.getParent());
            try (OutputStream out = Files.newOutputStream(PRESET_FILE)) {
                prop.store(out, "OptiMobilePlusz preset: " + name);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String loadPresetName() {
        if (!Files.exists(PRESET_FILE)) {
            return "balanced";
        }
        Properties prop = new Properties();
        try (InputStream input = Files.newInputStream(PRESET_FILE)) {
            prop.load(input);
            String name = prop.getProperty("preset.name", "balanced");
            applyPreset(name);
            return name;
        } catch (IOException e) {
            e.printStackTrace();
            return "balanced";
        }
    }
}
