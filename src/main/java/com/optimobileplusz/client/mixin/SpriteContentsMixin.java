package com.optimobileplusz.client.mixin;

import com.optimobileplusz.core.OptiCore;
import com.optimobileplusz.module.AnimationThrottle;
import com.optimobileplusz.module.FrameBudgetManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.texture.SpriteAtlasTexture;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SpriteAtlasTexture.class)
public class SpriteContentsMixin {

    // A tickAnimatedSprites()V felelős az összes textúra animáció (víz, láva, stb.) léptetéséért.
    // Ez a metódus fix és publikus az 1.21.1-ben, így a Loom tökéletesen le tudja remapelni!
    @Inject(method = "tickAnimatedSprites()V", at = @At("HEAD"), cancellable = true)
    private void onTickAnimatedSprites(CallbackInfo ci) {
        Minecraft client = Minecraft.getInstance();
        
        if (client == null || client.player == null || client.world == null) {
            return;
        }

        if (AnimationThrottle.shouldSkipAnimatedSprites(OptiCore.getState()) || FrameBudgetManager.shouldStrongThrottleEffects()) {
            ci.cancel();
        }
    }
}