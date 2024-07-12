package com.ixbob.somanyrewards.lang;

import com.ixbob.somanyrewards.SoManyRewards;

import java.util.Locale;

public enum Language {
    ZH_CN("languages/zh_CN.yml", Locale.SIMPLIFIED_CHINESE),
    ENGLISH("languages/en_US.yml", Locale.ENGLISH);

    private final String resLoc;
    private final Locale locale;

    Language(String resLoc, Locale locale) {
        this.resLoc = resLoc;
        this.locale = locale;
    }

    public String getResLoc() {
        return resLoc;
    }

    public Locale getLocale() {
        return locale;
    }

    public void saveResource() {
        SoManyRewards.getInstance().saveResource(this.getResLoc(), false);
    }
}
