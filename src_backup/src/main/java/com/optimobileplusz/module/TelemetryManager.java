package com.optimobileplusz.module;

import com.optimobileplusz.config.OptiMobileConfig;
import com.optimobileplusz.core.Log;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.Minecraft;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.Properties;

public class TelemetryManager {

    private static final Path TELEMETRY_FILE = FabricLoader.getInstance().getConfigDir().resolve("optimobileplusz-telemetry.properties");

    public static void update() {
        if (!OptiMobileConfig.telemetryEnabled) {
            return;
        }

        Minecraft client = Minecraft.method_1551();
        if (client == null) {
            return;
        }

        Properties prop = new Properties();
        prop.setProperty("timestamp", Instant.now().toString());
        prop.setProperty("fps", String.valueOf(client.method_47599()));
        prop.setProperty("particles", String.valueOf(ParticleLimiter.getParticleMultiplier()));
        prop.setProperty("frameBudgetActive", String.valueOf(FrameBudgetManager.isBudgetActive()));

        try {
            Files.createDirectories(TELEMETRY_FILE.getParent());
            try (OutputStream output = Files.newOutputStream(TELEMETRY_FILE)) {
                prop.store(output, "OptiMobilePlusz telemetry snapshot");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
