package com.handrt.mybatis;

import com.handrt.mybatis.entity.SystemRole;
import com.handrt.mybatis.framework.ParameterMapping;
import com.handrt.mybatis.framework.builder.XMLConfigBuilder;
import com.handrt.mybatis.framework.config.Configuration;
import com.handrt.mybatis.framework.config.MappedStatement;
import com.handrt.mybatis.framework.sqlsource.BoundSql;
import com.handrt.mybatis.framework.sqlsource.SqlSource;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

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
	public void test() throws Exception  {
		Connection connection = null ;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			// 1,根据配置文件路径，读取配置文件（输出：InputStream流）
			String resource = "SqlMapConfig.xml";
			// 获取相对路径
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(resource);

			XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder();
			Configuration configuration = xmlConfigBuilder.parse(inputStream);

			// 查询xml配置
			System.out.println(configuration);

			// 获取连接
			DataSource dataSource = configuration.getDataSource();
			connection = dataSource.getConnection();

			// 定义sql语句？表示占位符
			MappedStatement mappedStatement = configuration.getMappedStatements("test.selectByPrimaryKey");
			// 获取sqlSource，它还包含了从映射文件中解析出来的sql文本（包含#{}）
			SqlSource sqlSource = mappedStatement.getSqlSource();
			/**
			 * 解析sql文本，将#{}替换为？，
			 * 并且将替换前的参数信息和解析后的sql语句封装到BoundSql对象中
			 */
			BoundSql boundSql = sqlSource.getBoundSql();
			// 获取sql
			String sql  =  boundSql.getSql();
			// 获取预处理 statement
			String statementType = mappedStatement.getStatementType();
			List<Object> results = new ArrayList<>();

			if(statementType.equals("prepared")){
				preparedStatement = connection.prepareStatement(sql);

				// 设置参数，第一个参数为sql 语句中参数的序号（从1开始），第二参数为设置的
				Long id = 90828950997987328L;
                SystemRole systemRole = new SystemRole();
				List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
				for(int i = 0; i < parameterMappings.size();i++) {
					ParameterMapping parameterMapping = parameterMappings.get(i);

					// 要知道参数类型
					Class<?> parameterTpyeClass = parameterMapping.getParameterTypeClass();
					// 要知道参数值（只要知道 参数名称， 就可以去入参对象）
					String parameterName = parameterMapping.getParameterName();
					// 要判断入参是什么类型（8种基本类型，String ,POJO，Map，集合和数组类型等）
					if(parameterTpyeClass == Long.class){
						Long value = id;
						preparedStatement.setObject(i + 1,value);
					}else {

						// 利用反射，通过参数名称，获取对象的属性值
                        Field field = parameterTpyeClass.getDeclaredField(parameterName);
                        Object value = field.get(systemRole);
                        preparedStatement.setObject(i+1,value);
						//preparedStatement.setLong(i + 1,null);
					}
				}


				// 向数据库发出sql执行查询，查询出结果集
				rs = preparedStatement.executeQuery();

				// 遍历查询结果集

				/**
				 *  查询sql的入参类型，出参类型
				 */
				Class<?> resultTypeClass = mappedStatement.getResultTypeClass();

				while(rs.next()){
					Object resultObj = resultTypeClass.newInstance();
					// 通过反射去给实例赋值
					ResultSetMetaData metaData = rs.getMetaData();
					// 获取所有的列数
					int columnCount = metaData.getColumnCount();
					for(int i =1;i<=columnCount; i++){

						String columnName = metaData.getColumnName(i);

						// 前提是列名和属性名称要一致
						Field declaredField = resultTypeClass.getDeclaredField(columnName);
                        // 暴力访问
                        declaredField.setAccessible(true);

                        Type type = declaredField.getGenericType();
                        if("java.lang.Long".equals(type.getTypeName())){
                            // rs的下标是从1开始的
                            declaredField.set(resultObj , Long.parseLong(rs.getObject(i)+""));
                        }else if("java.lang.Integer".equals(type.getTypeName())){
                            // rs的下标是从1开始的
                            declaredField.set(resultObj , Integer.parseInt(rs.getObject(i)+""));
                        }else{
                            // rs的下标是从1开始的
                            declaredField.set(resultObj , rs.getObject(i));
                        }



					}
					results.add(resultObj);

					System.out.println(rs.getLong("id") +" "+rs.getString("name"));

				}
			}else if(statementType.equals("callable")){


			}

		}catch (Exception e){
			e.printStackTrace();
		}finally {
			if(rs != null){
				rs.close();;
			}
			if(preparedStatement != null){
				preparedStatement.close();
			}
			if(connection != null){
				connection.close();
			}


		}
	}


	

}
