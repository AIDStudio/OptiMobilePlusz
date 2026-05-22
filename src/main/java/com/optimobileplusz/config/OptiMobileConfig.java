package com.optimobileplusz.config;

import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class OptiMobileConfig {
    public static boolean fastClouds = true;
    public static boolean particlesEnabled = false;
    public static boolean showFps = true;
    public static boolean frameBudgetEnabled = true;
    public static boolean smartEntityCulling = true;
    public static boolean throttledAnimations = true;
    public static boolean adaptiveChunkLoadingEnabled = true;
    public static boolean lodEnabled = true;
    public static boolean asyncTextureStreamingEnabled = true;
    public static boolean sceneSimplifierEnabled = false;
    public static boolean gpuProfileEnabled = true;
    public static boolean backgroundTickThrottleEnabled = true;
    public static boolean soundBudgetEnabled = false;
    public static boolean telemetryEnabled = false;
    public static boolean showDetailedOverlay = false;

    private static final Path CONFIG_FILE = FabricLoader.getInstance().getConfigDir().resolve("optimobileplusz.properties");

    public static void save() {
        Properties prop = new Properties();
        prop.setProperty("fastClouds", String.valueOf(fastClouds));
        prop.setProperty("particlesEnabled", String.valueOf(particlesEnabled));
        prop.setProperty("showFps", String.valueOf(showFps));
        prop.setProperty("frameBudgetEnabled", String.valueOf(frameBudgetEnabled));
        prop.setProperty("smartEntityCulling", String.valueOf(smartEntityCulling));
        prop.setProperty("throttledAnimations", String.valueOf(throttledAnimations));
        prop.setProperty("adaptiveChunkLoadingEnabled", String.valueOf(adaptiveChunkLoadingEnabled));
        prop.setProperty("lodEnabled", String.valueOf(lodEnabled));
        prop.setProperty("asyncTextureStreamingEnabled", String.valueOf(asyncTextureStreamingEnabled));
        prop.setProperty("sceneSimplifierEnabled", String.valueOf(sceneSimplifierEnabled));
        prop.setProperty("gpuProfileEnabled", String.valueOf(gpuProfileEnabled));
        prop.setProperty("backgroundTickThrottleEnabled", String.valueOf(backgroundTickThrottleEnabled));
        prop.setProperty("soundBudgetEnabled", String.valueOf(soundBudgetEnabled));
        prop.setProperty("telemetryEnabled", String.valueOf(telemetryEnabled));
        prop.setProperty("showDetailedOverlay", String.valueOf(showDetailedOverlay));

        try {
            Files.createDirectories(CONFIG_FILE.getParent());
            try (OutputStream output = Files.newOutputStream(CONFIG_FILE)) {
                prop.store(output, "OptiMobilePlusz Configuration");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void load() {
        if (!Files.exists(CONFIG_FILE)) {
            save();
            return;
        }

        Properties prop = new Properties();
        try (InputStream input = Files.newInputStream(CONFIG_FILE)) {
            prop.load(input);
            fastClouds = Boolean.parseBoolean(prop.getProperty("fastClouds", "true"));
            particlesEnabled = Boolean.parseBoolean(prop.getProperty("particlesEnabled", "false"));
            showFps = Boolean.parseBoolean(prop.getProperty("showFps", "true"));
            frameBudgetEnabled = Boolean.parseBoolean(prop.getProperty("frameBudgetEnabled", "true"));
            smartEntityCulling = Boolean.parseBoolean(prop.getProperty("smartEntityCulling", "true"));
            throttledAnimations = Boolean.parseBoolean(prop.getProperty("throttledAnimations", "true"));
            adaptiveChunkLoadingEnabled = Boolean.parseBoolean(prop.getProperty("adaptiveChunkLoadingEnabled", "true"));
            lodEnabled = Boolean.parseBoolean(prop.getProperty("lodEnabled", "true"));
            asyncTextureStreamingEnabled = Boolean.parseBoolean(prop.getProperty("asyncTextureStreamingEnabled", "true"));
            sceneSimplifierEnabled = Boolean.parseBoolean(prop.getProperty("sceneSimplifierEnabled", "false"));
            gpuProfileEnabled = Boolean.parseBoolean(prop.getProperty("gpuProfileEnabled", "true"));
            backgroundTickThrottleEnabled = Boolean.parseBoolean(prop.getProperty("backgroundTickThrottleEnabled", "true"));
            soundBudgetEnabled = Boolean.parseBoolean(prop.getProperty("soundBudgetEnabled", "false"));
            telemetryEnabled = Boolean.parseBoolean(prop.getProperty("telemetryEnabled", "false"));
            showDetailedOverlay = Boolean.parseBoolean(prop.getProperty("showDetailedOverlay", "false"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
