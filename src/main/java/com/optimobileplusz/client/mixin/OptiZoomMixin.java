package com.optimobileplusz.client.mixin;

import com.optimobileplusz.client.ZoomState;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRenderer.class)
public class OptiZoomMixin {

    /**
     * Ez a metódus felel a FOV (látómező) belső módosításáért a 26.1.x alatt.
     */
    @Inject(method = "getFov(Lnet/minecraft/client/render/Camera;FZ)F", at = @At("RETURN"), cancellable = true, require = 0)
    private void onGetFov(CallbackInfoReturnable<Float> cir) {
        double zoom = ZoomState.getZoomMultiplier();
        
        if (zoom > 1.0) {
            float originalFov = cir.getReturnValue();
            cir.setReturnValue(originalFov / (float) zoom);
        }
    }
}
