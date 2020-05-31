package org.nj.zzy.common.http;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Locale;

import org.springframework.web.servlet.HandlerInterceptor;

import lombok.extern.slf4j.Slf4j;

/**
 * 处理Http请求Headers参数中的信息
 *
 * @author Zhenyuan Zhang
 * @time 2020-05-31 10:00
 */
@Slf4j
public class HeaderInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String language = request.getHeader("Accept-Language");
        Context context = ThreadLocalUtil.getContext();
        Locale locale = "en-US".equalsIgnoreCase(language) ? Locale.US : Locale.SIMPLIFIED_CHINESE;
        log.info("Accept-Language: {}", locale);
        context.setLocale(locale);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ThreadLocalUtil.remove();
    }
}
