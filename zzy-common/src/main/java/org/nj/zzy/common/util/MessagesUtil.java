package org.nj.zzy.common.util;

import java.util.Locale;
import java.util.Optional;

import org.nj.zzy.common.http.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;

/**
 * @author Zhenyuan Zhang
 * @time 2020/5/31 18:56
 */
@Component
public class MessagesUtil {

    private static MessageSource messageSource;

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        MessagesUtil.messageSource = messageSource;
    }

    public static String getMessage(String code, Object... params) {
        Locale locale = Optional.ofNullable(ThreadLocalUtil.getContext().getLocale()).orElse(Locale.SIMPLIFIED_CHINESE);
        try {
            return messageSource.getMessage(code, params, locale);
        } catch (NoSuchMessageException e) {
            return code;
        }
    }
}