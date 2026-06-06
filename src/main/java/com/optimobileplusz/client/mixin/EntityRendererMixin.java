package com.optimobileplusz.client.mixin;

import com.optimobileplusz.module.EntityCulling;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityRenderer.class)
public abstract class EntityRendererMixin<T extends Entity> {

    @Inject(method = "shouldRender", at = @At("HEAD"), cancellable = true)
    private void onShouldRender(T entity, Frustum frustum, double x, double y, double z, CallbackInfoReturnable<Boolean> cir) {
        MinecraftClient client = MinecraftClient.getInstance();
        
        // 1. Ha az entitás a saját karakterünk, azt mindenképpen rajzoljuk ki
        if (client == null || entity == client.player) {
            return; 
        }

        // 2. Egyedi entitás-eltávolítás logikája
        if (!EntityCulling.shouldRender(entity)) {
            cir.setReturnValue(false);
            return;
        }

        // 3. Frustum ellenőrzés: ha látható, engedélyezzük a renderelést
        // A method_23093 -> isVisible helyett használjuk a hivatalosisVisible metódust
        if (frustum != null && frustum.isVisible(entity.getBoundingBox())) {
            return; 
        }
    }
}
