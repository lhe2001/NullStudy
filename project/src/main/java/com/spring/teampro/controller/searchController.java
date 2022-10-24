package com.spring.teampro.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.teampro.search.dao.Count;
import com.spring.teampro.search.dto.SearchDTO;
import com.spring.teampro.search.service.SearchService;

@Controller
public class searchController {
	
	@Autowired
	SearchService searchService;
	
	@Autowired
	Count Count;
	
	@RequestMapping(value="/mainSearch.do", method=RequestMethod.GET)
	public String mainSearch(Model model
			, @RequestParam("search") String search
			, @RequestParam("selectValue") String selectValue
			, HttpServletRequest request) {
		System.out.println("Controller mainSearch.do");
		// main 에서 옵션에 따라 해당 jsp로 이동
		
		HttpSession session = request.getSession();
		Object userInfo = session.getAttribute("userInfo");
		if (userInfo != null) {
			
			if (selectValue.equals("boardSearch")) {
				selectValue = "all"; // 전체 검색으로 설정
				model.addAttribute(search);
				model.addAttribute(selectValue);
				return "forward:/boardSearch.do"; // 검색어들고 boardSearch.jsp 로
				
				
			} else if (selectValue.equals("teamSearch")) {
				model.addAttribute(search);
				return "forward:/teamSearch.do"; // 검색어들고 teamSearch.jsp 로
				
				
			} else if (selectValue.equals("userSearch")) {
				model.addAttribute(search); 
				return "forward:/userSearch.do"; // 검색어들고 userSearch.jsp 로
				
				
			} else {
				System.out.println("main search 실패");
				return "forward:/main.do";
				
			}
		
		} else {
			
			model.addAttribute("searchLoginDo", "searchLoginDo");
			return "main";
			
		}
	}
	
	// -------------------------게시글 검색------------------------------- 	
	@RequestMapping(value="/boardSearch.do", method=RequestMethod.GET)
	public ModelAndView boardSearch(HttpServletRequest request
						, @RequestParam("search") String search
						, @RequestParam("selectValue") String selectValue) {
		// @RequestParam("search") 검색어 sql로 전달
		System.out.println("Controller boardSearch.do");
		System.out.println(search);
		System.out.println(selectValue);
		
		int _pageNum = 1;
		String str_pageNum = request.getParameter("pageNum");
		if(str_pageNum != null) {
			_pageNum = Integer.parseInt(str_pageNum);
		}
		try {
			_pageNum = Integer.parseInt(str_pageNum);
		} catch (NumberFormatException nfe) {}
		
		Map map = new HashMap();
		ModelAndView view = new ModelAndView();
		SearchDTO dto = new SearchDTO(); 
		dto.setSearch(search);
		
		List<SearchDTO> list = null;

		
		int count = 0;
		
		// 다시 jsp로 보내서 활용
		if (selectValue.equals("all")) {
			
			count = Count.searchBoardSelectCount(dto);
			map = paging(_pageNum, count);
			list = searchService.getSearchBoardSelectList(dto);
			
		} else if (selectValue.equals("b_title")) {
			
			count = Count.b_titleSearchBoardSelectCount(dto);
			map = paging(_pageNum, count);
			list = searchService.getB_titleSearchBoardSelectList(dto);
			// db 자유 게시글에서 검색어와 b_title 일치하는 거 가져옴
			
		} else if (selectValue.equals("b_content")) {
			
			count = Count.b_contentSearchBoardSelectCount(dto);
			map = paging(_pageNum, count);
			list = searchService.getB_contentSearchBoardSelectList(dto);
			// db 자유 게시글에서 검색어와 b_content 일치하는 거 가져옴
			
			
		} else if (selectValue.equals("nickName")) {
			
			count = Count.nickNameSearchBoardSelectCount(dto);
			map = paging(_pageNum, count);
			list = searchService.getNickNameSearchBoardSelectList(dto);
			// db 자유 게시글에서 검색어와 nickName 일치하는 거 가져옴
			
			
		} else if (selectValue.equals("boardSearch")) {
			
			count = Count.searchBoardSelectCount(dto);
			map = paging(_pageNum, count);
			list = searchService.getSearchBoardSelectList(dto);
			// db 자유 게시글에서 검색어 일치하는 거 가져옴
			
		
		} else {
			
			System.out.println("컨트롤러 boardSearch 메소드에서 getSelectList를 하는데 실패");
			
		}
		
		view.setViewName("boardSearch");
		view.addObject("search", search);
		view.addObject("selectValue", selectValue);
		view.addObject("list", list);
		view.addObject("map", map);
		
		return view;
	}
	
	// -------------------------팀 검색----------------------------------	
	@RequestMapping(value="/teamSearch.do", method=RequestMethod.GET)
	public ModelAndView teamSearch(@RequestParam("search") String search
								   , HttpServletRequest request) {
		
		System.out.println("Controller teamSearch.do");
		System.out.println(search);

		int _pageNum = 0;
		String str_pageNum = request.getParameter("pageNum");
		if(str_pageNum != null) {
			_pageNum = Integer.parseInt(str_pageNum);
		}
		try {
			_pageNum = Integer.parseInt(str_pageNum);
		} catch (NumberFormatException nfe) {}

		Map map = new HashMap();
		ModelAndView view = new ModelAndView();
		SearchDTO dto = new SearchDTO(); 
		dto.setSearch(search);
		
		List<SearchDTO> list = null;
		
		int count = Count.t_nameSearchTeamSelectCount(dto);
		map = paging(_pageNum, count);
		list = searchService.getT_nameSearchTeamSelectList(dto);
		// db에서 검색어가 없을 때 팀 목록 전체를 가져옴
		
		view.setViewName("teamSearch");
		view.addObject("search", search);
		view.addObject("list", list);
		view.addObject("map", map);
		
		return view;
	}
	
	// --------------------------------유저 검색-------------------------------
	@RequestMapping(value="/userSearch.do", method=RequestMethod.GET)
	public ModelAndView userSearch(@RequestParam("search") String search
								   , HttpServletRequest request) {
		
		System.out.println("Controller userSearch.do");
		System.out.println(search);
		
		int _pageNum = 0;
		String str_pageNum = request.getParameter("pageNum");
		if(str_pageNum != null) {
			_pageNum = Integer.parseInt(str_pageNum);
		}
		try {
			_pageNum = Integer.parseInt(str_pageNum);
		} catch (NumberFormatException nfe) {}
		
		Map map = new HashMap();
		ModelAndView view = new ModelAndView();
		SearchDTO dto = new SearchDTO(); 
		
		dto.setSearch(search);
		
		List<SearchDTO> list = null;
		
		int count = Count.nickNameSearchUserSelectCount(dto);
		map = paging(_pageNum, count);
		list = searchService.getNickNameSearchUserSelectList(dto);
		// db에서 검색어가 없을 때 팀 목록 전체를 가져옴
		
		view.setViewName("userSearch");
		view.addObject("search", search);
		view.addObject("list", list);
		view.addObject("map", map);

		return view;
	}
	
	public Map paging(int _pageNum, int count) {
		
		int pageNum = 1;		// 현재 페이지
		int countPerPage = 2;	// 한 페이지당 보여줄 글 개수
		pageNum = _pageNum;
		
		int lastPage = (int)Math.ceil(((double)count / countPerPage)); // 마지막 페이징번호 링크
		int section = 5; // 한 페이지에 보이는 a링크 페이지 수
		int sec_position = (int)Math.ceil(((double)pageNum / section));
		int firstNo = ((sec_position-1) * section) + 1; // 첫번째 a링크
		int lastNo = firstNo + section - 1; // 마지막 a링크
		int start = ((countPerPage * pageNum) - countPerPage) + 1;
		int end = countPerPage * pageNum;
				
		SearchDTO dto = new SearchDTO();
		
		dto.setStart(start);
		dto.setEnd(end);
		dto.setFirstNo(firstNo);
		dto.setLastNo(lastNo);
		dto.setLastPage(lastPage);
		dto.setPageNum(pageNum);
		
		Map map = new HashMap();
		map.put("dto", dto);
		
		return map;
	}
}
