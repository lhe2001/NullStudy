package com.spring.teampro.search.service;

import java.util.List;

import com.spring.teampro.search.dto.TeamDTO;

public interface TeamService {

	List<TeamDTO> getSearchTeamSelectList();

	List<TeamDTO> getT_nameSearchTeamSelectList();

}
