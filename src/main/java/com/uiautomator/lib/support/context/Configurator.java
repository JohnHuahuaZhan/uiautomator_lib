package com.uiautomator.lib.support.context;

public class Configurator {
    private static final Configurator ourInstance = new Configurator();

    public static Configurator getInstance() {
        return ourInstance;
    }

    private Configurator() {
    }

    public static final long defaultPollingEvery = 500;
}
