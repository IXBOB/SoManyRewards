package com.ixbob.somanyrewards.config.configbean;

import java.util.List;

public class BasicGameTimeSpecialRewardsConfigBean extends BasicGameTimeNormalRewardsConfigBean {

    protected final int displayWhen;

    public BasicGameTimeSpecialRewardsConfigBean(RewardType type, int id, List<String> rewardCommands, String localItemTitle, List<String> localItemLores, int displayWhen) {
        super(type, id, rewardCommands, localItemTitle, localItemLores);
        this.displayWhen = displayWhen;
    }

    public int getDisplayWhen() {
        return displayWhen;
    }
}
