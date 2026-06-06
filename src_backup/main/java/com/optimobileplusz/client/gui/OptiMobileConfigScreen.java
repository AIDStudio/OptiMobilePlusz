package com.optimobileplusz.client.gui;

import com.optimobileplusz.config.OptiMobileConfig;
import com.optimobileplusz.core.OptiCore;
import net.minecraft.class_2561;
import net.minecraft.class_332;
import net.minecraft.class_4185;
import net.minecraft.class_437;

public class OptiMobileConfigScreen extends class_437 {
    private final class_437 parent;

    public OptiMobileConfigScreen(class_437 parent) {
        super(class_2561.method_43471("gui.optimobileplusz.title"));
        this.parent = parent;
    }

    private class_2561 label(String key, boolean value) {
        return class_2561.method_43469(key, class_2561.method_43471(value ? "gui.optimobileplusz.enabled" : "gui.optimobileplusz.disabled"));
    }

    private class_2561 stateLabel() {
        return class_2561.method_43469("gui.optimobileplusz.currentMode", class_2561.method_43471("gui.optimobileplusz.state." + OptiCore.getState().name()));
    }

    @Override
    protected void method_25426() {
        int centerX = this.field_22789 / 2;
        int leftX = centerX - 110;
        int rightX = centerX + 10;
        int y = 40;

        class_4185 stateButton = class_4185.method_46430(stateLabel(), button -> {})
            .method_46434(centerX - 100, y, 200, 20)
            .method_46431();
        stateButton.field_22763 = false;
        this.method_37063(stateButton);

        y += 28;
        class_4185 generalLabel = class_4185.method_46430(class_2561.method_43471("gui.optimobileplusz.section.general"), button -> {})
            .method_46434(centerX - 100, y, 200, 20)
            .method_46431();
        generalLabel.field_22763 = false;
        this.method_37063(generalLabel);

        y += 24;
        this.method_37063(class_4185.method_46430(label("gui.optimobileplusz.fastClouds", OptiMobileConfig.fastClouds), button -> {
                OptiMobileConfig.fastClouds = !OptiMobileConfig.fastClouds;
                OptiMobileConfig.save();
                button.method_25355(label("gui.optimobileplusz.fastClouds", OptiMobileConfig.fastClouds));
            })
            .method_46434(centerX - 100, y, 200, 20)
            .method_46431());

        y += 24;
        this.method_37063(class_4185.method_46430(label("gui.optimobileplusz.particles", OptiMobileConfig.particlesEnabled), button -> {
                OptiMobileConfig.particlesEnabled = !OptiMobileConfig.particlesEnabled;
                OptiMobileConfig.save();
                button.method_25355(label("gui.optimobileplusz.particles", OptiMobileConfig.particlesEnabled));
            })
            .method_46434(centerX - 100, y, 200, 20)
            .method_46431());

        y += 28;
        class_4185 advancedLabel = class_4185.method_46430(class_2561.method_43471("gui.optimobileplusz.section.advanced"), button -> {})
            .method_46434(centerX - 100, y, 200, 20)
            .method_46431();
        advancedLabel.field_22763 = false;
        this.method_37063(advancedLabel);

        y += 24;
        this.method_37063(class_4185.method_46430(label("gui.optimobileplusz.frameBudget", OptiMobileConfig.frameBudgetEnabled), button -> {
                OptiMobileConfig.frameBudgetEnabled = !OptiMobileConfig.frameBudgetEnabled;
                OptiMobileConfig.save();
                button.method_25355(label("gui.optimobileplusz.frameBudget", OptiMobileConfig.frameBudgetEnabled));
            })
            .method_46434(leftX, y, 100, 20)
            .method_46431());
        this.method_37063(class_4185.method_46430(label("gui.optimobileplusz.entityCulling", OptiMobileConfig.smartEntityCulling), button -> {
                OptiMobileConfig.smartEntityCulling = !OptiMobileConfig.smartEntityCulling;
                OptiMobileConfig.save();
                button.method_25355(label("gui.optimobileplusz.entityCulling", OptiMobileConfig.smartEntityCulling));
            })
            .method_46434(rightX, y, 100, 20)
            .method_46431());

        y += 24;
        this.method_37063(class_4185.method_46430(label("gui.optimobileplusz.animationThrottle", OptiMobileConfig.throttledAnimations), button -> {
                OptiMobileConfig.throttledAnimations = !OptiMobileConfig.throttledAnimations;
                OptiMobileConfig.save();
                button.method_25355(label("gui.optimobileplusz.animationThrottle", OptiMobileConfig.throttledAnimations));
            })
            .method_46434(leftX, y, 100, 20)
            .method_46431());
        this.method_37063(class_4185.method_46430(label("gui.optimobileplusz.fpsCounter", OptiMobileConfig.showFps), button -> {
                OptiMobileConfig.showFps = !OptiMobileConfig.showFps;
                OptiMobileConfig.save();
                button.method_25355(label("gui.optimobileplusz.fpsCounter", OptiMobileConfig.showFps));
            })
            .method_46434(rightX, y, 100, 20)
            .method_46431());

        y += 24;
        this.method_37063(class_4185.method_46430(label("gui.optimobileplusz.adaptiveChunkLoading", OptiMobileConfig.adaptiveChunkLoadingEnabled), button -> {
                OptiMobileConfig.adaptiveChunkLoadingEnabled = !OptiMobileConfig.adaptiveChunkLoadingEnabled;
                OptiMobileConfig.save();
                button.method_25355(label("gui.optimobileplusz.adaptiveChunkLoading", OptiMobileConfig.adaptiveChunkLoadingEnabled));
            })
            .method_46434(leftX, y, 100, 20)
            .method_46431());
        this.method_37063(class_4185.method_46430(label("gui.optimobileplusz.lod", OptiMobileConfig.lodEnabled), button -> {
                OptiMobileConfig.lodEnabled = !OptiMobileConfig.lodEnabled;
                OptiMobileConfig.save();
                button.method_25355(label("gui.optimobileplusz.lod", OptiMobileConfig.lodEnabled));
            })
            .method_46434(rightX, y, 100, 20)
            .method_46431());

        y += 24;
        this.method_37063(class_4185.method_46430(label("gui.optimobileplusz.textureStreaming", OptiMobileConfig.asyncTextureStreamingEnabled), button -> {
                OptiMobileConfig.asyncTextureStreamingEnabled = !OptiMobileConfig.asyncTextureStreamingEnabled;
                OptiMobileConfig.save();
                button.method_25355(label("gui.optimobileplusz.textureStreaming", OptiMobileConfig.asyncTextureStreamingEnabled));
            })
            .method_46434(leftX, y, 100, 20)
            .method_46431());
        this.method_37063(class_4185.method_46430(label("gui.optimobileplusz.sceneSimplifier", OptiMobileConfig.sceneSimplifierEnabled), button -> {
                OptiMobileConfig.sceneSimplifierEnabled = !OptiMobileConfig.sceneSimplifierEnabled;
                OptiMobileConfig.save();
                button.method_25355(label("gui.optimobileplusz.sceneSimplifier", OptiMobileConfig.sceneSimplifierEnabled));
            })
            .method_46434(rightX, y, 100, 20)
            .method_46431());

        y += 24;
        this.method_37063(class_4185.method_46430(label("gui.optimobileplusz.gpuProfile", OptiMobileConfig.gpuProfileEnabled), button -> {
                OptiMobileConfig.gpuProfileEnabled = !OptiMobileConfig.gpuProfileEnabled;
                OptiMobileConfig.save();
                button.method_25355(label("gui.optimobileplusz.gpuProfile", OptiMobileConfig.gpuProfileEnabled));
            })
            .method_46434(leftX, y, 100, 20)
            .method_46431());
        this.method_37063(class_4185.method_46430(label("gui.optimobileplusz.backgroundTickThrottle", OptiMobileConfig.backgroundTickThrottleEnabled), button -> {
                OptiMobileConfig.backgroundTickThrottleEnabled = !OptiMobileConfig.backgroundTickThrottleEnabled;
                OptiMobileConfig.save();
                button.method_25355(label("gui.optimobileplusz.backgroundTickThrottle", OptiMobileConfig.backgroundTickThrottleEnabled));
            })
            .method_46434(rightX, y, 100, 20)
            .method_46431());

        y += 24;
        this.method_37063(class_4185.method_46430(label("gui.optimobileplusz.soundBudget", OptiMobileConfig.soundBudgetEnabled), button -> {
                OptiMobileConfig.soundBudgetEnabled = !OptiMobileConfig.soundBudgetEnabled;
                OptiMobileConfig.save();
                button.method_25355(label("gui.optimobileplusz.soundBudget", OptiMobileConfig.soundBudgetEnabled));
            })
            .method_46434(leftX, y, 100, 20)
            .method_46431());
        this.method_37063(class_4185.method_46430(label("gui.optimobileplusz.telemetry", OptiMobileConfig.telemetryEnabled), button -> {
                OptiMobileConfig.telemetryEnabled = !OptiMobileConfig.telemetryEnabled;
                OptiMobileConfig.save();
                button.method_25355(label("gui.optimobileplusz.telemetry", OptiMobileConfig.telemetryEnabled));
            })
            .method_46434(rightX, y, 100, 20)
            .method_46431());

        y += 24;
        this.method_37063(class_4185.method_46430(label("gui.optimobileplusz.overlay", OptiMobileConfig.showDetailedOverlay), button -> {
                OptiMobileConfig.showDetailedOverlay = !OptiMobileConfig.showDetailedOverlay;
                OptiMobileConfig.save();
                button.method_25355(label("gui.optimobileplusz.overlay", OptiMobileConfig.showDetailedOverlay));
            })
            .method_46434(leftX, y, 100, 20)
            .method_46431());

        y += 28;
        this.method_37063(class_4185.method_46430(class_2561.method_43471("gui.optimobileplusz.reset"), button -> {
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
                this.field_22787.method_1507(new OptiMobileConfigScreen(this.parent));
            })
            .method_46434(centerX - 100, y, 200, 20)
            .method_46431());

        this.method_37063(class_4185.method_46430(class_2561.method_43471("gui.optimobileplusz.done"), button -> {
            this.field_22787.method_1507(this.parent);
        }).method_46434(centerX - 100, this.field_22790 - 40, 200, 20)
        .method_46431());
    }

    @Override
    public void method_25394(class_332 context, int mouseX, int mouseY, float delta) {
        this.method_52752(context);
        context.method_27534(this.field_22793, this.field_22785, this.field_22789 / 2, 16, 0xFFFFFF);
        context.method_27534(this.field_22793, class_2561.method_43471("gui.optimobileplusz.description"), this.field_22789 / 2, 30, 0xAAAAFF);
        super.method_25394(context, mouseX, mouseY, delta);
    }
}
