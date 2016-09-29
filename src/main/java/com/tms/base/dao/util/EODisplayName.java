package com.tms.base.dao.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ʵ����ʾ������(������)
 *
 * @author slx
 * @date 2010-7-1 ����08:55:19
 * @version 1.0
 */
@Target( {ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface EODisplayName {

    String value();

}
