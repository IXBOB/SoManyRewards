package com.ixbob.somanyrewards.builder;

import org.bukkit.entity.Player;

public class CommandStrBuilder {

    private final StringBuffer cmdBuff;

    public CommandStrBuilder(String command) {
        this.cmdBuff = new StringBuffer(command);
    }

    public CommandStrBuilder setPlayer(Player player) {
        cmdBuff.replace(cmdBuff.indexOf("%"), cmdBuff.indexOf("%", cmdBuff.indexOf("%") + 1) + 1, player.getName());
        return this;
    }

    public String get() {
        return cmdBuff.toString();
    }
}
