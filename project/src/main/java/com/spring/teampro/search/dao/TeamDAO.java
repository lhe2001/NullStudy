package com.spring.teampro.search.dao;

import java.util.List;

import com.spring.teampro.search.dto.TeamDTO;

public interface TeamDAO {

	List<TeamDTO> searchTeamSelectList();

	List<TeamDTO> t_nameSearchTeamSelectList();
	
}
