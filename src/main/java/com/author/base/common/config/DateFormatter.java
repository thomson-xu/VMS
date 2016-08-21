package com.author.base.common.config;

/**
 * Created by Administrator on 2016/8/9.
 */

import com.author.base.converter.DateConverter;

import java.util.Date;

public class DateFormatter implements Formatter {
    public DateFormatter() {
    }

    public Date doFormat(Object value) {
        DateConverter dc = new DateConverter();
        Date d = dc.convert((String) value) ;
        return d;
    }
}
