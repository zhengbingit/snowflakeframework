package org.zhengbin.snowflake.framework.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zhengbin.snowflake.framework.annotation.Transaction;
import org.zhengbin.snowflake.framework.helper.DatabaseHelper;

import java.lang.reflect.Method;

/**
 * 事务代理类
 * Created by zhengbinMac on 2017/4/8.
 */
public final class TransactionProxy implements Proxy{
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionProxy.class);
    private static final ThreadLocal<Boolean> FLAG_HOLDER = new ThreadLocal<Boolean>(){
        @Override
        protected Boolean initialValue() {
            return false;
        }
    };

    public Object doProxy(ProxyChain proxyChain) throws Throwable {
        Object result;
        boolean flag = FLAG_HOLDER.get();
        Method method = proxyChain.getTargetMethod(); // 获取表明 @Transaction 注解的方法
        if (!flag && method.isAnnotationPresent(Transaction.class)) {
            FLAG_HOLDER.set(true); // 更新标志位
            try {
                DatabaseHelper.beginTransaction(); // 开启事务
                LOGGER.debug("begin transaction");
                result = proxyChain.doProxyChain(); // 执行代理
                DatabaseHelper.commitTransaction(); // 提交事务
                LOGGER.debug("commit transaction");
            }catch (Exception e) {
                DatabaseHelper.rollbackTransaction(); // 回滚事务
                LOGGER.debug("rollback transaction");
                throw e;
            }finally {
                FLAG_HOLDER.remove(); // 移除本地线程变量中的标志
            }
        }else {
            result = proxyChain.doProxyChain();
        }
        return result;
    }
}
