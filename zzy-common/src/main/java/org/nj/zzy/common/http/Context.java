package org.nj.zzy.common.http;

import java.util.Locale;

import org.apache.catalina.User;

import lombok.Data;

/**
 * @author Zhenyuan Zhang
 * @time 2020-05-31 10:00
 */
@Data
public class Context {
    private Locale locale;

    private String user;
}
