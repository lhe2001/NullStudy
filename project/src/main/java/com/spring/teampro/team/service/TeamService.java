package com.spring.teampro.team.service;

import java.util.List;

import com.spring.teampro.team.dto.TeamInfoDTO;

public interface TeamService {

	List getMyTeamList(int userkey);

	TeamInfoDTO getTeamInfo(int t_key);

	List getTeamMemberInfo(int t_key);


}
