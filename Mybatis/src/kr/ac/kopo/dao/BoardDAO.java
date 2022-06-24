package kr.ac.kopo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;
import org.junit.experimental.categories.IncludeCategories;

import kr.ac.kopo.MyConfig;
import kr.ac.kopo.vo.BoardVO;

public class BoardDAO {
	private SqlSession session;
	
	public BoardDAO() {
		session = new MyConfig().getInstance();
	}
	
	public void insert() {
		BoardVO board = new BoardVO();
		board.setTitle("mybatis 삽입2");
		board.setWriter("구이경");
		board.setContent("mybatis에서 삽입2");
		
		// board.xml의 쿼리문 실행하기 위해서 sqlsesssion 객체 필요 
		//	board.xml의	namespace+id 합치기
		session.insert("dao.BoardDAO.insertBoard",board); 
		//sqlsesssion는 오토 커밋이 아니다 .
		session.commit();
		System.out.println("삽입완료");
	}
	
	public void select () {
		List <BoardVO> boardList = session.selectList("dao.BoardDAO.selectAllBoard");
		for(BoardVO board : boardList) {
			System.out.println(board);
		}
	}
	
	
	public void selectOne() {
		// 33번 게시물 조회
		BoardVO vo = new BoardVO();
		vo.setNo(1);
		
//		BoardVO board = session.selectOne("dao.BoardDAO.selectByNo", 33);
//		BoardVO board = session.selectOne("dao.BoardDAO.selectByNo2", vo);
		BoardVO board = session.selectOne("dao.BoardDAO.selectByNo3", vo);
		System.out.println(board);
	}
	
	public void selectWhere() {
		//제목이 "제목"으로 시작하는 게시물 조회
		List <BoardVO> list = session.selectList("dao.BoardDAO.selectWhere","삽입");
		for(BoardVO board : list) {
			System.out.println(board);
		}
		
	}
	
	public void selectWhere2() {
		
		BoardVO vo = new BoardVO();
		
		/*
		 * //방법 1 : 제목이 mybatis 삽입이면서 작성자가 user 인 게시물 조회 vo.setWriter("구이경");
		 * vo.setTitle("mybatis 삽입");
		 */
		
		//방법 2 : 제목이 mybatis 삽입 게시물 조회
		//vo.setTitle("mybatis 삽입");
		
		//방법 3 : 작성자가 user인 게시물 조회
		vo.setWriter("구이경");
		List <BoardVO> list = session.selectList("dao.BoardDAO.selectWhere2", vo);
		for(BoardVO board2 : list) {
			System.out.println(board2);
		}
		
	}
	
	public void selectNos() {
		// 1, 2, 6, 10 ,15 ,19, 24, 30 ,33 에 속한 게시물 조회
		int [] nos = {1, 2, 6, 10 ,15 ,19, 24, 30 ,33}; 
		
		BoardVO vo = new BoardVO();
		vo.setNos(nos);
		
		// boarvo에 nos를 멤버변수로 쓰는게 쫌 찝찝
		//List <BoardVO> list = session.selectList("dao.BoardDAO.selectNos",vo);
		
		
		List <BoardVO> list = session.selectList("dao.BoardDAO.selectNos2",nos);
	
		for(BoardVO board : list) {
			System.out.println(board);
		}
	}
	
	void selectMap() {
//		제목이 "mybatis 삽입"이면서 작성자가 "user"인 게시물 조회
		//vo 형태 대신, vo를 만들 필요가 없으면서 db에 접근해야될때 map을 쓴다. 
		Map<String, String> map = new HashMap<>();
		map.put("title", "mybatis 삽입");
		map.put("writer", "구이경");
		
		List<BoardVO> list = session.selectList("dao.BoardDAO.selectMap", map);
		//List<BoardVO> list = session.selectList("dao.BoardDAO.selectMap2", map);
		for(BoardVO board : list) {
			System.out.println(board);
		}
	}
	void selectMap2() {
//		24번 게시물 조회
		Map<String, Object> board = session.selectOne("dao.BoardDAO.selectMap2", 24);
		
		Set<String> keys = board.keySet();
		for(String key : keys) {
			System.out.println(key + " : " + board.get(key));
		}
	}
	
	
	public void work() {
		
		//selectMap2();
		selectMap();
		//selectNos();
		//selectWhere2();
		//selectWhere();
		//selectOne();
		//select();
		//insert();
	}
	
}
