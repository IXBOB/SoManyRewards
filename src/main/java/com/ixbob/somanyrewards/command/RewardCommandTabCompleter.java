package com.ixbob.somanyrewards.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RewardCommandTabCompleter implements TabCompleter {
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String exactCommand, @NotNull String[] subStrs) {
        if (subStrs.length == 1) {
            return List.of("BasicGameTime");
        }
        return List.of();
    }
}
