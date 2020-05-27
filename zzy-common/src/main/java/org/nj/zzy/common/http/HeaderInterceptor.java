package org.nj.zzy.common.http;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Locale;

import org.springframework.web.servlet.HandlerInterceptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HeaderInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String language = request.getHeader("Accept-Language");
        System.out.println("HeaderInterceptor: " + Thread.currentThread().getName());
        Context context = ThreadLocalUtil.getContext();
        System.out.println("HeaderInterceptor: 1" + ThreadLocalUtil.getContext());
        context.setLocale("en-US".equalsIgnoreCase(language) ? Locale.US : Locale.SIMPLIFIED_CHINESE);
        System.out.println("HeaderInterceptor: 2" + ThreadLocalUtil.getContext());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ThreadLocalUtil.remove();
    }
}
