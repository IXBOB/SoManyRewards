package com.ixbob.somanyrewards.ui.button;

import org.jetbrains.annotations.NotNull;

public interface IPageControlButtonFactory {

    ButtonFactory setType(@NotNull ButtonType buttonType);

    enum ButtonType {
        NEXT,
        PREVIOUS
    }
}
