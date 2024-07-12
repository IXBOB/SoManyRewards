package com.ixbob.somanyrewards.listener;

import com.ixbob.somanyrewards.ui.UIManager;
import com.ixbob.somanyrewards.ui.BasicUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class OnPlayerClickInventoryListener implements Listener {
    @EventHandler
    public void onPlayerClickInventory(InventoryClickEvent event) {
        if (event.getInventory().getHolder() instanceof Player player) {
            BasicUI openingCustomUI = UIManager.getInstance().getOpeningCustomUI(player);
            if (openingCustomUI != null) {
                openingCustomUI.onClick(event.getRawSlot(), event.getClick(), event);
            }
        }
    }
}
