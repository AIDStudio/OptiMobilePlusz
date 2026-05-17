package com.optimobileplusz.config;

import java.io.*;
import java.util.Properties;

public class OptiMobileConfig {
    public static boolean fastClouds = true;
    public static boolean particlesEnabled = false;
    public static boolean showFps = true; // EZ HIÁNYZOTT!

    private static final String CONFIG_FILE = "config/optimobileplusz.properties";

    public static void save() {
        Properties prop = new Properties();
        prop.setProperty("fastClouds", String.valueOf(fastClouds));
        prop.setProperty("particlesEnabled", String.valueOf(particlesEnabled));
        prop.setProperty("showFps", String.valueOf(showFps)); // MENTÉSRE IS KELL
        
        File file = new File("config");
        if (!file.exists()) file.mkdirs();

        try (OutputStream output = new FileOutputStream(CONFIG_FILE)) {
            prop.store(output, "OptiMobilePlusz Configuration");
        } catch (IOException e) { e.printStackTrace(); }
    }

    public static void load() {
        File file = new File(CONFIG_FILE);
        if (!file.exists()) { save(); return; }
        
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream(CONFIG_FILE)) {
            prop.load(input);
            fastClouds = Boolean.parseBoolean(prop.getProperty("fastClouds", "true"));
            particlesEnabled = Boolean.parseBoolean(prop.getProperty("particlesEnabled", "false"));
            showFps = Boolean.parseBoolean(prop.getProperty("showFps", "true")); // BETÖLTÉSRE IS KELL
        } catch (IOException e) { e.printStackTrace(); }
    }
}
