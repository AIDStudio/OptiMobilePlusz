package com.optimobileplusz.client.mixin;

import com.optimobileplusz.config.OptiMobileConfig;
import net.minecraft.client.option.CloudRenderMode;
import net.minecraft.client.option.GameOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameOptions.class)
public class CloudMixin {

    /**
     * A getCloudRenderModeValue metódus injektálása a GameOptions osztályba.
     * Mojang mapping szerint a CloudRenderMode.FAST a megfelelője a régi field_18163-nak.
     */
    @Inject(method = "getCloudRenderModeValue", at = @At("HEAD"), cancellable = true)
    private void checkCloudConfig(CallbackInfoReturnable<CloudRenderMode> cir) {
        if (OptiMobileConfig.fastClouds) {
            // A FAST a hivatalos Mojang név a 'fast' felhő üzemmódra
            cir.setReturnValue(CloudRenderMode.FAST);
        }
    }
}
