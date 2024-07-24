package com.ixbob.somanyrewards.config;

import com.ixbob.somanyrewards.SoManyRewards;
import com.ixbob.somanyrewards.config.bean.BasicGameTimeConfigRewardsConfigBean;
import com.ixbob.somanyrewards.config.bean.BasicGameTimeNormalRewardsConfigBean;
import com.ixbob.somanyrewards.config.bean.BasicGameTimeSpecialRewardsConfigBean;
import com.ixbob.somanyrewards.enums.BasicGameTimeRewardType;
import com.ixbob.somanyrewards.util.LogUtils;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConfigSubHolderBasicGameTime {
    private static final ConfigSubHolderBasicGameTime instance = new ConfigSubHolderBasicGameTime();
    private final List<BasicGameTimeConfigRewardsConfigBean> beans = new ArrayList<>();

    private int config_eachTimePeriodShown;
    private ConfigurationSection configLegacy_normalRewards;
    private ConfigurationSection configLegacy_specialRewards;
    private Material config_normalRewardsDefaultDisplayMaterial;
    private Material config_specialRewardsDefaultDisplayMaterial;

    public static ConfigSubHolderBasicGameTime getInstance() {
        return instance;
    }

    public void loadData() {
        clearHoldingBeans();
        FileConfiguration config = SoManyRewards.getInstance().getConfig();
        ConfigurationSection rewardsOfBasicPlayTimeConfig = config.getConfigurationSection("Rewards.BasicPlayTime");
        assert rewardsOfBasicPlayTimeConfig != null;
        this.config_eachTimePeriodShown = rewardsOfBasicPlayTimeConfig.getInt("each_time_period_shown");
        this.config_normalRewardsDefaultDisplayMaterial = Material.valueOf(rewardsOfBasicPlayTimeConfig.getString("normal_rewards_default_display_material"));
        this.configLegacy_normalRewards = rewardsOfBasicPlayTimeConfig.getConfigurationSection("normal_rewards");
        this.config_specialRewardsDefaultDisplayMaterial = Material.valueOf(rewardsOfBasicPlayTimeConfig.getString("special_rewards_default_display_material"));
        this.configLegacy_specialRewards = rewardsOfBasicPlayTimeConfig.getConfigurationSection("special_rewards");
        loadFromLegacyConfigRewards(configLegacy_normalRewards);
        loadFromLegacyConfigRewards(configLegacy_specialRewards);
    }

    public void addBean(@NotNull BasicGameTimeConfigRewardsConfigBean bean) {
        try {
            if (getBean(bean.getType(), bean.getId()) == null) {
                beans.add(bean);
                return;
            }
            throw new IllegalAccessException("The id of the bean to be added conflicts with the id of an existing bean.");
        } catch (IllegalAccessException e) {
            LogUtils.logFatal(e);
        }
    }

    public BasicGameTimeConfigRewardsConfigBean getBean(BasicGameTimeRewardType rewardType, int id) {
        return beans.stream().filter(bean -> (bean.getType() == rewardType && bean.getId() == id)).findFirst().orElse(null);
    }

    public <T extends BasicGameTimeConfigRewardsConfigBean> List<T> getBeans(BasicGameTimeRewardType rewardType, Class<T> clazz) {
        return new ArrayList<>(beans.stream()
                .filter(obj -> obj.getType() == rewardType)
                .map(clazz::cast)
                .toList());
    }

    public void clearHoldingBeans() {
        beans.clear();
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
                addBean(new BasicGameTimeSpecialRewardsConfigBean(
                        BasicGameTimeRewardType.SPECIAL, id, rewardCommands, localItemTitle, localItemLores, displayWhen.get()));
                continue;
            }
            addBean(new BasicGameTimeNormalRewardsConfigBean(
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