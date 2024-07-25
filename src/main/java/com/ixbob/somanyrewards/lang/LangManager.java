package com.ixbob.somanyrewards.lang;

import org.bukkit.entity.Player;

import java.util.*;

public class LangManager {
    private static final LangManager instance = new LangManager();
    private final ArrayList<LangLoader> loadedLangInstances = new ArrayList<>();

    private LangManager() {}

    public static LangManager getInstance() {
        return instance;
    }

    public void createLoader(Player player, Locale locale) {
        Language languageEnum = Arrays.stream(Language.values()).filter(loc -> loc.getLocale().equals(locale)).findFirst().orElse(null);
        if (languageEnum == null) languageEnum = Language.ENGLISH;
        if (loadedLangInstances.stream().anyMatch(p -> p.getOwner().equals(player))) {
            throw new RuntimeException("Player already added to the language map!");
        }
        LangLoader langLoader = new LangLoader(player, languageEnum);
        loadedLangInstances.add(langLoader);
    }

    public String getLang(Player player, String key){
        return getLoader(player).getTranslateValue(key);
    }

    public void removeLoader(Player player) {
        Optional<LangLoader> loader = findLoader(player);
        loader.ifPresent(loadedLangInstances::remove);
    }

    public LangLoader getLoader(Player player) {
        Optional<LangLoader> loader = findLoader(player);
        return loader.orElse(null);
    }

    private Optional<LangLoader> findLoader(Player player) {
        return loadedLangInstances.stream().filter(p -> p.getOwner().equals(player)).findFirst();
    }
}
