package com.ixbob.somanyrewards.command;

import com.ixbob.somanyrewards.manager.UIManager;
import com.ixbob.somanyrewards.ui.RewardOfBasicGameTimeUI;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class RewardCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String exactCommandStr, @NotNull String[] subs) {
        if (sender instanceof Player player) {
            if (subs[0].equals("BasicGameTime")) {
                RewardOfBasicGameTimeUI basicGameTimeUI = new RewardOfBasicGameTimeUI(player, 3, 2, 0);
                basicGameTimeUI.setDisplayItem(0, 0, new ItemStack(Material.GOLD_INGOT));
                basicGameTimeUI.setDisplayItem(0, 1, new ItemStack(Material.PAPER));
                basicGameTimeUI.setDisplayItem(1, 0, new ItemStack(Material.DIAMOND));
                basicGameTimeUI.setDisplayItem(1, 1, new ItemStack(Material.PAPER));
                basicGameTimeUI.addLeftButton(0,0);
                basicGameTimeUI.addLeftButton(0,1);
                basicGameTimeUI.addLeftButton(1,0);
                basicGameTimeUI.addLeftButton(1,1);
                UIManager.getInstance().openUI(player, basicGameTimeUI);
            }
        }
        return true;
    }
}
