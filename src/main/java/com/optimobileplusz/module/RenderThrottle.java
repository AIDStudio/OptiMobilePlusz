package com.optimobileplusz.module;

import com.optimobileplusz.core.OptimizationState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GameOptions;

public class RenderThrottle {
    public static void update(OptimizationState state) {
        // 1. MinecraftClient.getInstance() a method_1551() helyett
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null) return;
        
        // 2. GameOptions a class_315 (field_1690) helyett
        GameOptions options = client.options;
        if (options == null) return;
        
        // 3. Árnyékok kikapcsolása (getEntityShadows() a method_42435() helyett)
        options.getEntityShadows().setValue(false);
        
        // 4. Szimulációs távolság beállítása (getSimulationDistance() a method_42510() helyett)
        options.getSimulationDistance().setValue(8);
        
        // 5. Kamera imbolygás kikapcsolása (getBobView() a method_42448() helyett)
        options.getBobView().setValue(false);
        
        // 6. Fókuszvesztéskor megállás javítása (pauseOnLostFocus a field_1837 helyett)
        options.pauseOnLostFocus = true;
    }
}
