package com.spring.teampro.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.teampro.mystudy.dto.TodoDTO;
import com.spring.teampro.mystudy.service.TodoService;

@RestController
public class MyTodoController {
	private static final Logger logger = LoggerFactory.getLogger(MyTodoController.class);
	
	@Autowired
	TodoService todoService;
	
	@RequestMapping(value="/mystudy/myTodoList.do", method= {RequestMethod.GET, RequestMethod.POST})
	public List myTodoList(
			HttpServletRequest request
			) {
		HttpSession session = request.getSession();
		Integer userkey  = (Integer) session.getAttribute("userKey");
		
		logger.info("userkey"+userkey);
		
		List list = todoService.AllListTodo(userkey);
		
		return list;
	}
	
	@RequestMapping(value = "/mystudy/AddTodo.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody public String AddTodo(
			@ModelAttribute TodoDTO tdto
			, HttpServletRequest request
			, Model model
			) {
		logger.info("아작스 통신");
		
		//유저키 세션으로 잡아오기
		HttpSession session = request.getSession();
		Integer userkey = (Integer) session.getAttribute("userKey");
		logger.info(">> AddTodo--userkey"+userkey);
		tdto.setUserkey(userkey);
			
		int count = todoService.CountTodo(userkey);
		logger.info(">> AddTodo--count"+count);
		
		if(count < 6) {
				todoService.insertTodo(tdto);
//				return "redirect:/mystudy/myTodoList.do";
				return count+"";
		} else {
			model.addAttribute("msg", "일정등록실패!");
			return "error";
		}
	}
//	@RequestMapping(value="/mystudy/delTodo.do", method= {RequestMethod.GET, RequestMethod.POST})
//	public String deleteTodo(@RequestParam("m_td_key") int m_td_key,
//			Model model
//			) {
//		todoService.delTodo(m_td_key);
//		
//		return "redirect:/mystudy/myTodoList.do";
//	}
	
//	@RequestMapping(value="/mystudy/newTodo.do", method= {RequestMethod.GET, RequestMethod.POST})
//	public String newTeam(
//			HttpServletRequest request
//			,Model model
//			,@ModelAttribute TodoDTO tDTO
//			) {
//		
//		HttpSession session = request.getSession();
//		Integer userkey  = (Integer) session.getAttribute("userKey");
//		logger.info("userkey"+userkey);
//
//		int tdCount = todoService.CountTodo(userkey);
//		
//		if(tdCount > 6) {
//			model.addAttribute("result", "투두개수제한");
//			return "signIn";
//		} else {
//			
//			tDTO.setUserkey(userkey);
//			todoService.insertTodo(tDTO);
//			return "redirect:/mystudy/myTodoList.do";
//		}
//		
//	}
	
}
