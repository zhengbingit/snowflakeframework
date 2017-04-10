package org.snowflake.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 服务类注解
 * Created by zhengbinMac on 2017/3/28.
 */
@Target(ElementType.TYPE) // TYPE 为类、接口或枚举声明
@Retention(RetentionPolicy.RUNTIME)
public @interface Service {
}
