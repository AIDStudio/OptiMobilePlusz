package com.optimobileplusz.client.mixin;

import com.optimobileplusz.config.OptiMobileConfig;
import net.minecraft.class_315;
import net.minecraft.class_4063;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_315.class)
public class CloudMixin {
    @Inject(method = "getCloudRenderModeValue", at = @At("HEAD"), cancellable = true)
    private void checkCloudConfig(CallbackInfoReturnable<class_4063> cir) {
        if (OptiMobileConfig.fastClouds) {
            cir.setReturnValue(class_4063.field_18163);
        }
    }
}
