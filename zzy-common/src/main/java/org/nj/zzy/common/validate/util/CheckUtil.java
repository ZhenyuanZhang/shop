package org.nj.zzy.common.validate.util;

import org.nj.zzy.common.validate.exception.BaseException;

public final class CheckUtil {

    private CheckUtil() {
    }

    public static void checkValidate(boolean throwException, int code, String errorMsg, Object... params) {
        if (throwException) {
            throw new BaseException(code, errorMsg, params);
        }
    }

    public static void checkBadRequest(boolean throwException, String errorMsg, Object... params) {
        checkValidate(throwException, 400, errorMsg, params);
    }

    public static void checkBusinessException(boolean throwException, String errorMsg, Object... params) {
        checkValidate(throwException, 500, errorMsg, params);
    }
}
