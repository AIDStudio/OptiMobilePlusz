package com.optimobileplusz.client.hook;

import com.optimobileplusz.client.event.OptiClientTickEvents;
import net.minecraft.client.MinecraftClient; // Helyes import

public class ClientHooks {

    /**
     * Ezt a metódust hívja meg a rendszer egy Mixin-ből.
     * Modernizálva a Mojang mappinghez.
     */
    public static void onClientTick() {
        // A method_1551() helyett a getInstance() metódust használjuk
        OptiClientTickEvents.tick(MinecraftClient.getInstance());
    }
}
