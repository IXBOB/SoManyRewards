package com.ixbob.somanyrewards.ui;

import com.ixbob.somanyrewards.config.ConfigHolder;
import com.ixbob.somanyrewards.playerdata.PlayerDataBlock;
import com.ixbob.somanyrewards.playerdata.PlayerDataManager;
import org.bukkit.entity.Player;

public class RewardOfBasicGameTimeUI extends BasicPageableUI {

    public RewardOfBasicGameTimeUI(Player owner, int lineAmount, int pageAmount, int displayingPageIndex) {
        super(owner, lineAmount, pageAmount, displayingPageIndex);
    }

    @Override
    public void buildUIInitialDisplay() {
        final ConfigHolder configHolder = ConfigHolder.getInstance();
        final PlayerDataBlock dataBlock = PlayerDataManager.getInstance().getPlayerDataBlock(player);
        //TODO: use Statistics when release. this is for test only now.
        final int playTimeMinutes = 225;
        final int claimedBasicPlayTime = dataBlock.getClaimedBasicGameTime();
        final int eachTimePeriodShown = configHolder.getEachTimePeriodShown();
        //TODO: 重写内容初始显示，显示至玩家已游玩时长可以显示的一页

    }
}
