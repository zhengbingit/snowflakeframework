package org.snowflake.framework.util;

import org.apache.commons.lang3.ArrayUtils;

/**
 * 数组工具类
 * Created by zhengbinMac on 2017/3/28.
 */
public final class ArrayUtil {
    public static boolean isNotEmpty(Object[] objects) {
        return !ArrayUtils.isEmpty(objects);
    }

    public static boolean isEmpty(Object[] objects) {
        return ArrayUtils.isEmpty(objects);
    }
}
