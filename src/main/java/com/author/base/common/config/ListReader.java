package com.author.base.common.config;

/**
 * Created by Administrator on 2016/8/9.
 */

import com.author.annotations.ConfigElement;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListReader implements Reader {
    public ListReader() {
    }

    public List doRead(ConfigElement el, Element node) throws Exception {
        ArrayList list = new ArrayList();
        Element listNode = node.element("list");
        Iterator it = listNode.elementIterator();

        while(it.hasNext()) {
            Element childNode = (Element)it.next();
            String val = childNode.getTextTrim();
            list.add(val);
        }

        return list;
    }
}
