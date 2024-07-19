package com.ixbob.somanyrewards.ui;

public interface IPageableUI {

    void setDisplayingPage(int page);

    void nextDisplayingPage();

    void lastDisplayingPage();

    int getDisplayingPage();

}
