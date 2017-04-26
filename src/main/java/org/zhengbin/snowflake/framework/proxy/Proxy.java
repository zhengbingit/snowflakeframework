package org.zhengbin.snowflake.framework.proxy;

/**
 * 代理接口
 * Created by zhengbinMac on 2017/4/5.
 */
public interface Proxy {
    Object doProxy(ProxyChain proxyChain) throws Throwable;
}
