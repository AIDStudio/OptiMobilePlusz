package com.optimobileplusz.core;

public class Log {

    private static final String PREFIX = "[OptiMobile+] ";

    public static void info(String message) {

        System.out.println(PREFIX + "[INFO] " + message);
    }

    public static void warn(String message) {

        System.out.println(PREFIX + "[WARN] " + message);
    }

    public static void error(String message) {

        System.err.println(PREFIX + "[ERROR] " + message);
    }
}