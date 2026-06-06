package com.optimobileplusz.client.mixin;

import com.optimobileplusz.config.OptiMobileConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class HudMixin {

    @Inject(method = "render", at = @At("TAIL"))
    private void onRender(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();

        // Ellenőrizzük, hogy a kliens és a világ betöltődött-e
        if (client != null && client.world != null && OptiMobileConfig.showFps) {
            
            // Az FPS lekérése a modern metódussal
            int fps = client.getCurrentFps();
            if (fps > 999) fps = 999;

            // Képernyő szélesség lekérése a DrawContext-ből
            int screenWidth = context.getScaledWindowWidth();
            int totalContentWidth = 38; 
            int startX = (screenWidth / 2) - (totalContentWidth / 2);
            int y = 10;

            // Ide jöhet a további rajzolási logikád a context.fill() vagy drawText() használatával
        }
    }
}
