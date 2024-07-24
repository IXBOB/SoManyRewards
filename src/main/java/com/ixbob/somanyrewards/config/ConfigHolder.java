package com.ixbob.somanyrewards.config;

public class ConfigHolder implements IConfigHolder {

    private static ConfigHolder instance;

    public static ConfigHolder getInstance() {
        if (instance == null) {
            instance = new ConfigHolder();
        }
        return instance;
    }

    @Override
    public void loadData() {
        ConfigSubHolderBasicGameTime.getInstance().loadData();
    }
}
