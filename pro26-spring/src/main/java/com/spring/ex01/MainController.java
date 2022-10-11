package com.spring.ex01;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/test")
public class MainController {

	@RequestMapping(value="/main1.do", method=RequestMethod.GET)
	public ModelAndView main1() {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("msg","main1");
		mav.setViewName("main");
		
		return mav;
	}
	
	@RequestMapping(value="/main2.do", method=RequestMethod.POST)
	public ModelAndView main2() {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("msg","main2");
		mav.setViewName("main");
		
		return mav;
	}
	
}
