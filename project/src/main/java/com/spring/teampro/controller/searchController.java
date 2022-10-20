package com.spring.teampro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.teampro.search.dto.SearchDTO;
import com.spring.teampro.search.service.SearchService;

@Controller
public class searchController {
	
	@Autowired
	SearchService searchService;
	
//	@RequestMapping(value="/main.do", method=RequestMethod.GET)
//	public String main(Model model) {
//		// main 접속 기능
//		return "main";
//	}
	
	@RequestMapping(value="/mainSearch.do", method=RequestMethod.GET)
	public String mainSearch(Model model
			, @RequestParam("search") String search
			, @RequestParam("selectValue") String selectValue) {
		System.out.println("Controller mainSearch.do");
		// main 에서 옵션에 따라 해당 jsp로 이동
		
		if (selectValue.equals("boardSearch")) {
			selectValue = "all"; // 전체 검색으로 설정
			model.addAttribute(search);
			model.addAttribute(selectValue);
			return "forward:/boardSearch.do"; // 검색어들고 boardSearch.jsp 로
			
			
		} else if (selectValue.equals("teamSearch")) {
			model.addAttribute(search);
			return "forward:/teamSearch.do"; // 검색어들고 teamSearch.jsp 로
			
			
		} else if (selectValue.equals("userSearch")) {
			userSearch(search);
			model.addAttribute(search); 
			return "forward:/userSearch.do"; // 검색어들고 userSearch.jsp 로
			
			
		} else {
			System.out.println("main search 실패");
			return "forward:main.do";
			
		}
		
	}
	
	// -------------------------게시글 검색------------------------------- 	
	@RequestMapping(value="/boardSearch.do", method=RequestMethod.GET)
	public ModelAndView boardSearch(
						@RequestParam("search") String search
						, @RequestParam("selectValue") String selectValue) {
		// @RequestParam("search") 검색어 sql로 전달
		System.out.println("Controller boardSearch.do");
		
		System.out.println(search);
		System.out.println(selectValue);
		
		List<SearchDTO> list = null;
		
		// 다시 jsp로 보내서 활용
		if (selectValue.equals("all")) {
			
			list = searchService.getSearchBoardSelectList(search);
			// db 자유 게시글에서 검색어와 b_title 일치하는 거 가져옴
			
		} else if (selectValue.equals("b_title")) {
			
			list = searchService.getB_titleSearchBoardSelectList(search);
			// db 자유 게시글에서 검색어와 b_title 일치하는 거 가져옴
			
		} else if (selectValue.equals("b_content")) {
			
			list = searchService.getB_contentSearchBoardSelectList(search);
			// db 자유 게시글에서 검색어와 b_content 일치하는 거 가져옴
			
		} else if (selectValue.equals("nickName")) {
			
			list = searchService.getNickNameSearchBoardSelectList(search);
			// db 자유 게시글에서 검색어와 nickName 일치하는 거 가져옴
			
		} else if (selectValue.equals("boardSearch")) {
			
			list = searchService.getSearchBoardSelectList(search);
			// db 자유 게시글에서 검색어 일치하는 거 가져옴
		
		} else {
			
			System.out.println("컨트롤러 boardSearch 메소드에서 getSelectList를 하는데 실패");
			
		}
		
		int listNull = 0;
		if (list == null || list.size() == 0) {
			System.out.println("list == null | searchService.getBoardAllSelectList() 대체 실행");
			list = searchService.getBoardAllSelectList();
			// 조회된 것이 없으면 전부 불러오기
			
			listNull = 1;
			// 검색어에 대한 결과가 없다는 걸 알려주기 위해
		} else {
			System.out.println("list != null");
		}
		
		ModelAndView view = new ModelAndView();
		view.setViewName("boardSearch");
		view.addObject("search", search);
		view.addObject("selectValue", selectValue);
		view.addObject("list", list);
		view.addObject("listNull", listNull);
		
		return view;
	}
	
	// -------------------------팀 검색----------------------------------	
	@RequestMapping(value="/teamSearch.do", method=RequestMethod.GET)
	public ModelAndView teamSearch(@RequestParam("search") String search) {
		System.out.println("Controller teamSearch.do");
		
		System.out.println(search);
		
		List<SearchDTO> list = null;
		
		list = searchService.getT_nameSearchTeamSelectList(search);
		// db에서 검색어가 없을 때 팀 목록 전체를 가져옴
		
		int listNull = 0;
		if (list == null || list.size() == 0) {
			System.out.println("list == null | searchService.getTeamAllList() 대체 실행");
			list = searchService.getTeamAllList();
			// 조회된 것이 없으면 전부 불러오기
			listNull = 1;
		} else {
			System.out.println("list != null");
		}
		
		ModelAndView view = new ModelAndView();
		view.setViewName("teamSearch");
		view.addObject("search", search);
		view.addObject("list", list);
		view.addObject("listNull", listNull);

		return view;
	}
	
	// --------------------------------유저 검색-------------------------------
	@RequestMapping(value="/userSearch.do", method=RequestMethod.GET)
	public ModelAndView userSearch(@RequestParam("search") String search) {
		System.out.println("Controller userSearch.do");
		
		System.out.println(search);
		
		List<SearchDTO> list = null;
		
		list = searchService.getNickNameSearchUserSelectList(search);
		// db에서 검색어가 없을 때 팀 목록 전체를 가져옴
		
		int listNull = 0;
		if (list == null || list.size() == 0) {
			System.out.println("list == null | getUserAllList() 대체 실행");
			list = searchService.getUserAllList();
			// 조회된 것이 없으면 전부 불러오기
			listNull = 1;
		} else {
			System.out.println("list != null");
		}
		
		ModelAndView view = new ModelAndView();
		view.setViewName("userSearch");
		view.addObject("search", search);
		view.addObject("list", list);
		view.addObject("listNull", listNull);

		return view;
	}
	
}
