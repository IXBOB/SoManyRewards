package com.ixbob.somanyrewards.command;

import com.ixbob.somanyrewards.ui.button.PageControlButtonFactory;
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
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

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
                configHolder.getSpecialRewards().forEach(rewardListMap -> {
                    int displayWhen = (int) rewardListMap.get(3).get("display_when");
                    specialRewardTimeList.add(displayWhen);
                });
                int playedNeedLeastNodeAmount = (int) specialRewardTimeList.stream().filter(
                        element -> element <= playTimeMinutes).count()
                        + (playTimeMinutes / eachTimePeriodShown);
                int createNodeAmount = playedNeedLeastNodeAmount + (9 - playedNeedLeastNodeAmount % 9);
                int createPageAmount = createNodeAmount / 9;

                //start initializing UI.
                final ItemStack NORMAL_REWARDS_DEFAULT_DISPLAY_STACK = new ItemStack(
                        configHolder.getNormalRewardsDefaultDisplayMaterial());
                final ItemStack SPECIAL_REWARDS_DEFAULT_DISPLAY_STACK = new ItemStack(
                        configHolder.getSpecialRewardsDefaultDisplayMaterial());
                int creatingNodeTime = 0;
                int createdSpecialNodeAmount = 0;
                int createdNormalNodeAmount = 0;
                for (int page = 0; page < createPageAmount; page++) {
                    for (int index = 0; index <= 8; index++) {
                        if (!specialRewardTimeList.isEmpty()
                                && creatingNodeTime <= specialRewardTimeList.get(0)
                                && specialRewardTimeList.get(0) <= creatingNodeTime + eachTimePeriodShown) {
                            basicGameTimeUI.setDisplayItem(page, index, ItemUtils.getNamedItemStack(
                                    SPECIAL_REWARDS_DEFAULT_DISPLAY_STACK, player,
                                    (String) configHolder.getSpecialRewards().get(createdSpecialNodeAmount).get(1).get("local_item_title"),
                                    (ArrayList<String>) configHolder.getSpecialRewards().get(createdSpecialNodeAmount).get(2).get("local_item_lores")));
                            specialRewardTimeList.remove(0);
                            createdSpecialNodeAmount++;
                        } else {
                            creatingNodeTime += eachTimePeriodShown;
                            basicGameTimeUI.setDisplayItem(page, index, ItemUtils.getNamedItemStack(
                                    NORMAL_REWARDS_DEFAULT_DISPLAY_STACK, player,
                                    (String) configHolder.getNormalRewards().get(createdNormalNodeAmount % configHolder.getNormalRewards().size()).get(1).get("local_item_title"),
                                    (ArrayList<String>) configHolder.getNormalRewards().get(createdNormalNodeAmount % configHolder.getNormalRewards().size()).get(2).get("local_item_lores")));
                            createdNormalNodeAmount++;
                        }
                    }
                }

                //仅演示用，后续删除
                basicGameTimeUI.setDisplayItem(0, 22,
                        ItemUtils.getNamedItemStack(new ItemStack(Material.PAPER), player, "下一页", null));
                basicGameTimeUI.setDisplayItem(1, 22,
                        ItemUtils.getNamedItemStack(new ItemStack(Material.PAPER), player, "上一页", new ArrayList<>(List.of("test1", "test2"))));
                basicGameTimeUI.addButton(0, 22, ClickType.LEFT, PageControlButtonFactory.getInstance()
                        .setType(PageControlButtonFactory.ButtonType.NEXT)
                        .setPlayer(player)
                        .create());
                basicGameTimeUI.addButton(1, 22, ClickType.LEFT, PageControlButtonFactory.getInstance()
                        .setType(PageControlButtonFactory.ButtonType.PREVIOUS)
                        .setPlayer(player)
                        .create());

                UIManager.getInstance().openUI(player, basicGameTimeUI);
            }
        }
        return true;
    }


}
