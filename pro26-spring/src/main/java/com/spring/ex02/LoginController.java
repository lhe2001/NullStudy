package com.spring.ex02;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	@RequestMapping(value={"/test/loginForm.do", "/test/loginForm2.do"}
					, method=RequestMethod.GET)
	public ModelAndView loginForm(HttpServletRequest req) {
		
		String uri = req.getRequestURI();
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("uri",uri);
		mav.setViewName("loginForm");
		
		return mav;
	}
	
	@RequestMapping(value={"/test/login.do"}
					, method= {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView login(HttpServletRequest req,HttpServletResponse resp) {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("userID",req.getParameter("userID"));
		mav.addObject("userName",req.getParameter("userName"));
		mav.addObject("msg","한글");
		
		mav.setViewName("result");
		
		return mav;
	}
	
	@RequestMapping(value={"/test/login2.do"}
	, method= {RequestMethod.POST})
	public ModelAndView login2(HttpServletRequest req,HttpServletResponse resp,
			@RequestParam("userID") String userID,
			@RequestParam("userName") String userName,
			@RequestParam(value="email", required=false) String email
			) {
	ModelAndView mav = new ModelAndView();
	
	mav.addObject("userID",userID);
	mav.addObject("userName",userName);
	mav.addObject("msg","한글");
	mav.addObject("email",email);
	
	mav.setViewName("result");
	
	return mav;
	}
	
	@RequestMapping(value={"/test/login3.do"}
	, method= {RequestMethod.POST})
	public ModelAndView login3(HttpServletRequest req,HttpServletResponse resp,
			@RequestParam Map info
			) {
	ModelAndView mav = new ModelAndView();
	
	mav.addObject("info",info);
	
	mav.setViewName("result");
	
	return mav;
	}

	@RequestMapping(value={"/test/login4.do"}
	, method= {RequestMethod.POST})
	public ModelAndView login4(
			@ModelAttribute("info") LoginDTO loginDTO
			) {
	System.out.println("login4 실행");
	ModelAndView mav = new ModelAndView();
	
	mav.setViewName("result");
	
	return mav;
	}
	
	@RequestMapping(value={"/test/login5.do"}
	, method= {RequestMethod.POST})
	public String login5(
			Model model,
			@RequestParam("userID") String userID,
			@RequestParam("userName") String userName,
			@RequestParam(value="email", required=false) String email
			) {
	
		model.addAttribute("userID",userID);
		model.addAttribute("userName",userName);
		model.addAttribute("email",email);
	
		return "result";
	
	}
	
	@RequestMapping(value={"/test/login5_1.do"}
	, method= {RequestMethod.POST})
	public String login5_1(	) {
	
		return "result";
	}
	
	
	
	
	
	
}
