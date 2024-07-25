package com.ixbob.somanyrewards.config;

import com.ixbob.somanyrewards.SoManyRewards;
import com.ixbob.somanyrewards.config.bean.ConfigBeanBasicGameTimeNormalRewards;
import com.ixbob.somanyrewards.config.bean.ConfigBeanBasicGameTimeSpecialRewards;
import com.ixbob.somanyrewards.config.bean.holder.BeansHolderConfigBasicGameTime;
import com.ixbob.somanyrewards.enums.BasicGameTimeRewardType;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;
import java.util.Optional;

public class ConfigSubHolderBasicGameTime extends BeansHolderConfigBasicGameTime implements IConfigHolder {
    private static final ConfigSubHolderBasicGameTime instance = new ConfigSubHolderBasicGameTime();

    private int config_eachTimePeriodShown;
    private ConfigurationSection configLegacy_normalRewards;
    private ConfigurationSection configLegacy_specialRewards;
    private Material config_normalRewardsDefaultDisplayMaterial;
    private Material config_specialRewardsDefaultDisplayMaterial;

    public static ConfigSubHolderBasicGameTime getInstance() {
        return instance;
    }

    @Override
    public void loadData() {
        clearHoldingBeans();
        FileConfiguration config = SoManyRewards.getInstance().getConfig();
        ConfigurationSection rewardsOfBasicPlayTimeConfig = config.getConfigurationSection("Rewards.BasicPlayTime");
        assert rewardsOfBasicPlayTimeConfig != null;
        this.config_eachTimePeriodShown = rewardsOfBasicPlayTimeConfig.getInt("each_time_period_shown");
        this.config_normalRewardsDefaultDisplayMaterial = Material.valueOf(rewardsOfBasicPlayTimeConfig.getString("normal_rewards_default_display_material"));
        this.config_specialRewardsDefaultDisplayMaterial = Material.valueOf(rewardsOfBasicPlayTimeConfig.getString("special_rewards_default_display_material"));
        this.configLegacy_normalRewards = rewardsOfBasicPlayTimeConfig.getConfigurationSection("normal_rewards");
        this.configLegacy_specialRewards = rewardsOfBasicPlayTimeConfig.getConfigurationSection("special_rewards");
        loadFromLegacyConfigRewards(configLegacy_normalRewards);
        loadFromLegacyConfigRewards(configLegacy_specialRewards);
    }

    private void loadFromLegacyConfigRewards(ConfigurationSection configSection) {
        List<String> rewardCommands;
        String localItemTitle;
        List<String> localItemLores;
        Optional<Integer> displayWhen;
        for (int id = 0; id < configSection.getKeys(false).size(); id++) {
            rewardCommands = configSection.getStringList(id + ".rewards");
            localItemTitle = configSection.getString(id + ".local_item_title");
            localItemLores = configSection.getStringList(id + ".local_item_lores");
            if (configSection.getName().equals("special_rewards")) {
                displayWhen = Optional.of(configSection.getInt(id + ".display_when"));
                addBean(new ConfigBeanBasicGameTimeSpecialRewards(
                        BasicGameTimeRewardType.SPECIAL, id, rewardCommands, localItemTitle, localItemLores, displayWhen.get()));
                continue;
            }
            addBean(new ConfigBeanBasicGameTimeNormalRewards(
                    BasicGameTimeRewardType.NORMAL, id, rewardCommands, localItemTitle, localItemLores));
        }
    }

    public ConfigurationSection getLegacyConfigNormalRewards() {
        return configLegacy_normalRewards;
    }

    public ConfigurationSection getLegacyConfigSpecialRewards() {
        return configLegacy_specialRewards;
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
