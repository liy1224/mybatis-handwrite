package com.handrt.mybatis.framework.sqlsource;


import com.handrt.mybatis.framework.ParameterMapping;

import java.util.List;

/**
 * 组合对象，将sql入参进行了封装
 */
public class BoundSql {


    private String sql;

    // 一个sql语句中包含多个#{},每个#{}表示一个入参信息【参数名称和参数类型】---封装到一个对象中（ParameterMapping）
    private List<ParameterMapping> parameterMappings;

    public BoundSql(String sql, List<ParameterMapping> parameterMappings) {
        this.sql = sql;
        this.parameterMappings = parameterMappings;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public List<ParameterMapping> getParameterMappings() {
        return parameterMappings;
    }

    public void addParameterMapping(ParameterMapping parameterMapping) {
        this.parameterMappings.add(parameterMapping);
    }
}
