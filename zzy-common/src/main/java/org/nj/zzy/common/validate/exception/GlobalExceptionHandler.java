package org.nj.zzy.common.validate.exception;

import java.util.Locale;

import org.nj.zzy.common.http.ResponseBean;
import org.nj.zzy.common.http.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @Autowired
    private MessageSource messageSource;

    /**
     * 拦截自定义的异常
     */
    @ExceptionHandler(BaseException.class)
    public ResponseBean<?> handleBaseException(BaseException e) {
        Locale locale = ThreadLocalUtil.getContext().getLocale();
        String message = messageSource.getMessage(e.getMessage(), e.getParams(), locale);
        return ResponseBean.getNoData(e.getCode(), message);
    }
}
