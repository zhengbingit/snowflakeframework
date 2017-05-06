package org.zhengbin.snowflake.framework.bean;

/**
 * 封装表单参数
 * Created by zhengbinMac on 2017/4/29.
 */
public class FormParam {
    private String fieldName;
    private Object fieldValue;

    public FormParam(String fieldName, Object fieldValue) {
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }

}
