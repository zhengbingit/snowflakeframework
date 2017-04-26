package org.zhengbin.snowflake.framework.bean;

import java.lang.reflect.Method;

/**
 * 处理对象
 * 封装 Action 信息
 * Created by zhengbinMac on 2017/3/28.
 */
public class Handler {
    /**
     * Controller 类
     */
    private Class<?> controllerClass;

    /**
     * Action 方法
     */
    private Method actionMethod;

    public Handler(Class<?> controllerClass, Method actionMethod) {
        this.controllerClass = controllerClass;
        this.actionMethod = actionMethod;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public Method getActionMethod() {
        return actionMethod;
    }
}
