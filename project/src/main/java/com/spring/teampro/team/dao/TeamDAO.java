package com.spring.teampro.team.dao;

import java.util.List;

import com.spring.teampro.team.dto.TeamInfoDTO;

public interface TeamDAO {

	List getMyTeamList(int userkey);

	TeamInfoDTO getTeamInfo(int t_key);

}
