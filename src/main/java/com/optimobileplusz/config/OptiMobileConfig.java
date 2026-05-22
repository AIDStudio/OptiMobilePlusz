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

    private static final Path CONFIG_FILE = FabricLoader.getInstance().getConfigDir().resolve("optimobileplusz.properties");

    public static void save() {
        Properties prop = new Properties();
        prop.setProperty("fastClouds", String.valueOf(fastClouds));
        prop.setProperty("particlesEnabled", String.valueOf(particlesEnabled));
        prop.setProperty("showFps", String.valueOf(showFps));
        prop.setProperty("frameBudgetEnabled", String.valueOf(frameBudgetEnabled));
        prop.setProperty("smartEntityCulling", String.valueOf(smartEntityCulling));
        prop.setProperty("throttledAnimations", String.valueOf(throttledAnimations));

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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
