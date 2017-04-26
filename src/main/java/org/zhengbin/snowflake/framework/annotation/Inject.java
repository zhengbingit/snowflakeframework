package org.zhengbin.snowflake.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 依赖注入注解
 * Created by zhengbinMac on 2017/3/28.
 */
@Target(ElementType.FIELD) // FIELD 为字段声明
@Retention(RetentionPolicy.RUNTIME)
public @interface Inject {
}
