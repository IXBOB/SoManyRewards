package com.ixbob.somanyrewards;

import com.ixbob.somanyrewards.command.RewardCommand;
import com.ixbob.somanyrewards.command.RewardCommandTabCompleter;
import com.ixbob.somanyrewards.listener.OnPlayerClickInventoryListener;
import com.ixbob.somanyrewards.listener.OnPlayerCloseInventoryListener;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class SoManyRewards extends JavaPlugin {

    private static SoManyRewards instance;

    @Override
    public void onEnable() {

        instance = this;
        saveDefaultConfig();

        getCommand("rewards").setExecutor( new RewardCommand());
        getCommand("rewards").setTabCompleter(new RewardCommandTabCompleter());

        registerListeners(
                new OnPlayerClickInventoryListener(),
                new OnPlayerCloseInventoryListener()
        );
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static SoManyRewards getInstance() {
        return instance;
    }

    public void registerListeners(Listener... listeners) {
        for (Listener listener : listeners) {
            getServer().getPluginManager().registerEvents(listener, this);
        }
    }
}
