package com.spring.teampro.search.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.teampro.search.dao.TeamDAO;
import com.spring.teampro.search.dto.TeamDTO;

@Service
public class TeamServiceImpl implements TeamService {

	@Autowired
	TeamDAO teamDAO;
	
	@Override
	public List<TeamDTO> getSearchTeamSelectList() {
		
		return teamDAO.searchTeamSelectList();
		
	}
	
	@Override
	public List<TeamDTO> getT_nameSearchTeamSelectList() {
		
		return teamDAO.t_nameSearchTeamSelectList();
		
	}
	
}
