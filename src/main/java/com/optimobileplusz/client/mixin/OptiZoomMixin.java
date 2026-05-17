package com.optimobileplusz.client.mixin;

import com.optimobileplusz.client.OptimobileClient;
import com.optimobileplusz.client.ZoomState;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRenderer.class)
public class OptiZoomMixin {

    @Unique
    private static double currentZoom = 1.0;

    @Unique
    private static final double TARGET_ZOOM_LEVEL = 4.0;

    // Kicsit kisebb érték a simább átmenetért (0.1 - 0.2 között ideális)
    @Unique
    private static final double ZOOM_SMOOTHING = 0.15;

    /**
     * Ez a metódus felel a FOV (látómező) tényleges módosításáért.
     * Csak leolvassa a ZoomState-et, nem módosítja azt.
     */
    @Inject(method = "getFov(Lnet/minecraft/client/render/Camera;FZ)F", at = @At("RETURN"), cancellable = true, require = 0)
    private void onGetFov(CallbackInfoReturnable<Float> cir) {
        double zoom = ZoomState.getZoomMultiplier();
        
        // Csak akkor módosítjuk a visszatérési értéket, ha van aktív zoom
        if (zoom > 1.0) {
            float originalFov = cir.getReturnValue();
            cir.setReturnValue(originalFov / (float) zoom);
        }
    }
}
