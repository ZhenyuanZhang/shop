package org.nj.zzy.common.util;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

import org.nj.zzy.common.validate.exception.BaseException;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Zhenyuan Zhang
 * @time 2020/6/4 9:38
 */
public final class CustomFileUtil {

    private CustomFileUtil() {}

    public static InputStream convert2InputStream(MultipartFile file) {
        try {
            return file.getInputStream();
        } catch (IOException e) {
            throw new BaseException(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
