package org.zhengbin.snowflake.framework.bean;

/**
 * 返回数据对象
 *  会将其写入 HttpServletResponse 对象中，从而直接输出至浏览器
 * Created by zhengbinMac on 2017/3/28.
 */
public class Data {
    /**
     * 模型数据
     */
    private Object model;

    public Data(Object model) {
        this.model = model;
    }

    public Object getModel() {
        return model;
    }
}
