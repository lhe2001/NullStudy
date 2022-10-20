package com.spring.teampro.search.service;

import java.util.List;

import com.spring.teampro.search.dto.SearchDTO;

public interface SearchService {

	// 게시판 List
	
	List<SearchDTO> getBoardAllSelectList();

	List<SearchDTO> getSearchBoardSelectList(String search);

	List<SearchDTO> getB_titleSearchBoardSelectList(String search);

	List<SearchDTO> getB_contentSearchBoardSelectList(String search);

	List<SearchDTO> getNickNameSearchBoardSelectList(String search);

	
	// 팀 List
	
	List<SearchDTO> getTeamAllList();

	List<SearchDTO> getT_nameSearchTeamSelectList(String search);
	
	
	// 유저 List

	List<SearchDTO> getUserAllList();

	List<SearchDTO> getNickNameSearchUserSelectList(String search);


}
