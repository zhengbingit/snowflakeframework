package org.zhengbin.snowflake.framework.proxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import net.sf.cglib.proxy.MethodProxy;

/**
 * Created by zhengbinMac on 2017/4/5.
 */
public class ProxyChain {
    private final Class<?> targetClass;     // 目标类
    private final Object targetObject;      // 目标对象
    private final Method targetMethod;      // 目标方法
    private final MethodProxy methodProxy;  // CGLib 提供的方法代理对象
    private final Object[] methodParams;    // 方法参数

    private List<Proxy> proxyList = new ArrayList<Proxy>(); // 代理列表
    private int proxyIndex = 0; // 代理索引（代理列表的下标）

    public ProxyChain(Class<?> targetClass, Object targetObject, Method targetMethod,
                      MethodProxy methodProxy, Object[] methodParams, List<Proxy> proxyList) {
        this.targetClass = targetClass;
        this.targetObject = targetObject;
        this.targetMethod = targetMethod;
        this.methodProxy = methodProxy;
        this.methodParams = methodParams;
        this.proxyList = proxyList;
    }

    public Object[] getMethodParams() {
        return methodParams;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public Method getTargetMethod() {
        return targetMethod;
    }

    /**
     * 通过 proxyIndex 来充当代理对象的计数器，若尚未达到 proxyList 的上限，则从 proxyList 中取出相应的 Proxy 对象，
     * 并调用其 doProxy 方法。
     * 在 Proxy 接口的实现中会提供相应的横切逻辑，并调用 doProxyChain 方法，随后将再次调用当前 ProxyChain 对象的
     * doProxyChain 方法，直到 proxyIndex 达到 proxyList 的上限为止，最后调用 methodProxy 的 invokeSuper 方法，
     * 执行目标对象的业务逻辑。
     * @return
     * @throws Throwable
     */
    public Object doProxyChain() throws Throwable{
        Object methodResult;
        if (proxyIndex < proxyList.size()) {
            // 执行代理
            methodResult = proxyList.get(proxyIndex++).doProxy(this);
        }else {
            // 执行目标对象的业务逻辑
            methodResult = methodProxy.invokeSuper(targetObject, methodParams);
        }
        return methodResult;
    }
}
