package com.kane.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 包名: com.kane.annotation
 * 说明：认证注解
 * 创建时间： 2025-11-21
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Authorization {
    /**
     * 是否需要认证
     */
    boolean value() default true;

    /**
     * 角色
     */
    int role() default 0;
}
