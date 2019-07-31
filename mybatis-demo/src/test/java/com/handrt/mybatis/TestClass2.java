package com.handrt.mybatis;

import com.handrt.mybatis.framework.builder.XMLConfigBuilder;
import com.handrt.mybatis.framework.config.Configuration;
import org.junit.Test;

import java.io.InputStream;

/**
 * 测试基础案例
 * 基于xml
 * @author Administrator
 *
 */
public class TestClass2 {

	/**
	 * 加载全局配置文件
	 */

	@Test
	public void test()throws Exception  {
		// 1,根据配置文件路径，读取配置文件（输出：InputStream流）
		String resource = "SqlMapConfig.xml";
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(resource);

		XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder();
		Configuration configuration = xmlConfigBuilder.parse(inputStream);
	}


	

}
