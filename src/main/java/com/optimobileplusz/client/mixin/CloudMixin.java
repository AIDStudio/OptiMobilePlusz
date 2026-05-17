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
    @Inject(method = "getCloudRenderModeValue", at = @At("HEAD"), cancellable = true)
    private void checkCloudConfig(CallbackInfoReturnable<CloudRenderMode> cir) {
        if (OptiMobileConfig.fastClouds) {
            cir.setReturnValue(CloudRenderMode.FAST);
        }
    }
}
