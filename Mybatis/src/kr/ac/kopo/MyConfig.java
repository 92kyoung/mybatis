package kr.ac.kopo;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyConfig {
	private SqlSession session;
	
	//기본생성자 생성
	public MyConfig() {
		
		String resource = "mybatis-config.xml";
		try {
			InputStream inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory 
							= new SqlSessionFactoryBuilder().build(inputStream);
		
	        session = sqlSessionFactory.openSession();
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public SqlSession getInstance() {
		return session;
	}
	
}













