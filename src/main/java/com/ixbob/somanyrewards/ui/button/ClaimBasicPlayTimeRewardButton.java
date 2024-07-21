package com.ixbob.somanyrewards.ui.button;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
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
        Bukkit.broadcast(Component.text("领取奖励！"));
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public int getInvIndex() {
        return invIndex;
    }
}
