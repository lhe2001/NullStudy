package com.spring.teampro.team.dao;

import java.sql.Date;
import java.util.List;

import com.spring.teampro.team.dto.MemberRequestDTO;
import com.spring.teampro.team.dto.TeamInfoDTO;
import com.spring.teampro.team.dto.TeamMemberDTO;

public interface TeamDAO {

	List getMyTeamList(int userkey);

	TeamInfoDTO getTeamInfo(int t_key);

	List getTeamMemberInfo(int t_key);

	int updateTeamInfo(TeamInfoDTO dto);

	int updateLMemo(TeamInfoDTO dto);

	int removeMember(TeamMemberDTO dto);

	List allTeamList();

	int memberRequest(MemberRequestDTO dto);

	int anyAlarm(int t_key);

	List getRequestList(int t_key);

	int acceptMember(MemberRequestDTO dto);

	int deleteTeam(int t_key);

	int updateMemberCount(int t_key);

	int alreadyRequest(MemberRequestDTO dto);

	int rejectMember(MemberRequestDTO dto);

	int addNewTeam(TeamInfoDTO dto);

	String getTDay(int t_key);

	int updateDday(TeamInfoDTO dto);

	int existTeamName(String t_name);



}
