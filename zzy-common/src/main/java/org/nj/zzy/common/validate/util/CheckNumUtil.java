package org.nj.zzy.common.validate.util;

public final class CheckNumUtil {

    private CheckNumUtil() {
    }

    public static boolean isInRange(int target, int min, int max) {
        return target >= min && target <= max;
    }

}
