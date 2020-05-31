package org.nj.zzy.common.http;

import lombok.Data;

/**
 * @author Zhenyuan Zhang
 * @time 2020-05-31 10:00
 */
@Data
public class ResponseBean<T> {
    private int code;

    private String message;

    private T data;

    public static ResponseBean<?> getNoData(int code, String message) {
        ResponseBean<?> responseBean = new ResponseBean<>();
        responseBean.setCode(code);
        responseBean.setMessage(message);
        return responseBean;
    }

    public static <T> ResponseBean<T> getOK() {
        ResponseBean<T> responseBean = new ResponseBean<>();
        responseBean.setCode(200);
        responseBean.setMessage("OK");
        return responseBean;
    }
}
