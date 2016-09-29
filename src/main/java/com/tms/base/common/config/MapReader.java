package com.tms.base.common.config;

/**
 * Created by Administrator on 2016/8/9.
 */

import com.tms.base.annotations.ConfigElement;
import org.dom4j.Element;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MapReader implements Reader {
    public MapReader() {
    }

    public Map doRead(ConfigElement el, Element node) throws Exception {
        HashMap map = new HashMap();
        Iterator it = node.elementIterator();

        while(it.hasNext()) {
            Element e = (Element)it.next();
            String key = e.getName();
            String value = e.getText();
            map.put(key, value);
        }

        return map;
    }
}
