package com.optimobileplusz.client.mixin;

import com.optimobileplusz.client.ZoomState;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

// Közvetlenül a ClientPlayerEntity-t mixeljük, így nem kell típusvizsgálat
@Mixin(ClientPlayerEntity.class)
public abstract class OptiPlayerMixin {

    // A changeLookDirection(double cursorDeltaX, double cursorDeltaY) metódus 
    // a ClientPlayerEntity osztályban érhető el.
    
    @ModifyVariable(
            method = "changeLookDirection(DD)V",
            at = @At("HEAD"),
            ordinal = 0,
            argsOnly = true
    )
    private double modifyLookX(double x) {
        double zoom = ZoomState.getZoomMultiplier();
        return zoom > 1.0 ? x / zoom : x;
    }

    @ModifyVariable(
            method = "changeLookDirection(DD)V",
            at = @At("HEAD"),
            ordinal = 1,
            argsOnly = true
    )
    private double modifyLookY(double y) {
        double zoom = ZoomState.getZoomMultiplier();
        return zoom > 1.0 ? y / zoom : y;
    }
}
