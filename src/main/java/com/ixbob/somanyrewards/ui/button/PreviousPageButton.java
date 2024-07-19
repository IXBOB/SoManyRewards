package com.ixbob.somanyrewards.ui.button;

import com.ixbob.somanyrewards.ui.IPageableUI;
import com.ixbob.somanyrewards.ui.UIManager;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PreviousPageButton extends BasicButton {

    private final int pageIndex;
    private final int invIndex;

    public PreviousPageButton(@NotNull Player player, int pageIndex, int invIndex) {
        super(player);
        this.pageIndex = pageIndex;
        this.invIndex = invIndex;
    }

    @Override
    public void run() {
        if (UIManager.getInstance().getOpeningCustomUI(super.getPlayer()) instanceof IPageableUI ui) {
            ui.lastDisplayingPage();
        }
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public int getInvIndex() {
        return invIndex;
    }
}
