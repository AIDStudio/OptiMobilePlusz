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
        int leftX = centerX - 110;
        int rightX = centerX + 10;
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
            .dimensions(leftX, y, 100, 20)
            .build());
        this.addDrawableChild(ButtonWidget.builder(label("gui.optimobileplusz.entityCulling", OptiMobileConfig.smartEntityCulling), button -> {
                OptiMobileConfig.smartEntityCulling = !OptiMobileConfig.smartEntityCulling;
                OptiMobileConfig.save();
                button.setMessage(label("gui.optimobileplusz.entityCulling", OptiMobileConfig.smartEntityCulling));
            })
            .dimensions(rightX, y, 100, 20)
            .build());

        y += 24;
        this.addDrawableChild(ButtonWidget.builder(label("gui.optimobileplusz.animationThrottle", OptiMobileConfig.throttledAnimations), button -> {
                OptiMobileConfig.throttledAnimations = !OptiMobileConfig.throttledAnimations;
                OptiMobileConfig.save();
                button.setMessage(label("gui.optimobileplusz.animationThrottle", OptiMobileConfig.throttledAnimations));
            })
            .dimensions(leftX, y, 100, 20)
            .build());
        this.addDrawableChild(ButtonWidget.builder(label("gui.optimobileplusz.fpsCounter", OptiMobileConfig.showFps), button -> {
                OptiMobileConfig.showFps = !OptiMobileConfig.showFps;
                OptiMobileConfig.save();
                button.setMessage(label("gui.optimobileplusz.fpsCounter", OptiMobileConfig.showFps));
            })
            .dimensions(rightX, y, 100, 20)
            .build());

        y += 24;
        this.addDrawableChild(ButtonWidget.builder(label("gui.optimobileplusz.adaptiveChunkLoading", OptiMobileConfig.adaptiveChunkLoadingEnabled), button -> {
                OptiMobileConfig.adaptiveChunkLoadingEnabled = !OptiMobileConfig.adaptiveChunkLoadingEnabled;
                OptiMobileConfig.save();
                button.setMessage(label("gui.optimobileplusz.adaptiveChunkLoading", OptiMobileConfig.adaptiveChunkLoadingEnabled));
            })
            .dimensions(leftX, y, 100, 20)
            .build());
        this.addDrawableChild(ButtonWidget.builder(label("gui.optimobileplusz.lod", OptiMobileConfig.lodEnabled), button -> {
                OptiMobileConfig.lodEnabled = !OptiMobileConfig.lodEnabled;
                OptiMobileConfig.save();
                button.setMessage(label("gui.optimobileplusz.lod", OptiMobileConfig.lodEnabled));
            })
            .dimensions(rightX, y, 100, 20)
            .build());

        y += 24;
        this.addDrawableChild(ButtonWidget.builder(label("gui.optimobileplusz.textureStreaming", OptiMobileConfig.asyncTextureStreamingEnabled), button -> {
                OptiMobileConfig.asyncTextureStreamingEnabled = !OptiMobileConfig.asyncTextureStreamingEnabled;
                OptiMobileConfig.save();
                button.setMessage(label("gui.optimobileplusz.textureStreaming", OptiMobileConfig.asyncTextureStreamingEnabled));
            })
            .dimensions(leftX, y, 100, 20)
            .build());
        this.addDrawableChild(ButtonWidget.builder(label("gui.optimobileplusz.sceneSimplifier", OptiMobileConfig.sceneSimplifierEnabled), button -> {
                OptiMobileConfig.sceneSimplifierEnabled = !OptiMobileConfig.sceneSimplifierEnabled;
                OptiMobileConfig.save();
                button.setMessage(label("gui.optimobileplusz.sceneSimplifier", OptiMobileConfig.sceneSimplifierEnabled));
            })
            .dimensions(rightX, y, 100, 20)
            .build());

        y += 24;
        this.addDrawableChild(ButtonWidget.builder(label("gui.optimobileplusz.gpuProfile", OptiMobileConfig.gpuProfileEnabled), button -> {
                OptiMobileConfig.gpuProfileEnabled = !OptiMobileConfig.gpuProfileEnabled;
                OptiMobileConfig.save();
                button.setMessage(label("gui.optimobileplusz.gpuProfile", OptiMobileConfig.gpuProfileEnabled));
            })
            .dimensions(leftX, y, 100, 20)
            .build());
        this.addDrawableChild(ButtonWidget.builder(label("gui.optimobileplusz.backgroundTickThrottle", OptiMobileConfig.backgroundTickThrottleEnabled), button -> {
                OptiMobileConfig.backgroundTickThrottleEnabled = !OptiMobileConfig.backgroundTickThrottleEnabled;
                OptiMobileConfig.save();
                button.setMessage(label("gui.optimobileplusz.backgroundTickThrottle", OptiMobileConfig.backgroundTickThrottleEnabled));
            })
            .dimensions(rightX, y, 100, 20)
            .build());

        y += 24;
        this.addDrawableChild(ButtonWidget.builder(label("gui.optimobileplusz.soundBudget", OptiMobileConfig.soundBudgetEnabled), button -> {
                OptiMobileConfig.soundBudgetEnabled = !OptiMobileConfig.soundBudgetEnabled;
                OptiMobileConfig.save();
                button.setMessage(label("gui.optimobileplusz.soundBudget", OptiMobileConfig.soundBudgetEnabled));
            })
            .dimensions(leftX, y, 100, 20)
            .build());
        this.addDrawableChild(ButtonWidget.builder(label("gui.optimobileplusz.telemetry", OptiMobileConfig.telemetryEnabled), button -> {
                OptiMobileConfig.telemetryEnabled = !OptiMobileConfig.telemetryEnabled;
                OptiMobileConfig.save();
                button.setMessage(label("gui.optimobileplusz.telemetry", OptiMobileConfig.telemetryEnabled));
            })
            .dimensions(rightX, y, 100, 20)
            .build());

        y += 24;
        this.addDrawableChild(ButtonWidget.builder(label("gui.optimobileplusz.overlay", OptiMobileConfig.showDetailedOverlay), button -> {
                OptiMobileConfig.showDetailedOverlay = !OptiMobileConfig.showDetailedOverlay;
                OptiMobileConfig.save();
                button.setMessage(label("gui.optimobileplusz.overlay", OptiMobileConfig.showDetailedOverlay));
            })
            .dimensions(leftX, y, 100, 20)
            .build());

        y += 28;
        this.addDrawableChild(ButtonWidget.builder(Text.translatable("gui.optimobileplusz.reset"), button -> {
                OptiMobileConfig.fastClouds = true;
                OptiMobileConfig.particlesEnabled = false;
                OptiMobileConfig.showFps = true;
                OptiMobileConfig.frameBudgetEnabled = true;
                OptiMobileConfig.smartEntityCulling = true;
                OptiMobileConfig.throttledAnimations = true;
                OptiMobileConfig.adaptiveChunkLoadingEnabled = true;
                OptiMobileConfig.lodEnabled = true;
                OptiMobileConfig.asyncTextureStreamingEnabled = true;
                OptiMobileConfig.sceneSimplifierEnabled = false;
                OptiMobileConfig.gpuProfileEnabled = true;
                OptiMobileConfig.backgroundTickThrottleEnabled = true;
                OptiMobileConfig.soundBudgetEnabled = false;
                OptiMobileConfig.telemetryEnabled = false;
                OptiMobileConfig.showDetailedOverlay = false;
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
