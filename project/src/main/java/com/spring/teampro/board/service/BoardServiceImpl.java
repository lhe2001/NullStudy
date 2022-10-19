package com.spring.teampro.board.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.teampro.board.dao.BoardDAO;
import com.spring.teampro.board.dto.BoardDTO;

@Service("boardService")
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardDAO boardDAO;
	
	// 게시글 전체를 조회할 수 있는 메소드 호출
	@Override
	public List<BoardDTO> getListArticles() {
		// dao클래스에 selectAllArticles() 메소드를 게시글 전체를 조회할 수 있게 짜면 된다.
		List<BoardDTO> list = boardDAO.selectAllArticles();
		// list를 리턴해주면 끝!
		return list;
	}

	// 게시글을 추가 할 수 있는 메소드 호출
	@Override
	public int getAddArticle(Map map) throws Exception {
		return boardDAO.addNewArticle(map);
	}

	// 게시글을 상세보기로 볼 수 있는 메소드 호출
	@Override
	public BoardDTO getViewArticle(int b_articleNo) {
		BoardDTO dto = new BoardDTO();
		dto = boardDAO.selectViewArticle(b_articleNo);
		return dto;
	}

	// 게시글 수정 메소드
	@Override
	public void getUpdateArticle(Map map) {
		boardDAO.modifyArticle(map);
	}

	// 게시글 삭제 메소드
	@Override
	public List<Integer> getDeleteArticle(int b_articleNo) {
		List<Integer> list = boardDAO.selectRemovedArticle(b_articleNo);
		boardDAO.deleteArticle(b_articleNo);
		return list;
	}

	// 게시글 답글 추가 메소드
	@Override
	public int getAddReply(Map map) {
		return boardDAO.addNewArticle(map);
	}


	// 게시글 전체로 검색하는 메소드
	@Override
	public List<BoardDTO> getAllSearch(BoardDTO dto) {
		List<BoardDTO> list = boardDAO.searchAllArticle(dto);
		return list;
	}

	// 말머리로 리스트를 보여줄 메소드
	@Override
	public List<BoardDTO> getSelectViewArticle(BoardDTO dto) {
		List<BoardDTO> list = boardDAO.wiewAllArticle(dto);
		return list;
	}
	// 비밀글을 상세보기로 볼 수 있는 메소드 호출
	@Override
	public BoardDTO getViewSecret(int b_articleNo) {
		BoardDTO dto = new BoardDTO();
		dto= boardDAO.searchPw(b_articleNo);
		System.out.println("service" + dto);
		return dto;
	}
	
	// 비밀번호를 가져오자
	@Override
	public int getPwd(int b_articleNo) {
		return boardDAO.getPw(b_articleNo);
	}

}
