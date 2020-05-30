package org.nj.zzy.common.aop.operationlog;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.nj.zzy.common.domain.BusinessType;
import org.nj.zzy.common.domain.OperationType;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OperationLog {
    String value() default "";

    BusinessType businessType() default BusinessType.NULL;

    OperationType operationType() default OperationType.NULL;

    boolean saveArgs() default true;

}
