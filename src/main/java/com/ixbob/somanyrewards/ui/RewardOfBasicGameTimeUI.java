package com.ixbob.somanyrewards.ui;

import com.ixbob.somanyrewards.SoManyRewards;
import com.ixbob.somanyrewards.manager.UIManager;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;

public class RewardOfBasicGameTimeUI extends BasicUI implements PageableUI{

    private int displayingPageIndex = 0;
    private ArrayList<ArrayList<Integer>> leftButtonsWithPages = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> rightButtonsWithPages = new ArrayList<>();
    private ArrayList<HashMap<Integer, ItemStack>> itemsMapWithPages = new ArrayList<>();

    public RewardOfBasicGameTimeUI(Player owner, int lineAmount, int pageAmount, int displayingPageIndex) {
        super(lineAmount, owner);
        for (int i = 1; i <= pageAmount; i++) {
            itemsMapWithPages.add(new HashMap<>());
            leftButtonsWithPages.add(new ArrayList<>());
            rightButtonsWithPages.add(new ArrayList<>());
        }
        this.displayingPageIndex = displayingPageIndex;
    }

    @Override
    public void onClick(int index, ClickType clickType, InventoryClickEvent event) {
        super.onClick(index, clickType, event);
        if ( (clickType == ClickType.LEFT && checkLeftButtonIfContains(displayingPageIndex, index))
                || (clickType == ClickType.RIGHT && checkRightButtonIfContains(displayingPageIndex, index)) ) {
            boolean success = searchAndPerformButtonAction(displayingPageIndex, index, clickType);
            if (!success) {
                throw new IllegalArgumentException("button action not registered in ButtonRegistries enum!");
            }
        }
        event.setCancelled(true);
    }

    private boolean searchAndPerformButtonAction(int pageIndex, int index, ClickType clickType) {
        for (ButtonRegistries buttonRegistry : ButtonRegistries.values()) {
            if (buttonRegistry.getPageIndex() == pageIndex
                    && buttonRegistry.getIndex() == index
                    && buttonRegistry.getClickType() == clickType) {
                ButtonRunnable action = buttonRegistry.getAction();
                if (action.needRunnerPlayer) action.setRunnerPlayer(owner);
                Bukkit.getScheduler().runTask(SoManyRewards.getInstance(), action);
                return true;
            }
        }
        return false;
    }

    public void setDisplayItem(int pageIndex, int index, ItemStack item) {
        removeDisplayItem(pageIndex, index);
        itemsMapWithPages.get(pageIndex).put(index, item);
    }

    public void removeDisplayItem(int pageIndex, int index) {
        itemsMapWithPages.get(pageIndex).remove(index);
    }

    public boolean checkLeftButtonIfContains(int pageIndex, int buttonIndex) {
        return leftButtonsWithPages.get(pageIndex).contains(buttonIndex);
    }

    public boolean checkRightButtonIfContains(int pageIndex, int buttonIndex) {
        return rightButtonsWithPages.get(pageIndex).contains(buttonIndex);
    }

    @Override
    public void setDisplayingPage(int pageIndex) {
        if (pageIndex < 0) throw new IllegalStateException("page index cannot less than 0!");
        this.displayingPageIndex = pageIndex;
        initOrRefreshInventoryContents();

    }

    public void nextDisplayingPage() {
        setDisplayingPage(++this.displayingPageIndex);
    }

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

    public void addLeftButton(int page, int index) {
        leftButtonsWithPages.get(page).add(index);
    }

    public void removeLeftButton(int page, int index) {
        leftButtonsWithPages.get(page).remove(index);
    }

    public void addRightButton(int page, int index) {
        rightButtonsWithPages.get(page).add(index);
    }

    public void removeRightButton(int page, int index) {
        rightButtonsWithPages.get(page).remove(index);
    }

    public enum ButtonRegistries{
        HELLO_WORLD(0, 0, ClickType.LEFT, new HelloWorldLeftButtonRunnable(false)),
        NEXT_PAGE(0, 1, ClickType.LEFT, new NextPageLeftButtonRunnable(true)),

        BYEBYE_WORLD(1, 0, ClickType.LEFT, new ByebyeWorldLeftButtonRunnable(false)),
        LAST_PAGE(1, 1, ClickType.LEFT, new LastPageLeftButtonRunnable(true));

        private final int pageIndex;
        private final int index;
        private final ClickType clickType;
        private final ButtonRunnable action;

        ButtonRegistries(int pageIndex, int index, ClickType clickType, ButtonRunnable action) {
            this.pageIndex = pageIndex;
            this.index = index;
            this.clickType = clickType;
            this.action = action;
        }

        public int getPageIndex() {
            return pageIndex;
        }

        public int getIndex() {
            return index;
        }

        public ClickType getClickType() {
            return clickType;
        }

        public ButtonRunnable getAction() {
            return action;
        }
    }

    private static class HelloWorldLeftButtonRunnable extends ButtonRunnable {
        public HelloWorldLeftButtonRunnable(boolean needRunnerPlayer) {
            super(needRunnerPlayer);
        }
        @Override
        public void run() {
            super.run();
            Bukkit.broadcast(Component.text("Hello world!"));
        }
    }

    private static class NextPageLeftButtonRunnable extends ButtonRunnable {
        public NextPageLeftButtonRunnable(boolean needRunnerPlayer) {
            super(needRunnerPlayer);
        }
        @Override
        public void run() {
            super.run();
            if (UIManager.getInstance().getOpeningCustomUI(runnerPlayer) instanceof RewardOfBasicGameTimeUI ui) {
                Bukkit.broadcast(Component.text("next page!"));
                ui.nextDisplayingPage();
            }
        }
    }

    private static class ByebyeWorldLeftButtonRunnable extends ButtonRunnable {
        public ByebyeWorldLeftButtonRunnable(boolean needRunnerPlayer) {
            super(needRunnerPlayer);
        }
        @Override
        public void run() {
            super.run();
            Bukkit.broadcast(Component.text("Byebye world!"));
        }
    }

    private static class LastPageLeftButtonRunnable extends ButtonRunnable {
        public LastPageLeftButtonRunnable(boolean needRunnerPlayer) {
            super(needRunnerPlayer);
        }
        @Override
        public void run() {
            super.run();
            if (UIManager.getInstance().getOpeningCustomUI(runnerPlayer) instanceof RewardOfBasicGameTimeUI ui) {
                Bukkit.broadcast(Component.text("last page!"));
                ui.lastDisplayingPage();
            }
        }
    }
}
