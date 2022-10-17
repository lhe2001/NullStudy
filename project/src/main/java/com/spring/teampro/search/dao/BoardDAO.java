package com.spring.teampro.search.dao;

import java.util.List;

import com.spring.teampro.search.dto.BoardDTO;

public interface BoardDAO {

	List<BoardDTO> boardAllSelectList();

	List<BoardDTO> searchBoardSelectList();

	List<BoardDTO> b_titleSearchBoardSelectList();

	List<BoardDTO> b_contentSearchBoardSelectList();

	List<BoardDTO> nickNameSearchBoardSelectList();

}
