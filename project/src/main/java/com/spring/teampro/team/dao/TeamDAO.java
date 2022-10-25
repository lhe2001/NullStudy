package com.spring.teampro.team.dao;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.spring.teampro.profile.dto.ProfileUpdateDTO;
import com.spring.teampro.team.dto.ChallengeDTO;
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

	List allTeamList(TeamInfoDTO dto);

	int memberRequest(MemberRequestDTO dto);

	int anyAlarm(int t_key);

	List getRequestList(int t_key);

	int acceptMember(MemberRequestDTO dto);

	int deleteTeam(int t_key, List list);

	int updateMemberCount(int t_key);

	int alreadyRequest(MemberRequestDTO dto);

	int rejectMember(MemberRequestDTO dto);

	int addNewTeam(TeamInfoDTO dto);

	String getTDay(int t_key);

	int updateDday(TeamInfoDTO dto);

	int existTeamName(String t_name);

	Map getMyRequest(int userkey);

	int cancleRequest(MemberRequestDTO dto);

	int addChallenge(ChallengeDTO dto);

	ChallengeDTO getLatestChallenge(int t_key);

	int attendChallenge(ChallengeDTO dto);

	List getChallengeList(ChallengeDTO dto);

	ChallengeDTO getSummary(ChallengeDTO dto);

	int reviseSummary(ChallengeDTO dto);

	List getMyHistory(ChallengeDTO dto);

	int updateChallengeTitle(ChallengeDTO dto);

	List getTCkey(int t_key);

	List getMainTeamList();

	int alreadyToday(ChallengeDTO dto);

	int setPhoto(ProfileUpdateDTO dto);

	String getPhoto(int userkey);



}
