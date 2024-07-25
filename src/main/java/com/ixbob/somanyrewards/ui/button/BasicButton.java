package com.ixbob.somanyrewards.ui.button;

import org.bukkit.entity.Player;

public abstract class BasicButton implements Runnable {
    protected Player player;

    public BasicButton(Player player) {
        this.player = player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
