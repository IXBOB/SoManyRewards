package com.ixbob.somanyrewards.ui.button;

import org.bukkit.entity.Player;

public class ClaimBasicPlayTimeRewardButton extends BasicButton {

    private final int pageIndex;
    private final int invIndex;

    public ClaimBasicPlayTimeRewardButton(Player player, int pageIndex, int invIndex) {
        super(player);
        this.pageIndex = pageIndex;
        this.invIndex = invIndex;
    }

    @Override
    public void run() {

    }

    public int getPageIndex() {
        return pageIndex;
    }

    public int getInvIndex() {
        return invIndex;
    }
}
