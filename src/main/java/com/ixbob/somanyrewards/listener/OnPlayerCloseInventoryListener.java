package com.ixbob.somanyrewards.listener;

import com.ixbob.somanyrewards.manager.UIManager;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class OnPlayerCloseInventoryListener implements Listener {
    @EventHandler
    public void onPlayerCloseInventory(InventoryCloseEvent event) {
        HumanEntity playerHuman = event.getPlayer();
        if (playerHuman instanceof Player player) {
            if (UIManager.getInstance().getOpeningCustomUI(player) != null) {
                UIManager.getInstance().closeUI(player);
            }
        }
    }
}
