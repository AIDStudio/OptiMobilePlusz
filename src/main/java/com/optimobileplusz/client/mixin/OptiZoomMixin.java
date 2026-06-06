package com.optimobileplusz.client.mixin;

import com.optimobileplusz.client.ZoomState;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRenderer.class)
public class OptiZoomMixin {

    /**
     * Ez a metódus felel a FOV módosításáért.
     * A GameRenderer.getFov(Camera camera, float tickDelta, boolean changingFov) 
     * metódust injektáljuk Mojang mapping szerint.
     */
    @Inject(method = "getFov(Lnet/minecraft/client/render/Camera;FZ)F", at = @At("RETURN"), cancellable = true)
    private void onGetFov(Camera camera, float tickDelta, boolean changingFov, CallbackInfoReturnable<Float> cir) {
        double zoom = ZoomState.getZoomMultiplier();
        
        // Csak akkor módosítjuk, ha a zoom értéke aktív (nagyobb, mint 1.0)
        if (zoom > 1.0) {
            float originalFov = cir.getReturnValue();
            // Az eredeti FOV osztása a zoom mértékével
            cir.setReturnValue((float) (originalFov / zoom));
        }
    }
}
