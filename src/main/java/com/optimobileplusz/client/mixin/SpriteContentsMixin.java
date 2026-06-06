package com.optimobileplusz.client.mixin;

import com.optimobileplusz.core.OptiCore;
import com.optimobileplusz.module.AnimationThrottle;
import com.optimobileplusz.module.FrameBudgetManager;
import net.minecraft.class_1059;
import net.minecraft.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_1059.class)
public class SpriteContentsMixin {

    // A tickAnimatedSprites()V felelős az összes textúra animáció (víz, láva, stb.) léptetéséért.
    // Ez a metódus fix és publikus az 1.21.1-ben, így a Loom tökéletesen le tudja remapelni!
    @Inject(method = "tickAnimatedSprites()V", at = @At("HEAD"), cancellable = true)
    private void onTickAnimatedSprites(CallbackInfo ci) {
        Minecraft client = Minecraft.method_1551();
        
        if (client == null || client.field_1724 == null || client.field_1687 == null) {
            return;
        }

        if (AnimationThrottle.shouldSkipAnimatedSprites(OptiCore.getState()) || FrameBudgetManager.shouldStrongThrottleEffects()) {
            ci.cancel();
        }
    }
}