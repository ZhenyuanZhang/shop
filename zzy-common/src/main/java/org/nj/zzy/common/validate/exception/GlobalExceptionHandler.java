package org.nj.zzy.common.validate.exception;

import org.nj.zzy.common.http.ResponseBean;
import org.nj.zzy.common.util.MessagesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 拦截自定义的异常
     */
    @ExceptionHandler(BaseException.class)
    public ResponseBean<?> handleBaseException(BaseException e) {
        String message = MessagesUtil.getMessage(e.getErrorMsg(), e.getParams());
        return ResponseBean.getNoData(e.getCode(), message);
    }
}
