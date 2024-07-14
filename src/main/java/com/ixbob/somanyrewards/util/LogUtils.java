package com.ixbob.somanyrewards.util;

import com.ixbob.somanyrewards.SoManyRewards;

import java.util.Arrays;
import java.util.logging.Level;

public class LogUtils {
    public static void logFatal(Exception e) {
        SoManyRewards.getInstance().getLogger().log(Level.SEVERE, e.getMessage());
        SoManyRewards.getInstance().getLogger().log(Level.SEVERE, Arrays.toString(e.getStackTrace()));
    }
}
