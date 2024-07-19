package com.ixbob.somanyrewards.ui.button;

import org.bukkit.entity.Player;

public class ClaimRewardButtonFactory implements ButtonFactory {

    private static final ClaimRewardButtonFactory instance = new ClaimRewardButtonFactory();
    private Player player;
    private int pageIndex;
    private int invIndex;

    public static ClaimRewardButtonFactory getInstance() {
        return instance;
    }

    @Override
    public BasicButton create() {
        return new ClaimRewardButton(player, pageIndex, invIndex);
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
