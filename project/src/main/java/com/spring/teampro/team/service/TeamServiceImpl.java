package com.spring.teampro.team.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.teampro.profile.dto.ProfileUpdateDTO;
import com.spring.teampro.team.dao.TeamDAO;
import com.spring.teampro.team.dto.ChallengeDTO;
import com.spring.teampro.team.dto.MemberRequestDTO;
import com.spring.teampro.team.dto.MyTeamListDTO;
import com.spring.teampro.team.dto.TeamInfoDTO;
import com.spring.teampro.team.dto.TeamMemberDTO;

@Service
public class TeamServiceImpl implements TeamService {
	
	private static final Logger logger = LoggerFactory.getLogger(TeamServiceImpl.class);

	@Autowired
	TeamDAO dao;
	
	@Override
	public int setPhoto(ProfileUpdateDTO dto) {
		return dao.setPhoto(dto);
	}
	
	@Override
	public String getPhoto(int userkey) {
		return dao.getPhoto(userkey);
	}
	//>>>>>>>>>>>>>>>>팀 관련>>>>>>>>>>>>>>>>>>>>>>>>>>>>>//
	//나의 팀 리스트
	@Override
	public List<MyTeamListDTO> getMyTeamList(int userkey) {
		List<MyTeamListDTO> list =  dao.getMyTeamList(userkey);
		
		for(int i=0; i<list.size();i++) {
			MyTeamListDTO dto =list.get(i);
			int t_filed = dto.getT_field();
			
			switch(t_filed) {
			case 1:
				dto.setT_field2("코딩");
				break;
			case 2:
				dto.setT_field2("자격증");
				break;
			case 3:
				dto.setT_field2("토익");
				break;
			case 4:
				dto.setT_field2("기타");
				break;
			}
		}
		return list;
	}
	
	@Override
	public List getMainTeamList() {
		List<TeamInfoDTO> list = dao.getMainTeamList();
		
		for(int i=0; i<list.size();i++) {
			TeamInfoDTO dto = list.get(i);
			int t_filed = dto.getT_field();
			
			switch(t_filed) {
			case 1:
				dto.setT_field2("코딩");
				break;
			case 2:
				dto.setT_field2("자격증");
				break;
			case 3:
				dto.setT_field2("토익");
				break;
			case 4:
				dto.setT_field2("기타");
				break;
			}
		}
		return list;
	}
	
	//내가 속한 팀인가요?
	@Override
	public boolean alreadyMyTeam(int userkey,int t_key) {
		
		boolean result = false;
		
		List list = dao.getMyTeamList(userkey);
		
		for(int i=0; i<list.size();i++) {
			MyTeamListDTO dto = (MyTeamListDTO) list.get(i);
			int myt_key = dto.getT_key();
			if(myt_key == t_key) {
				result = true;
			}
		}
		return result;
	}
	
	//팀정보 가져오기
	@Override
	public TeamInfoDTO getTeamInfo(int t_key) {
		
		TeamInfoDTO dto =  dao.getTeamInfo(t_key);
		int t_filed = dto.getT_field();
		
		switch(t_filed) {
		case 1:
			dto.setT_field2("코딩");
			break;
		case 2:
			dto.setT_field2("자격증");
			break;
		case 3:
			dto.setT_field2("토익");
			break;
		case 4:
			dto.setT_field2("기타");
			break;
		}
		
		return dto;
	}

	//팀정보 업데이트 하기
	@Override
	public int updateTeamInfo(TeamInfoDTO dto) {
		return dao.updateTeamInfo(dto);
	}
		
	//조장한마디 업데이트 하기 
	@Override
	public int updateLMemo(TeamInfoDTO dto) {
		return dao.updateLMemo(dto);
	}
	
	//전체 팀리스트 가져오기
	@Override
	public List getAllTeamList(TeamInfoDTO dto) {
		
		List list = dao.allTeamList(dto);
		
		for(int i=0; i<list.size();i++) {
			TeamInfoDTO dtoo = (TeamInfoDTO)list.get(i);
			int t_filed = dtoo.getT_field();
			
			switch(t_filed) {
			case 1:
				dtoo.setT_field2("코딩");
				break;
			case 2:
				dtoo.setT_field2("자격증");
				break;
			case 3:
				dtoo.setT_field2("토익");
				break;
			case 4:
				dtoo.setT_field2("기타");
				break;
			}
		}
		
		return list;
	}

	//팀삭제
	@Override
	public int deleteTeam(int t_key) {
		List list = dao.getTCkey(t_key);
		return dao.deleteTeam(t_key,list);
	}
	//디데이 가져오기
	@Override
	public String getTDay(int t_key) {
		return dao.getTDay(t_key);
	}
	//디데이 수정하기
	@Override
	public int updateDday(TeamInfoDTO dto) {
		return dao.updateDday(dto);
	}
	//뉴 챌린지
	@Override
	public int addChallenge(ChallengeDTO dto) {
		return dao.addChallenge(dto);
	}
	//latest Challenge 가져오기
	@Override
	public ChallengeDTO getLatestChallenge(int t_key) {
		ChallengeDTO dto = dao.getLatestChallenge(t_key);
		if(dto == null) {
			dto = new ChallengeDTO();
			dto.setTc_title("아직 없습니다");
			dto.setTc_key(0);
		}
		return dto;
	}
	//오늘 출석을 했는가? 
	@Override
	public boolean alreadyToday(ChallengeDTO dto) {
		boolean result = false;
		int count = dao.alreadyToday(dto);
		if(count > 0) {
			result = true;
		}
		return result;
	}
	//attendChallenge 출석
	@Override
	public int attendChallenge(ChallengeDTO dto) {
		return dao.attendChallenge(dto);
	}
	//나의 현재 챌린지 상태 가져오기
	@Override
	public List getChallengeList(ChallengeDTO dto) {
		return dao.getChallengeList(dto);
	}
	//나의 현재 챌린지 서머리 가져오기
	@Override
	public ChallengeDTO getSummary(ChallengeDTO dto) {
		ChallengeDTO dtoo = dao.getSummary(dto);
		
		if(dtoo == null) {
			dtoo = new ChallengeDTO();
			dtoo.setTcs_key(dto.getTcs_key());
			dtoo.setTcs_summary("업데이트 해주세요");
		}else if(dtoo.getTcs_summary()==null) {
			dtoo.setTcs_summary("기록이 없습니다");
		}
		return dtoo;
	}
	//revise Summary
	@Override
	public int reviseSummary(ChallengeDTO dto) {
		return dao.reviseSummary(dto);
	}
	//updateChallengeTitle
	@Override
	public int updateChallengeTitle(ChallengeDTO dto) {
		return dao.updateChallengeTitle(dto);
	}
	//>>>>>>>>>>>>>>>>팀멤버 관련>>>>>>>>>>>>>>>>>>>>>>>>>>>>>//
	//팀 멤버 정보 가져오기
	@Override
	public List getTeamMemberInfo(int t_key) {
		
		List list = dao.getTeamMemberInfo(t_key);
		
		for(int i=0; i<list.size(); i++) {
			TeamMemberDTO memberDTO = (TeamMemberDTO) list.get(i);
			Date lastTime = memberDTO.getLastTime();
			SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd hh:mm a");
			String time = format.format(lastTime);
			memberDTO.setLastTime2(time);
		}
		return list;
	}

	//멤버 강퇴
	@Override
	public int removeMember(TeamMemberDTO dto){
		return dao.removeMember(dto);
	}
	
	//팀개설
	@Override
	public int addNewTeam(TeamInfoDTO dto) {
		return dao.addNewTeam(dto);
	}
	
	//팀이름 중복체크
	@Override
	public boolean existTeamName(String t_name) {
		boolean result = false;
		int count = dao.existTeamName(t_name);
		if(count > 0) {
			result = true;
		}
		return result;
	}

	//>>>>>>>>>>>>>>>>팀가입요청 관련>>>>>>>>>>>>>>>>>>>>>>>>>>>>>//
	//가입요청하기
	@Override
	public int requestMember(MemberRequestDTO dto) {
		return dao.memberRequest(dto);
	}

	//이미 가입요청했는가?
	@Override
	public boolean alreadyRequest(MemberRequestDTO dto) {
		boolean result = false;
		int count = dao.alreadyRequest(dto);
		if(count > 0) {
			result = true;
		}
		return result;
	}
	
	//가입요청이 있는가?
	@Override
	public int anyAlarm(int t_key) {
		return dao.anyAlarm(t_key);
	}

	//있다면 가입요청 메세지 리스트 가져오기
	@Override
	public List getRequestList(int t_key) {
		return dao.getRequestList(t_key);
	}

	//멤버 수락하기
	@Override
	public int acceptMember(MemberRequestDTO dto) {
		dao.acceptMember(dto);
		int t_key = dto.getT_key();
		return dao.updateMemberCount(t_key);
	}
	//멤버 거절하기
	@Override
	public int rejectMember(MemberRequestDTO dto) {
		return dao.rejectMember(dto);
	}
	//나의 요청 현황
	@Override
	public Map getMyRequest(int userkey) {
		
		Map map = dao.getMyRequest(userkey);
		
		List waitingList = (List) map.get("waitingList");
		for(int i=0; i<waitingList.size();i++) {
			MemberRequestDTO dto = (MemberRequestDTO) waitingList.get(i);
			int t_filed = dto.getT_field();
			switch(t_filed) {
			case 1:
				dto.setT_field2("코딩");
				break;
			case 2:
				dto.setT_field2("자격증");
				break;
			case 3:
				dto.setT_field2("토익");
				break;
			case 4:
				dto.setT_field2("기타");
				break;
			}
		}
		List rejectList = (List) map.get("rejectList");
		for(int i=0; i<rejectList.size();i++) {
			MemberRequestDTO dto = (MemberRequestDTO) rejectList.get(i);
			int t_filed = dto.getT_field();
			switch(t_filed) {
			case 1:
				dto.setT_field2("코딩");
				break;
			case 2:
				dto.setT_field2("자격증");
				break;
			case 3:
				dto.setT_field2("토익");
				break;
			case 4:
				dto.setT_field2("기타");
				break;
			}
		}
		
		return map;
	}
	//가입요청 취소하기 & //거절된 요청 삭제하기
	@Override
	public int cancleRequest(MemberRequestDTO dto) {
		return dao.cancleRequest(dto);
	}

	//>>>>>>>>>>>>>>>>>>>>챌린지 관련
	//지난 챌린지 가져오기 
	@Override
	public List getMyHistory(ChallengeDTO dto) {
		return dao.getMyHistory(dto);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	



}
