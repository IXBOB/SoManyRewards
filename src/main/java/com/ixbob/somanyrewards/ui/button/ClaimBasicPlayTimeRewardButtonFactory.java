package com.ixbob.somanyrewards.ui.button;

import com.ixbob.somanyrewards.annotation.RequireNotNull;
import com.ixbob.somanyrewards.enums.BasicGameTimeRewardType;
import com.ixbob.somanyrewards.util.AnnotationUtils;
import org.bukkit.entity.Player;

import java.util.List;

public class ClaimBasicPlayTimeRewardButtonFactory implements ButtonFactory {

    private static final ClaimBasicPlayTimeRewardButtonFactory instance = new ClaimBasicPlayTimeRewardButtonFactory();

    @RequireNotNull
    private Player player;
    @RequireNotNull
    private BasicGameTimeRewardType type;
    /**
     * id: records the number of buttons of the category to which the button belongs
     * that have been created (excluding itself) when the button is created.
     * For example, when a normal button is created, there are already 2 normal buttons,
     * so the id of this button is 2.
     * (That is, the id starts from 0. So type+id can distinguish the identity of each button)
     */
    @RequireNotNull
    private Integer id;
    @RequireNotNull
    private Integer pageIndex;
    @RequireNotNull
    private Integer invIndex;
    @RequireNotNull
    private List<String> commands;

    private ClaimBasicPlayTimeRewardButtonFactory() {}

    public static ClaimBasicPlayTimeRewardButtonFactory getInstance() {
        return instance;
    }

    @Override
    public BasicButton create() {
        AnnotationUtils.validateFields(this);
        return new ClaimBasicPlayTimeRewardButton(player, type, id, pageIndex, invIndex, commands);
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

    public ClaimBasicPlayTimeRewardButtonFactory setPos(int pageIndex, int invIndex) {
        this.pageIndex = pageIndex;
        this.invIndex = invIndex;
        return this;
    }

    public ClaimBasicPlayTimeRewardButtonFactory setCommands(List<String> commands) {
        this.commands = commands;
        return this;
    }
}
