package org.snowflake.framework.helper;

import org.snowflake.framework.ConfigConstant;
import org.snowflake.framework.util.PropsUtil;

import java.util.Properties;

/**
 * 属性文件助手类
 * Created by zhengbinMac on 2017/3/28.
 */
public final class ConfigHelper {
    private static final Properties CONFIG_PROPS = PropsUtil.loadProps(ConfigConstant.CONFIG_FILE);

    /**
     * 获取数据库驱动程序
     */
    public static String getJdbcDriver() {
        return getStr(ConfigConstant.JDBC_DRIVER);
    }

    /**
     * 获取 JDBC URL
     */
    public static String getJdbcUrl() {
        return getStr(ConfigConstant.JDBC_URL);
    }

    /**
     * 获取 JDBC 用户名
     */
    public static String getJdbcUsername() {
        return getStr(ConfigConstant.JDBC_USERNAME);
    }

    /**
     * 获取 JDBC 密码
     */
    public static String getJdbcPassword() {
        return getStr(ConfigConstant.JDBC_PASSWORD);
    }

    /**
     * 获取应用基础包名
     */
    public static String getAppBasePackage() {
        return getStr(ConfigConstant.APP_BASE_PACKAGE);
    }

    /**
     * 获取应用 JSP 路径
     */
    public static String getAppJspPath() {
        return getStr(ConfigConstant.APP_JSP_PATH, "/WEB-INF/view");
    }

    /**
     * 获取应用静态资源路径
     */
    public static String getAppAssetPath() {
        return getStr(ConfigConstant.APP_ASSET_PATH, "/asset/");
    }

    /**
     * 获取指定key的值
     */
    private static String getStr(String str) {
        return PropsUtil.getString(CONFIG_PROPS, str);
    }
    private static String getStr(String str, String def) {
        return PropsUtil.getString(CONFIG_PROPS, str, def);
    }
}
