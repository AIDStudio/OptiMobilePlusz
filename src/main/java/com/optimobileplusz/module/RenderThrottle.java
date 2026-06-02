package com.optimobileplusz.module;

import com.optimobileplusz.core.OptimizationState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;

public class RenderThrottle {
    public static void update(OptimizationState state) {
        Options opt = Minecraft.getInstance().options;
        if (opt == null) return;
        
        // Árnyékok kikapcsolása
        opt.getEntityShadows().setValue(false);
        
        // [TÖRÖLVE] opt.getViewDistance().setValue(4); 
        // Ezt most már az OptiCore kezeli dinamikusan az FPS alapján!
        
        // Szimulációs távolság minimumra (8 chunk mobilon ideális)
        opt.getSimulationDistance().setValue(8);
        
        // Kamera imbolygás kikapcsolása
        opt.getBobView().setValue(false);
        
        // Fókuszvesztéskor megállás javítva (egyenesen összefűzve)
        opt.pauseOnLostFocus = true;
    }
}