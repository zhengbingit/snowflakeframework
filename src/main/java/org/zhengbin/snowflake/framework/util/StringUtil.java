package org.zhengbin.snowflake.framework.util;

/**
 * Created by zhengbinMac on 2017/3/25.
 */

import org.apache.commons.lang3.StringUtils;

/**
 * 字符串工具类
 */
public final class StringUtil {
    /**
     * 判断字符串是否为空
     */
    public static boolean isEmpty(String str) {
        if (str != null) {
            str = str.trim(); // 返回字符串的副本，忽略前导空白和尾部空白。
        }
        return StringUtils.isEmpty(str);
    }

    /**
     * 判断字符串是否非空
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static String[] splitString(String str, String c) {
        return StringUtils.split(str, c);
    }

    public static String lowerCase(String str) {
        return StringUtils.lowerCase(str);
    }

    /**
     * 字符串分隔符
     */
    public static final String SEPARATOR = String.valueOf((char) 29);
}
