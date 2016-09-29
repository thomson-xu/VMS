package com.tms.base.common.config;

/**
 * Created by Administrator on 2016/8/9.
 */

import com.tms.base.annotations.ConfigElement;
import com.tms.base.annotations.Document;
import org.dom4j.Element;

import java.lang.reflect.Field;

public class ReadConfig {
    public ReadConfig() {
    }

    public <T> T configToBean(Class<T> clz) throws Exception {
        Document doc = (Document)clz.getAnnotation(Document.class);
        if(doc == null) {
            return null;
        } else {
            String path = doc.path();
            org.dom4j.Document document = ConfigurationUtils.getXmlDocument(path);
            Element root = document.getRootElement();
            Object bean = clz.newInstance();
            Field[] fields = clz.getDeclaredFields();

            for(int i = 0; i < fields.length; ++i) {
                Field field = fields[i];
                ConfigElement ec = (ConfigElement)field.getAnnotation(ConfigElement.class);
                if(ec != null) {
                    Element value = this.getElementByTagName(root, ec.tag());
                    Reader reader = (Reader)ec.reader().newInstance();
                    Object value1 = reader.doRead(ec, value);
                    field.setAccessible(true);
                    field.set(bean, value1);
                } else {
                    String var14 = this.nullReader(root, field);
                    field.setAccessible(true);
                    field.set(bean, var14);
                }
            }

            root.element("");
            return (T) bean;
        }
    }

    private String nullReader(Element root, Field field) {
        Element el = this.getElementByTagName(root, field.getName());
        return el.getTextTrim();
    }

    private Element getElementByTagName(Element root, String name) {
        Element el = root.element(name);
        return el;
    }
}

