package com.optimobileplusz.client;

import net.minecraft.class_3532;

public class ZoomState {

    private static double zoomMultiplier = 1.0;
    private static double currentZoom = 1.0;
    private static final double TARGET_ZOOM_LEVEL = 4.0;
    private static final double ZOOM_SMOOTHING = 0.15;

    public static double getZoomMultiplier() {
        return zoomMultiplier;
    }

    public static void setZoomMultiplier(double zoom) {
        zoomMultiplier = zoom;
    }

    public static void tickZoom(boolean zooming) {
        double target = zooming ? TARGET_ZOOM_LEVEL : 1.0;
        currentZoom = class_3532.method_16436(ZOOM_SMOOTHING, currentZoom, target);

        if (Math.abs(currentZoom - 1.0) < 0.001) {
            currentZoom = 1.0;
        }

        setZoomMultiplier(currentZoom);
    }
}