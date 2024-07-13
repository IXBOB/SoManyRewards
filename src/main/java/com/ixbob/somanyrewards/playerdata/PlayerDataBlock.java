package com.ixbob.somanyrewards.playerdata;

import com.ixbob.somanyrewards.SoManyRewards;
import org.bukkit.entity.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;

public class PlayerDataBlock {
    private final Player player;
    private int claimedBasicGameTime;

    public PlayerDataBlock(Player player) {
        this.player = player;
        claimedBasicGameTime = 0;
    }

    public PlayerDataBlock(Player player, int claimedBasicGameTime) {
        this.player = player;
        this.claimedBasicGameTime = claimedBasicGameTime;
    }

    public Player getPlayer() {
        return player;
    }

    public int getClaimedBasicGameTime() {
        return claimedBasicGameTime;
    }

    /**
     * Called by Listener
     */
    public void setClaimedBasicGameTime(int newValue) {
        this.claimedBasicGameTime = newValue;
        updateDataFile(player, Data.CLAIMED_REWARDS_OF_BASIC_GAME_TIME, newValue);
    }

    private void updateDataFile(Player player, Data dataEnum, Object newValue){
        File dataFile = PlayerDataManager.getInstance().getDataFile(player);
        ArrayList<String> changedLines = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(dataFile))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains(dataEnum.getKey())) {
                    line = dataEnum.getKey() + newValue;
                }
                changedLines.add(line);
            }
        } catch (IOException e) {
            SoManyRewards.getInstance().getLogger().log(Level.SEVERE, e.getMessage());
            SoManyRewards.getInstance().getLogger().log(Level.SEVERE, Arrays.toString(e.getStackTrace()));
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(dataFile))) {
            for (String newLine : changedLines) {
                bufferedWriter.write(newLine);
                bufferedWriter.close();
            }
        } catch (IOException e) {
            SoManyRewards.getInstance().getLogger().log(Level.SEVERE, e.getMessage());
            SoManyRewards.getInstance().getLogger().log(Level.SEVERE, Arrays.toString(e.getStackTrace()));
        }
    }
}
