package com.ixbob.somanyrewards.ui;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class UIManager {

    private static final UIManager instance = new UIManager();
    private static final HashMap<Player, BasicUI> holdersMap = new HashMap<>();

    private UIManager() {}

    public static UIManager getInstance() {
        return instance;
    }

    public void openUI(Player player, BasicUI ui) {
        addUI(player, ui);
        ui.buildUIInitialDisplay();
        player.openInventory(ui.getInventory());
    }

    public void closeUI(Player player) {
        removeUI(player);
    }

    public BasicUI getOpeningCustomUI(Player player) {
        if (holdersMap.containsKey(player)) {
            return holdersMap.get(player);
        }
        return null;
    }

    private void addUI(Player player, BasicUI ui) {
        if (holdersMap.containsKey(player)) {
            throw new IllegalArgumentException("Player " + player + " is already opening a UI!");
        }
        holdersMap.put(player, ui);
    }

    private void removeUI(Player player) {
        holdersMap.remove(player);
    }
}
