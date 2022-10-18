 package com.spring.teampro.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.teampro.signupin.dto.SignUpInDTO;
import com.spring.teampro.signupin.service.SignUpInService;

@Controller
public class SignUpInController {
	
	@Autowired
	SignUpInService signUpInService;
	
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
	
	//회원가입 메소드 
	@RequestMapping(value="/signUp.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String signUp(
			@RequestParam("id") String id,
			@RequestParam("pw") String pw,
			@RequestParam("re_pw") String rePw,
			@RequestParam("name") String name,
			@RequestParam("nickname") String nickname,
			@RequestParam("sex") String sex,
			@RequestParam("email") String email,
			HttpServletResponse res) throws Exception{
		
		PrintWriter out = res.getWriter();
		
		SignUpInDTO dto = new SignUpInDTO();
		dto.setId(id);
		dto.setPw(pw);
		dto.setRePw(rePw);
		dto.setName(name);
		dto.setNickName(nickname);
		dto.setSex(sex);
		dto.setEmail(email);

		int idCheck = signUpInService.getIdCheck(dto);
		int emailCheck = signUpInService.getEmailCheck(dto);
		int pwCheck = signUpInService.getPwCheck(dto);
		
		System.out.println("idcheck 결과" + idCheck);
		System.out.println("emailcheck 결과" + emailCheck);
		System.out.println("pwcheck 결과" + pwCheck);
		
		if(idCheck==1) {
			if(pwCheck==1) {
				if(emailCheck==1) {
					if(!(dto.getSex().equals("none"))) {
						int resultAdd=signUpInService.doAddMember(dto);
						if(resultAdd==1) {
							//가입성공 메세지 송출 > 로그인페이지로 이동 
							return "signIn";
							
						} else {
							//가입 실패시 다시 회원가입페이지로
							out.print("<script>");
							out.print("alert('가입실패, 회원가입페이지로 이동합니다.');");
							out.print("history.back()");
							out.print("</script>");
						}
					}
					
				}else if(emailCheck==-1) {
					//이메일 사용불가
					out.print("<script>");
					out.print("alert('중복된 이메일입니다.');");
					out.print("history.back()");
					out.print("</script>");
				}else {
					//db오류
					out.print("<script>");
					out.print("alert('가입실패, 회원가입페이지로 이동합니다.');");
					out.print("history.back()");
					out.print("</script>");
				}
				
			}else if(pwCheck==-1){
				//비번값 불일치
				out.print("<script>");
				out.print("alert('비밀번호, 비밀번호확인값 불일치, 회원가입페이지로 이동합니다.');");
				out.print("history.back()");
				out.print("</script>");
			}else {
				//db오류
				out.print("<script>");
				out.print("alert('가입실패, 회원가입페이지로 이동합니다.');");
				out.print("history.back()");
				out.print("</script>");
			}
			
		}else if(idCheck==-1) {
			//아이디 사용불가, 사용중이 아이디 있음
			out.print("<script>");
			out.print("alert('동일한 아이디 존재.');");
			out.print("history.back()");
			out.print("</script>");
		}else {
			//db오류
			out.print("<script>");
			out.print("alert('가입실패, 회원가입페이지로 이동합니다.');");
			out.print("history.back()");
			out.print("</script>");
		}
		return "fffff";
		
	}
	
	@RequestMapping(value="/signIn.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String signIn(Model model,
			@RequestParam("id") String id,
			@RequestParam("pw") String pw,
			HttpServletRequest req) {
		//result 99:관리자 0:실패 1: 로그인진행 
		int result = signUpInService.doSignIn(id, pw);
		
		
		if( result == 99) {
			
			
			return "관리자 메인 페이지로";
		}else if(result ==1 ) {
			//로그인 성공 > 메인2로 이동
			signUpInService.updateLastTime(id);
			HttpSession session = req.getSession();
			session.setAttribute("userInfo", signUpInService.getUserInfo(id));
			session.setAttribute("userKey", signUpInService.getUserInfo(id).getUserKey());
			return "main2";
		}else {
			//로그인 실패 >  로그인 페이지로 
			return "signIn";
		}
	}
	
	//로그아웃 
	@RequestMapping(value="/signOut.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String signOut(HttpServletRequest req) {
		req.getSession().invalidate();
		return "main";
	}
	
}
