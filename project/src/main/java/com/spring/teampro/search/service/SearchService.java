package com.spring.teampro.search.service;

import java.util.List;

import com.spring.teampro.search.dto.SearchDTO;

public interface SearchService {

	// 게시판 List
	
	List<SearchDTO> getSearchBoardSelectList(SearchDTO dto);

	List<SearchDTO> getB_titleSearchBoardSelectList(SearchDTO dto);

	List<SearchDTO> getB_contentSearchBoardSelectList(SearchDTO dto);

	List<SearchDTO> getNickNameSearchBoardSelectList(SearchDTO dto);

	
	// 팀 List
	
	List<SearchDTO> getT_nameSearchTeamSelectList(SearchDTO dto);
	
	
	// 유저 List

	List<SearchDTO> getNickNameSearchUserSelectList(SearchDTO dto);


}
