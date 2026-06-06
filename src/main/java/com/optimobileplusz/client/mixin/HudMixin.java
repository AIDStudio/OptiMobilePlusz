package com.optimobileplusz.client.mixin;

import com.optimobileplusz.config.OptiMobileConfig;
import com.optimobileplusz.module.HudOverlayManager;
import net.minecraft.Minecraft;
import net.minecraft.class_329;
import net.minecraft.class_332;
import net.minecraft.class_9779;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_329.class)
public class HudMixin {

    @Inject(method = "render", at = @At("TAIL"))
    private void onRender(class_332 context, class_9779 tickCounter, CallbackInfo ci) {
        Minecraft client = Minecraft.method_1551();

        if (client != null && client.field_1687 != null && OptiMobileConfig.showFps) {
            if (!client.method_53526().method_53536()) {
                
                int fps = client.method_47599();
                if (fps > 999) fps = 999;

                int screenWidth = context.method_51421();
                int totalContentWidth = 38; 
                int startX = (screenWidth / 2) - (totalContentWidth / 2);
                int y = 10;

                // Meghatározzuk a színt az FPS függvényében
                int fpsColor;
                if (fps >= 100) {
                    fpsColor = 0xFF00FFCC; // Cián / Szuper FPS
                } else if (fps >= 60) {
                    fpsColor = 0xFF00FF00; // Zöld / Jó FPS
                } else if (fps >= 30) {
                    fpsColor = 0xFFFFFF00; // Sárga / Közepes
                } else {
                    fpsColor = 0xFFFF0000; // Piros / Lag
                }

                // Számok kirajzolása a dinamikus színnel
                drawPixelNum(context, fps / 100, startX, y, fpsColor);           
                drawPixelNum(context, (fps / 10) % 10, startX + 8, y, fpsColor);   
                drawPixelNum(context, fps % 10, startX + 16, y, fpsColor);        
                
                // Az "FPS" felirat marad az eredeti elegáns narancssárga
                drawCharFPS(context, startX + 26, y);
            }
        }
    }

    // Frissített rajzoló, ami már paraméterként kapja a színt (int color)
    private void drawPixelNum(class_332 context, int num, int x, int y, int color) {
        int[] digits = {
            0x7b6f, // 0
            0x2492, // 1
            0x73e7, // 2
            0x73cf, // 3
            0x5bc9, // 4
            0x79cf, // 5
            0x79ef, // 6
            0x7249, // 7
            0x7bef, // 8
            0x7bcf  // 9
        };

        if (num < 0 || num > 9) return;
        int mask = digits[num];

        for (int i = 0; i < 15; i++) {
            if ((mask & (1 << (14 - i))) != 0) {
                int px = x + (i % 3) * 2;
                int py = y + (i / 3) * 2;
                context.method_25294(px, py, px + 2, py + 2, color);
            }
        }
    }

    private void drawCharFPS(class_332 context, int x, int y) {
        int c = 0xFFFFAA00; // Narancssárga

        // --- 'F' BETŰ ---
        context.method_25294(x, y, x + 2, y + 10, c);         
        context.method_25294(x + 2, y, x + 6, y + 2, c);     
        context.method_25294(x + 2, y + 4, x + 5, y + 6, c); 

        // --- 'P' BETŰ ---
        int px = x + 6;
        context.method_25294(px, y, px + 2, y + 10, c);       
        context.method_25294(px + 2, y, px + 6, y + 2, c);   
        context.method_25294(px + 2, y + 4, px + 6, y + 6, c); 
        context.method_25294(px + 4, y + 2, px + 6, y + 4, c); 

        // --- 'S' BETŰ ---
        int sx = x + 12;
        context.method_25294(sx, y, sx + 6, y + 2, c);       
        context.method_25294(sx, y + 2, sx + 2, y + 4, c);   
        context.method_25294(sx, y + 4, sx + 6, y + 6, c);   
        context.method_25294(sx + 4, y + 6, sx + 6, y + 8, c); 
        context.method_25294(sx, y + 8, sx + 6, y + 10, c);  
    }
}