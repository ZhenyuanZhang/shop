package org.nj.zzy.common.aop.operationlog;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.nj.zzy.common.constant.BusinessType;
import org.nj.zzy.common.constant.OperationType;

/**
 * @author Zhenyuan Zhang
 * @time 2020-05-31 10:00
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OperationLog {
    String value() default "";

    BusinessType businessType() default BusinessType.NULL;

    OperationType operationType() default OperationType.NULL;

    boolean saveArgs() default true;

}
