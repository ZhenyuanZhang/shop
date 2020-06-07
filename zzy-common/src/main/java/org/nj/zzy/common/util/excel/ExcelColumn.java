package org.nj.zzy.common.util.excel;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;

/**
 * @author Zhenyuan Zhang
 * @time 2020/6/4 9:38
 */
@Documented
@Inherited
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelColumn {

    @AliasFor("zh_CN")
    String value() default "";

    @AliasFor("value")
    String zh_CN() default "";

    String en_US() default "";
}
