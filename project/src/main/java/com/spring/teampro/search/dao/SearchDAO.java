package com.spring.teampro.search.dao;

import java.util.List;

import com.spring.teampro.search.dto.SearchDTO;

public interface SearchDAO {

	// 자유 게시판
	
	List<SearchDTO> boardAllSelectList();

	List<SearchDTO> searchBoardSelectList(String search);

	List<SearchDTO> b_titleSearchBoardSelectList(String search);

	List<SearchDTO> b_contentSearchBoardSelectList(String search);

	List<SearchDTO> nickNameSearchBoardSelectList(String search);

	// 팀 목록
	
	List<SearchDTO> teamAllList();
	
	List<SearchDTO> t_nameSearchTeamSelectList(String search);

	// 유저 목록

	List<SearchDTO> userAllList();

	List<SearchDTO> nickNameSearchUserSelectList(String search);
}
