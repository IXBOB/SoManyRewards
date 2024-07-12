package com.ixbob.somanyrewards.ui;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public class RewardOfBasicGameTimeUI extends BasicPageableUI {

    public RewardOfBasicGameTimeUI(Player owner, int lineAmount, int pageAmount, int displayingPageIndex) {
        super(owner, lineAmount, pageAmount, displayingPageIndex);
    }

    @Override
    protected BasicPageableUI.ButtonRegistries[] getButtonRegistries() {
        return ButtonRegistriesImpl.values();
    }

    public enum ButtonRegistriesImpl implements BasicPageableUI.ButtonRegistries {
        HELLO_WORLD(0, 0, ClickType.LEFT, new HelloWorldLeftButtonRunnable(false)),
        NEXT_PAGE(0, 1, ClickType.LEFT, new NextPageLeftButtonRunnable(true)),

        BYEBYE_WORLD(1, 0, ClickType.LEFT, new ByebyeWorldLeftButtonRunnable(false)),
        LAST_PAGE(1, 1, ClickType.LEFT, new LastPageLeftButtonRunnable(true));

        private final int pageIndex;
        private final int index;
        private final ClickType clickType;
        private final ButtonRunnable action;

        ButtonRegistriesImpl(int pageIndex, int index, ClickType clickType, ButtonRunnable action) {
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
