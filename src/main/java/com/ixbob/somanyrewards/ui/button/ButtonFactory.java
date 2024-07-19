package com.ixbob.somanyrewards.ui.button;

import org.bukkit.entity.Player;

public interface ButtonFactory {

    BasicButton create();

    ButtonFactory setPlayer(Player player);

}
