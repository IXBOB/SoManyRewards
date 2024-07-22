package com.ixbob.somanyrewards.ui.button;

import com.ixbob.somanyrewards.enums.BasicGameTimeRewardType;
import org.bukkit.entity.Player;

public class ClaimBasicPlayTimeRewardButtonFactory implements ButtonFactory {

    private static final ClaimBasicPlayTimeRewardButtonFactory instance = new ClaimBasicPlayTimeRewardButtonFactory();
    private Player player;
    private BasicGameTimeRewardType type;
    private int id;
    private int pageIndex;
    private int invIndex;

    public static ClaimBasicPlayTimeRewardButtonFactory getInstance() {
        return instance;
    }

    @Override
    public BasicButton create() {
        return new ClaimBasicPlayTimeRewardButton(player, type, id, pageIndex, invIndex);
    }

    @Override
    public ClaimBasicPlayTimeRewardButtonFactory setPlayer(Player player) {
        this.player = player;
        return this;
    }

    public ClaimBasicPlayTimeRewardButtonFactory setType(BasicGameTimeRewardType type) {
        this.type = type;
        return this;
    }

    public ClaimBasicPlayTimeRewardButtonFactory setId(int id) {
        this.id = id;
        return this;
    }

    public ClaimBasicPlayTimeRewardButtonFactory setData(int pageIndex, int invIndex) {
        this.pageIndex = pageIndex;
        this.invIndex = invIndex;
        return this;
    }
}
