package com.ixbob.somanyrewards.config.bean;

import com.ixbob.somanyrewards.enums.BasicGameTimeRewardType;
import org.bukkit.Material;

import java.util.List;

public class ConfigBeanBasicGameTimeNormalRewards extends ConfigBeanBasicGameTimeRewards {
    public ConfigBeanBasicGameTimeNormalRewards(BasicGameTimeRewardType type, int id, Material displayMaterial, List<String> rewardCommands, String localItemTitle, List<String> localItemLores) {
        super(type, id, displayMaterial, rewardCommands, localItemTitle, localItemLores);
    }
}
