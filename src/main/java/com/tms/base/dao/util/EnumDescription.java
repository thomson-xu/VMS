package com.tms.base.dao.util;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ö������
 *
 * @author slx
 * @date 2009-9-2 ����05:13:57
 * @version 1.0
 */
@Target( {ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EnumDescription {

    String value();
}