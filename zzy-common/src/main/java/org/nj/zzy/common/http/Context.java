package org.nj.zzy.common.http;

import java.util.Locale;

import org.apache.catalina.User;

import lombok.Data;

@Data
public class Context {
    private Locale locale;

    private String user;
}
