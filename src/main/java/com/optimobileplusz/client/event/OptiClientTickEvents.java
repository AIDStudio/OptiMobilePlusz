package com.optimobileplusz.client.event;

import com.optimobileplusz.client.monitor.FpsMonitor;
import com.optimobileplusz.core.OptiCore;
import net.minecraft.client.MinecraftClient;

public class OptiClientTickEvents {

    /**
     * Ezt a metódust hívja meg a rendszer minden egyes kliens-oldali tick végén.
     * Itt frissítjük a mérőket és futtatjuk az optimalizációt.
     */
    public static void tick(MinecraftClient client) {
        // Ha a játékos nincs bent egy világban (pl. főmenüben van), ne csináljunk semmit
        if (client.world == null) {
            return;
        }

        // 1. Frissítjük az FPS adatokat a monitorban
        FpsMonitor.update();

        // 2. Futtatjuk a mod mag-optimalizációs logikáját
        OptiCore.update();
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
