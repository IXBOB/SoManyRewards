package com.ixbob.somanyrewards.config.bean;

import com.ixbob.somanyrewards.enums.BasicGameTimeRewardType;

import java.util.List;

public class BasicGameTimeSpecialRewardsConfigBean extends BasicGameTimeNormalRewardsConfigBean {

    protected final int displayWhen;

    public BasicGameTimeSpecialRewardsConfigBean(BasicGameTimeRewardType type, int id, List<String> rewardCommands, String localItemTitle, List<String> localItemLores, int displayWhen) {
        super(type, id, rewardCommands, localItemTitle, localItemLores);
        this.displayWhen = displayWhen;
    }

    public int getDisplayWhen() {
        return displayWhen;
    }
}
