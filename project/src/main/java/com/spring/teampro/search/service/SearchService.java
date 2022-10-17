package com.spring.teampro.search.service;

import java.util.List;

import com.spring.teampro.search.dto.SearchDTO;

public interface SearchService {

	// 게시판 서치
	List<SearchDTO> getBoardAllSelectList();

	List<SearchDTO> getSearchBoardSelectList();

	List<SearchDTO> getB_titleSearchBoardSelectList();

	List<SearchDTO> getB_contentSearchBoardSelectList();

	List<SearchDTO> getNickNameSearchBoardSelectList();
	
	// 팀 서치
	List<SearchDTO> getSearchTeamSelectList();

	List<SearchDTO> getT_nameSearchTeamSelectList();
	
	// 유저 서치
	List<SearchDTO> getSearchUserSelectList();

	List<SearchDTO> getNickNameSearchUserSelectList();
	
}
