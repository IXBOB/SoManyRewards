package com.ixbob.somanyrewards.ui.button;

import com.ixbob.somanyrewards.builder.CommandStrBuilder;
import com.ixbob.somanyrewards.enums.BasicGameTimeRewardType;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

public class ClaimBasicPlayTimeRewardButton extends BasicButton {

    private final BasicGameTimeRewardType type;
    private final int id;
    private final int pageIndex;
    private final int invIndex;
    private final List<String> commands;

    public ClaimBasicPlayTimeRewardButton(Player player, BasicGameTimeRewardType type, int id, int pageIndex, int invIndex, List<String> commands) {
        super(player);
        this.type = type;
        this.id = id;
        this.pageIndex = pageIndex;
        this.invIndex = invIndex;
        this.commands = commands;
    }

    @Override
    public void run() {
        Bukkit.broadcast(Component.text("领取奖励！"));
        for (String command : commands) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), (new CommandStrBuilder(command)).setPlayer(player).get());
        }
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

    public List<String> getCommands() {
        return commands;
    }
}
