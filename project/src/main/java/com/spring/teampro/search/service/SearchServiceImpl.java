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
		
		List<SearchDTO> list = searchDAO.boardAllSelectList();
		return list;
	}
	
	@Override
	public List<SearchDTO> getSearchBoardSelectList(String search) {
		
		List<SearchDTO> list = searchDAO.searchBoardSelectList(search);
		return list;
	}
	
	@Override
	public List<SearchDTO> getB_titleSearchBoardSelectList(String search) {
		
		List<SearchDTO> list = searchDAO.b_titleSearchBoardSelectList(search);
		return list;
	}
	
	@Override
	public List<SearchDTO> getB_contentSearchBoardSelectList(String search) {
		
		List<SearchDTO> list = searchDAO.b_contentSearchBoardSelectList(search);
		return list;
	}
	
	@Override
	public List<SearchDTO> getNickNameSearchBoardSelectList(String search) {
		
		List<SearchDTO> list = searchDAO.nickNameSearchBoardSelectList(search);
		return list;
	}
	
	// 팀 목록
	
	@Override
	public List<SearchDTO> getTeamAllList() {
		
		List<SearchDTO> list = searchDAO.teamAllList();
		return list;
	}
	
	@Override
	public List<SearchDTO> getT_nameSearchTeamSelectList(String search) {
		
		List<SearchDTO> list = searchDAO.t_nameSearchTeamSelectList(search);
		return list;
	}
	
	// 유저 목록
	
	@Override
	public List<SearchDTO> getUserAllList() {	
			
		List<SearchDTO> list = searchDAO.userAllList();
		return list;
	}
	
	@Override
	public List<SearchDTO> getNickNameSearchUserSelectList(String search) {	
		
		List<SearchDTO> list = searchDAO.nickNameSearchUserSelectList(search);
		return list;
	}
	
}
