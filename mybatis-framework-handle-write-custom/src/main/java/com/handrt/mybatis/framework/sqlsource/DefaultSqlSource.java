package com.handrt.mybatis.framework.sqlsource;

public class DefaultSqlSource implements SqlSource {

    /**
     * 存储未解析的sql文本
     */
    private  String sqlText;

    public DefaultSqlSource(String sqlTest){
        this.sqlText = sqlTest;
    }
    /**
     * 执行sqlsession的时候调用
      */
    @Override
    public BoundSql getBoundSql() {
        return null;
    }

}
