package com.handrt.mybatis.framework.reader;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;

import java.net.URL;

/**
 *
 */
public class DocumentReader {
    /**
     * 创建Document对象
     * @param inputStream
     * @return
     */
    public Document createDocument(URL inputStream) {
        // 解析document
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(inputStream);
        }catch (Exception e){

        }
        return document;
    }
}
