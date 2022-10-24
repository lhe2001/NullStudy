package com.spring.teampro.controller;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.teampro.profile.dto.ProfileUpdateDTO;
import com.spring.teampro.signupin.dto.SignUpInDTO;
import com.spring.teampro.signupin.service.SignUpInService;

@Controller
public class ProfileUpdateController {

	@Autowired
	SqlSession sqlSession;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	SignUpInService signUpInService;
	
	@RequestMapping(value="/profileUpdate.do", method=RequestMethod.GET)
	public String profileUpdate(Model model) {
		return "profileUpdate";
	}
	
	@RequestMapping(value="/update.do", method=RequestMethod.GET)
	public String Update(Model model
								, @RequestParam("pw") String id
								, @RequestParam("pw") String pw
								, @RequestParam("nickname") String nickname
								, @RequestParam("email") String email
								, @RequestParam("intro") String intro) {

		
		SignUpInDTO sdto = new SignUpInDTO();
		sdto = (SignUpInDTO)session.getAttribute("userInfo");
		int userkey = sdto.getUserKey();
		
		ProfileUpdateDTO dto = new ProfileUpdateDTO();
		dto.setUserKey(userkey);
		System.out.println(userkey);
		
		dto.setId(id);
		System.out.println(id);
		dto.setPw(pw);
		System.out.println(pw);
		dto.setNickName(nickname);
		System.out.println(nickname);
		dto.setEmail(email);
		System.out.println(email);
		dto.setIntro(intro);
		System.out.println(intro);

		sqlSession.update("mapper.profileUpdate.profileUpdate", dto);

		return "forward:/selectNewUserDate.do";
	}
	
	@RequestMapping(value="/selectNewUserDate.do", method=RequestMethod.GET)
	public String selectNewUserDate(Model model
									, @RequestParam("id") String id) {
		
		session.setAttribute("userInfo", signUpInService.getUserInfo(id));
		
		return "main2";
	}
	
}
