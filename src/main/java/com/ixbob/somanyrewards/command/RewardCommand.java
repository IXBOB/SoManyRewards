package com.ixbob.somanyrewards.command;

import com.ixbob.somanyrewards.util.ItemUtils;
import com.ixbob.somanyrewards.config.ConfigHolder;
import com.ixbob.somanyrewards.playerdata.PlayerDataBlock;
import com.ixbob.somanyrewards.playerdata.PlayerDataManager;
import com.ixbob.somanyrewards.ui.UIManager;
import com.ixbob.somanyrewards.ui.RewardOfBasicGameTimeUI;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class RewardCommand implements CommandExecutor {

    private final ConfigHolder configHolder = ConfigHolder.getInstance();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String exactCommandStr, @NotNull String[] subs) {
        if (sender instanceof Player player) {
            if (subs[0].equals("BasicGameTime")) {
                RewardOfBasicGameTimeUI basicGameTimeUI = new RewardOfBasicGameTimeUI(player, 3, 2, 0);
                PlayerDataBlock dataBlock = PlayerDataManager.getInstance().getPlayerDataBlock(player);
                //TODO: use Statistics when release. this is for test only now.
                int playTimeMinutes = 225;
                int claimedBasicPlayTime = dataBlock.getClaimedBasicGameTime();
                int eachTimePeriodShown = configHolder.getEachTimePeriodShown();
                ArrayList<Integer> specialRewardTimeList = new ArrayList<>();
                System.out.println(configHolder.getSpecialRewards());
                configHolder.getSpecialRewards().forEach(rewardListMap -> {
                    int displayWhen = (int) rewardListMap.get(2).get("display_when");
                    System.out.println(displayWhen);
                    specialRewardTimeList.add(displayWhen);
                });
                int playedNeedLeastNodeAmount = (int) specialRewardTimeList.stream().filter(element -> element <= playTimeMinutes).count()
                        + (playTimeMinutes / eachTimePeriodShown);
                System.out.println(playedNeedLeastNodeAmount);
                int createNodeAmount = playedNeedLeastNodeAmount + (9 - playedNeedLeastNodeAmount % 9);
                int createPageAmount = createNodeAmount / 9;
                System.out.println(createNodeAmount);
                System.out.println(createPageAmount);

                final ItemStack NORMAL_REWARDS_DEFAULT_DISPLAY_STACK = new ItemStack(configHolder.getNormalRewardsDefaultDisplayMaterial());
                final ItemStack SPECIAL_REWARDS_DEFAULT_DISPLAY_STACK = new ItemStack(configHolder.getSpecialRewardsDefaultDisplayMaterial());
                int creatingNodeTime = 0;
                for (int page = 0; page < createPageAmount; page++) {
                    for (int index = 0; index <= 8; index++) {
                        if (!specialRewardTimeList.isEmpty()
                                && creatingNodeTime <= specialRewardTimeList.get(0)
                                && specialRewardTimeList.get(0) <= creatingNodeTime + eachTimePeriodShown) {
                            basicGameTimeUI.setDisplayItem(page, index, ItemUtils.getNamedItemStack(SPECIAL_REWARDS_DEFAULT_DISPLAY_STACK, String.valueOf(specialRewardTimeList.get(0))));
                            specialRewardTimeList.remove(0);
                        } else {
                            creatingNodeTime += eachTimePeriodShown;
                            basicGameTimeUI.setDisplayItem(page, index, ItemUtils.getNamedItemStack(NORMAL_REWARDS_DEFAULT_DISPLAY_STACK, String.valueOf(creatingNodeTime)));
                        }
                    }
                }

                //仅演示用，后续删除
                basicGameTimeUI.setDisplayItem(0, 22, ItemUtils.getNamedItemStack(new ItemStack(Material.PAPER), "下一页"));
                basicGameTimeUI.setDisplayItem(1, 22, ItemUtils.getNamedItemStack(new ItemStack(Material.PAPER), "上一页"));
                basicGameTimeUI.addLeftButton(0, 22, RewardOfBasicGameTimeUI.ButtonRegistriesImpl.NEXT_PAGE_1);
                basicGameTimeUI.addLeftButton(1, 22, RewardOfBasicGameTimeUI.ButtonRegistriesImpl.LAST_PAGE);
                UIManager.getInstance().openUI(player, basicGameTimeUI);
            }
        }
        return true;
    }


}
