package org.snowflake.framework;

/**
 * 提供相关配置项常量
 * Created by zhengbinMac on 2017/3/28.
 */
public interface ConfigConstant {
    String CONFIG_FILE = "config.properties";

    String JDBC_DRIVER = "snowflake.framework.jdbc.driver";
    String JDBC_URL = "snowflake.framework.jdbc.url";
    String JDBC_USERNAME = "snowflake.framework.jdbc.username";
    String JDBC_PASSWORD = "snowflake.framework.jdbc.password";

    String APP_BASE_PACKAGE = "snowflake.framework.app.base_package";
    String APP_JSP_PATH = "snowflake.framework.app.jsp_path";
    String APP_ASSET_PATH = "snowflake.framework.app.asset_path";
}
