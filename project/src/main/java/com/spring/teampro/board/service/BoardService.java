package com.spring.teampro.board.service;

import java.util.List;
import java.util.Map;

import com.spring.teampro.board.dto.BoardDTO;
import com.spring.teampro.board.dto.PageDTO;


public interface BoardService {
		
		// 게시글 전체를 조회할 수 있는 메소드 호출
		public List<BoardDTO> getListArticles(int pageNum, int amount);

		// 게시글을 추가 할 수 있는 메소드 호출
		public int getAddArticle(Map map) throws Exception;

		// 게시글을 상세보기로 볼 수 있는 메소드 호출
		public  BoardDTO getViewArticle(int b_articleNo);

		// 게시글 수정 메소드
		public void getUpdateArticle(Map map)throws Exception;

		// 게시글 삭제 메소드
		public List<Integer> getDeleteArticle(int b_articleNo);

		// 게시글 답글 추가 메소드
		public int getAddReply(Map map);

		// 게시글 전체로 검색하는 메소드
		public List<BoardDTO> getAllSearch(BoardDTO dto);

		// 말머리로 리스트를 보여줄 메소드
		public List<BoardDTO> getSelectViewArticle(BoardDTO dto);
		
		// 비밀글을 상세보기로 볼 수 있는 메소드 호출
		public BoardDTO getViewSecret(int b_articleNo);
		
		// 비밀번호를 가져오자
		public int getPwd(int b_articleNo);
		
		// 조회수 추가 메소드
		public void getView(BoardDTO dto);
		
		// 페이징 메소드
		public List<PageDTO> getPaging(int pageNum, int amount);
		
		// 총 페이지 수 가져오는 메소드
		public int getPage();
}
