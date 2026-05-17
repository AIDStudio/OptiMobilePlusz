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
    @Inject(method = "getFov", at = @At("RETURN"), cancellable = true)
    private void onGetFov(CallbackInfoReturnable<Double> cir) {
        double zoom = ZoomState.getZoomMultiplier();
        
        // Csak akkor módosítjuk a visszatérési értéket, ha van aktív zoom
        if (zoom > 1.0) {
            double originalFov = cir.getReturnValue();
            cir.setReturnValue(originalFov / zoom);
        }
    }

    /**
     * A kliens frissítési ciklusában (tick) számoljuk ki az aktuális zoom mértékét.
     * Így elkerüljük a getFov-on belüli végtelen ciklusokat és felesleges számításokat.
     */
    @Inject(method = "tick", at = @At("HEAD"))
    private void onTick(CallbackInfo ci) {
        boolean isZooming = OptimobileClient.zoomKey != null && OptimobileClient.zoomKey.isPressed();
        
        double target = isZooming ? TARGET_ZOOM_LEVEL : 1.0;

        // Lineáris interpoláció (lerp) a sima mozgáshoz
        currentZoom = MathHelper.lerp(
                ZOOM_SMOOTHING,
                currentZoom,
                target
        );

        // Ha nagyon közel vagyunk az 1.0-hoz vagy a célhoz, kerekítsük le, 
        // hogy ne számoljon a gép feleslegesen mikroszkopikus törtszámokat.
        if (Math.abs(currentZoom - 1.0) < 0.001) {
            currentZoom = 1.0;
        }

        // Frissítjük a központi állapotot, amit az OptiPlayerMixin is lát
        ZoomState.setZoomMultiplier(currentZoom);
    }
}
