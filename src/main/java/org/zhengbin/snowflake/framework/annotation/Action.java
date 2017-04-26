package org.zhengbin.snowflake.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Action 方法注解
 * Created by zhengbinMac on 2017/3/28.
 */
@Target(ElementType.METHOD) // 声明该注解的目标类型，该注解为方法类型
@Retention(RetentionPolicy.RUNTIME) // 声明注解的保留期限
public @interface Action {
    /**
     * 请求类型与路径
     * @return
     */
    String value();
}
