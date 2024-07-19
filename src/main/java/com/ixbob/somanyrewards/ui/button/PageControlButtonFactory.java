package com.ixbob.somanyrewards.ui.button;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PageControlButtonFactory implements ButtonFactory, IPageControlButtonFactory {

    private static final PageControlButtonFactory instance = new PageControlButtonFactory();
    private Player player;
    private int pageIndex;
    private int invIndex;

    public static PageControlButtonFactory getInstance() {
        return instance;
    }

    @Override
    @Deprecated
    public BasicButton create() {
        throw new IllegalArgumentException("Please use create(ButtonType) instead");
    }

    @Override
    public BasicButton create(ButtonType buttonType) {
        return switch (buttonType) {
            case NEXT -> new NextPageButton(player, pageIndex, invIndex);
            case PREVIOUS -> new PreviousPageButton(player, pageIndex, invIndex);
        };
    }

    @Override
    public PageControlButtonFactory setPlayer(@NotNull Player player) {
        this.player = player;
        return this;
    }

    public PageControlButtonFactory setData(int pageIndex, int invIndex) {
        this.pageIndex = pageIndex;
        this.invIndex = invIndex;
        return this;
    }

    public enum ButtonType {
        NEXT,
        PREVIOUS
    }
}
