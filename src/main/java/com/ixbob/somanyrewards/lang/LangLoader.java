package com.ixbob.somanyrewards.lang;

import com.ixbob.somanyrewards.SoManyRewards;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.HashMap;

public class LangLoader {

    private final Player owner;
    private final Language language;
    private final HashMap<String, String> translationMap = new HashMap<>();

    LangLoader(Player owner, Language language) {
        this.owner = owner;
        this.language = language;
    }

    public String getTranslateValue (String key) {
        if (translationMap.isEmpty()) {
            File languageFile = new File(SoManyRewards.getInstance().getDataFolder(), language.getResLoc());
            FileConfiguration translations = YamlConfiguration.loadConfiguration(languageFile.getAbsoluteFile());
            for (String translation : translations.getKeys(false)) {
                translationMap.put(translation, translations.getString(translation));
            }
        }
        return translationMap.get(key);

    }

    public Player getOwner() {
        return owner;
    }

    public Language getLanguage() {
        return language;
    }
}
