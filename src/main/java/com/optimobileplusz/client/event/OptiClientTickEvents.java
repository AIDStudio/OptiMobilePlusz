package com.optimobileplusz.client.event;

import com.optimobileplusz.client.OptimobileClient;
import com.optimobileplusz.client.ZoomState;
import com.optimobileplusz.client.monitor.FpsMonitor;
import com.optimobileplusz.module.BackgroundTickThrottler;
import com.optimobileplusz.core.OptiCore;
import net.minecraft.client.MinecraftClient;

public class OptiClientTickEvents {

    /**
     * Ezt a metódust hívja meg a rendszer minden egyes kliens-oldali tick végén.
     * Itt frissítjük a mérőket és futtatjuk az optimalizációt.
     */
    public static void tick(MinecraftClient client) {
        // Ha a játékos nincs bent egy világban (pl. főmenüben van), ne csináljunk semmit
        if (client.getWorld() == null) {
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
     * Regisztrálja az eseménykezelőt a Fabric API-n keresztül a 26.1.x szabvány szerint.
     */
    public static void register() {
        // JAVÍTVA: END_CLIENT_TICK helyett az új 26.1.x kompatibilis END_WORLD_TICK-et használjuk a MinecraftClient példánnyal
        net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents.END_WORLD_TICK.register(world -> {
            MinecraftClient client = MinecraftClient.getInstance();
            tick(client);
        });
    }
}
