package com.ixbob.somanyrewards.ui.button;

import com.ixbob.somanyrewards.enums.BasicGameTimeRewardType;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ClaimBasicPlayTimeRewardButton extends BasicButton {

    private final BasicGameTimeRewardType type;
    private final int id;
    private final int pageIndex;
    private final int invIndex;

    public ClaimBasicPlayTimeRewardButton(Player player, BasicGameTimeRewardType type, int id, int pageIndex, int invIndex) {
        super(player);
        this.type = type;
        this.id = id;
        this.pageIndex = pageIndex;
        this.invIndex = invIndex;
    }

    @Override
    public void run() {
        Bukkit.broadcast(Component.text("领取奖励！"));
    }

    public BasicGameTimeRewardType getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public int getInvIndex() {
        return invIndex;
    }
}
