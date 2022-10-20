package com.spring.teampro.search.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.teampro.search.dto.SearchDTO;

@Repository
public class SearchDAOImpl implements SearchDAO {

	@Autowired
	SqlSession sqlSession;
	
	// 자유 게시판
	
	SearchDTO dto = new SearchDTO();
	@Override
	public List<SearchDTO> boardAllSelectList() {
		System.out.println("SearchDAOImpl boardAllList 실행");
			
		List<SearchDTO> boardAllSelectList = sqlSession.selectList("mapper.search.boardAllList");
		
		return boardAllSelectList;
	} //  -------------------- 모든 게시글 조회
	
	@Override
	public List<SearchDTO> searchBoardSelectList(String search) {
		System.out.println("SearchDAOImpl SearchBoardSelectList 실행");
		
		List<SearchDTO> SearchBoardSelectList = sqlSession.selectList("mapper.search.searchBoardSelectList", search);
		
		return SearchBoardSelectList;
	} // 게시글 제목, 이름, 내용 조회
	
	@Override
	public List<SearchDTO> b_titleSearchBoardSelectList(String search) {
		System.out.println("SearchDAOImpl b_titleSearchBoardSelectList 실행");
		
		List<SearchDTO> b_titleSearchBoardSelectList = sqlSession.selectList("mapper.search.b_titleSearchBoardSelectList", search);
		
		return b_titleSearchBoardSelectList;
	} // 제목 조회
	
	@Override
	public List<SearchDTO> nickNameSearchBoardSelectList(String search) {
		System.out.println("SearchDAOImpl nickNameSearchBoardSelectList 실행");
		
		List<SearchDTO> nickNameSearchBoardSelectList = sqlSession.selectList("mapper.search.nickNameSearchBoardSelectList", search);
		
		return nickNameSearchBoardSelectList;
	} // 이름 조회
	
	@Override
	public List<SearchDTO> b_contentSearchBoardSelectList(String search) {
		System.out.println("SearchDAOImpl b_contentSearchBoardSelectList 실행");
		
		List<SearchDTO> b_contentSearchBoardSelectList = sqlSession.selectList("mapper.search.b_contentSearchBoardSelectList", search);
		
		return b_contentSearchBoardSelectList;
	} // 내용 조회
	
	// ----------------------- 팀 목록
	
	@Override
	public List<SearchDTO> teamAllList() {
		System.out.println("SearchDAOImpl teamAllList 실행");
		
		List<SearchDTO> teamAllList = sqlSession.selectList("mapper.search.teamAllList");
		
		return teamAllList;
	} // 모든 팀 조회
	
	@Override
	public List<SearchDTO> t_nameSearchTeamSelectList(String search) {
		System.out.println("SearchDAOImpl t_nameSearchTeamSelectList 실행");
		
		List<SearchDTO> t_nameSearchTeamSelectList = sqlSession.selectList("mapper.search.t_nameSearchTeamSelectList", search);
		
		return t_nameSearchTeamSelectList;
	} // 이름 조회
	
	// ----------------------- 유저 조회
	
	@Override
	public List<SearchDTO> userAllList() {
		System.out.println("SearchDAOImpl userAllList 실행");
		
		List<SearchDTO> userAllList = sqlSession.selectList("mapper.search.userAllList");
		
		return userAllList;
	} // 이름 조회
	
	@Override
	public List<SearchDTO> nickNameSearchUserSelectList(String search) {
		System.out.println("SearchDAOImpl nickNameSearchUserSelectList 실행");
		
		List<SearchDTO> nickNameSearchUserSelectList = sqlSession.selectList("mapper.search.nickNameSearchUserSelectList", search);
		
		return nickNameSearchUserSelectList;
	} // 이름 조회
	
}
