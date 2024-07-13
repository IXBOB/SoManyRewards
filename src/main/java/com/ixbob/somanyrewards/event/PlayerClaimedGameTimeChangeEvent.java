package com.ixbob.somanyrewards.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class PlayerClaimedGameTimeChangeEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();

    private boolean isCancelled = false;
    private final Player player;
    private final int newClaimedGameTime;

    public PlayerClaimedGameTimeChangeEvent(Player player, int newClaimedGameTime) {
        this.player = player;
        this.newClaimedGameTime = newClaimedGameTime;
    }

    public Player getPlayer() {
        return player;
    }

    public int getNewClaimedGameTime() {
        return newClaimedGameTime;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean setValue) {
        isCancelled = setValue;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}