package com.handrt.mybatis.framework.builder;


import com.handrt.mybatis.framework.config.Configuration;
import com.handrt.mybatis.framework.reader.DocumentReader;
import org.apache.commons.dbcp.BasicDataSource;
import org.dom4j.Document;
import org.dom4j.Element;

import java.net.URL;
import java.util.List;
import java.util.Properties;

/**
 *  主要作用就是：解析全局配置文件
 */
public class XMLConfigBuilder {

    private Configuration configuration;

    public XMLConfigBuilder(){
        this.configuration = new Configuration();
    }


    /**
     *  解析全局配置文件
     * @param inputStream
     * @return
     */
    public Configuration parse(URL inputStream) {
        // 创建Document对象（不会对mybatis语以进行解析）
        DocumentReader documentReader = new DocumentReader();
        Document document = documentReader.createDocument(inputStream);

        // 根据mybatis语义去解析Document对象，将解析结果封装到一个对象（Configuration）
        // 解析全局配置文件的根路径
        parseConfiguration(document.getRootElement());

        return configuration;
    }

    /**
     *   解析Configuration的根路径
     * @param rootElement
     */
    private void parseConfiguration(Element rootElement) {
        // 解析environment标签
        paseEnvironmentsElement(rootElement.element("environments"));

        // 解析mappers标签
        parseMappersElement(rootElement.element("mappers"));
    }

    /**
     * 解析mappers标签
     * @param mapperElement
     */
    private void parseMappersElement(Element mapperElement) {
        List<Element> elements = mapperElement.elements();
        elements.forEach(element->{
            parseMapperElement(element);
        });
    }

    private void parseMapperElement(Element element) {

    }

    /**
     * 解析environments标签
     * @param element
     */
    private void paseEnvironmentsElement(Element element) {
        // 解析数据源信息（dom4j+xpath）
        // 将数据源信息封装到Configuration对象中

        // 获取默认的环境对象的ID
        String defaultEnvId = element.attributeValue("default");
        if(defaultEnvId == null || "".equals(defaultEnvId)){
            return ;
        }
        // 获取所有的environment标签对象
        List<Element> elements = element.elements();
        elements.forEach(envElement -> {
            String envId = envElement.attributeValue("id");
            if(envId.equals(defaultEnvId)){
                // 创建数据源对象
                createDataSource(envElement);
            }

        });
    }

    /**
     * 参数是：environment标签
     * @param envElement
     */
    private void createDataSource(Element envElement) {
        // 获取数据源信息
        Element dataSourceElement = envElement.element("dataSource");
        // 获取连接池类型
        String dataSourceType = dataSourceElement.attributeValue("type");
        // 获取所有连接池下的属性+
        List<Element> propertys = (List<Element>) dataSourceElement.element("property");
        Properties properties = new Properties();
        propertys.forEach(property -> {
            String name = property.attributeValue("name");
            String value = property.attributeValue("value");
            properties.setProperty(name,value);
        });

        if("DBCP".equals(dataSourceType)){
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setDriverClassName(properties.getProperty("driver"));
            dataSource.setUrl(properties.getProperty("url"));
            dataSource.setUsername(properties.getProperty("username"));
            dataSource.setPassword(properties.getProperty("password"));
            configuration.setDataSource(dataSource);
        }

    }

}
