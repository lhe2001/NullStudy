package com.spring.teampro.team.service;

import java.util.List;

import com.spring.teampro.team.dto.MemberRequestDTO;
import com.spring.teampro.team.dto.TeamInfoDTO;
import com.spring.teampro.team.dto.TeamMemberDTO;

public interface TeamService {

	List getMyTeamList(int userkey);

	TeamInfoDTO getTeamInfo(int t_key);

	List getTeamMemberInfo(int t_key);

	int updateTeamInfo(TeamInfoDTO dto);

	int updateLMemo(TeamInfoDTO dto);

	int removeMember(int tm_key);

	List getAllTeamList();

	boolean alreadyMyTeam(int userkey, int t_key);

	int requestMember(MemberRequestDTO dto);

	int anyAlarm(int t_key);


}
