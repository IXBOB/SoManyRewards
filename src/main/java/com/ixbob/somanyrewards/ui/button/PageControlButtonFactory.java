package com.ixbob.somanyrewards.ui.button;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PageControlButtonFactory implements ButtonFactory, IPageControlButtonFactory {

    private static final PageControlButtonFactory instance = new PageControlButtonFactory();
    private Player player;
    private ButtonType buttonType;

    public static PageControlButtonFactory getInstance() {
        return instance;
    }

    @Override
    public BasicButton create() {
        return switch (buttonType) {
            case NEXT -> new NextPageButton(player);
            case PREVIOUS -> new PreviousPageButton(player);
        };
    }

    @Override
    public PageControlButtonFactory setPlayer(@NotNull Player player) {
        this.player = player;
        return this;
    }

    @Override
    public PageControlButtonFactory setType(@NotNull ButtonType buttonType) {
        this.buttonType = buttonType;
        return this;
    }
}
