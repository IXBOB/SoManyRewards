package com.ixbob.somanyrewards.config;

import com.ixbob.somanyrewards.SoManyRewards;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigHolder {

    private static ConfigHolder instance;
    private int config_eachTimePeriodShown;
    private Material config_normalRewardsDefaultDisplayMaterial;
    private ConfigurationSection config_normalRewards;
    private Material config_specialRewardsDefaultDisplayMaterial;
    private ConfigurationSection config_specialRewards;

//    private ArrayList<ArrayList<HashMap<String, ?>>> normalRewards;
//    private ArrayList<ArrayList<HashMap<String, ?>>> specialRewards;

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

        BasicGameTimeConfigSubHolder.getInstance().loadData();
    }

    public ConfigurationSection getLegacyConfigNormalRewards() {
        return config_normalRewards;
    }

    public ConfigurationSection getLegacyConfigSpecialRewards() {
        return config_specialRewards;
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
