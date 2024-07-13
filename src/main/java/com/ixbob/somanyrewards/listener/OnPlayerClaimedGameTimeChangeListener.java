package com.ixbob.somanyrewards.listener;

import com.ixbob.somanyrewards.event.PlayerClaimedGameTimeChangeEvent;
import com.ixbob.somanyrewards.playerdata.PlayerDataBlock;
import com.ixbob.somanyrewards.playerdata.PlayerDataManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class OnPlayerClaimedGameTimeChangeListener implements Listener {
    @EventHandler
    public void onPlayerClaimedGameTimeChange(PlayerClaimedGameTimeChangeEvent event) {
        int newTime = event.getNewClaimedGameTime();
        Player player = event.getPlayer();
        PlayerDataBlock dataBlock = PlayerDataManager.getInstance().getPlayerDataBlock(player);
        dataBlock.setClaimedBasicGameTime(newTime);
    }
}
