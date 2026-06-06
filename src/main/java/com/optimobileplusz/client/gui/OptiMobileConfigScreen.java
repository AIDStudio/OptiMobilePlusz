package com.optimobileplusz.client.gui;

import com.optimobileplusz.config.OptiMobileConfig;
import com.optimobileplusz.core.OptiCore;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class OptiMobileConfigScreen extends Screen {
    private final Screen parent;

    public OptiMobileConfigScreen(Screen parent) {
        super(Text.translatable("gui.optimobileplusz.title"));
        this.parent = parent;
    }

    private Text label(String key, boolean value) {
        return Text.translatable(key, Text.translatable(value ? "gui.optimobileplusz.enabled" : "gui.optimobileplusz.disabled"));
    }

    private Text stateLabel() {
        return Text.translatable("gui.optimobileplusz.currentMode", Text.translatable("gui.optimobileplusz.state." + OptiCore.getState().name()));
    }

    @Override
    protected void init() {
        int centerX = this.width / 2;
        
        // "Kész" gomb hozzáadása a modern widget rendszerrel
        this.addDrawableChild(ButtonWidget.builder(Text.translatable("gui.optimobileplusz.done"), button -> {
            this.client.setScreen(this.parent);
        }).dimensions(centerX - 100, this.height - 40, 200, 20).build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
    }
}
