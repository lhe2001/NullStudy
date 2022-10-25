package com.spring.teampro.search.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.teampro.search.dao.SearchDAO;
import com.spring.teampro.search.dto.SearchDTO;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	SearchDAO searchDAO;
	
	SearchDTO dto = new SearchDTO(); 
	
	// 자유 게시판
	
	@Override
	public List<SearchDTO> getSearchBoardSelectList(SearchDTO dto) {
		
		List<SearchDTO> list = searchDAO.searchBoardSelectList(dto);
		return list;
	}
	
	@Override
	public List<SearchDTO> getB_titleSearchBoardSelectList(SearchDTO dto) {
		
		List<SearchDTO> list = searchDAO.b_titleSearchBoardSelectList(dto);
		return list;
	}
	
	@Override
	public List<SearchDTO> getB_contentSearchBoardSelectList(SearchDTO dto) {
		
		List<SearchDTO> list = searchDAO.b_contentSearchBoardSelectList(dto);
		return list;
	}
	
	@Override
	public List<SearchDTO> getNickNameSearchBoardSelectList(SearchDTO dto) {
		
		List<SearchDTO> list = searchDAO.nickNameSearchBoardSelectList(dto);
		return list;
	}
	
	// 팀 목록
	
	@Override
	public List<SearchDTO> getT_nameSearchTeamSelectList(SearchDTO dto) {
		
		List<SearchDTO> list = searchDAO.t_nameSearchTeamSelectList(dto);
		return list;
	}
	
	// 유저 목록
	
	@Override
	public List<SearchDTO> getNickNameSearchUserSelectList(SearchDTO dto) {	
		
		List<SearchDTO> list = searchDAO.nickNameSearchUserSelectList(dto);
		return list;
	}
	
}
