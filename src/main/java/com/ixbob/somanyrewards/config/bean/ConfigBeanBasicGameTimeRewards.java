package com.ixbob.somanyrewards.config.bean;

import com.ixbob.somanyrewards.enums.BasicGameTimeRewardType;

import java.util.List;

public abstract class ConfigBeanBasicGameTimeRewards extends BasicConfigBean {

    private final BasicGameTimeRewardType type;
    private final int id; //order displayed in the UI, starts from 0.
    private final List<String> rewardCommands;
    private final String localItemTitle;
    private final List<String> localItemLores;

    public ConfigBeanBasicGameTimeRewards(final BasicGameTimeRewardType type, final int id, final List<String> rewardCommands, final String localItemTitle, final List<String> localItemLores) {
        this.type = type;
        this.id = id;
        this.rewardCommands = rewardCommands;
        this.localItemTitle = localItemTitle;
        this.localItemLores = localItemLores;
    }

    public BasicGameTimeRewardType getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public List<String> getRewardCommands() {
        return rewardCommands;
    }

    public String getLocalItemTitle() {
        return localItemTitle;
    }

    public List<String> getLocalItemLores() {
        return localItemLores;
    }
}
