package com.spring.teampro.search.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.teampro.search.dto.SearchDTO;
import com.spring.teampro.search.service.SearchService;
import com.spring.teampro.search.service.SearchService;
import com.spring.teampro.search.service.SearchService;

@Controller
public class searchController {

	
	@Autowired
	SearchService searchService;
	
	@RequestMapping(value="/teampro/main.do", method=RequestMethod.GET)
	public String main(Model model) {	
		System.out.println("main 입장");

		return "main";
	}
	
	// -------------------------게시글 검색------------------------------- 
	
	@RequestMapping(value="/search/boardAll.do", method=RequestMethod.GET)
	public String boardAll(Model model) {	
		System.out.println("boardAll 실행");
		// db 자유 게시글 전부 가져옴
		List<SearchDTO> list = searchService.getBoardAllSelectList();
		model.addAttribute("list", list);
		
		return "boardSearch";
	}
	
	@RequestMapping(value="/search/searchBoard.do", method=RequestMethod.GET)
	public String searchBoard(Model model) {	
		System.out.println("searchBoard 실행");
		// db 자유 게시글에서 검색어 일치하는 거 가져옴
		List<SearchDTO> list = searchService.getSearchBoardSelectList();
		model.addAttribute("list", list);
		
		return "boardSearch";
	}
	
	@RequestMapping(value="/search/b_titleSearchBoard.do", method=RequestMethod.GET)
	public String b_titleSearchBoard(Model model) {	
		System.out.println("b_titleSearchBoard 실행");
		// db 자유 게시글에서 검색어와 b_title 일치하는 거 가져옴
		List<SearchDTO> list = searchService.getB_titleSearchBoardSelectList();
		model.addAttribute("list", list);
		
		return "boardSearch";
	}
	
	@RequestMapping(value="/search/b_contentSearchBoard.do", method=RequestMethod.GET)
	public String b_contentSearchBoard(Model model) {	
		System.out.println("b_contentSearchBoard 실행");
		// db에서 자유 게시글에서 검색어와 b_content 일치하는 거 가져옴
		List<SearchDTO> list = searchService.getB_contentSearchBoardSelectList();
		model.addAttribute("list", list);
		
		return "boardSearch";
	}
	
	@RequestMapping(value="/search/nickNameSearchBoard.do", method=RequestMethod.GET)
	public String nickNameSearchBoard(Model model) {	
		System.out.println("nickNameSearchBoard 실행");
		// db 자유 게시글에서 검색어와 nickName 일치하는 거 가져옴
		List<SearchDTO> list = searchService.getNickNameSearchBoardSelectList();
		model.addAttribute("list", list);
		
		return "boardSearch";
	}
	
	// -------------------------팀 검색----------------------------------
	
	@RequestMapping(value="/search/searchTeam.do", method=RequestMethod.GET)
	public String searchTeam(Model model) {	
		System.out.println("searchTeam 실행");
		// db 팀 목록 전부 가져옴
		List<SearchDTO> list = searchService.getSearchTeamSelectList();
		model.addAttribute("list", list);
		
		return "teamSearch";
	}
	
	@RequestMapping(value="/search/t_nameSearchTeam.do", method=RequestMethod.GET)
	public String t_nameSearchTeam(Model model) {	
		System.out.println("searchTeam 실행");
		// db 팀 목록에서 검색어와 t_name 일치하는 거 가져옴
		List<SearchDTO> list = searchService.getT_nameSearchTeamSelectList();
		model.addAttribute("list", list);
		
		return "teamSearch";
	}
	
	// --------------------------------유저 검색-------------------------------
	
	@RequestMapping(value="/search/searchUser.do", method=RequestMethod.GET)
	public String searchUser(Model model) {	
		System.out.println("searchTeam 실행");
		// db 유저 목록 전부 가져옴
		List<SearchDTO> list = searchService.getSearchUserSelectList();
		model.addAttribute("list", list);
		
		return "userSearch";
	}
	
	@RequestMapping(value="/search/nickNameSearchUser.do", method=RequestMethod.GET)
	public String nickNameSearchUser(Model model) {	
		System.out.println("searchTeam 실행");
		// db 팀 목록에서 검색어와 t_name 일치하는 거 가져옴
		List<SearchDTO> list = searchService.getNickNameSearchUserSelectList();
		model.addAttribute("list", list);
		
		return "userSearch";
	}
	
}
