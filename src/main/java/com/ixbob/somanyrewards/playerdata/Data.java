package com.ixbob.somanyrewards.playerdata;

public enum Data {
    CLAIMED_REWARDS_OF_BASIC_GAME_TIME(0, "claimed_rewards_of_basic_game_time:");

    private final int lineIndex;
    private final String key;

    private Data(int lineIndex, String key) {
        this.lineIndex = lineIndex;
        this.key = key;
    }

    public int getLineIndex() {
        return lineIndex;
    }

    public String getKey() {
        return key;
    }
}
