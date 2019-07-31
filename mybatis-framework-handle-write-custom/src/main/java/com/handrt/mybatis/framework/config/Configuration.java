package com.handrt.mybatis.framework.config;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class Configuration {

    private DataSource dataSource;

    private Map<String,MappedStatement> mappedStatements = new HashMap<>();


    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public MappedStatement getMappedStatements(String statementId) {
        return mappedStatements.get(statementId);
    }

    public void addMappedStatements(String statementId, MappedStatement mappedStatement) {
        this.mappedStatements.put(statementId,mappedStatement);
    }
}
