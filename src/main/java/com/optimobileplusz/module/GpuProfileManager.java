package com.optimobileplusz.module;

import com.optimobileplusz.config.OptiMobileConfig;
import com.optimobileplusz.core.Log;
import com.optimobileplusz.core.OptimizationState;
import net.fabricmc.loader.api.FabricLoader;

public class GpuProfileManager {

    private static OptimizationState lastProfile = null;

    public static void update(OptimizationState state) {
        if (!OptiMobileConfig.gpuProfileEnabled) {
            return;
        }

        OptimizationState suggested = suggestProfile();
        if (suggested != lastProfile) {
            lastProfile = suggested;
            Log.info("GPU profile set to: " + suggested);
        }
    }

    private static OptimizationState suggestProfile() {
        int cores = Runtime.getRuntime().availableProcessors();
        boolean lowMemory = FabricLoader.getInstance().getConfigDir().toFile().getUsableSpace() < 512L * 1024 * 1024;
        String arch = System.getProperty("os.arch", "unknown").toLowerCase();
        if (arch.contains("arm") || arch.contains("aarch64") || cores <= 4 || lowMemory) {
            return OptimizationState.PERFORMANCE;
        }
        if (cores <= 6) {
            return OptimizationState.BALANCED;
        }
        return OptimizationState.ULTRA;
    }

    public static boolean isGpuProfileActive() {
        return OptiMobileConfig.gpuProfileEnabled;
    }
}
