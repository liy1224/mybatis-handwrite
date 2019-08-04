package com.handrt.mybatis.framework.sqlsession;

import java.util.List;

public interface SqlSession {

    /**
     * 条件查询
     * @param id
     * @param parameterObj
     * @param <T>
     * @return
     */
    public <T> T selectOne(String id,Object parameterObj);


    public <T> List<T> selectList(String id, Object parameterObjs);
}
