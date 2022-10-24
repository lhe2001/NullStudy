package com.spring.teampro.search.dao;

import java.util.List;

import com.spring.teampro.search.dto.SearchDTO;

public interface SearchDAO {

	// 자유 게시판
	
	List<SearchDTO> boardAllSelectList(SearchDTO dto);

	List<SearchDTO> searchBoardSelectList(SearchDTO dto);

	List<SearchDTO> b_titleSearchBoardSelectList(SearchDTO dto);

	List<SearchDTO> b_contentSearchBoardSelectList(SearchDTO dto);

	List<SearchDTO> nickNameSearchBoardSelectList(SearchDTO dto);

	// 팀 목록
	
	List<SearchDTO> teamAllList(SearchDTO dto);
	
	List<SearchDTO> t_nameSearchTeamSelectList(SearchDTO dto);

	// 유저 목록

	List<SearchDTO> userAllList(SearchDTO dto);

	List<SearchDTO> nickNameSearchUserSelectList(SearchDTO dto);
}
