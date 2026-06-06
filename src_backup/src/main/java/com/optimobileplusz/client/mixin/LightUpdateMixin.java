package com.optimobileplusz.client.mixin;

import com.optimobileplusz.module.FrameBudgetManager;
import net.minecraft.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WorldRenderer.class)
public class LightUpdateMixin {
    @Inject(method = "hasUpdates", at = @At("HEAD"), cancellable = true)
    private void limitLightUpdates(CallbackInfoReturnable<Boolean> cir) {
        // Néha "hazudunk" a játéknak, hogy nincs frissítés,
        // így nem fog minden egyes nanoszekundumban újra számolni
        if (FrameBudgetManager.shouldStrongThrottleEffects() || Math.random() > 0.5) {
            cir.setReturnValue(false);
        }
    }
}
