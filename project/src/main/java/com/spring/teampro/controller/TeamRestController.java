package com.spring.teampro.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.teampro.team.dto.ChallengeDTO;
import com.spring.teampro.team.dto.MemberRequestDTO;
import com.spring.teampro.team.dto.TeamInfoDTO;
import com.spring.teampro.team.dto.TeamMemberDTO;
import com.spring.teampro.team.service.TeamService;

@RestController
public class TeamRestController {
	
private static final Logger logger = LoggerFactory.getLogger(TeamController.class);
	
	@Autowired
	TeamService service;
	
	//>>>>>>>>>>>>>>>>main 페이지 관련>>>>>>>>>>>>>>>>>>>>>>>>>>>>>//

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
	//getMainTeamList
	@RequestMapping(value="/teamRest/getMainTeamList.do", method= {RequestMethod.GET, RequestMethod.POST})
	public List getMainTeamList(
			) {
		
		return service.getMainTeamList();
	}
	
	
	//>>>>>>>>>>>>>>>>teamDetail 페이지 관련>>>>>>>>>>>>>>>>>>>>>>>>>>>>>//
	
	//팀정보 Update
	@RequestMapping(value="/teamRest/updateTeamInfo.do", method= {RequestMethod.GET, RequestMethod.POST})
	public int updateTeamInfo(@RequestBody TeamInfoDTO dto
			) {
		int result = -1;
		String lMemo = dto.getT_lMemo();
		if(lMemo == null) {
			result = service.updateTeamInfo(dto);
		}else {
			result = service.updateLMemo(dto);
		}
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
		logger.info("t_day>>>>>>>>>>"+dto.getT_day());
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
	
	//뉴챌린지 
	@RequestMapping(value="/teamRest/newChallenge.do", method= {RequestMethod.GET, RequestMethod.POST})
	public int newChallenge(@RequestBody ChallengeDTO dto
			) {
		return service.addChallenge(dto);
	}
	//챌린지 출석하기
	@RequestMapping(value="/teamRest/attendChallenge.do", method= {RequestMethod.GET, RequestMethod.POST})
	public int attendChallenge(HttpServletRequest request, HttpServletResponse response,
			@RequestBody ChallengeDTO dto
			) {
		HttpSession session = request.getSession();
		int userkey  = (Integer) session.getAttribute("userKey");
		dto.setUserKey(userkey);
		
		Date now = new Date();
		SimpleDateFormat tansFormat = new SimpleDateFormat("yyyy-MM-dd");
		String tcs_date = tansFormat.format(now);
		logger.info(tcs_date);
		
		dto.setTcs_date(tcs_date);
		//확인하기 
		boolean result = service.alreadyToday(dto);
		logger.info(">>>>>>>>>>>>>>>>>>>"+result);
		int count = -1;
		if(result == true) {
			return count;
		}else {
			System.out.println("여기오나???????????????????????????????");
			return service.attendChallenge(dto);
		}
	}
	//showSummary
	@RequestMapping(value="/teamRest/showSummary.do", method= {RequestMethod.GET, RequestMethod.POST})
	public ChallengeDTO showSummary(HttpServletRequest request, HttpServletResponse response,
			@RequestBody ChallengeDTO dto
			) {
		HttpSession session = request.getSession();
		int userkey  = (Integer) session.getAttribute("userKey");
		dto.setUserKey(userkey);
		
		return service.getSummary(dto);
	}
	//showSummary
	@RequestMapping(value="/teamRest/history.do", method= {RequestMethod.GET, RequestMethod.POST})
	public List history(HttpServletRequest request, HttpServletResponse response,
			@RequestBody ChallengeDTO dto
			) {
		HttpSession session = request.getSession();
		int userkey  = (Integer) session.getAttribute("userKey");
		dto.setUserKey(userkey);
		
		//현황 가져오기
		return service.getChallengeList(dto);
	}
	//멤버 서머리 보기 
	@RequestMapping(value="/teamRest/memberSummary.do", method= {RequestMethod.GET, RequestMethod.POST})
	public List memberSummary(
			@RequestBody ChallengeDTO dto
			) {
		List list = new ArrayList();
		list = service.getChallengeList(dto);
		if(list.size() == 0) {
			list.add(-1);
		}
		return list;
	}
	//reviseSummary
	@RequestMapping(value="/teamRest/reviseSummary.do", method= {RequestMethod.GET, RequestMethod.POST})
	public int reviseSummary(HttpServletRequest request, HttpServletResponse response,
			@RequestBody ChallengeDTO dto
			) {
		
		return service.reviseSummary(dto);
	}
	//updateChallengeTitle
	@RequestMapping(value="/teamRest/updateChallengeTitle.do", method= {RequestMethod.GET, RequestMethod.POST})
	public int updateChallengeTitle(@RequestBody ChallengeDTO dto
			) {
		
		return service.updateChallengeTitle(dto);
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
	//allTeamFilter 필드값으로 팀구분하기
	@RequestMapping(value="/teamRest/allTeamFilter.do", method= {RequestMethod.GET, RequestMethod.POST})
	public List allTeamFilter(@RequestBody TeamInfoDTO dto, Model model
			) {
		return service.getAllTeamList(dto);
	}
	
	//>>>>>>>>>>>>>>>>팀개설 관련>>>>>>>>>>>>>>>>>>>>>>>>>>>>>//
	//팀이름 중복체크 하기 
	@RequestMapping(value="/teamRest/checkTeamName.do", method= {RequestMethod.GET, RequestMethod.POST})
	public boolean checkTeamName(@RequestBody TeamInfoDTO dto
			) {
		String t_name = dto.getT_name();
		return service.existTeamName(t_name);
	}
	
	//>>>>>>>>>>>>>>>>myTeamList 관련>>>>>>>>>>>>>>>>>>>>>>>>>>>>>//
	//가입요청 취소하기 & //거절된 요청 삭제하기 
	@RequestMapping(value="/teamRest/cancleRequest.do", method= {RequestMethod.GET, RequestMethod.POST})
	public int cancleRequest(@RequestBody MemberRequestDTO dto
			) {
		return service.cancleRequest(dto);
	}
}
