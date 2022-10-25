package com.spring.teampro.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.teampro.profile.dto.ProfileUpdateDTO;
import com.spring.teampro.team.dto.ChallengeDTO;
import com.spring.teampro.team.dto.TeamInfoDTO;
import com.spring.teampro.team.dto.TeamMemberDTO;
import com.spring.teampro.team.service.TeamService;

@Controller
public class TeamController {
	
	private static final Logger logger = LoggerFactory.getLogger(TeamController.class);
	
	private static String image = "C:\\profile_file";
	
	@Autowired
	TeamService service;
	
	//프로필 사진 업데이트 하기
	@RequestMapping(value="/team/changeProfile.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String changeProfile(HttpServletRequest request, HttpServletResponse response,
			Model model
			) {
		HttpSession session = request.getSession();
		int userkey  = (Integer) session.getAttribute("userKey");
		
		return "teamPage/changeProfile";
	}
	
	//프로필사진 가져오기
	@RequestMapping(value="/team/download.do", method= {RequestMethod.GET, RequestMethod.POST})
	public void getProfile(@RequestParam("userkey") int userkey,
			HttpServletRequest request, HttpServletResponse response,
			Model model
			) throws IOException {
		
		logger.info("profile"+userkey);
		
		OutputStream out = response.getOutputStream();
		
		String photo = service.getPhoto(userkey);
		String downFile = image + "\\temp\\" + photo;
		
		File f = new File(downFile);
		FileInputStream in = new FileInputStream( f );
		// 버퍼를 이용해 8kb씩 전송
		byte[] buf = new byte[1024 * 8];
		while(true) {
			int count = in.read(buf);
			if(count == -1) {
				break;
			}
			out.write(buf, 0, count);
		}
		
		in.close();
		out.close();
	}
	
	//프로필 사진 업로드
	@RequestMapping(value="/team/upload.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String upload(MultipartHttpServletRequest multirequest, 
			HttpServletResponse response,HttpServletRequest request,
			Model model
			) throws Exception {
		HttpSession session = request.getSession();
		int userkey  = (Integer) session.getAttribute("userKey");
		
		ProfileUpdateDTO dto = new ProfileUpdateDTO();
		dto.setUserKey(userkey);
		
		Enumeration enu = multirequest.getParameterNames();
		while(enu.hasMoreElements()) {
			String name = (String)enu.nextElement();
			String value = multirequest.getParameter(name);
		}
		//파일이름 반환
		String fileName = upload(multirequest);
		model.addAttribute("fileName", fileName);
		dto.setPhoto(fileName);
		
		int result = service.setPhoto(dto);
		logger.info(">>>>>>>>>upload"+fileName +"//" +result);
		
		return "teamPage/changeProfile";
	}
	
	//파일 이름 가져오는 메소드
	private String upload(MultipartHttpServletRequest multipartRequest)
			throws Exception {

		String photo = null;
		
		Iterator<String> fileNames = multipartRequest.getFileNames();
		
		while(fileNames.hasNext()) {
			String fileName = fileNames.next();
			MultipartFile mFile = multipartRequest.getFile(fileName);
			photo = mFile.getOriginalFilename();
			File file = new File(image + "\\temp\\" + fileName);
			System.out.println("file ===> " + file);
			if(mFile.getSize() !=0) {
				if(!file.exists()) {
					file.getParentFile().mkdirs();
					mFile.transferTo(new File(image + "\\temp\\" + photo));
				}
			}
		}
		return photo;
	}
	
	//>>>>>>>>>>>>>>>>myTeamList 페이지 관련>>>>>>>>>>>>>>>>>>>>>>>>>>>>>//
	//myTeamList로 가기 
		@RequestMapping(value="/team/myTeamList.do", method= {RequestMethod.GET, RequestMethod.POST})
		public String myTeamList(HttpServletRequest request, HttpServletResponse response,
				Model model
				) {
			HttpSession session = request.getSession();
			int userkey  = (Integer) session.getAttribute("userKey");
			//1.나의 팀목록 가져오기
			List myTeamList = service.getMyTeamList(userkey);
			model.addAttribute("myTeamList", myTeamList);
			//2.나의 가입요청 현황 가져오기
			Map requestMap = service.getMyRequest(userkey);
			model.addAttribute("requestMap", requestMap);
			
			return "myTeamList";
		}
	
	//>>>>>>>>>>>>>>>>teamDATAIL 페이지 관련>>>>>>>>>>>>>>>>>>>>>>>>>>>>>//
	
	@RequestMapping(value="/team/teamDetail.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String teamDetail(HttpServletRequest request, HttpServletResponse response,
			Model model,
			@RequestParam("t_key")int t_key
			) {
	
		HttpSession session = request.getSession();
		int userkey  = (Integer) session.getAttribute("userKey");
		
		String nextPage = "teamDetail_member";
		

		
		//1. 팀정보들,,,
		//2. 조장말
		TeamInfoDTO tdto = service.getTeamInfo(t_key);
		int leader = tdto.getUserKey();
		
		//나는 admin 또는 조장인가?
		if(userkey == leader) {
			nextPage= "teamDetail";
		}else if(userkey < 10000) {
			nextPage="teamDetail_admin";
		}
		
		model.addAttribute("teamInfo",service.getTeamInfo(t_key));
		//3.팀멤버 정보들
		model.addAttribute("MemberInfo",service.getTeamMemberInfo(t_key));
		//4.latest 챌린지를 가져오기
		ChallengeDTO dto = service.getLatestChallenge(t_key);
		model.addAttribute("challenge",service.getLatestChallenge(t_key));
		//5. 나의 현재 챌린지 상태 가져오기
		dto.setUserKey(userkey);
		model.addAttribute("myCurrent",service.getChallengeList(dto));
		//6.나의 현재 챌린지 서머리 가져오기.
		model.addAttribute("summary",service.getSummary(dto));
		//7.getMyHistory (userkey, t_key)
		model.addAttribute("myHistory",service.getMyHistory(dto));
		//8.팀원신청 숫자
		model.addAttribute("anyAlarm",service.anyAlarm(t_key));
		//9.디데이가져오기
		model.addAttribute("dDay", service.getTDay(t_key));

	return nextPage;
	}
	
	//팀삭제
	@RequestMapping(value="/team/delTeam.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String deleteTeam(@RequestParam("t_key") int t_key,
			Model model
			) {
		service.deleteTeam(t_key);
		
		return "redirect:/team/allTeamList.do";
	}
	
	//팀탈퇴하기
	@RequestMapping(value="/team/leaveTeam.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String leaveTeam(@RequestParam("t_key") int t_key,
			HttpServletRequest request, HttpServletResponse response
			) {
		HttpSession session = request.getSession();
		int userkey  = (Integer) session.getAttribute("userKey");
		logger.info("탈퇴시켜줘!!");
		TeamMemberDTO dto = new TeamMemberDTO();
		dto.setT_key(t_key);
		dto.setuserKey(userkey);
		
		service.removeMember(dto);
		
		return "redirect:/team/allTeamList.do";
	}
	//챌린지 수정, 리셋팝업
	@RequestMapping(value="/team/updateChallenge.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String myChallenge(@RequestParam("t_key") int t_key,
			Model model
			) {
		
		model.addAttribute("t_key",t_key);
		ChallengeDTO dto = service.getLatestChallenge(t_key);
		model.addAttribute("current",dto);
		return "teamPage/updateChallenge";
	}
	
	//>>>>>>>>>>>>>>>>allTeamList 페이지 관련>>>>>>>>>>>>>>>>>>>>>>>>>>>>>//
	
	//allTeamList.jsp로 가기
	@RequestMapping(value="/team/allTeamList.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String getAllTeamList(
			Model model
			) {
		TeamInfoDTO dto = new TeamInfoDTO();
		List list = service.getAllTeamList(dto);
		model.addAttribute("allTeamList", list);

	return "allTeamList";
	}
	
	//팀원 신청 팝업 이동.
	@RequestMapping(value="/team/newRequest.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String newRequest(@RequestParam("t_key") int t_key,
			Model model
			) {
		List list = service.getRequestList(t_key);
		model.addAttribute("requestList", list);
		
		return "teamPage/memberRequest";
	}
	//팀원 신청_멤버 팝업 이동.
	@RequestMapping(value="/team/newRequest_member.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String newRequest_member(@RequestParam("t_key") int t_key,
			Model model
			) {
		List list = service.getRequestList(t_key);
		model.addAttribute("requestList", list);
		
		return "teamPage/memberRequest_member";
	}
	
	//디데이 수정 팝업 이동.
	@RequestMapping(value="/team/reviseDday.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String reviseDday(@RequestParam("t_key") int t_key,
			Model model
			) {
		//1.현재 디데이를 가져오기.
		String dDay = service.getTDay(t_key);
		model.addAttribute("dDay", dDay);
		model.addAttribute("t_key", t_key);
		
		return "teamPage/dDayForm";
	}
	
	//>>>>>>>>>>>>>>>>team개설 페이지 관련>>>>>>>>>>>>>>>>>>>>>>>>>>>>>//
	
	//createTeam.jsp로 가기
	@RequestMapping(value="/team/createForm.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String createForm(
			Model model
			) {
		return "createTeam";
	}
	
	//팀생성하기!
	@RequestMapping(value="/team/newTeam.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String newTeam(@ModelAttribute("teamInfo") TeamInfoDTO dto,
			Model model
			) {
		logger.info("팀생성하기!>>>>>>>>>"+dto.getT_name());
		
		service.addNewTeam(dto);
		
		return "redirect:/team/allTeamList.do";
	}
	
	
}
