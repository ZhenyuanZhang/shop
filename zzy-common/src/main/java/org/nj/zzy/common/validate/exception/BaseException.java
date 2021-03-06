package org.nj.zzy.common.validate.exception;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = 3764069094189685349L;

    private int code;

    private String errorMsg;

    private Object[] params;

    public BaseException(int code, String message, Object... params) {
        super(message);
        this.code = code;
        this.errorMsg = message;
        this.params = params;
    }
}
