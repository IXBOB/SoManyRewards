package com.ixbob.somanyrewards.config.bean.holder;

import com.ixbob.somanyrewards.config.bean.BasicConfigBean;
import org.jetbrains.annotations.NotNull;

public interface BeansHolder<T extends BasicConfigBean> {

    void addBean(@NotNull T bean);


}
