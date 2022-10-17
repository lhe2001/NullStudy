package com.spring.teampro.search.dao;

import java.util.List;

import com.spring.teampro.search.dto.SearchDTO;

public interface SearchDAO {

	// 자유 게시판
	
	List<SearchDTO> boardAllSelectList();

	List<SearchDTO> searchBoardSelectList();

	List<SearchDTO> b_titleSearchBoardSelectList();

	List<SearchDTO> b_contentSearchBoardSelectList();

	List<SearchDTO> nickNameSearchBoardSelectList();

	// 팀 목록
	
	List<SearchDTO> searchTeamSelectList();

	List<SearchDTO> t_nameSearchTeamSelectList();


	// 유저 목록

	List<SearchDTO> searchUserSelectList();

	List<SearchDTO> nickNameSearchUserSelectList();

}
