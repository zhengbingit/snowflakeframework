package org.snowflake.framework.util;

import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Map 与 JavaBean 的相互转换
 * Created by zhengbinMac on 2017/4/24.
 */
public class BeanUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(BeanUtil.class);

    public static Map<String, Object> beanToMap(Object obj) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Class cls = obj.getClass();
        Field[] fields = cls.getDeclaredFields(); // 返回 Field 对象的一个数组，这些对象反映此 Class 对象所表示的类或接口所声明的所有字段。
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.get(obj)!=null) {
                    resultMap.put(field.getName(), field.get(obj));
                }
            }
        } catch (Exception e) {
            LOGGER.error("mapToBean failed, Exception: ", e);
        }
        return resultMap;
    }

    public static void mapToBean(Map<String, Object> map, Object obj) {
        try {
            Class cla = obj.getClass();
            for (String key : map.keySet()) {
                Field field = cla.getDeclaredField(key);
                field.setAccessible(true);
                field.set(obj, map.get(key));
            }
        } catch (Exception e) {
            LOGGER.error("mapToBean failed, Exception: ", e);
        }
    }
}
