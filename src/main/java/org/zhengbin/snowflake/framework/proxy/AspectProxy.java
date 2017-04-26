package org.zhengbin.snowflake.framework.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * 切面代理类（抽象模板类）
 * Created by zhengbinMac on 2017/4/5.
 */
public abstract class AspectProxy implements Proxy{
    private static final Logger LOGGER = LoggerFactory.getLogger(AspectProxy.class);

    public final Object doProxy(ProxyChain proxyChain) throws Throwable {
        Object result;
        // 获取代理类的 目标类
        Class<?> cls = proxyChain.getTargetClass();
        // 获取代理类的 目标方法
        Method method =  proxyChain.getTargetMethod();
        // 获取代理类的 方法参数
        Object[] params = proxyChain.getMethodParams();

        begin();
        try {
            // 是否拦截
            if (intercept(cls, method, params)) {
                // 执行前置增强
                before(cls, method, params);
                result = proxyChain.doProxyChain();
                // 执行后置增强
                after(cls, method, params, result);
            } else {
                result = proxyChain.doProxyChain();
            }
        } catch (Exception e) {
            LOGGER.error("proxy failure", e);
            error(cls, method, params, e);
            throw e;
        } finally {
            end();
        }
        return result;
    }

    public void begin() {
    }

    public boolean intercept(Class<?> cls, Method method, Object[] params) throws Throwable {
        return true;
    }

    public abstract void before(Class<?> cls, Method method, Object[] params) throws Throwable;

    public abstract void after(Class<?> cls, Method method, Object[] params, Object result) throws Throwable;

    public void error(Class<?> cls, Method method, Object[] params, Throwable e){

    }

    public void end() {

    }
}
