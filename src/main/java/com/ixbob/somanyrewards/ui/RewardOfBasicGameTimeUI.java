package com.ixbob.somanyrewards.ui;

import com.ixbob.somanyrewards.config.ConfigSubHolderBasicGameTime;
import com.ixbob.somanyrewards.config.bean.ConfigBeanBasicGameTimeNormalRewards;
import com.ixbob.somanyrewards.config.bean.ConfigBeanBasicGameTimeSpecialRewards;
import com.ixbob.somanyrewards.enums.BasicGameTimeRewardType;
import com.ixbob.somanyrewards.lang.LangManager;
import com.ixbob.somanyrewards.playerdata.PlayerDataBlock;
import com.ixbob.somanyrewards.playerdata.PlayerDataManager;
import com.ixbob.somanyrewards.ui.button.ClaimBasicPlayTimeRewardButtonFactory;
import com.ixbob.somanyrewards.ui.button.IPageControlButtonFactory;
import com.ixbob.somanyrewards.ui.button.PageControlButtonFactory;
import com.ixbob.somanyrewards.util.ItemUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.Iterator;

public class RewardOfBasicGameTimeUI extends BasicPageableUI {

    final ConfigSubHolderBasicGameTime configSubHolder = ConfigSubHolderBasicGameTime.getInstance();
    final ItemStack NORMAL_REWARDS_DEFAULT_DISPLAY_STACK = new ItemStack(
            configSubHolder.getNormalRewardsDefaultDisplayMaterial());
    final ItemStack SPECIAL_REWARDS_DEFAULT_DISPLAY_STACK = new ItemStack(
            configSubHolder.getSpecialRewardsDefaultDisplayMaterial());

    public RewardOfBasicGameTimeUI(Player owner, int lineAmount, int pageAmount, int displayingPageIndex) {
        super(owner, lineAmount, pageAmount, displayingPageIndex);
    }

    @Override
    public void buildUIInitialDisplay() {
        final PlayerDataBlock dataBlock = PlayerDataManager.getInstance().getPlayerDataBlock(super.owner);
        //TODO: use Statistics when release. this is for test only now.
        final int playTimeMinutes = 225;
        final int claimedBasicPlayTime = dataBlock.getClaimedBasicGameTime();
        final int eachTimePeriodShown = configSubHolder.getEachTimePeriodShown();

        //lease: all normal and all special that able to be claimed till now.
        int leastNodeAmount = configSubHolder.getBeans(
                BasicGameTimeRewardType.SPECIAL,
                ConfigBeanBasicGameTimeNormalRewards.class).size()
                + (playTimeMinutes / eachTimePeriodShown);
        int createNodeAmount = leastNodeAmount + (9 - leastNodeAmount % 9);
        int createPageAmount = createNodeAmount / 9;

        Iterator<ConfigBeanBasicGameTimeNormalRewards> normalBeansIter = configSubHolder.getBeans(
                BasicGameTimeRewardType.NORMAL,
                ConfigBeanBasicGameTimeNormalRewards.class).iterator();
        Iterator<ConfigBeanBasicGameTimeSpecialRewards> specialBeansIter = configSubHolder.getBeans(
                BasicGameTimeRewardType.SPECIAL,
                ConfigBeanBasicGameTimeSpecialRewards.class).iterator();
        ConfigBeanBasicGameTimeNormalRewards selectedNormal = null;
        ConfigBeanBasicGameTimeSpecialRewards selectedSpecial = null;

        int creatingNormalIdTotal = -1;  // how much normal rewards we've created
        int creatingSpecialIdTotal = -1; // how much special rewards we've created
        int timeOfCreatingReward = 0;
        Integer nextSpecialTime = null;

        for (int page = 0; page < createPageAmount; page++) {
            //add display reward displays.
            for (int index = 0; index <= 8; index++) {
                if (selectedNormal == null) {
                    if (!normalBeansIter.hasNext()) {
                        normalBeansIter = configSubHolder.getBeans(
                                BasicGameTimeRewardType.NORMAL,
                                ConfigBeanBasicGameTimeNormalRewards.class).iterator();
                    }
                    selectedNormal = normalBeansIter.next();
                    System.out.println(selectedNormal);
                }
                if (selectedSpecial == null && specialBeansIter.hasNext()) {
                    selectedSpecial = specialBeansIter.next();
                    nextSpecialTime = selectedSpecial.getDisplayWhen();
                }

                if (selectedSpecial != null) {
                    if (timeOfCreatingReward + eachTimePeriodShown >= nextSpecialTime) {
                        creatingSpecialIdTotal++;
                        setDisplayItem(page, index, ItemUtils.getNamedItemStack(
                                SPECIAL_REWARDS_DEFAULT_DISPLAY_STACK,
                                owner,
                                selectedSpecial.getLocalItemTitle(),
                                selectedSpecial.getLocalItemLores()));
                        addButton(page, index, ClickType.LEFT, ClaimBasicPlayTimeRewardButtonFactory.getInstance()
                                .setPlayer(owner)
                                .setType(BasicGameTimeRewardType.SPECIAL)
                                .setId(creatingSpecialIdTotal)
                                .setPos(page, index)
                                .setCommands(configSubHolder
                                        .getBean(BasicGameTimeRewardType.SPECIAL, creatingSpecialIdTotal)
                                        .getRewardCommands())
                                .create());
                        selectedSpecial = null;
                        continue;
                    }
                }

                creatingNormalIdTotal++;
                timeOfCreatingReward += eachTimePeriodShown;
                setDisplayItem(page, index, ItemUtils.getNamedItemStack(
                        NORMAL_REWARDS_DEFAULT_DISPLAY_STACK,
                        owner,
                        selectedNormal.getLocalItemTitle(),
                        selectedNormal.getLocalItemLores()));
                addButton(page, index, ClickType.LEFT, ClaimBasicPlayTimeRewardButtonFactory.getInstance()
                        .setPlayer(owner)
                        .setType(BasicGameTimeRewardType.NORMAL)
                        .setId(creatingNormalIdTotal)
                        .setPos(page, index)
                        .setCommands(configSubHolder
                                .getBean(BasicGameTimeRewardType.NORMAL, getCreatingNormalIdCycle(creatingNormalIdTotal))
                                .getRewardCommands())
                        .create());
                selectedNormal = null;
            }

            //add next/previous page button.
            if (page != 0) {
                setDisplayItem(page, 18, ItemUtils.getNamedItemStack(
                        new ItemStack(Material.PAPER),
                        owner,
                        LangManager.getInstance().getLang(owner, "ui_display_previous_page"),
                        null));
                addButton(page, 18, ClickType.LEFT, PageControlButtonFactory.getInstance()
                        .setType(IPageControlButtonFactory.ButtonType.PREVIOUS)
                        .setPlayer(owner)
                        .create());
            }
            if (page != createPageAmount - 1) {
                setDisplayItem(page, 26, ItemUtils.getNamedItemStack(
                        new ItemStack(Material.PAPER),
                        owner,
                        LangManager.getInstance().getLang(owner, "ui_display_next_page"),
                        null));
                addButton(page, 26, ClickType.LEFT, PageControlButtonFactory.getInstance()
                        .setType(IPageControlButtonFactory.ButtonType.NEXT)
                        .setPlayer(owner)
                        .create());
            }
        }
    }

    private int getCreatingNormalIdCycle(int creatingNormalIdTotal) {
        return creatingNormalIdTotal % configSubHolder.getNormalRewardsAmount();
    }
}
