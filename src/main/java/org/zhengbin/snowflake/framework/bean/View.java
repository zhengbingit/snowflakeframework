package org.zhengbin.snowflake.framework.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回视图对象
 * Created by zhengbinMac on 2017/3/28.
 */
public class View {
    /**
     * 视图路径
     */
    private String path;

    /**
     * 模型数据
     *  Map 类型的键值对，可在视图中根据模型的键名获取键值
     */
    private Map<String, Object> model;

    public View(String path) {
        this.path = path;
        model = new HashMap<String, Object>();
    }

    public View addModel(String key, Object value) {
        model.put(key, value);
        return this;
    }

    public String getPath() {
        return path;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    @Override
    public String toString() {
        return "View{" +
                "path='" + path + '\'' +
                ", model=" + model +
                '}';
    }
}
