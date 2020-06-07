package org.nj.zzy.common.aop;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Zhenyuan Zhang
 * @time 2020/6/2 20:37
 */
@Component
@Aspect
@Order(100)
@Slf4j
public class BusinessLogAspect {
    // 表示匹配带有自定义注解的方法
    @Pointcut("execution(* org.nj.zzy..*Controller.*(..))")
    public void businessLog() {}

    @Before("businessLog()")
    public void doBefore(JoinPoint joinPoint) {

        HttpServletRequest request =
            Optional.ofNullable((ServletRequestAttributes)RequestContextHolder.getRequestAttributes())
                .map(ServletRequestAttributes::getRequest).orElse(null);

        String httpType = Optional.ofNullable(request).map(HttpServletRequest::getMethod).orElse("null");
        String uri = Optional.ofNullable(request).map(HttpServletRequest::getRequestURI).orElse("null");
        String args =
            Arrays.stream(joinPoint.getArgs()).map(Object::toString).collect(Collectors.joining(",", "[", "]"));

        log.info("URI: {} {} \r\nEnter {}, Request:{}", httpType, uri, joinPoint.toShortString(), args);
    }

    @After("businessLog()")
    public void doAfter(JoinPoint joinPoint) {
        log.info("Exit {}", joinPoint.toShortString());
    }
}
