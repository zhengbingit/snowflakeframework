package org.zhengbin.snowflake.framework.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zhengbin.snowflake.framework.annotation.Action;
import org.zhengbin.snowflake.framework.bean.Handler;
import org.zhengbin.snowflake.framework.bean.Request;
import org.zhengbin.snowflake.framework.util.ArrayUtil;
import org.zhengbin.snowflake.framework.util.CollectionUtil;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 控制器助手类
 * Created by zhengbinMac on 2017/3/28.
 */
public final class ControllerHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerHelper.class);
    /**
     * 用于存放请求与处理器的映射关系（简称 Action Map）
     */
    private static final Map<Request, Handler> ACTION_MAP = new HashMap<Request, Handler>();

    static {
        // 获取所有的 Controller 类
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
        if (CollectionUtil.isNotEmpty(controllerClassSet)) {
            // 遍历这些 Controller 类
            for (Class<?> controllerClass : controllerClassSet) {
                // 获取 Controller 类中定义的方法
                Method[] methods = controllerClass.getDeclaredMethods();
                if (ArrayUtil.isNotEmpty(methods)) {
                    // 遍历这些 Controller 类中的方法
                    for (Method method : methods) {
                        if (method.isAnnotationPresent(Action.class)) {
                            // 从 Action 注解中获取 URL 映射规则
                            Action action = method.getAnnotation(Action.class);
                            // 获取请求类型和路径
                            String mapping = action.value();
                            // 验证 URL 映射规则
//                            if (mapping.matches("\\w+:/\\w*")) {
                            if (mapping.matches(".*:/.*")) { // 修改正则规则，path 中包含 ':/' 即可
                                String[] array = mapping.split(":");
                                if (ArrayUtil.isNotEmpty(array) && array.length==2) {
                                    String requestMethod = array[0];
                                    String requestPath = array[1];
                                    Request request = new Request(requestMethod, requestPath);
                                    Handler handler = new Handler(controllerClass, method);
                                    // 存入 ACTION_MAP 中
                                    ACTION_MAP.put(request, handler);
                                }
                            } else {
                                LOGGER.error("mapping error, mapping = {}", mapping);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 获取 Handler
     *  从 ACTION_MAP 中返回
     */
    public static Handler getHandler(String requestMethod, String requestPath) {
        Request request = new Request(requestMethod, requestPath);
        return ACTION_MAP.get(request);
    }
}
