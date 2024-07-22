package com.ixbob.somanyrewards.config.bean;

import com.ixbob.somanyrewards.enums.BasicGameTimeRewardType;

import java.util.List;

public class BasicGameTimeNormalRewardsConfigBean extends BasicGameTimeConfigRewardsConfigBean{
    public BasicGameTimeNormalRewardsConfigBean(BasicGameTimeRewardType type, int id, List<String> rewardCommands, String localItemTitle, List<String> localItemLores) {
        super(type, id, rewardCommands, localItemTitle, localItemLores);
    }
}
