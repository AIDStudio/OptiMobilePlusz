package com.optimobileplusz.client.mixin;

import com.optimobileplusz.client.ZoomState;
import net.minecraft.Entity;
import net.minecraft.class_746;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(Entity.class)
public abstract class OptiPlayerMixin {

    // Az 1.21.1+ verziókban a changeLookDirection intermediary neve: method_5730
    // A 'require = 0' biztosítja, hogy ne omoljon össze a játék, ha valamiért mégis változna a név
    @ModifyVariable(
            method = "changeLookDirection(DD)V",
            at = @At("HEAD"),
            ordinal = 0,
            argsOnly = true,
            require = 0
    )
    private double modifyLookX(double x) {
        if ((Object) this instanceof class_746) {
            double zoom = ZoomState.getZoomMultiplier();
            if (zoom > 1.0) {
                return x / zoom;
            }
        }
        return x;
    }

    @ModifyVariable(
            method = "changeLookDirection(DD)V",
            at = @At("HEAD"),
            ordinal = 1,
            argsOnly = true,
            require = 0
    )
    private double modifyLookY(double y) {
        if ((Object) this instanceof class_746) {
            double zoom = ZoomState.getZoomMultiplier();
            if (zoom > 1.0) {
                return y / zoom;
            }
        }
        return y;
    }
}
