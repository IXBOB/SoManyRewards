package com.ixbob.somanyrewards.listener;

import com.ixbob.somanyrewards.lang.LangManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnPlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        LangManager.getInstance().createLoader(player, player.locale());

        String lang = LangManager.getInstance().getLang(player, "test");
        System.out.println(lang);
        System.out.println("1");
        player.sendMessage(lang);
    }
}
