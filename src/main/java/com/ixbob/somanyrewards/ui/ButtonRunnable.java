package com.ixbob.somanyrewards.ui;

import org.bukkit.entity.Player;

public abstract class ButtonRunnable implements Runnable {
    protected final boolean needRunnerPlayer;
    protected Player runnerPlayer;

    public ButtonRunnable(boolean needRunnerPlayer) {
        this.needRunnerPlayer = needRunnerPlayer;
    }

    @Override
    public void run() {
        if (needRunnerPlayer && runnerPlayer == null) throw new NullPointerException("This runnable need a player to run but found null!");
    }

    public boolean isNeedRunnerPlayer() {
        return needRunnerPlayer;
    }

    public void setRunnerPlayer(Player runnerPlayer) {
        this.runnerPlayer = runnerPlayer;
    }

    public Player getRunnerPlayer() {
        return runnerPlayer;
    }
}
