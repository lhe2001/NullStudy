package com.spring.teampro.team.dao;

import java.util.List;

import com.spring.teampro.team.dto.TeamInfoDTO;
import com.spring.teampro.team.dto.TeamMemberDTO;

public interface TeamDAO {

	List getMyTeamList(int userkey);

	TeamInfoDTO getTeamInfo(int t_key);

	List getTeamMemberInfo(int t_key);

	int updateTeamInfo(TeamInfoDTO dto);

	int updateLMemo(TeamInfoDTO dto);

	int removeMember(int tm_key);

	List allTeamList();

}
