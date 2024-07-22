package com.ixbob.somanyrewards.ui.button;

import com.ixbob.somanyrewards.annotation.RequireNotNull;
import com.ixbob.somanyrewards.enums.BasicGameTimeRewardType;
import com.ixbob.somanyrewards.util.AnnotationUtils;
import org.bukkit.entity.Player;

public class ClaimBasicPlayTimeRewardButtonFactory implements ButtonFactory {

    private static final ClaimBasicPlayTimeRewardButtonFactory instance = new ClaimBasicPlayTimeRewardButtonFactory();

    @RequireNotNull
    private Player player;
    @RequireNotNull
    private BasicGameTimeRewardType type;
    @RequireNotNull
    private Integer id;
    @RequireNotNull
    private Integer pageIndex;
    @RequireNotNull
    private Integer invIndex;

    public static ClaimBasicPlayTimeRewardButtonFactory getInstance() {
        return instance;
    }

    @Override
    public BasicButton create() {
        AnnotationUtils.validateFields(this);
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
