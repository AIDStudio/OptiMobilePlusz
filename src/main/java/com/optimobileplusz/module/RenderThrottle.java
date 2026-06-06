package com.optimobileplusz.module;

import com.optimobileplusz.core.OptimizationState;
import net.minecraft.Minecraft;
import net.minecraft.class_315;

public class RenderThrottle {
    public static void update(OptimizationState state) {
        class_315 opt = Minecraft.method_1551().field_1690;
        if (opt == null) return;
        
        // Árnyékok kikapcsolása
        opt.method_42435().method_41748(false);
        
        // [TÖRÖLVE] opt.getViewDistance().setValue(4); 
        // Ezt most már az OptiCore kezeli dinamikusan az FPS alapján!
        
        // Szimulációs távolság minimumra (8 chunk mobilon ideális)
        opt.method_42510().method_41748(8);
        
        // Kamera imbolygás kikapcsolása
        opt.method_42448().method_41748(false);
        
        // Fókuszvesztéskor megállás javítva (egyenesen összefűzve)
        opt.field_1837 = true;
    }
}