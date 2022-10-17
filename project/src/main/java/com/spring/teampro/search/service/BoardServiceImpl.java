package com.spring.teampro.search.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.teampro.search.dao.BoardDAO;
import com.spring.teampro.search.dto.BoardDTO;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardDAO boardDAO;
	
	@Override
	public List<BoardDTO> getBoardAllSelectList() {
		
		return boardDAO.boardAllSelectList();
		
	}
	
	@Override
	public List<BoardDTO> getSearchBoardSelectList() {
		
		return boardDAO.searchBoardSelectList();
		
	}
	
	@Override
	public List<BoardDTO> getB_titleSearchBoardSelectList() {
		
		return boardDAO.b_titleSearchBoardSelectList();
		
	}
	
	@Override
	public List<BoardDTO> getB_contentSearchBoardSelectList() {
		
		return boardDAO.b_contentSearchBoardSelectList();
		
	}
	
	@Override
	public List<BoardDTO> getNickNameSearchBoardSelectList() {
		
		return boardDAO.nickNameSearchBoardSelectList();
		
	}
	
}
