package com.ixbob.somanyrewards.ui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class BasicUI implements IBasicUI{

    protected Player owner;
    protected Inventory inventory;
    private final int lineAmount;
    private final int maxIndex;
    private ArrayList<Integer> leftButtons = new ArrayList<>();
    private ArrayList<Integer> rightButtons = new ArrayList<>();
    private HashMap<Integer, ItemStack> itemsMap = new HashMap<>();

    public BasicUI(int lineAmount, Player owner) {
        this.owner = owner;
        this.inventory = Bukkit.createInventory(owner, lineAmount * 9);
        this.lineAmount = lineAmount;
        this.maxIndex = lineAmount * 9 - 1;
    }

    @Override
    public void addLeftButton(int index) {
        leftButtons.add(index);
    }

    @Override
    public void addRightButton(int index) {
        rightButtons.add(index);
    }

    @Override
    public void removeLeftButton(int index) {
        leftButtons.remove(index);
    }

    @Override
    public void removeRightButton(int index) {
        rightButtons.remove(index);
    }

    @Override
    public boolean checkLeftButtonIfContains(int buttonIndex) {
        return leftButtons.contains(buttonIndex);
    }

    @Override
    public boolean checkRightButtonIfContains(int buttonIndex) {
        return rightButtons.contains(buttonIndex);
    }

    @Override
    public void setDisplayItem(int index, ItemStack item) {
        removeDisplayItem(index);
        itemsMap.put(index, item);
    }

    @Override
    public void removeDisplayItem(int index) {
        itemsMap.remove(index);
    }

    @Override
    public void onClick(int index, ClickType type, InventoryClickEvent event) {
        if (index > event.getRawSlot()) {
            return;
        }

    }

    @Override
    public Inventory getInventory() {
        initOrRefreshInventoryContents();
        return inventory;
    }

    @Override
    public void initOrRefreshInventoryContents() {
        for (int index : itemsMap.keySet()) {
            inventory.setItem(index, itemsMap.get(index));
        }
    }
}
