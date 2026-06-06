package com.optimobileplusz.client.event;

import com.optimobileplusz.client.OptimobileClient;
import com.optimobileplusz.client.ZoomState;
import com.optimobileplusz.client.monitor.FpsMonitor;
import com.optimobileplusz.module.BackgroundTickThrottler;
import net.minecraft.client.MinecraftClient;
import com.optimobileplusz.core.OptiCore;

public class OptiClientTickEvents {

    /**
     * Ezt a metódust hívja meg a rendszer minden egyes kliens-oldali tick végén.
     */
    public static void tick(MinecraftClient client) {
        // Ha a játékos nincs bent egy világban (pl. főmenüben van), ne csináljunk semmit
        if (client.world == null) {
            return;
        }

        // 1. Frissítjük az FPS adatokat a monitorban
        FpsMonitor.update();

        // 2. Ha a játék a háttérben van, ne futtassuk a nehezebb optimalizációs ciklust.
        if (!BackgroundTickThrottler.shouldSkipHeavyTick()) {
            OptiCore.update();
        }

        // 3. Kliens oldali zoom állapot frissítése
        // A method_1434() helyett az isPressed() metódust használjuk
        boolean isZooming = OptimobileClient.zoomKey != null && OptimobileClient.zoomKey.isPressed();
        ZoomState.tickZoom(isZooming);
    }
}
