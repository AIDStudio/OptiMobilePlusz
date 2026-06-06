package com.optimobileplusz.client.mixin;

import com.optimobileplusz.module.FrameBudgetManager;
import net.minecraft.client.render.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WorldRenderer.class)
public class LightUpdateMixin {

    /**
     * A hasUpdates metódus injectálása a WorldRenderer-be.
     * Ez a metódus a Mojang mappingben is "hasUpdates" néven érhető el.
     */
    @Inject(method = "hasUpdates", at = @At("HEAD"), cancellable = true)
    private void limitLightUpdates(CallbackInfoReturnable<Boolean> cir) {
        // Ha a keretköltségvetés (FrameBudget) indokolja, 
        // a játékot "megtévesztjük", hogy nincs frissítés,
        // így csökkentve a processzorterhelést.
        if (FrameBudgetManager.shouldStrongThrottleEffects() || Math.random() > 0.5) {
            cir.setReturnValue(false);
        }
    }
}
