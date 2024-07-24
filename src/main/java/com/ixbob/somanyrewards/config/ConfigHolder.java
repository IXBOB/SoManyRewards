package com.ixbob.somanyrewards.config;

public class ConfigHolder {

    private static ConfigHolder instance;

    public static ConfigHolder getInstance() {
        if (instance == null) {
            instance = new ConfigHolder();
        }
        return instance;
    }

    public void loadData() {
        ConfigSubHolderBasicGameTime.getInstance().loadData();
    }
}
