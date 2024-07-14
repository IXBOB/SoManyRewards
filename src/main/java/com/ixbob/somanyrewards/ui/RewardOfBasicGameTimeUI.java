package com.ixbob.somanyrewards.ui;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class RewardOfBasicGameTimeUI extends BasicPageableUI {

    public RewardOfBasicGameTimeUI(Player owner, int lineAmount, int pageAmount, int displayingPageIndex) {
        super(owner, lineAmount, pageAmount, displayingPageIndex);
    }

    @Override
    protected BasicPageableUI.ButtonRegistries[] getButtonRegistries() {
        return ButtonRegistriesImpl.values();
    }

    public enum ButtonRegistriesImpl implements BasicPageableUI.ButtonRegistries {
        HELLO_WORLD(new HelloWorldLeftButtonRunnable(false)),
        NEXT_PAGE_1(new NextPageLeftButtonRunnable(true)),
//        NEXT_PAGE_2(1, 22, ClickType.LEFT, new NextPageLeftButtonRunnable(true)),
//        NEXT_PAGE_3(2, 22, ClickType.LEFT, new NextPageLeftButtonRunnable(true)),

        BYEBYE_WORLD(new ByebyeWorldLeftButtonRunnable(false)),
        LAST_PAGE(new LastPageLeftButtonRunnable(true));

        private final ButtonRunnable action;

        ButtonRegistriesImpl(ButtonRunnable action) {
            this.action = action;
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
