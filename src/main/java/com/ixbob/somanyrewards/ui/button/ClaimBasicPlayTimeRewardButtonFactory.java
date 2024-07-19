package com.ixbob.somanyrewards.ui.button;

import org.bukkit.entity.Player;

public class ClaimBasicPlayTimeRewardButtonFactory implements ButtonFactory {

    private static final ClaimBasicPlayTimeRewardButtonFactory instance = new ClaimBasicPlayTimeRewardButtonFactory();
    private Player player;
    private int pageIndex;
    private int invIndex;

    public static ClaimBasicPlayTimeRewardButtonFactory getInstance() {
        return instance;
    }

    @Override
    public BasicButton create() {
        return new ClaimBasicPlayTimeRewardButton(player, pageIndex, invIndex);
    }

    @Override
    public ButtonFactory setPlayer(Player player) {
        this.player = player;
        return this;
    }

    public ButtonFactory setData(int pageIndex, int invIndex) {
        this.pageIndex = pageIndex;
        this.invIndex = invIndex;
        return this;
    }
}
