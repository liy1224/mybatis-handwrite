package com.handrt.mybatis.framework.sqlsession;

import java.util.List;

public class DefaultSqlSession implements SqlSession {

    @Override
    public <T> T selectOne(String id, Object parameterObj) {
        return null;
    }

    @Override
    public <T> List<T> selectList(String id, Object parameterObjs) {
        return null;
    }
}
