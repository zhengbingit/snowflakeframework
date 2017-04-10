package org.snowflake.framework.bean;

import org.snowflake.framework.util.CastUtil;
import org.snowflake.framework.util.CollectionUtil;

import java.util.Map;

/**
 * 请求参数对象
 *  从 HttpServletRequest 对象中获取所有请求参数，并将其初始化到一个 Param 对象中。
 * Created by zhengbinMac on 2017/3/28.
 */
public class Param {
    private Map<String, Object> paramMap;

    public Param(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    /**
     * 根据参数名获取 long 型参数值
     */
    public long getLong(String name) {
        return CastUtil.castLong(paramMap.get(name));
    }

    /**
     * 获取所有字段信息
     */
    public Map<String, Object> getMap() {
        return paramMap;
    }

    /**
     * 验证参数是否为空
     */
    public boolean isEmpty() {
        return CollectionUtil.isEmpty(paramMap);
    }
}
