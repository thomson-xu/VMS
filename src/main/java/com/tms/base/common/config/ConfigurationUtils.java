package com.tms.base.common.config;

/**
 * Created by Administrator on 2016/8/9.
 */

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Properties;

public class ConfigurationUtils {
    public ConfigurationUtils() {
    }

    public static Document getXmlDocument(String filepath) throws UnsupportedEncodingException, DocumentException {
        String basePath = ConfigurationUtils.class.getClassLoader().getResource("").getPath() + filepath;
        basePath = URLDecoder.decode(basePath, "UTF-8");
        SAXReader reader = new SAXReader();
        Document doc = reader.read(new File(basePath));
        resolveInclude(doc);
        return doc;
    }

    public static Document getXmlDocument(String path, String filename) throws UnsupportedEncodingException, DocumentException {
        String realpath = (StringUtils.endsWith(path, "/")?path:path + "/") + (filename == null?"":filename);
        return getXmlDocument(realpath);
    }

    private static Document resolveInclude(Document doc) throws UnsupportedEncodingException, DocumentException {
        Element root = doc.getRootElement();
        List ins = root.elements("include");

        for(int i = 0; i < ins.size(); ++i) {
            Element node = (Element)ins.get(i);
            String includePath = node.attributeValue("path");
            Element includeRoot = getXmlDocument(includePath).getRootElement();
            root.appendContent(includeRoot);
        }

        return doc;
    }

    public static Properties getProperties(String filepath) throws IOException {
        String basePath = ConfigurationUtils.class.getClassLoader().getResource("").getPath() + filepath;
        Properties p = new Properties();
        FileInputStream in = new FileInputStream(basePath);
        p.load(in);
        return p;
    }

}
