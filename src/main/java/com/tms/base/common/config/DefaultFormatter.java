package com.tms.base.common.config;

/**
 * Created by Administrator on 2016/8/9.
 */
public class DefaultFormatter implements Formatter {
    public DefaultFormatter() {
    }

    public Object doFormat(Object value) {
        return value;
    }
}
