package com.ixbob.somanyrewards.ui;

import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public interface IBasicUI {
    void buildUIInitialDisplay();

    void addLeftButton(int index);

    void addRightButton(int index);

    void removeLeftButton(int index);

    void removeRightButton(int index);

    boolean checkLeftButtonIfContains(int index);

    boolean checkRightButtonIfContains(int index);

    void setDisplayItem(int index, ItemStack item);

    void removeDisplayItem(int index);

    void onClick(int index, ClickType type, InventoryClickEvent event);

    Inventory getInventory();

    void initOrRefreshInventoryContents();
}
