package com.handrt.mybatis.framework.sqlsource;

public interface SqlSource {
    /**
     *  需要对sql文本进行解析
     * @return
     */
    BoundSql  getBoundSql();
}
