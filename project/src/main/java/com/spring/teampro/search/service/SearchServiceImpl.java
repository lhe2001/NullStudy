package com.spring.teampro.search.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.teampro.search.dao.SearchDAO;
import com.spring.teampro.search.dao.SearchDAO;
import com.spring.teampro.search.dao.SearchDAO;
import com.spring.teampro.search.dto.SearchDTO;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	SearchDAO searchDAO;
	
	// 자유 게시판
	
	@Override
	public List<SearchDTO> getBoardAllSelectList() {
		return searchDAO.boardAllSelectList();
	}
	
	@Override
	public List<SearchDTO> getSearchBoardSelectList() {
		return searchDAO.searchBoardSelectList();
	}
	
	@Override
	public List<SearchDTO> getB_titleSearchBoardSelectList() {
		return searchDAO.b_titleSearchBoardSelectList();
	}
	
	@Override
	public List<SearchDTO> getB_contentSearchBoardSelectList() {
		return searchDAO.b_contentSearchBoardSelectList();
	}
	
	@Override
	public List<SearchDTO> getNickNameSearchBoardSelectList() {
		return searchDAO.nickNameSearchBoardSelectList();	
	}
	
	// 팀 목록
	
	@Override
	public List<SearchDTO> getSearchTeamSelectList() {
		return searchDAO.searchTeamSelectList();
	}
	
	@Override
	public List<SearchDTO> getT_nameSearchTeamSelectList() {
		return searchDAO.t_nameSearchTeamSelectList();	
	}
	
	// 유저 목록
	
	@Override
	public List<SearchDTO> getSearchUserSelectList() {	
		return searchDAO.searchUserSelectList();	
	}
	
	@Override
	public List<SearchDTO> getNickNameSearchUserSelectList() {	
		return searchDAO.nickNameSearchUserSelectList();	
	}
	
}
