package com.ixbob.somanyrewards.ui.button;

import com.ixbob.somanyrewards.ui.IPageableUI;
import com.ixbob.somanyrewards.ui.UIManager;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PreviousPageButton extends BasicButton {

    public PreviousPageButton(@NotNull Player player) {
        super(player);
    }

    @Override
    public void run() {
        if (UIManager.getInstance().getOpeningCustomUI(super.getPlayer()) instanceof IPageableUI ui) {
            ui.lastDisplayingPage();
        }
    }
}
