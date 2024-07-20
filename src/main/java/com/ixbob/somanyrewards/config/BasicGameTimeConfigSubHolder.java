package com.ixbob.somanyrewards.config;

import com.ixbob.somanyrewards.config.configbean.BasicGameTimeConfigRewardsConfigBean;
import com.ixbob.somanyrewards.config.configbean.BasicGameTimeNormalRewardsConfigBean;
import com.ixbob.somanyrewards.config.configbean.BasicGameTimeSpecialRewardsConfigBean;
import com.ixbob.somanyrewards.util.LogUtils;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public class BasicGameTimeConfigSubHolder {
    private static final BasicGameTimeConfigSubHolder instance = new BasicGameTimeConfigSubHolder();
    private List<BasicGameTimeConfigRewardsConfigBean> beans;

    public static BasicGameTimeConfigSubHolder getInstance() {
        return instance;
    }

    public void loadData() {
        clear();
        ConfigHolder configHolder = ConfigHolder.getInstance();
        loadFromLegacyConfigRewards(configHolder.getLegacyConfigNormalRewards());
        loadFromLegacyConfigRewards(configHolder.getLegacyConfigSpecialRewards());
    }

    public void addBean(@NotNull BasicGameTimeConfigRewardsConfigBean bean) {
        try {
            if (getBean(bean.getType(), bean.getId()) != null) {
                beans.add(bean);
                return;
            }
            throw new IllegalAccessException("The id of the bean to be added conflicts with the id of an existing bean.");
        } catch (IllegalAccessException e) {
            LogUtils.logFatal(e);
        }
    }

    public BasicGameTimeConfigRewardsConfigBean getBean(BasicGameTimeConfigRewardsConfigBean.RewardType rewardType, int id) {
        return beans.stream().filter(bean -> (bean.getType() == rewardType && bean.getId() == id)).findFirst().orElse(null);
    }

    public void clear() {
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
                        BasicGameTimeConfigRewardsConfigBean.RewardType.SPECIAL, id, rewardCommands, localItemTitle, localItemLores, displayWhen.get()));
                continue;
            }
            addBean(new BasicGameTimeNormalRewardsConfigBean(
                    BasicGameTimeConfigRewardsConfigBean.RewardType.NORMAL, id, rewardCommands, localItemTitle, localItemLores));
        }
    }
}
