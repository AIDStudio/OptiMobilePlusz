package com.optimobileplusz.client.event;

import com.optimobileplusz.client.OptimobileClient;
import com.optimobileplusz.client.ZoomState;
import com.optimobileplusz.client.monitor.FpsMonitor;
import com.optimobileplusz.module.BackgroundTickThrottler;
import com.optimobileplusz.core.OptiCore;
import net.minecraft.client.MinecraftClient; // <-- JAVÍTVA: Fabric-os MinecraftClient import

public class OptiClientTickEvents {

    /**
     * Ezt a metódust hívja meg a rendszer minden egyes kliens-oldali tick végén.
     * Itt frissítjük a mérőket és futtatjuk az optimalizációt.
     */
    public static void tick(MinecraftClient client) { // <-- JAVÍTVA: Típus átírva MinecraftClient-re
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
        boolean isZooming = OptimobileClient.zoomKey != null && OptimobileClient.zoomKey.isPressed();
        ZoomState.tickZoom(isZooming);
    }

    /**
     * Regisztrálja az eseménykezelőt a Fabric API-n keresztül.
     */
    public static void register() {
        net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents.END_CLIENT_TICK.register(client -> {
            tick(client);
        });
    }
}
