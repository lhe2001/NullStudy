package com.spring.teampro.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.teampro.signupin.dto.SignUpInDTO;
import com.spring.teampro.team.service.TeamService;

@RestController
public class TeamRestController {
	
private static final Logger logger = LoggerFactory.getLogger(TeamController.class);
	
	@Autowired
	TeamService service;

	@RequestMapping(value="/teamRest/myTeamList.do", method= {RequestMethod.GET, RequestMethod.POST})
	public List myTeamList(HttpServletRequest request, HttpServletResponse response,
			Model model
			) {
	HttpSession session = request.getSession();
	SignUpInDTO userInfo = (SignUpInDTO)session.getAttribute("userInfo");
	int userkey = userInfo.getUserKey();
	
	logger.info("userkey"+userkey);
	
	List list = service.getMyTeamList(userkey);
	
	return list;
	}
	
}
