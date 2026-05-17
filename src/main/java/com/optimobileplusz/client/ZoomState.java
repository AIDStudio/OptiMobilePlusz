package com.optimobileplusz.client;

public class ZoomState {

    private static double zoomMultiplier = 1.0;

    public static double getZoomMultiplier() {
        return zoomMultiplier;
    }

    public static void setZoomMultiplier(double zoom) {
        zoomMultiplier = zoom;
    }
}