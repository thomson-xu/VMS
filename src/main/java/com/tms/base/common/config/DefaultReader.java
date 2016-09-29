package com.tms.base.common.config;

/**
 * Created by Administrator on 2016/8/9.
 */

import com.tms.base.annotations.ConfigElement;
import org.dom4j.Element;

public class DefaultReader implements Reader {
    public DefaultReader() {
    }

    public Object doRead(ConfigElement el, Element node) throws Exception {
        String property = el.property();
        String value = "";
        if(property != null && !"".equals(property)) {
            value = node.attributeValue(property);
        } else {
            value = node.getText();
        }

        Formatter format = (Formatter)el.fomatter().newInstance();
        Object val = format.doFormat(value);
        return val;
    }
}
