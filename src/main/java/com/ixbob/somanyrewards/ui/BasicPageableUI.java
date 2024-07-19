package com.ixbob.somanyrewards.ui;

import com.ixbob.somanyrewards.SoManyRewards;
import com.ixbob.somanyrewards.ui.button.BasicButton;
import com.ixbob.somanyrewards.util.LogUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class BasicPageableUI extends BasicUI implements IPageableUI {

    private final int pageAmount;
    private int displayingPageIndex;
    private final ArrayList<HashMap<Integer, BasicButton>> leftButtonsWithPages = new ArrayList<>();
    private final ArrayList<HashMap<Integer, BasicButton>> rightButtonsWithPages = new ArrayList<>();
    private final ArrayList<HashMap<Integer, ItemStack>> itemsMapWithPages = new ArrayList<>();

    public BasicPageableUI(Player owner, int lineAmount, int pageAmount, int displayingPageIndex) {
        super(lineAmount, owner);
        this.pageAmount = pageAmount;
        for (int i = 1; i <= pageAmount; i++) {
            itemsMapWithPages.add(new HashMap<>());
            leftButtonsWithPages.add(new HashMap<>());
            rightButtonsWithPages.add(new HashMap<>());
        }
        this.displayingPageIndex = displayingPageIndex;
    }

    @Override
    public void onClick(int index, ClickType clickType, InventoryClickEvent event) {
        super.onClick(index, clickType, event);
        if ( (clickType == ClickType.LEFT && checkLeftButtonIfContains(displayingPageIndex, index))
                || (clickType == ClickType.RIGHT && checkRightButtonIfContains(displayingPageIndex, index)) ) {
            searchAndPerformButtonAction(displayingPageIndex, index, clickType);
        }
    }

    private void searchAndPerformButtonAction(int pageIndex, int index, ClickType clickType) {
        BasicButton button = getAppliedButton(pageIndex, index, clickType);
        Bukkit.getScheduler().runTask(SoManyRewards.getInstance(), button);
    }

    public void setDisplayItem(int pageIndex, int index, ItemStack item) {
        removeDisplayItem(pageIndex, index);
        itemsMapWithPages.get(pageIndex).put(index, item);
    }

    public void removeDisplayItem(int pageIndex, int index) {
        itemsMapWithPages.get(pageIndex).remove(index);
    }

    public boolean checkLeftButtonIfContains(int pageIndex, int buttonIndex) {
        return leftButtonsWithPages.get(pageIndex).containsKey(buttonIndex);
    }

    public boolean checkRightButtonIfContains(int pageIndex, int buttonIndex) {
        return rightButtonsWithPages.get(pageIndex).containsKey(buttonIndex);
    }

    @Override
    public void setDisplayingPage(int pageIndex) {
        if (pageIndex < 0) throw new IllegalStateException("page index cannot less than 0!");
        if (pageIndex >= pageAmount) throw new IllegalStateException("page index out of bounds!");
        this.displayingPageIndex = pageIndex;
        initOrRefreshInventoryContents();
    }

    @Override
    public void nextDisplayingPage() {
        setDisplayingPage(++this.displayingPageIndex);
    }

    @Override
    public void lastDisplayingPage() {
        setDisplayingPage(--this.displayingPageIndex);
    }

    public void initOrRefreshInventoryContents() {
        Bukkit.broadcast(Component.text(String.format("你正在查看第 %s 页", displayingPageIndex)));
        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, new ItemStack(Material.AIR));
        }
        for (int index : itemsMapWithPages.get(displayingPageIndex).keySet()) {
            inventory.setItem(index, itemsMapWithPages.get(displayingPageIndex).get(index));
        }
    }

    @Override
    public int getDisplayingPage() {
        return displayingPageIndex;
    }

    public void addButton(int page, int index, ClickType clickType, BasicButton button) {
        if (clickType == ClickType.LEFT) {
            leftButtonsWithPages.get(page).put(index, button);
        } else if (clickType == ClickType.RIGHT) {
            rightButtonsWithPages.get(page).put(index, button);
        }
    }

    public void removeButton(int page, int index, ClickType clickType) {
        if (clickType == ClickType.LEFT) {
            leftButtonsWithPages.get(page).remove(index);
        } else if (clickType == ClickType.RIGHT) {
            rightButtonsWithPages.get(page).remove(index);
        }
    }

    /**
     * get button action runnable,
     * which should be applied to the specific inventory grid by using addLeftButton() or addRightButton()
     */
    public BasicButton getAppliedButton(int page, int index, @NotNull ClickType clickType) {
        BasicButton result = null;
        try {
            if (clickType == ClickType.LEFT) {
                result = leftButtonsWithPages.get(page).get(index);
            } else if (clickType == ClickType.RIGHT) {
                result = rightButtonsWithPages.get(page).get(index);
            }
        } catch (Exception e) { LogUtils.logFatal(e); }
        return result;
    }
}
