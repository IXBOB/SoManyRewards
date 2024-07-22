package com.ixbob.somanyrewards.util;

import com.ixbob.somanyrewards.annotation.RequireNotNull;

import java.lang.reflect.Field;

public class AnnotationUtils {

    /**
     * {@link com.ixbob.somanyrewards.annotation.RequireNotNull}
     */
    public static void validateFields(Object instance) {
        for (Field field : instance.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(RequireNotNull.class)) {
                try {
                    field.setAccessible(true);
                    if (field.get(instance) == null) {
                        throw new IllegalStateException("Field '" + field.getName() + "' can't be null!");
                    }
                } catch (IllegalAccessException | IllegalArgumentException e) {
                    LogUtils.logFatal(e);
                }
            }
        }
    }
}
