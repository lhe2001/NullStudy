package com.spring.teampro.team.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.teampro.team.dao.TeamDAO;
import com.spring.teampro.team.dto.MyTeamListDTO;
import com.spring.teampro.team.dto.TeamInfoDTO;
import com.spring.teampro.team.dto.TeamMemberDTO;

@Service
public class TeamServiceImpl implements TeamService {
	
	private static final Logger logger = LoggerFactory.getLogger(TeamServiceImpl.class);

	@Autowired
	TeamDAO dao;
	
	//나의 팀 리스트
	@Override
	public List getMyTeamList(int userkey) {
		return  dao.getMyTeamList(userkey);
		
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

	//멤버 강퇴
	@Override
	public int removeMember(int tm_key){
		return dao.removeMember(tm_key);
	}

	//전체 팀리스트 가져오기
	@Override
	public List getAllTeamList() {
		
		List list = dao.allTeamList();
		
		for(int i=0; i<list.size();i++) {
			TeamInfoDTO dto = (TeamInfoDTO)list.get(i);
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


}
