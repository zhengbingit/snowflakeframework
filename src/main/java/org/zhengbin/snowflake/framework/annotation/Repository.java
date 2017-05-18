package org.zhengbin.snowflake.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * DAO 层注解
 * Created by zhengbinMac on 2017/5/6.
 */
@Target(ElementType.TYPE) // TYPE 为类、接口或枚举声明
@Retention(RetentionPolicy.RUNTIME)
public @interface Repository {
}
