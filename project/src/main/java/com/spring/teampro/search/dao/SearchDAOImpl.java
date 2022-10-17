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
	
	@Override
	public List<SearchDTO> boardAllSelectList() {
		System.out.println("SearchDAOImpl boardAllSelectList 실행");
		
		List<SearchDTO> boardAllSelectList = sqlSession.selectList("mapper.search.boardAllSelectList");
		
		return boardAllSelectList;
	} // 모든 게시글 조회
	
	@Override
	public List<SearchDTO> searchBoardSelectList() {
		System.out.println("SearchDAOImpl SearchBoardSelectList 실행");
		
		List<SearchDTO> SearchBoardSelectList = sqlSession.selectList("mapper.search.SearchBoardSelectList");
		
		return SearchBoardSelectList;
	} // 게시글 제목, 이름, 내용 조회
	
	@Override
	public List<SearchDTO> b_titleSearchBoardSelectList() {
		System.out.println("SearchDAOImpl b_titleSearchBoardSelectList 실행");
		
		List<SearchDTO> b_titleSearchBoardSelectList = sqlSession.selectList("mapper.Board.b_titleSearchBoardSelectList");
		
		return b_titleSearchBoardSelectList;
	} // 제목 조회
	
	@Override
	public List<SearchDTO> nickNameSearchBoardSelectList() {
		System.out.println("SearchDAOImpl nickNameSearchBoardSelectList 실행");
		
		List<SearchDTO> nickNameSearchBoardSelectList = sqlSession.selectList("mapper.Board.nickNameSearchBoardSelectList");
		
		return nickNameSearchBoardSelectList;
	} // 이름 조회
	
	@Override
	public List<SearchDTO> b_contentSearchBoardSelectList() {
		System.out.println("SearchDAOImpl b_contentSearchBoardSelectList 실행");
		
		List<SearchDTO> b_contentSearchBoardSelectList = sqlSession.selectList("mapper.Board.b_contentSearchBoardSelectList");
		
		return b_contentSearchBoardSelectList;
	} // 내용 조회
	
	// 팀 목록
	
	@Override
	public List<SearchDTO> searchTeamSelectList() {
		System.out.println("SearchDAOImpl searchTeamSelectList 실행");
		
		List<SearchDTO> searchTeamSelectList = sqlSession.selectList("mapper.Board.searchTeamSelectList");
		
		return searchTeamSelectList;
	} // 모든 팀 조회
	
	@Override
	public List<SearchDTO> t_nameSearchTeamSelectList() {
		System.out.println("SearchDAOImpl t_nameSearchTeamSelectList 실행");
		
		List<SearchDTO> t_nameSearchTeamSelectList = sqlSession.selectList("mapper.Board.t_nameSearchTeamSelectList");
		
		return t_nameSearchTeamSelectList;
	} // 이름 조회
	
	// 유저 조회
	
	@Override
	public List<SearchDTO> searchUserSelectList() {
		System.out.println("SearchDAOImpl searchUserSelectList 실행");
		
		List<SearchDTO> searchUserSelectList = sqlSession.selectList("mapper.Board.searchUserSelectList");
		
		return searchUserSelectList;
	} // 이름 조회
	
	@Override
	public List<SearchDTO> nickNameSearchUserSelectList() {
		System.out.println("SearchDAOImpl nickNameSearchUserSelectList 실행");
		
		List<SearchDTO> nickNameSearchUserSelectList = sqlSession.selectList("mapper.Board.nickNameSearchUserSelectList");
		
		return nickNameSearchUserSelectList;
	} // 이름 조회
	
	
}
