package com.handrt.mybatis.framework.builder;

import com.handrt.mybatis.framework.config.Configuration;
import com.handrt.mybatis.framework.config.MappedStatement;
import com.handrt.mybatis.framework.reader.DocumentReader;
import com.handrt.mybatis.framework.sqlsource.DefaultSqlSource;
import com.handrt.mybatis.framework.sqlsource.SqlSource;
import org.dom4j.Document;
import org.dom4j.Element;

import java.io.InputStream;
import java.util.List;

public class XMLMapperBuilder {

    private Configuration configuration;

    public  XMLMapperBuilder(Configuration configuration){
        this.configuration = configuration;
    }

    /**
     * 解析mapper映射文件
     * @param inputStream
     */
    public void parse(InputStream inputStream) {
        //创建Document对象（不对mybatis进行解析）
        DocumentReader documentReader = new DocumentReader();
        Document document = documentReader.createDocument(inputStream);

        // 解析mapper映射文件更标签
        parseMapperElement(document.getRootElement());
    }
    // 解析mapper映射文件更标签
    private void parseMapperElement(Element rootElement) {
        String namespace = rootElement.attributeValue("namespace");
        List<Element> selectElements = rootElement.elements();
        selectElements.forEach(element -> {
            parseStatement(namespace,element);
        });
    }

    private void parseStatement(String namespace,Element element) {
        String id = element.attributeValue("id");
        String parameterType = element.attributeValue("parameterType");
        // 获取入参类型（java类型）
        Class<?> parameterTypeClass =  getTypeClass(parameterType);
        String resultType = element.attributeValue("resultType");
        // 获取结果映射类型
        Class<?> resultTypeClass =  getTypeClass(resultType);

        String statementType = element.attributeValue("statementType");

        // 未解析的#{}的sql文本
        String sqlText = element.getTextTrim();
        //解析sql文本
        SqlSource sqlSource = new DefaultSqlSource(sqlText);

        // 将select信息封装到到MapedStatement对象中，如何将MappedStatement封装到Configuration中
        MappedStatement mappedStatement =
                new MappedStatement(id,parameterTypeClass,resultTypeClass,statementType,sqlSource);

        configuration.addMappedStatements(namespace+"."+id,mappedStatement);
    }

    private Class<?> getTypeClass(String parameterType) {
        try {
            Class<?> clazz = Class.forName(parameterType);
            return clazz;
        }catch (Exception e){ }
        return null;
    }
}
