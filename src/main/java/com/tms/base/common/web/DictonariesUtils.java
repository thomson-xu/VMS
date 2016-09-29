package com.tms.base.common.web;

/**
 * Created by Administrator on 2016/8/9.
 */

import com.tms.base.common.config.ConfigurationUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.tree.DefaultAttribute;

import java.io.UnsupportedEncodingException;
import java.util.*;

public class DictonariesUtils {
    private static final String DICT_PATH = "config/dictionaries.xml";
    private static Document staticdoc = null;

    public DictonariesUtils() {
    }

    public static List<Map<String, String>> getById(String id) throws UnsupportedEncodingException, DocumentException {
        return get("id", id);
    }

    public static List<Map<String, String>> getByName(String name) throws UnsupportedEncodingException, DocumentException {
        return get("name", name);
    }

    public static List<Map<String, String>> get(String property, String value) throws UnsupportedEncodingException, DocumentException {
        Document doc = getDocument();
        Element root = doc.getRootElement();
        String xpath = "dictonary[@" + property + "=\'" + value + "\']";
        Element node = (Element)root.selectSingleNode(xpath);
        if(node == null) {
            return null;
        } else {
            List list = itemsToList(node);
            return list;
        }
    }

    public static Map<String, Object> getDetailsById(String id) throws UnsupportedEncodingException, DocumentException {
        return getDetails("id", id);
    }

    public static Map<String, Object> getDetailsByName(String name) throws UnsupportedEncodingException, DocumentException {
        return getDetails("name", name);
    }

    public static Map<String, Object> getDetails(String property, String value) throws UnsupportedEncodingException, DocumentException {
        Document doc = getDocument();
        Element root = doc.getRootElement();
        String xpath = "dictonary[@" + property + "=\'" + value + "\']";
        Element node = (Element)root.selectSingleNode(xpath);
        if(node == null) {
            return null;
        } else {
            Map details = toMap(node);
            return details;
        }
    }

    public static List<Map<String, Object>> getSpecifiedById(String dictIds) throws UnsupportedEncodingException, DocumentException {
        String[] dicts = dictIds.split("\\|");
        return getSpecifiedById(dicts);
    }

    public static List<Map<String, Object>> getSpecifiedById(String... dicts) throws UnsupportedEncodingException, DocumentException {
        ArrayList list = new ArrayList();

        for(int i = 0; i < dicts.length; ++i) {
            String id = dicts[i];
            Map map = getDetailsById(id);
            if(map != null) {
                list.add(map);
            }
        }

        return list;
    }

    public static List<Map<String, Object>> getSpecifiedByName(String dictNames) throws UnsupportedEncodingException, DocumentException {
        String[] dicts = dictNames.split("\\|");
        return getSpecifiedByName(dicts);
    }

    public static List<Map<String, Object>> getSpecifiedByName(String... dicts) throws UnsupportedEncodingException, DocumentException {
        ArrayList list = new ArrayList();

        for(int i = 0; i < dicts.length; ++i) {
            String name = dicts[i];
            Map map = getDetailsByName(name);
            if(map != null) {
                list.add(map);
            }
        }

        return list;
    }

    private static Document getDocument() throws UnsupportedEncodingException, DocumentException {
        if(staticdoc == null) {
            staticdoc = ConfigurationUtils.getXmlDocument("config/dictionaries.xml");
        }

        return staticdoc;
    }

    public static void refresh() throws UnsupportedEncodingException, DocumentException {
        staticdoc = ConfigurationUtils.getXmlDocument("config/dictionaries.xml");
    }

    public static List<Map<String, Object>> getAll() throws UnsupportedEncodingException, DocumentException {
        ArrayList list = new ArrayList();
        Document doc = getDocument();
        Element root = doc.getRootElement();
        String xpath = "dictonary";
        List nodes = root.selectNodes(xpath);
        Iterator its = nodes.iterator();

        while(its.hasNext()) {
            Element node = (Element)its.next();
            Map map = toMap(node);
            list.add(map);
        }

        return list;
    }

    private static Map<String, Object> toMap(Element node) {
        List list = itemsToList(node);
        HashMap details = new HashMap();
        details.put("id", node.attributeValue("id"));
        details.put("name", node.attributeValue("name"));
        details.put("cname", node.attributeValue("cname"));
        details.put("data", list);
        return details;
    }

    private static List<Map<String, String>> itemsToList(Element node) {
        Iterator its = node.elementIterator();
        ArrayList list = new ArrayList();

        while(its.hasNext()) {
            Element item = (Element)its.next();
            Iterator attrs = item.attributeIterator();
            HashMap map = new HashMap();

            while(attrs.hasNext()) {
                DefaultAttribute attr = (DefaultAttribute)attrs.next();
                map.put(attr.getName(), attr.getValue());
            }

            list.add(map);
        }

        return list;
    }

    public static void main(String[] args) {
    }
}