package com.spring.teampro.search.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.teampro.search.dto.BoardDTO;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<BoardDTO> boardAllSelectList() {
		System.out.println("BoardDAOImpl boardAllSelectList 실행");
		
		List<BoardDTO> boardAllSelectList = sqlSession.selectList("mapper.search.boardAllSelectList");
		
		return boardAllSelectList;
	} // 모든 게시글 조회
	
	@Override
	public List<BoardDTO> searchBoardSelectList() {
		System.out.println("BoardDAOImpl SearchBoardSelectList 실행");
		
		List<BoardDTO> SearchBoardSelectList = sqlSession.selectList("mapper.search.SearchBoardSelectList");
		
		return SearchBoardSelectList;
	} // 게시글 제목, 이름, 내용 조회
	
	@Override
	public List<BoardDTO> b_titleSearchBoardSelectList() {
		System.out.println("BoardDAOImpl b_titleSearchBoardSelectList 실행");
		
		List<BoardDTO> b_titleSearchBoardSelectList = sqlSession.selectList("mapper.Board.b_titleSearchBoardSelectList");
		
		return b_titleSearchBoardSelectList;
	} // 제목 조회
	
	@Override
	public List<BoardDTO> nickNameSearchBoardSelectList() {
		System.out.println("BoardDAOImpl nickNameSearchBoardSelectList 실행");
		
		List<BoardDTO> nickNameSearchBoardSelectList = sqlSession.selectList("mapper.Board.nickNameSearchBoardSelectList");
		
		return nickNameSearchBoardSelectList;
	} // 이름 조회
	
	@Override
	public List<BoardDTO> b_contentSearchBoardSelectList() {
		System.out.println("BoardDAOImpl b_contentSearchBoardSelectList 실행");
		
		List<BoardDTO> b_contentSearchBoardSelectList = sqlSession.selectList("mapper.Board.b_contentSearchBoardSelectList");
		
		return b_contentSearchBoardSelectList;
	} // 내용 조회
	
}
