package com.ixbob.somanyrewards.config.bean;

import com.ixbob.somanyrewards.enums.BasicGameTimeRewardType;

import java.util.List;

public class ConfigBeanBasicGameTimeNormalRewards extends ConfigBeanBasicGameTimeRewards {
    public ConfigBeanBasicGameTimeNormalRewards(BasicGameTimeRewardType type, int id, List<String> rewardCommands, String localItemTitle, List<String> localItemLores) {
        super(type, id, rewardCommands, localItemTitle, localItemLores);
    }
}
