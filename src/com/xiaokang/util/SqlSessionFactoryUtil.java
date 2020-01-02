package com.xiaokang.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * 
 * @Description: SqlSessionFactoryUtil工具类,获取SqlSession对象来操作数据库
 * @author 小康
 * @version V1.0.0 2019年11月23日 下午1:01:58
 */
public class SqlSessionFactoryUtil {
	private static SqlSessionFactory sqlSessionFactory = null;
	private static InputStream is = null;
	// 在静态代码块读取mybatis-config.xml配置文件
	static {
		try {
			is = Resources.getResourceAsStream("mybatis-config.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 采用单例模式创建工厂对象
	public static SqlSessionFactory getSqlSessionFactory() {
		if (sqlSessionFactory == null) {
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
		}
		return sqlSessionFactory;
	}

	// 获得SqlSession对象
	public static SqlSession openSqlSession() {
		return getSqlSessionFactory().openSession();
	}
}
