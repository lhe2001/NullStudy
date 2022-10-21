package com.spring.teampro.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.teampro.team.dto.MemberRequestDTO;
import com.spring.teampro.team.dto.TeamInfoDTO;
import com.spring.teampro.team.dto.TeamMemberDTO;
import com.spring.teampro.team.service.TeamService;

@RestController
public class TeamRestController {
	
private static final Logger logger = LoggerFactory.getLogger(TeamController.class);
	
	@Autowired
	TeamService service;

	//나의팀List
	@RequestMapping(value="/teamRest/myTeamList.do", method= {RequestMethod.GET, RequestMethod.POST})
	public List myTeamList(HttpServletRequest request, HttpServletResponse response
			) {
		HttpSession session = request.getSession();
		int userkey  = (Integer) session.getAttribute("userKey");
		
		logger.info("userkey"+userkey);
		
		List list = service.getMyTeamList(userkey);
		
		return list;
	}
	
	//>>>>>>>>>>>>>>>>teamDetail 페이지 관련>>>>>>>>>>>>>>>>>>>>>>>>>>>>>//
	
	//팀정보 Update
	@RequestMapping(value="/teamRest/updateTeamInfo.do", method= {RequestMethod.GET, RequestMethod.POST})
	public int updateTeamInfo(@RequestBody TeamInfoDTO dto
			) {
		int result = -1;
		logger.info("updateTeamInfo>>>>>>>>>>>>>>>>>>>>");
		String lMemo = dto.getT_lMemo();
		logger.info("lMemo>>>>>>>>>>>>>>>>>>>>"+lMemo);
		logger.info("t_key>>>>>>>>>>>>>>>>>>>>"+dto.getT_key());
		if(lMemo == null) {
			result = service.updateTeamInfo(dto);
		}else {
			result = service.updateLMemo(dto);
		}
		logger.info("result>>>>>>>>>>>>>>>>>>>>"+result);
		return result;
	}
	
	//멤버강퇴
	@RequestMapping(value="/teamRest/removeMember.do", method= {RequestMethod.GET, RequestMethod.POST})
	public int removeMember(@RequestBody TeamMemberDTO dto
			) {
		return service.removeMember(dto);
	}
	
	//멤버리스트 가져오기
	@RequestMapping(value="/teamRest/memberList.do", method= {RequestMethod.GET, RequestMethod.POST})
	public Map memberList(@RequestBody TeamMemberDTO dto,
			HttpServletRequest request, HttpServletResponse response
			) {
		Map map = new HashMap();
		int t_key = dto.getT_key();
		
		List list = service.getTeamMemberInfo(t_key);
		TeamInfoDTO tdto = service.getTeamInfo(t_key);
		
		HttpSession session = request.getSession();
		int userkey  = (Integer) session.getAttribute("userKey");
		boolean result = service.alreadyMyTeam(userkey, t_key);
				
		map.put("memberList", list);
		map.put("teamInfo", tdto);
		map.put("isMyTeam", result);
		
		return map;
	}
	
	//디데이 수정하기
	@RequestMapping(value="/teamRest/updatedDay.do", method= {RequestMethod.GET, RequestMethod.POST})
	public int updatedDay(@RequestBody TeamInfoDTO dto
			) {
		return service.updateDday(dto);
	}
	
	//가입 수락 하기
	@RequestMapping(value="/teamRest/acceptMember.do", method= {RequestMethod.GET, RequestMethod.POST})
	public int acceptMember(@RequestBody MemberRequestDTO dto
			) {
		return service.acceptMember(dto);
	}
	//가입 거절 하기
	@RequestMapping(value="/teamRest/rejectMember.do", method= {RequestMethod.GET, RequestMethod.POST})
	public int rejectMember(@RequestBody MemberRequestDTO dto
			) {
		return service.rejectMember(dto);
	}
	
	//>>>>>>>>>>>>>>>>allTeam 페이지 관련>>>>>>>>>>>>>>>>>>>>>>>>>>>>>//
	//가입요청 보내기 
	@RequestMapping(value="/teamRest/memberRequest.do", method= {RequestMethod.GET, RequestMethod.POST})
	public int memberRequest(@RequestBody MemberRequestDTO dto,
			HttpServletRequest request, HttpServletResponse response
			) {
		HttpSession session = request.getSession();
		int userkey  = (Integer) session.getAttribute("userKey");
		dto.setUserKey(userkey);
		
		boolean result = service.alreadyRequest(dto);
		if(result) {
			return -1;
		}else {
			return service.requestMember(dto);
		}
	}
	
	//>>>>>>>>>>>>>>>>팀개설 관련>>>>>>>>>>>>>>>>>>>>>>>>>>>>>//
	//팀이름 중복체크 하기 
	@RequestMapping(value="/teamRest/checkTeamName.do", method= {RequestMethod.GET, RequestMethod.POST})
	public boolean checkTeamName(@RequestBody TeamInfoDTO dto
			) {
		String t_name = dto.getT_name();
		return service.existTeamName(t_name);
	}
	
}
