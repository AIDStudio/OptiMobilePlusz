package com.optimobileplusz.client.gui;

import com.optimobileplusz.config.OptiMobileConfig;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

public class OptiMobileConfigScreen extends Screen {
    private final Screen parent;

    public OptiMobileConfigScreen(Screen parent) {
        super(Text.literal("OptiMobilePlusz Beállítások"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        int centerX = this.width / 2;

        // 1. Felhő kapcsoló gomb
        this.addDrawableChild(ButtonWidget.builder(
            Text.literal("Gyors felhők: " + (OptiMobileConfig.fastClouds ? "BE" : "KI")), 
            button -> {
                OptiMobileConfig.fastClouds = !OptiMobileConfig.fastClouds;
                OptiMobileConfig.save();
                button.setMessage(Text.literal("Gyors felhők: " + (OptiMobileConfig.fastClouds ? "BE" : "KI")));
            })
            .dimensions(centerX - 100, 60, 200, 20)
            .build());

        // 2. Részecske kapcsoló gomb
        this.addDrawableChild(ButtonWidget.builder(
            Text.literal("Részecskék: " + (OptiMobileConfig.particlesEnabled ? "BE" : "KI")), 
            button -> {
                OptiMobileConfig.particlesEnabled = !OptiMobileConfig.particlesEnabled;
                OptiMobileConfig.save();
                button.setMessage(Text.literal("Részecskék: " + (OptiMobileConfig.particlesEnabled ? "BE" : "KI")));
            })
            .dimensions(centerX - 100, 90, 200, 20)
            .build());
            
        this.addDrawableChild(ButtonWidget.builder(
    Text.literal("FPS számláló: " + (OptiMobileConfig.showFps ? "BE" : "KI")), 
    button -> {
        OptiMobileConfig.showFps = !OptiMobileConfig.showFps;
        OptiMobileConfig.save();
        button.setMessage(Text.literal("FPS számláló: " + (OptiMobileConfig.showFps ? "BE" : "KI")));
    })
    .dimensions(centerX - 100, 120, 200, 20)
    .build());


        // Kész (Vissza) gomb
        this.addDrawableChild(ButtonWidget.builder(Text.literal("Kész"), button -> {
            this.client.setScreen(this.parent);
        }).dimensions(centerX - 100, this.height - 40, 200, 20)
        .build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // JAVÍTÁS: A sima renderBackground helyett renderInGameBackground-ot használunk.
        // Ez megoldja a NullPointerException-t a Zalith Launcher / MobileGlues renderer alatt.
        this.renderInGameBackground(context);
        
        // Cím kirajzolása (árnyékkal)
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, 20, 0xFFFFFF);
        
        // Gombok és egyebek kirajzolása
        super.render(context, mouseX, mouseY, delta);
    }
}
