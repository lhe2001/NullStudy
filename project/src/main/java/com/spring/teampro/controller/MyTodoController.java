package com.spring.teampro.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.teampro.mystudy.service.TodoService;

@RestController
public class MyTodoController {
	private static final Logger logger = LoggerFactory.getLogger(MyTodoController.class);
	
	@Autowired
	TodoService todoService;
	
	@RequestMapping(value="/mystudy/myTodoList.do", method= {RequestMethod.GET, RequestMethod.POST})
	public List myTodoList(HttpServletRequest request
			) {
		HttpSession session = request.getSession();
		Integer userkey  = (Integer) session.getAttribute("userKey");
		
		logger.info("userkey"+userkey);
		
		List list = todoService.AllListTodo(userkey);
		
		return list;
	}
	
	
	
	
}
