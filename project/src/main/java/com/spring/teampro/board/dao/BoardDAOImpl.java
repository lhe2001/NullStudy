package com.spring.teampro.board.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.teampro.board.dto.BoardDTO;


@Repository("boardDAO")
public class BoardDAOImpl implements BoardDAO {

	@Autowired
	SqlSession sqlSession;	
	
	// selectAllArticles() 메소드 만들자!!! return 타입은 List <전체 목록 조회>
	@Override
	public List<BoardDTO> selectAllArticles() {

		List<BoardDTO> list = sqlSession.selectList("mapper.board.selectAllArticles");
		
		return list;
	}

	// max함수 이용하여 getMaxArticleNO() 메소드 먼저 생성 여기서만 쓰고 버릴 것 이므로 private로 만들자
	// return값이 숫자이므로 int로 받자
	private int getMaxArticleNO() {
		int articleNo = sqlSession.selectOne("mapper.board.getMaxArticleNO");
		System.out.println("DAO articleNo : " + articleNo);
		return articleNo; // 아닐경우(없겠지만) 랜덤숫자 0 돌려주자
	}

	// addNewArticle() 메소드 작성 <Board 에 글 추가>
	public int addNewArticle(Map map) {

		int b_articleNo = getMaxArticleNO();// 새 글 번호는 메소드 리턴값으로 쓰자
		System.out.println("DAO b_articleNo : " + b_articleNo);
		
		map.put("b_articleNo",b_articleNo);
		sqlSession.insert("mapper.board.addNewArticle",map);
		return b_articleNo;
	}

	// viewArticle() 메소드 작성 <글 상세 목록 view>
	// 여기서 수정 해야 할 것은 글 내용에 어떤 내용들을 보여줄지 생각...

	public BoardDTO selectViewArticle(int b_articleNo) {
		BoardDTO dto =new BoardDTO();
		dto.setB_articleNo(b_articleNo);
		dto = sqlSession.selectOne("mapper.board.selectViewArticle", b_articleNo);
		return dto;
	}

	// 글 수정하는 메소드
	public void modifyArticle(Map map) {
		sqlSession.update("mapper.board.modifyArticle", map);
	}

	// 삭제하는 메소드
	public void deleteArticle(int b_articleNo) {
		sqlSession.delete("mapper.board.deleteArticle",b_articleNo);
	}

	// 삭제할 글 번호 가져오는 메소드
	public List<Integer> selectRemovedArticle(int b_articleNo) {
		List<Integer> list = new ArrayList<Integer>();
		return list;
	}

	// 서치하는 메소드
	public List<BoardDTO> searchAllArticle(BoardDTO dto) {
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		list = sqlSession.selectList("mapper.board.searchAllArticle",dto);
		return list;
	}

	// 말머리에서 선택했을때 리스트 출력시킬 메소드
	public List<BoardDTO> wiewAllArticle(BoardDTO dto) {
//		int list_sel = dto.getList_sel();
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		list = sqlSession.selectList("mapper.board.wiewAllArticle",dto);
		return list;
	}

	// 비밀글 조회 메소드
	public BoardDTO searchPw(int b_articleNo) {
		BoardDTO dto = new BoardDTO();
		dto = sqlSession.selectOne("mapper.board.searchPw",b_articleNo);
		System.out.println("dao " + dto);
		return dto;
	}
	
	// 비밀번호 가져오는 메소드
	public int getPw(int b_articleNo) {
		int b_articlePwd = sqlSession.selectOne("mapper.board.getPw",b_articleNo);
		System.out.println("b_articlePwd = " + b_articlePwd);
		return b_articlePwd;
	}
}
