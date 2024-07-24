package com.ixbob.somanyrewards.config.bean.holder;

import com.ixbob.somanyrewards.config.bean.BasicGameTimeConfigRewardsConfigBean;
import com.ixbob.somanyrewards.enums.BasicGameTimeRewardType;
import com.ixbob.somanyrewards.util.LogUtils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public abstract class ConfigBasicGameTimeBeansHolder implements BeansHolder<BasicGameTimeConfigRewardsConfigBean> {

    private final List<BasicGameTimeConfigRewardsConfigBean> beans = new ArrayList<>();

    @Override
    public void addBean(@NotNull BasicGameTimeConfigRewardsConfigBean bean) {
        try {
            if (getBean(bean.getType(), bean.getId()) == null) {
                beans.add(bean);
                return;
            }
            throw new IllegalAccessException("The id of the bean to be added conflicts with the id of an existing bean.");
        } catch (IllegalAccessException e) {
            LogUtils.logFatal(e);
        }
    }

    public BasicGameTimeConfigRewardsConfigBean getBean(BasicGameTimeRewardType rewardType, int id) {
        return beans.stream().filter(bean -> (bean.getType() == rewardType && bean.getId() == id)).findFirst().orElse(null);
    }

    public <T extends BasicGameTimeConfigRewardsConfigBean> List<T> getBeans(BasicGameTimeRewardType rewardType, Class<T> clazz) {
        return new ArrayList<>(beans.stream()
                .filter(obj -> obj.getType() == rewardType)
                .map(clazz::cast)
                .toList());
    }

    public void clearHoldingBeans() {
        beans.clear();
    }
}
