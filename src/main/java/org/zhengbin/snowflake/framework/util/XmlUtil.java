package org.zhengbin.snowflake.framework.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhengbinMac on 2017/4/13.
 */
public class XmlUtil {
    public static Map<String, String> xmlToMap(String str) throws IOException, DocumentException {
        Map<String, String> resultMap = new HashMap<String, String>();
        SAXReader reader = new SAXReader();
        InputStream inputStream = new ByteArrayInputStream(str.getBytes("UTF-8"));
        Document document = reader.read(inputStream);
        Element root = document.getRootElement();
        List<Element> list = root.elements();
        for (Element e : list) {
            resultMap.put(e.getName(), e.getText());
        }
        inputStream.close();
        return resultMap;
    }

}
