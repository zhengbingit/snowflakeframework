package org.zhengbin.snowflake.framework;

import org.zhengbin.snowflake.framework.util.ClassUtil;
import org.zhengbin.snowflake.framework.helper.*;

/**
 * 加载响应的 Helper 类
 * Created by zhengbinMac on 2017/3/28.
 */
public final class HelperLoader {

    public static void init() {
        Class<?>[] classes = {
                ClassHelper.class,
                BeanHelper.class,
                AopHelper.class,
                IocHelper.class,
                ControllerHelper.class,
        };
        for (Class<?> clazz : classes) {
            ClassUtil.loadClass(clazz.getName()); // 不进行初始化，可提高效率
        }
    }
}
