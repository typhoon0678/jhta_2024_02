package com.typhoon0678.mybatis.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisConnectionFactory {

	private static final SqlSessionFactory sqlSessionFactory;

	static {
		String resource = "config.xml";

		try {
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

			System.out.println("session connected");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static SqlSession getSqlSession(boolean isCommit) {
		return sqlSessionFactory.openSession(isCommit);
	}

}
