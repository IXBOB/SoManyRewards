package com.ixbob.somanyrewards.ui;

import com.ixbob.somanyrewards.config.BasicGameTimeConfigSubHolder;
import com.ixbob.somanyrewards.config.ConfigHolder;
import com.ixbob.somanyrewards.config.bean.BasicGameTimeConfigRewardsConfigBean;
import com.ixbob.somanyrewards.config.bean.BasicGameTimeNormalRewardsConfigBean;
import com.ixbob.somanyrewards.config.bean.BasicGameTimeSpecialRewardsConfigBean;
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

    final ConfigHolder configHolder = ConfigHolder.getInstance();
    final BasicGameTimeConfigSubHolder configSubHolder = BasicGameTimeConfigSubHolder.getInstance();
    final ItemStack NORMAL_REWARDS_DEFAULT_DISPLAY_STACK = new ItemStack(
            configHolder.getNormalRewardsDefaultDisplayMaterial());
    final ItemStack SPECIAL_REWARDS_DEFAULT_DISPLAY_STACK = new ItemStack(
            configHolder.getSpecialRewardsDefaultDisplayMaterial());

    public RewardOfBasicGameTimeUI(Player owner, int lineAmount, int pageAmount, int displayingPageIndex) {
        super(owner, lineAmount, pageAmount, displayingPageIndex);
    }

    @Override
    public void buildUIInitialDisplay() {
        final PlayerDataBlock dataBlock = PlayerDataManager.getInstance().getPlayerDataBlock(super.owner);
        //TODO: use Statistics when release. this is for test only now.
        final int playTimeMinutes = 225;
        final int claimedBasicPlayTime = dataBlock.getClaimedBasicGameTime();
        final int eachTimePeriodShown = configHolder.getEachTimePeriodShown();

        //lease: all normal and all special that able to be claimed till now.
        int leastNodeAmount = configSubHolder.getBeans(
                BasicGameTimeConfigRewardsConfigBean.RewardType.SPECIAL,
                BasicGameTimeNormalRewardsConfigBean.class).size()
                + (playTimeMinutes / eachTimePeriodShown);
        int createNodeAmount = leastNodeAmount + (9 - leastNodeAmount % 9);
        int createPageAmount = createNodeAmount / 9;

        Iterator<BasicGameTimeNormalRewardsConfigBean> normalBeansIter = configSubHolder.getBeans(
                BasicGameTimeConfigRewardsConfigBean.RewardType.NORMAL,
                BasicGameTimeNormalRewardsConfigBean.class).iterator();
        Iterator<BasicGameTimeSpecialRewardsConfigBean> specialBeansIter = configSubHolder.getBeans(
                BasicGameTimeConfigRewardsConfigBean.RewardType.SPECIAL,
                BasicGameTimeSpecialRewardsConfigBean.class).iterator();
        BasicGameTimeNormalRewardsConfigBean selectedNormal = null;
        BasicGameTimeSpecialRewardsConfigBean selectedSpecial = null;

        int creatingNormalId = -1;
        int creatingSpecialId = -1;
        int timeOfCreatingReward = 0;
        Integer nextSpecialTime = null;

        for (int page = 0; page < createPageAmount; page++) {
            //add display reward displays.
            for (int index = 0; index <= 8; index++) {
                if (selectedNormal == null) {
                    if (!normalBeansIter.hasNext()) {
                        normalBeansIter = configSubHolder.getBeans(
                                BasicGameTimeConfigRewardsConfigBean.RewardType.NORMAL,
                                BasicGameTimeNormalRewardsConfigBean.class).iterator();
                    }
                    selectedNormal = normalBeansIter.next();
                }
                if (selectedSpecial == null && specialBeansIter.hasNext()) {
                    selectedSpecial = specialBeansIter.next();
                    nextSpecialTime = selectedSpecial.getDisplayWhen();
                }

                if (selectedSpecial != null) {
                    if (timeOfCreatingReward + eachTimePeriodShown >= nextSpecialTime) {
                        creatingSpecialId++;
                        setDisplayItem(page, index, ItemUtils.getNamedItemStack(
                                SPECIAL_REWARDS_DEFAULT_DISPLAY_STACK,
                                owner,
                                selectedSpecial.getLocalItemTitle(),
                                selectedSpecial.getLocalItemLores()));
                        addButton(page, index, ClickType.LEFT, ClaimBasicPlayTimeRewardButtonFactory.getInstance()
                                .setData(page, index)
                                .setPlayer(owner)
                                .create());
                        selectedSpecial = null;
                        continue;
                    }
                }

                creatingNormalId++;
                timeOfCreatingReward += eachTimePeriodShown;
                setDisplayItem(page, index, ItemUtils.getNamedItemStack(
                        NORMAL_REWARDS_DEFAULT_DISPLAY_STACK,
                        owner,
                        selectedNormal.getLocalItemTitle(),
                        selectedNormal.getLocalItemLores()));
                addButton(page, index, ClickType.LEFT, ClaimBasicPlayTimeRewardButtonFactory.getInstance()
                        .setData(page, index)
                        .setPlayer(owner)
                        .create());
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
}
