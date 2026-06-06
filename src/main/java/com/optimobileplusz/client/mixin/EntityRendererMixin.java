package com.optimobileplusz.client.mixin;

import com.optimobileplusz.module.EntityCulling;
import net.minecraft.Entity;
import net.minecraft.Minecraft;
import net.minecraft.class_4604;
import net.minecraft.class_897;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_897.class)
public abstract class EntityRendererMixin<T extends Entity> {

    @Inject(method = "shouldRender", at = @At("HEAD"), cancellable = true)
    private void onShouldRender(T entity, class_4604 frustum, double x, double y, double z, CallbackInfoReturnable<Boolean> cir) {
        Minecraft client = Minecraft.method_1551();
        
        // 1. Ha az entitás a saját karakterünk, azt mindenképpen rajzoljuk ki (F5 mód miatt)
        if (client == null || entity == client.field_1724) {
            return; 
        }

        if (!EntityCulling.shouldRender(entity)) {
            cir.setReturnValue(false);
            return;
        }

        // 2. Ha az entitás benne van a látómezőben (Frustum), akkor hagyni kell a normál renderelést
        if (frustum != null && frustum.method_23093(entity.method_5829())) {
            return; 
        }

        // 3. Ha eddig eljutott a kód, az azt jelenti, hogy a lény a hátunk mögött van 
        // vagy takarásban. Ekkor leállítjuk a renderelést az FPS javítása érdekében.
        cir.setReturnValue(false);
    }
}