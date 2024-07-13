package com.ixbob.somanyrewards.config;

import com.ixbob.somanyrewards.SoManyRewards;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ConfigHolder {

    private static ConfigHolder instance;
    private int config_eachTimePeriodShown;
    private Material config_normalRewardsDefaultDisplayMaterial;
    private ConfigurationSection config_normalRewards;
    private Material config_specialRewardsDefaultDisplayMaterial;
    private ConfigurationSection config_specialRewards;

    private ArrayList<ArrayList<HashMap<String, ?>>> normalRewards;
    private ArrayList<ArrayList<HashMap<String, ?>>> specialRewards;

    public static ConfigHolder getInstance() {
        if (instance == null) {
            instance = new ConfigHolder();
        }
        return instance;
    }

    public void loadData() {
        FileConfiguration config = SoManyRewards.getInstance().getConfig();
        ConfigurationSection rewardsOfBasicPlayTimeConfig = config.getConfigurationSection("Rewards.BasicPlayTime");
        assert rewardsOfBasicPlayTimeConfig != null;
        this.config_eachTimePeriodShown = rewardsOfBasicPlayTimeConfig.getInt("each_time_period_shown");
        this.config_normalRewardsDefaultDisplayMaterial = Material.valueOf(rewardsOfBasicPlayTimeConfig.getString("normal_rewards_default_display_material"));
        this.config_normalRewards = rewardsOfBasicPlayTimeConfig.getConfigurationSection("normal_rewards");
        this.config_specialRewardsDefaultDisplayMaterial = Material.valueOf(rewardsOfBasicPlayTimeConfig.getString("special_rewards_default_display_material"));
        this.config_specialRewards = rewardsOfBasicPlayTimeConfig.getConfigurationSection("special_rewards");

//        System.out.println(config_eachTimePeriodShown);
//        System.out.println(config_normalRewardsDefaultDisplayMaterial);
//        System.out.println(config_normalRewards.getKeys(false).size());

        normalRewards = readFromLegacyConfigRewards(config_normalRewards);
        specialRewards = readFromLegacyConfigRewards(config_specialRewards);

        System.out.println(normalRewards.toString());
        System.out.println(specialRewards.toString());
    }

    private ArrayList<ArrayList<HashMap<String, ?>>> readFromLegacyConfigRewards(ConfigurationSection configSection) {
        ArrayList<ArrayList<HashMap<String, ?>>> result = new ArrayList<>();
        for (int i = 0; i < configSection.getKeys(false).size(); i++) {
            result.add(new ArrayList<>());
            HashMap<String, List<String>> mapRewards = new HashMap<>();
            HashMap<String, List<String>> mapLocalTexts = new HashMap<>();
            mapRewards.put("rewards", configSection.getStringList(i + ".rewards"));
            mapLocalTexts.put("local_texts", configSection.getStringList(i + ".local_texts"));
            result.get(i).add(mapRewards);
            result.get(i).add(mapLocalTexts);
            if (configSection.getName().equals("special_rewards")) {
                HashMap<String, Integer> mapDisplayWhen = new HashMap<>();
                mapDisplayWhen.put("display_when", configSection.getInt(i + ".display_when"));
                result.get(i).add(mapDisplayWhen);
            }
        }
        return result;
    }

    public ArrayList<ArrayList<HashMap<String, ?>>> getNormalRewards() {
        return normalRewards;
    }

    public ArrayList<ArrayList<HashMap<String, ?>>> getSpecialRewards() {
        return specialRewards;
    }

    public int getEachTimePeriodShown() {
        return config_eachTimePeriodShown;
    }

    public Material getNormalRewardsDefaultDisplayMaterial() {
        return config_normalRewardsDefaultDisplayMaterial;
    }

    public Material getSpecialRewardsDefaultDisplayMaterial() {
        return config_specialRewardsDefaultDisplayMaterial;
    }
}
