package org.nj.zzy.common.validate.util;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import org.nj.zzy.common.constant.CommonConstant;
import org.nj.zzy.common.constant.CommonErrorConstant;
import org.nj.zzy.common.validate.exception.BaseException;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class CheckUtil {

    private CheckUtil() {}

    public static void checkValidate(boolean throwException, int code, String errorMsg, Object... params) {
        if (throwException) {
            log.error("BaseException:{}", errorMsg);
            throw new BaseException(code, errorMsg, params);
        }
    }

    public static void checkBadRequest(boolean throwException, String errorMsg, Object... params) {
        checkValidate(throwException, HttpServletResponse.SC_BAD_REQUEST, errorMsg, params);
    }

    public static void checkBusinessException(boolean throwException, String errorMsg, Object... params) {
        checkValidate(throwException, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, errorMsg, params);
    }

    // 文件Excel校验
    public static void checkExcelFile(MultipartFile file) {
        checkBadRequest(file == null || file.isEmpty(), CommonErrorConstant.CAN_NOT_BE_EMPTY, "excel");
        String suffix =
            Optional.ofNullable(file.getOriginalFilename()).map(f -> f.substring(f.lastIndexOf("."))).orElse(null);
        checkBadRequest(!CommonConstant.XLSX.equals(suffix), CommonErrorConstant.FILE_TYPE_INVALID,
            CommonConstant.XLSX);
    }
}
