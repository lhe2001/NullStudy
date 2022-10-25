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
	public List<SearchDTO> searchBoardSelectList(SearchDTO dto) {
		System.out.println("SearchDAOImpl SearchBoardSelectList 실행");
		
		List<SearchDTO> SearchBoardSelectList = sqlSession.selectList("mapper.search.searchBoardSelectList", dto);
		
		return SearchBoardSelectList;
	} // 게시글 제목, 이름, 내용 조회
	
	@Override
	public List<SearchDTO> b_titleSearchBoardSelectList(SearchDTO dto) {
		System.out.println("SearchDAOImpl b_titleSearchBoardSelectList 실행");
		
		List<SearchDTO> b_titleSearchBoardSelectList = sqlSession.selectList("mapper.search.b_titleSearchBoardSelectList", dto);
		
		return b_titleSearchBoardSelectList;
	} // 제목 조회
	
	@Override
	public List<SearchDTO> nickNameSearchBoardSelectList(SearchDTO dto) {
		System.out.println("SearchDAOImpl nickNameSearchBoardSelectList 실행");
		
		List<SearchDTO> nickNameSearchBoardSelectList = sqlSession.selectList("mapper.search.nickNameSearchBoardSelectList", dto);
		
		return nickNameSearchBoardSelectList;
	} // 이름 조회
	
	@Override
	public List<SearchDTO> b_contentSearchBoardSelectList(SearchDTO dto) {
		System.out.println("SearchDAOImpl b_contentSearchBoardSelectList 실행");
		
		List<SearchDTO> b_contentSearchBoardSelectList = sqlSession.selectList("mapper.search.b_contentSearchBoardSelectList", dto);
		
		return b_contentSearchBoardSelectList;
	} // 내용 조회
	
	// ----------------------- 팀 목록
	
	@Override
	public List<SearchDTO> t_nameSearchTeamSelectList(SearchDTO dto) {
		System.out.println("SearchDAOImpl t_nameSearchTeamSelectList 실행");
		
		List<SearchDTO> t_nameSearchTeamSelectList = sqlSession.selectList("mapper.search.t_nameSearchTeamSelectList", dto);
		
		return t_nameSearchTeamSelectList;
	} // 팀 이름 조회
	
	// ----------------------- 유저 조회
	
	@Override
	public List<SearchDTO> nickNameSearchUserSelectList(SearchDTO dto) {
		System.out.println("SearchDAOImpl nickNameSearchUserSelectList 실행");
		
		List<SearchDTO> nickNameSearchUserSelectList = sqlSession.selectList("mapper.search.nickNameSearchUserSelectList", dto);
		
		return nickNameSearchUserSelectList;
	} // 닉네임 조회
	
}
