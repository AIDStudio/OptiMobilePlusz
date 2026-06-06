package com.optimobileplusz.client.mixin;

import com.optimobileplusz.core.OptiCore;
import com.optimobileplusz.module.AnimationThrottle;
import com.optimobileplusz.module.FrameBudgetManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.SpriteContents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SpriteContents.class)
public class SpriteContentsMixin {

    /**
     * Az animált sprite-ok frissítésének (tick) injektálása.
     * A Mojang mappingben a metódus neve: tick (vagy ehhez hasonló a SpriteContents-en belül).
     */
    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void onTickAnimatedSprites(CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        
        // Biztonsági ellenőrzés: csak akkor futtatjuk, ha a kliens és a világ létezik
        if (client == null || client.player == null || client.world == null) {
            return;
        }

        // Ha a korlátozások aktívak, kihagyjuk az animációt
        if (AnimationThrottle.shouldSkipAnimatedSprites(OptiCore.getState()) || FrameBudgetManager.shouldStrongThrottleEffects()) {
            ci.cancel();
        }
    }
}
