package com.optimobileplusz.client.gui;

import com.optimobileplusz.config.OptiMobileConfig;
import com.optimobileplusz.core.OptiCore;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.DrawContext;
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
        int y = 40;

        ButtonWidget stateButton = ButtonWidget.builder(stateLabel(), button -> {})
            .dimensions(centerX - 100, y, 200, 20)
            .build();
        stateButton.active = false;
        this.addDrawableChild(stateButton);

        y += 28;
        ButtonWidget generalLabel = ButtonWidget.builder(Text.translatable("gui.optimobileplusz.section.general"), button -> {})
            .dimensions(centerX - 100, y, 200, 20)
            .build();
        generalLabel.active = false;
        this.addDrawableChild(generalLabel);

        y += 24;
        this.addDrawableChild(ButtonWidget.builder(label("gui.optimobileplusz.fastClouds", OptiMobileConfig.fastClouds), button -> {
                OptiMobileConfig.fastClouds = !OptiMobileConfig.fastClouds;
                OptiMobileConfig.save();
                button.setMessage(label("gui.optimobileplusz.fastClouds", OptiMobileConfig.fastClouds));
            })
            .dimensions(centerX - 100, y, 200, 20)
            .build());

        y += 24;
        this.addDrawableChild(ButtonWidget.builder(label("gui.optimobileplusz.particles", OptiMobileConfig.particlesEnabled), button -> {
                OptiMobileConfig.particlesEnabled = !OptiMobileConfig.particlesEnabled;
                OptiMobileConfig.save();
                button.setMessage(label("gui.optimobileplusz.particles", OptiMobileConfig.particlesEnabled));
            })
            .dimensions(centerX - 100, y, 200, 20)
            .build());

        y += 28;
        ButtonWidget advancedLabel = ButtonWidget.builder(Text.translatable("gui.optimobileplusz.section.advanced"), button -> {})
            .dimensions(centerX - 100, y, 200, 20)
            .build();
        advancedLabel.active = false;
        this.addDrawableChild(advancedLabel);

        y += 24;
        this.addDrawableChild(ButtonWidget.builder(label("gui.optimobileplusz.frameBudget", OptiMobileConfig.frameBudgetEnabled), button -> {
                OptiMobileConfig.frameBudgetEnabled = !OptiMobileConfig.frameBudgetEnabled;
                OptiMobileConfig.save();
                button.setMessage(label("gui.optimobileplusz.frameBudget", OptiMobileConfig.frameBudgetEnabled));
            })
            .dimensions(centerX - 100, y, 200, 20)
            .build());

        y += 24;
        this.addDrawableChild(ButtonWidget.builder(label("gui.optimobileplusz.entityCulling", OptiMobileConfig.smartEntityCulling), button -> {
                OptiMobileConfig.smartEntityCulling = !OptiMobileConfig.smartEntityCulling;
                OptiMobileConfig.save();
                button.setMessage(label("gui.optimobileplusz.entityCulling", OptiMobileConfig.smartEntityCulling));
            })
            .dimensions(centerX - 100, y, 200, 20)
            .build());

        y += 24;
        this.addDrawableChild(ButtonWidget.builder(label("gui.optimobileplusz.animationThrottle", OptiMobileConfig.throttledAnimations), button -> {
                OptiMobileConfig.throttledAnimations = !OptiMobileConfig.throttledAnimations;
                OptiMobileConfig.save();
                button.setMessage(label("gui.optimobileplusz.animationThrottle", OptiMobileConfig.throttledAnimations));
            })
            .dimensions(centerX - 100, y, 200, 20)
            .build());

        y += 24;
        this.addDrawableChild(ButtonWidget.builder(label("gui.optimobileplusz.fpsCounter", OptiMobileConfig.showFps), button -> {
                OptiMobileConfig.showFps = !OptiMobileConfig.showFps;
                OptiMobileConfig.save();
                button.setMessage(label("gui.optimobileplusz.fpsCounter", OptiMobileConfig.showFps));
            })
            .dimensions(centerX - 100, y, 200, 20)
            .build());

        y += 28;
        this.addDrawableChild(ButtonWidget.builder(Text.translatable("gui.optimobileplusz.reset"), button -> {
                OptiMobileConfig.fastClouds = true;
                OptiMobileConfig.particlesEnabled = false;
                OptiMobileConfig.showFps = true;
                OptiMobileConfig.frameBudgetEnabled = true;
                OptiMobileConfig.smartEntityCulling = true;
                OptiMobileConfig.throttledAnimations = true;
                OptiMobileConfig.save();
                this.client.setScreen(new OptiMobileConfigScreen(this.parent));
            })
            .dimensions(centerX - 100, y, 200, 20)
            .build());

        this.addDrawableChild(ButtonWidget.builder(Text.translatable("gui.optimobileplusz.done"), button -> {
            this.client.setScreen(this.parent);
        }).dimensions(centerX - 100, this.height - 40, 200, 20)
        .build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderInGameBackground(context);
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, 16, 0xFFFFFF);
        context.drawCenteredTextWithShadow(this.textRenderer, Text.translatable("gui.optimobileplusz.description"), this.width / 2, 30, 0xAAAAFF);
        super.render(context, mouseX, mouseY, delta);
    }
}
