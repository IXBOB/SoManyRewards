package com.ixbob.somanyrewards.config.bean;

import java.util.List;

public class BasicGameTimeNormalRewardsConfigBean extends BasicGameTimeConfigRewardsConfigBean{
    public BasicGameTimeNormalRewardsConfigBean(RewardType type, int id, List<String> rewardCommands, String localItemTitle, List<String> localItemLores) {
        super(type, id, rewardCommands, localItemTitle, localItemLores);
    }
}
