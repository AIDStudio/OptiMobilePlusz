package com.optimobileplusz.client.hook;

import com.optimobileplusz.client.event.OptiClientTickEvents;
import net.minecraft.client.MinecraftClient;

public class ClientHooks {

    // Ezt a metódust valószínűleg egy Mixin-ből hívod meg
    public static void onClientTick() {
        // Most már létezik a tick metódus, ami vár egy MinecraftClient példányt
        OptiClientTickEvents.tick(MinecraftClient.getInstance());
    }
}
