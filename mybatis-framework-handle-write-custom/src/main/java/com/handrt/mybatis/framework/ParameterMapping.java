package com.handrt.mybatis.framework;

public class ParameterMapping {

    //参数名称
    private String parameterName;

    //参数类型
    private Class<?> parameterTypeClass;

    public ParameterMapping(String content, Class<?> parameterTypeClass) {
        this.parameterName = content;
        this.parameterTypeClass = parameterTypeClass;
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public Class<?> getParameterTypeClass() {
        return parameterTypeClass;
    }

    public void setParameterTypeClass(Class<?> parameterTypeClass) {
        this.parameterTypeClass = parameterTypeClass;
    }
}
