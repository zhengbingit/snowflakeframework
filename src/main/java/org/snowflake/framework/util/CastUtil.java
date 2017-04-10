package org.snowflake.framework.util;

/**
 * Created by zhengbinMac on 2017/3/25.
 */

/**
 * 转型操作工具类
 */
public final class CastUtil {

    /**
     * 转为 String 型
     */
    public static String castString(Object object) {
        return castString(object, "");
    }

    /**
     * 转为 String 型（提供默认值）
     */
    public static String castString(Object object, String defaultValue) {
        return object != null ? String.valueOf(object) : defaultValue;
    }

    /**
     * 转为 double 型
     */
    public static double castDouble(Object object) {
        return castDouble(object, 0d);
    }

    /**
     * 转为 double 型（提供默认值）
     */
    public static double castDouble(Object object, double defaultValue) {
        double doubleValue = defaultValue;
        if (object != null) {
            String strValue = castString(object);
            if (StringUtil.isNotEmpty(strValue)) {
                try {
                    doubleValue = Double.parseDouble(strValue);
                }catch (NumberFormatException e) {
                    doubleValue = defaultValue;
                }
            }
        }
        return doubleValue;
    }

    /**
     * 转为 long 型
     */
    public static long castLong(Object object) {
        return castLong(object, 0l);
    }

    /**
     * 转为 long 型（提供默认值）
     */
    public static long castLong(Object object, long defaultValue) {
        long longValue = defaultValue;
        if (object != null) {
            String strValue = castString(object);
            if (StringUtil.isNotEmpty(strValue)) {
                try {
                    longValue = Long.parseLong(strValue);
                }catch (NumberFormatException e) {
                    longValue = defaultValue;
                }
            }
        }
        return longValue;
    }

    /**
     * 转为 int 型
     */
    public static int castInt(Object object) {
        return castInt(object, 0);
    }
    /**
     * 转为 int 型（提供默认值）
     */
    public static int castInt(Object object, int defaultValue) {
        int intValue = defaultValue;
        if (object != null) {
            String strValue = castString(object);
            if (StringUtil.isNotEmpty(strValue)) {
                try {
                    intValue = Integer.parseInt(strValue);
                }catch (NumberFormatException e) {
                    intValue = defaultValue;
                }
            }
        }
        return intValue;
    }

    /**
     * 转为 boolean 型
     */
    public static boolean castBoolean(Object object) {
        return castBoolean(object, false);
    }

    /**
     * 转为 boolean 型（提供默认值）
     */
    public static boolean castBoolean(Object object, boolean defaultValue) {
        boolean booleanValue = defaultValue;
        if (object != null) {
            booleanValue = Boolean.valueOf(castString(object));
        }
        return booleanValue;
    }
}
