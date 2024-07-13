package com.ixbob.somanyrewards.playerdata;

import com.ixbob.somanyrewards.SoManyRewards;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.*;
import java.util.logging.Level;

public class PlayerDataManager {

    private static PlayerDataManager instance;
    private static final String playerDataPath = SoManyRewards.getInstance().getDataFolder().getAbsolutePath() + "\\player_data";
    private final File playerDataFolder;
    private final HashMap<Player, PlayerDataBlock> playerDataBlocks = new HashMap<>();
    private List<UUID> registeredUUIDs = new ArrayList<>();

    public PlayerDataManager() {
        this.playerDataFolder = new File(playerDataPath);
        if (!playerDataFolder.exists()) {
            boolean mkdirSuccess = playerDataFolder.mkdirs();
            if (!mkdirSuccess) {
                throw new RuntimeException("Failed to create directory " + playerDataFolder.getAbsolutePath());
            }
        }
        File[] fileList = playerDataFolder.listFiles();
        if (fileList != null) {
            for (File file : fileList) {
                if (file.isFile()) {
                    registeredUUIDs.add(UUID.fromString(file.getName()));
                }
            }
        }
    }

    public static PlayerDataManager getInstance() {
        if (instance == null) {
            instance = new PlayerDataManager();
        }
        return instance;
    }

    public PlayerDataBlock getPlayerDataBlock(Player player) {
        if (!playerDataBlocks.containsKey(player)) {
            try {
                if (!registeredUUIDs.contains(player.getUniqueId())) {
                    firstRegister(player);
                    return new PlayerDataBlock(player);
                } else {
                    File dataFile = getDataFile(player);
                    FileReader fileReader = new FileReader(dataFile);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String line = bufferedReader.readLine();
                    int claimedBasicGameTime = Integer.parseInt(StringUtils.substringAfterLast(line, ":"));
                    return new PlayerDataBlock(player, claimedBasicGameTime);
                }
            } catch (IOException e) {
                SoManyRewards.getInstance().getLogger().log(Level.SEVERE, e.getMessage());
                SoManyRewards.getInstance().getLogger().log(Level.SEVERE, Arrays.toString(e.getStackTrace()));
            }
        }
        return playerDataBlocks.get(player);
    }

    private void firstRegister(Player player) throws IOException{
        createPlayerDataFile(player);
        registeredUUIDs.add(player.getUniqueId());
    }

    private void createPlayerDataFile(Player player) throws IOException {
        File playerDataFile = getDataFile(player);
        if (!playerDataFile.createNewFile()) {
            throw new RuntimeException("Player data file already exists for player: " + player.getName() + " UUID: " +player.getUniqueId().toString() + ", but didn't registered in the instance. Have you changed the data folder after the server startup?");
        }
        FileWriter fileWriter = new FileWriter(playerDataFile);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(Data.CLAIMED_REWARDS_OF_BASIC_GAME_TIME.getKey() + 0);
        bufferedWriter.close();
    }

    public @NotNull File getDataFile(Player player){
        String fileName = player.getUniqueId().toString();
        return new File(playerDataFolder, fileName);
    }
}
