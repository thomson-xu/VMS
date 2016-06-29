package com.visa.dao.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 字段显示名(中文名)注解
 * @author slx
 * @date 2010-7-1 上午08:56:37
 * @version 1.0
 */
@Target( {ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldDisplayName {

    String value();
}