 package com.spring.teampro.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.teampro.board.dto.BoardDTO;
import com.spring.teampro.signupin.dto.AdminDTO;
import com.spring.teampro.signupin.dto.SignUpInDTO;
import com.spring.teampro.signupin.service.SignUpInService;

@Controller
public class SignUpInController {
	
	@Autowired
	SignUpInService signUpInService;
	
	//회원가입 메소드 
	@RequestMapping(value="/signUp.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String signUp(Model model,
			@RequestParam("id") String id,
			@RequestParam("pw") String pw,
			@RequestParam("re_pw") String rePw,
			@RequestParam("name") String name,
			@RequestParam("nickname") String nickname,
			@RequestParam("sex") String sex,
			@RequestParam("email") String email,
			HttpServletResponse res) throws Exception{
		
		//파라미터에서 받아온 값 DTO에 세팅
		SignUpInDTO dto = new SignUpInDTO();
		dto.setId(id);
		dto.setPw(pw);
		dto.setRePw(rePw);
		dto.setName(name);
		dto.setNickName(nickname);
		dto.setSex(sex);
		dto.setEmail(email);
		
		// dto에 담아서 전달한 아이디, 이메일, 비번 체크 메소드 실행
		// id, email. pw: 사용 가능(1) 불가능(-1)
		int idCheck = signUpInService.getIdCheck(dto);
		int emailCheck = signUpInService.getEmailCheck(dto);
		int pwCheck = signUpInService.getPwCheck(dto);

		if(idCheck==1 && pwCheck==1 && emailCheck==1 && !("none".equals(dto.getSex()) ) ) {
			int resultAddMem=signUpInService.doAddMember(dto);
			
			if(resultAddMem==1) {
				//가입성공 메세지 송출 > 로그인페이지로 이동
				model.addAttribute("result", "가입성공");
				return "signIn";
			}else {
				//가입실패 메세지 송출 > 회원가입 페이지로 이동
				model.addAttribute("result", "가입실패(DB)");
				return "signUp";
			}
		}else {
			model.addAttribute("result", "가입실패(입력)");
			return "signUp";
		}
	}
	
	
	//회원가입폼에서 아이디 중복체크(Ajax로 넘어옴)
	//요청한 페이지로 viewresolver 거치치 않고 결과를 넘겨주기 위해서 @ResponseBody 적용
	@RequestMapping(value="/idcheck.do", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody public int idCheck(@RequestParam("id") String id) {
		//체크 결과에 따라 리턴값음 1(사용가능), -1(사용불가능) 
		int result = 0;
		SignUpInDTO dto = new SignUpInDTO();
		dto.setId(id);
		result = signUpInService.getIdCheck(dto);
		return result;
	}
	
	//회원가입폼에서 이메일 중복체크(Ajax로 넘어옴)
	@RequestMapping(value="/emailcheck.do", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody public int emailCheck(@RequestParam("email") String email) {
		//체크 결과에 따라 리턴값음 1(사용가능), -1(사용불가능) 
		int result = 0;
		SignUpInDTO dto = new SignUpInDTO();
		dto.setEmail(email);
		result = signUpInService.getEmailCheck(dto);
		return result;
	}
	
	
	@RequestMapping(value="/signIn.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String signIn(Model model,
			@RequestParam("id") String id,
			@RequestParam("pw") String pw,
			HttpServletRequest req) {
		//result 99:관리자 0:실패 1: 로그인진행 
		int result = signUpInService.doSignIn(id, pw);
		
		
		if( result == 99) {
			// 관리자 로그인 성공 > 메인 admin으로 이동 
			signUpInService.updateLastTime(id);
			HttpSession session = req.getSession();
			session.setAttribute("userInfo", signUpInService.getUserInfo(id));
			session.setAttribute("userKey", signUpInService.getUserInfo(id).getUserKey());
			
			List list = signUpInService.getTopArticles();
			model.addAttribute("articlesList", list);
			return "main_admin";
		}else if(result ==1 ) {
			//로그인 성공 > 메인2로 이동
			signUpInService.updateLastTime(id);
			HttpSession session = req.getSession();
			session.setAttribute("userInfo", signUpInService.getUserInfo(id));
			session.setAttribute("userKey", signUpInService.getUserInfo(id).getUserKey());
			
			List list = signUpInService.getTopArticles();
			model.addAttribute("articlesList", list);
			
			return "main2";
		}else {
			//로그인 실패 >  로그인 페이지로 
			model.addAttribute("msg", "로그인체크");
			return "signIn";
		}
	}
	
	//로고 클릭시 메인으로 이동하는함수
	@RequestMapping(value="/moveToMain.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String moveToMain(Model model, HttpServletRequest req) {
		HttpSession session = req.getSession();
		SignUpInDTO dto = (SignUpInDTO)session.getAttribute("userInfo");
		String id = dto.getId();
		String pw = dto.getPw();
		
		int result = signUpInService.doSignIn(id, pw);

		if( result == 99) {
			//로그 클릭시 관리자 메인으로 
			List list = signUpInService.getTopArticles();
			model.addAttribute("articlesList", list);
			return "main_admin";
		}else if(result ==1 ) {
			//로그 클릭시 유저 메인으로
			List list = signUpInService.getTopArticles();
			model.addAttribute("articlesList", list);
			return "main2";
		}else {
			return "signIn";
		}

	}
	
	
	
	//로그아웃 
	@RequestMapping(value="/signOut.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String signOut(HttpServletRequest req) {
		req.getSession().invalidate();
		return "main";
	}
	
	
	//관리자관련
	
	//<회원관련 조회>
	//회원조회 페이지로 이동하면서 전체회원 조회
	@RequestMapping(value="/memberList.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String memberList(Model model) {
		List<SignUpInDTO> list = signUpInService.getMemberList();
		model.addAttribute("list",list);
		return "memberList";
	}
	
	//이름으로 회원 조회
	@RequestMapping(value="/searchByName.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String searchNameList(Model model,
			@RequestParam("name") String keyword) {
		List<SignUpInDTO> list = signUpInService.getListByName(keyword);
		model.addAttribute("list",list);
		return "memberList";
	}
	
	//아이디로 회원 조회
	@RequestMapping(value="/searchById.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String searchIdList(Model model,
			@RequestParam("id") String keyword) {
		List<SignUpInDTO> list = signUpInService.getListById(keyword);
		model.addAttribute("list",list);
		return "memberList";
	}
	
	//이름+아이디로 회원 조회
	@RequestMapping(value="/searchByBoth.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String searchBothList(Model model,
			@RequestParam("both") String keyword) {
		List<SignUpInDTO> list = signUpInService.getListByBoth(keyword);
		model.addAttribute("list",list);
		return "memberList";
	}
	
	//<스터디 관련 조회>
	
	//<회원관련 조회>
	//회원조회 페이지로 이동하면서 전체회원 조회
	@RequestMapping(value="/studyList.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String studyList(Model model) {
		List<AdminDTO> list = signUpInService.getStudyList();
		model.addAttribute("list",list);
		return "studyList";
	}
	
	//팀명으로 조회
	@RequestMapping(value="/searchByTeamName.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String searchByTname(Model model,
			@RequestParam("teamName") String keyword) {
		List<AdminDTO> list = signUpInService.getListByTname(keyword);
		model.addAttribute("list",list);
		return "studyList";
		
	}
	
	//팀장으로 조회
	@RequestMapping(value="/searchByTeamLeader.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String searchByTleader(Model model,
			@RequestParam("teamLeader") String keyword) {
		List<AdminDTO> list = signUpInService.getListByTleader(keyword);
		model.addAttribute("list",list);
		return "studyList";
	}
	
	//팀정보로 조회
	@RequestMapping(value="/searchByTeamInfo.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String searchByTinfo(Model model,
			@RequestParam("teamInfo") String keyword) {
		List<AdminDTO> list = signUpInService.getListByTinfo(keyword);
		model.addAttribute("list",list);
		return "studyList";
	}
	
	//<회원정보 수정>
	// 수정 클릭시 수정폼으로 넘어가도록
	@RequestMapping(value="/modForm.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String modMember(Model model,
			@RequestParam("id") String id) {
		SignUpInDTO dto = signUpInService.getUserInfo(id);
		model.addAttribute("modUser", dto);
		
		return "modForm";
	}
	
	// 수정진행
	@RequestMapping(value="/modMember.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String modMember(Model model,
			@RequestParam("id") String id,
			@RequestParam("pw") String pw, 
			@RequestParam("nickName") String nickName,
			@RequestParam("sex") String sex,
			@RequestParam("email") String email) {
				
		SignUpInDTO dto = new SignUpInDTO();
		dto.setId(id);
		dto.setPw(pw);
		dto.setNickName(nickName);
		dto.setSex(sex);
		dto.setEmail(email);
		
		signUpInService.doModMember(dto);
		model.addAttribute("list", signUpInService.getListById(id));
		return "memberList";
	}
	
	//회원삭제 
	@RequestMapping(value="/delMember.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String delMember(Model model,
			@RequestParam("id") String id) {

		signUpInService.doDelMember(id);

		model.addAttribute("list", signUpInService.getMemberList());
		return "memberList";
	}
	
	//메인에 탑10 글 출력
	@RequestMapping(value="/topArticles.do", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody public List<BoardDTO> getTopArticles() {
		System.out.println("topArticles.do 접근 ");
		List<BoardDTO> list = signUpInService.getTopArticles();
		System.out.println(list.size());
		return list;
	}
	
	//메인에서 로그인 버튼 누르면 로그인 페이지로 이동 
	@RequestMapping(value="/moveToSignIn.do")
	public String moveToSignIn() {
		return "signIn";
	}
	
	//메인에서 로그인 버튼 누르면 로그인 페이지로 이동 
	@RequestMapping(value="/moveToSignUp.do")
	public String moveToSignUp() {
		return "signUp";
	}
	
	
	
}
