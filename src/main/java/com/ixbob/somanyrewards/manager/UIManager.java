package com.ixbob.somanyrewards.manager;

import com.ixbob.somanyrewards.ui.BasicUI;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class UIManager {

    private static UIManager instance;
    private static final HashMap<Player, BasicUI> holdersMap = new HashMap<>();

    public static UIManager getInstance() {
        if (instance == null) {
            instance = new UIManager();
        }
        return instance;
    }

    public void openUI(Player player, BasicUI ui) {
        addUI(player, ui);
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
