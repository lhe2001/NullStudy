package com.spring.teampro.search.service;

import java.util.List;

import com.spring.teampro.search.dto.BoardDTO;

public interface BoardService {

	List<BoardDTO> getBoardAllSelectList();

	List<BoardDTO> getSearchBoardSelectList();

	List<BoardDTO> getB_titleSearchBoardSelectList();

	List<BoardDTO> getB_contentSearchBoardSelectList();

	List<BoardDTO> getNickNameSearchBoardSelectList();
	
}
