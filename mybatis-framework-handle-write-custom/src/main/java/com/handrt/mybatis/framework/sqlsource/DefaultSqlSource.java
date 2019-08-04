package com.handrt.mybatis.framework.sqlsource;

public class DefaultSqlSource implements SqlSource {

    /**
     * 存储未解析的sql文本
     */
    private  String sqlText;

    /**
     *
     */
    private Class<?> parameterTypeClass;

    public DefaultSqlSource(String sqlTest,Class<?> parameterTypeClass){
        this.sqlText = sqlTest;
        this.parameterTypeClass = parameterTypeClass;
    }
    /**
     * 执行sqlsession的时候调用
      */
    @Override
    public BoundSql getBoundSql() {
        // 解析sql文本中的#{}
        ParameterMappingTokenHandler handler =
                new ParameterMappingTokenHandler(parameterTypeClass);

        GenericTokenParser tokenParser =
                new GenericTokenParser("#{","}",handler);

        // 获取解析之后的sql语句
        String sql = tokenParser.parse(sqlText);
        // 封装了解析之后的sql语句和参数映射信息集合
        return new BoundSql(sql,handler.getParameterMappings());
    }


}
