package com.optimobileplusz.client.mixin;

import com.optimobileplusz.config.OptiMobileConfig;
import net.minecraft.client.CloudStatus;
import net.minecraft.client.Options;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Options.class)
public class CloudMixin {
    @Inject(method = "getCloudRenderModeValue", at = @At("HEAD"), cancellable = true)
    private void checkCloudConfig(CallbackInfoReturnable<CloudStatus> cir) {
        if (OptiMobileConfig.fastClouds) {
            cir.setReturnValue(CloudStatus.FAST);
        }
    }
}
