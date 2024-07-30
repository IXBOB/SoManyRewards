package com.ixbob.somanyrewards.config.bean;

import com.ixbob.somanyrewards.enums.BasicGameTimeRewardType;
import org.bukkit.Material;

import java.util.List;

public class ConfigBeanBasicGameTimeSpecialRewards extends ConfigBeanBasicGameTimeNormalRewards {

    protected final int displayWhen;

    public ConfigBeanBasicGameTimeSpecialRewards(BasicGameTimeRewardType type, int id, Material displayMaterial, List<String> rewardCommands, String localItemTitle, List<String> localItemLores, int displayWhen) {
        super(type, id, displayMaterial, rewardCommands, localItemTitle, localItemLores);
        this.displayWhen = displayWhen;
    }

    public int getDisplayWhen() {
        return displayWhen;
    }
}
