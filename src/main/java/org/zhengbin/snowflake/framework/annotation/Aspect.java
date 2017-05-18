package org.zhengbin.snowflake.framework.annotation;

import java.lang.annotation.*;

/**
 * 切面注解类
 * Created by zhengbinMac on 2017/4/5.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {
    /**
     * 注解，拦截哪些含有某个注解的类
     */
    Class<? extends Annotation> value();
}
