package kr.ac.kopo.test;


import static org.junit.Assert.assertNotNull;

import org.apache.ibatis.session.SqlSession;
import org.junit.Ignore;

import kr.ac.kopo.MyConfig;
import kr.ac.kopo.dao.BoardDAO;

//test 하기 위한 class
public class Test {
	//@Ignore
	@org.junit.Test
	public void myConfig접속테스트 () throws Exception{
		MyConfig config = new MyConfig();
		SqlSession session = config.getInstance();
		
		assertNotNull(session);
	}
	@org.junit.Test
	public void BoardDAO생성테스트() throws Exception{
		BoardDAO dao = new BoardDAO();
	}
}
