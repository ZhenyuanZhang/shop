package org.nj.zzy.common.aop.operationlog;

import java.net.InetAddress;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.nj.zzy.common.http.ResponseBean;
import org.nj.zzy.common.http.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;

/**
 * @author Zhenyuan Zhang
 * @time 2020-05-31 10:00
 */
@Aspect
@Component
public class OperationAspect {

    @Autowired(required = false)
    private OperationLogMapper operationLogMapper;

    private static final String PARAM_CLASS = "paramClass";

    private static final String PARAM_JSON = "paramJson";

    // 表示匹配带有自定义注解的方法
    @Pointcut("@annotation(org.nj.zzy.common.aop.operationlog.OperationLog)")
    public void operationalLog() {}

    @Before("operationalLog()")
    public void doBefore(JoinPoint joinPoint) {}

    @Around("operationalLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        OperationLog annotation = methodSignature.getMethod().getAnnotation(OperationLog.class);
        Object[] args = joinPoint.getArgs();

        OperationLogDO operationLogDO = new OperationLogDO();
        operationLogDO.setMetaData(InetAddress.getLocalHost().getHostAddress());
        operationLogDO.setBusinessType(annotation.businessType().name());
        operationLogDO.setOperationType(annotation.operationType().name());
        if (annotation.saveArgs() && args != null && args.length > 0) {
            operationLogDO.setArgs(JSON
                .toJSONString((Arrays.stream(args).map(OperationAspect::getObjectJson).collect(Collectors.toList()))));
        }
        operationLogDO.setOperator(Optional.ofNullable(ThreadLocalUtil.getContext().getUser()).orElse("NA"));
        operationLogDO.setOperationTime(new Date());

        try {
            Object result = joinPoint.proceed();
            if (result instanceof ResponseBean) {
                operationLogDO.setResult(JSON.toJSONString(result));
            }
            operationLogDO.setTimeConsuming((int)(System.currentTimeMillis() - start));
            operationLogMapper.insertOperationLog(operationLogDO);
            return result;
        } catch (RuntimeException e) {
            operationLogDO.setErrorMsg(e.getMessage());
            operationLogMapper.insertOperationLog(operationLogDO);
            throw e;
        }
    }

    private static Map<String, String> getObjectJson(Object arg) {
        Map<String, String> paramInfos = Maps.newLinkedHashMapWithExpectedSize(2);
        paramInfos.put(PARAM_CLASS, arg.getClass().getName());
        paramInfos.put(PARAM_JSON, JSON.toJSONString(arg));
        return paramInfos;
    }

}
