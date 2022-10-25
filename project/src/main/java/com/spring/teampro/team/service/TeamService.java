package com.spring.teampro.team.service;

import java.util.List;
import java.util.Map;

import com.spring.teampro.profile.dto.ProfileUpdateDTO;
import com.spring.teampro.team.dto.ChallengeDTO;
import com.spring.teampro.team.dto.MemberRequestDTO;
import com.spring.teampro.team.dto.TeamInfoDTO;
import com.spring.teampro.team.dto.TeamMemberDTO;

public interface TeamService {

	List getMyTeamList(int userkey);

	TeamInfoDTO getTeamInfo(int t_key);

	List getTeamMemberInfo(int t_key);

	int updateTeamInfo(TeamInfoDTO dto);

	int updateLMemo(TeamInfoDTO dto);

	int removeMember(TeamMemberDTO dto);

	List getAllTeamList(TeamInfoDTO dto);

	boolean alreadyMyTeam(int userkey, int t_key);

	int requestMember(MemberRequestDTO dto);

	int anyAlarm(int t_key);

	List getRequestList(int t_key);

	int acceptMember(MemberRequestDTO dto);

	int deleteTeam(int t_key);

	boolean alreadyRequest(MemberRequestDTO dto);

	int rejectMember(MemberRequestDTO dto);

	int addNewTeam(TeamInfoDTO dto);

	String getTDay(int t_key);

	int updateDday(TeamInfoDTO dto);

	boolean existTeamName(String t_name);

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

	List getMainTeamList();

	boolean alreadyToday(ChallengeDTO dto);

	int setPhoto(ProfileUpdateDTO dto);

	String getPhoto(int userkey);




}
