package com.optimobileplusz.client.mixin;

import net.minecraft.client.MinecraftClient;
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
        MinecraftClient client = MinecraftClient.getInstance();
        
        if (client == null || client.player == null || client.world == null) {
            return;
        }

        // Lekérjük a világidőt tickekben
        long worldTime = client.world.getTime();
        
        // Minden második tickben teljesen letiltjuk az animációk frissítését.
        // Ez azonnal lefelezi a MobileGlues és a Poco GPU-ja közötti felesleges adatforgalmat, 
        // hatalmas FPS löketet adva anélkül, hogy bármi elromlana!
        if (worldTime % 2 == 0) {
            ci.cancel();
        }
    }
}