package com.ixbob.somanyrewards.command;

import com.ixbob.somanyrewards.ui.UIManager;
import com.ixbob.somanyrewards.ui.RewardOfBasicGameTimeUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class RewardCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String exactCommandStr, @NotNull String[] subs) {
        if (sender instanceof Player player) {
            if (subs[0].equals("BasicGameTime")) {
                RewardOfBasicGameTimeUI basicGameTimeUI = new RewardOfBasicGameTimeUI(player, 3, 2, 0);
                UIManager.getInstance().openUI(player, basicGameTimeUI);
            }
        }
        return true;
    }


}
