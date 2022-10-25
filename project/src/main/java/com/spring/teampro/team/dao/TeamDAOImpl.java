package com.spring.teampro.team.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.teampro.profile.dto.ProfileUpdateDTO;
import com.spring.teampro.team.dto.ChallengeDTO;
import com.spring.teampro.team.dto.MemberRequestDTO;
import com.spring.teampro.team.dto.TeamInfoDTO;
import com.spring.teampro.team.dto.TeamMemberDTO;

@Repository
public class TeamDAOImpl implements TeamDAO {

	private static final Logger logger = LoggerFactory.getLogger(TeamDAOImpl.class);
	
	@Autowired
	SqlSession sqlSession;
	

	//>>>>>>>>>>>>>>>SELECT 단순 가져오기>>>>>>>>>>>>>>
	//나의 팀 리스트 가져오기
	@Override
	public List getMyTeamList(int userkey) {
		List list = sqlSession.selectList("mapper.team.myTeamList",userkey);
		return sqlSession.selectList("mapper.team.myTeamList",userkey);
	}
	
	//팀정보 가져오기
	@Override
	public TeamInfoDTO getTeamInfo(int t_key) {
		
		return sqlSession.selectOne("mapper.team.teamInfo",t_key);
	}
	
	//전체팀 List 가져오기
	@Override
	public List allTeamList(TeamInfoDTO dto) {
		return sqlSession.selectList("mapper.team.allTeamList",dto);
	}
	
	//전체팀 getMainTeamList 가져오기
	@Override
	public List getMainTeamList() {
		return sqlSession.selectList("mapper.team.getMainTeamList");
	}
	
	//팀멤버 정보 가져오기
	@Override
	public List getTeamMemberInfo(int t_key) {
		
		return sqlSession.selectList("mapper.team.teamMemberInfo",t_key);
	}
	
	//가입요청 알람이 있는가? 몇개있는가?
	@Override
	public int anyAlarm(int t_key) {
		return sqlSession.selectOne("mapper.team.anyAlarm",t_key);
	}
	
	//팀에가입요청List를 가져오기
	@Override
	public List getRequestList(int t_key) {
		return sqlSession.selectList("mapper.team.getRequestList",t_key);
	}

	//이미 해당팀에 가입요청을 했는가?(중복체크)
	@Override
	public int alreadyRequest(MemberRequestDTO dto) {
		return sqlSession.selectOne("mapper.team.alreadyRequest",dto);
	}
	
	//디데이 가져오기
	@Override
	public String getTDay(int t_key) {
		return sqlSession.selectOne("mapper.team.getTDay",t_key);
	}
	//팀이름 중복체크 
	@Override
	public int existTeamName(String t_name) {
		return sqlSession.selectOne("mapper.team.existTeamName",t_name);
	}
	//나의 가입요청 가져오기
	@Override
	public Map getMyRequest(int userkey) {
		Map map = new HashMap();
		
		List waitingList =  sqlSession.selectList("mapper.team.getMyRequest_wait",userkey);
		List rejectList = sqlSession.selectList("mapper.team.getMyRequest_reject",userkey);
		
		map.put("waitingList", waitingList);
		map.put("rejectList", rejectList);
		
		return map;
	}
	//latest challenge 가져오기
	@Override
	public ChallengeDTO getLatestChallenge(int t_key) {
		return sqlSession.selectOne("mapper.team.getLatestChallenge",t_key);
	}
	//나의 현재 챌린지 상태 가져오기
	@Override
	public List getChallengeList(ChallengeDTO dto) {
		return sqlSession.selectList("mapper.team.getChallengeList",dto);
	}
	//나의 현재 챌린지 서머리 가져오기
	@Override
	public ChallengeDTO getSummary(ChallengeDTO dto) {
		return sqlSession.selectOne("mapper.team.getSummary",dto);
	}
	//지난 챌린지 가져오기 
	@Override
	public List getMyHistory(ChallengeDTO dto) {
		return sqlSession.selectList("mapper.team.getMyHistory",dto);
	}
	//오늘 출석을 했는가? 
	@Override
	public int alreadyToday(ChallengeDTO dto) {
		logger.info(">>>>>>"+dto.getTc_key());
		logger.info(">>>>>>"+dto.getUserKey());
		return sqlSession.selectOne("mapper.team.alreadyToday",dto);
	}
	//getTCkey
	@Override
	public List getTCkey(int t_key) {
		return sqlSession.selectList("mapper.team.getTCkey",t_key);
	}
	//getPhoto
	@Override
	public String getPhoto(int userkey) {
		logger.info(">>>>"+userkey);
		return sqlSession.selectOne("mapper.team.getPhoto",userkey);
	}
	
	//>>>>>>>>>>>>>>>UPDATE 수정하기>>>>>>>>>>>>>>
	//팀정보 업데이트 하기
	@Override
	public int updateTeamInfo(TeamInfoDTO dto) {
		return sqlSession.update("mapper.team.updateTeamInfo",dto);
	}
	
	//팀멤버수 업데이트 하기
	@Override
	public int updateMemberCount(int t_key) {
		int count = sqlSession.selectOne("mapper.team.getMemberCount",t_key);
		TeamInfoDTO dto = new TeamInfoDTO();
		logger.info("count>>>>>>>>>>>>>>>>>>>>:"+count);
		dto.setT_key(t_key);
		dto.setT_number(count);
		return sqlSession.update("mapper.team.updateMemberCount",dto);
	}

	//조장한마디 업데이트 하기
	@Override
	public int updateLMemo(TeamInfoDTO dto) {
		int result = sqlSession.update("mapper.team.updateLMemo",dto);
		logger.info("result>>>>>>>>>>>>>"+result+dto.getT_key());
		return sqlSession.update("mapper.team.updateLMemo",dto);
	}

	//가입요청을 수락하기
	@Override
	public int acceptMember(MemberRequestDTO dto) {
		sqlSession.update("mapper.team.accpetMember",dto);
		return sqlSession.insert("mapper.team.addTeamMember",dto);
	}
	
	//가입요청을 거절하기
	@Override
	public int rejectMember(MemberRequestDTO dto) {
		return sqlSession.update("mapper.team.rejectMember",dto);
	}
	
	//디데이 수정하기
	@Override
	public int updateDday(TeamInfoDTO dto) {
		logger.info("dto.getT_day()"+dto.getT_day());
		return sqlSession.update("mapper.team.updateDday",dto);
	}
	//revise Summary
	@Override
	public int reviseSummary(ChallengeDTO dto) {
		return sqlSession.update("mapper.team.reviseSummary",dto);
	}
	//updateChallengeTitle
	@Override
	public int updateChallengeTitle(ChallengeDTO dto) {
		return sqlSession.update("mapper.team.updateChallengeTitle",dto);
	}
	//updateProfile
	@Override
	public int setPhoto(ProfileUpdateDTO dto) {
		int result = sqlSession.update("mapper.team.setPhoto",dto);
		logger.info("USERKEY"+dto.getUserKey());
		System.out.println("upload"+result);
		return result;
	}
	
	//>>>>>>>>>>>>>>>DELETE 삭제하기>>>>>>>>>>>>>>
	//멤버 강퇴하기 
	@Override
	public int removeMember(TeamMemberDTO dto) {
		int remove = sqlSession.delete("mapper.team.removeMember",dto);
		int t_key = dto.getT_key();
		return this.updateMemberCount(t_key);
	}
	//팀삭제
	@Override
	public int deleteTeam(int t_key, List list) {
		logger.info("ddddddddddddddd"+list.size());
		logger.info("t_key>>>>>>>>>>"+t_key);
		if(list.size()>0) {
			sqlSession.delete("mapper.team.deleteTeam_summary",list);
		}
		sqlSession.delete("mapper.team.deleteTeam_challenge",t_key);
		sqlSession.delete("mapper.team.deleteTeam_request",t_key);
		sqlSession.delete("mapper.team.deleteTeam_member",t_key);
		return sqlSession.delete("mapper.team.deleteTeam_team",t_key);
	}
	//가입요청 취소하기 & 거절된 요청 삭제하기
	@Override
	public int cancleRequest(MemberRequestDTO dto) {
		return sqlSession.delete("mapper.team.deleteRequest",dto);
	}
	
	//>>>>>>>>>>>>>>>INSERT 추가하기>>>>>>>>>>>>>>
	//가입요청 업데이트
	@Override
	public int memberRequest(MemberRequestDTO dto) {
		return sqlSession.insert("mapper.team.memberRequest",dto);
	}
	//newChallenge
	@Override
	public int addChallenge(ChallengeDTO dto) {
		return sqlSession.insert("mapper.team.addChallenge",dto);
	}

	//새팀 생성하고 member넣기
	@Override
	public int addNewTeam(TeamInfoDTO dto) {
		sqlSession.insert("mapper.team.addNewTeam_team",dto);
		int t_key = sqlSession.selectOne("mapper.team.addNewTeam_teamkey",dto);
		dto.setT_key(t_key);
		
		return sqlSession.insert("mapper.team.addNewTeam_member",dto);
	}
	
	//challenge 출석
		@Override
		public int attendChallenge(ChallengeDTO dto) {
			return sqlSession.insert("mapper.team.attendChallenge",dto);
		}
	

	

	


	

	
	
	
	
}
