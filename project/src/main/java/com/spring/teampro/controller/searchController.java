package com.spring.teampro.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;
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
				return "forward:/";
				
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
						, @RequestParam("selectValue") String selectValue
						, @RequestParam(value="pagingValue", defaultValue="15") int pagingValue) {
		// @RequestParam("search") 검색어 sql로 전달
		System.out.println("Controller boardSearch.do");
		System.out.println(search);
		System.out.println(selectValue);
		System.out.println(pagingValue);
		
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
			map = paging(_pageNum, count, pagingValue);
			dto.setStart((Integer) map.get("start"));
			dto.setEnd((Integer) map.get("end"));
			dto.setFirstNo((Integer) map.get("firstNo"));
			dto.setLastNo((Integer) map.get("lastNo"));
			dto.setLastPage((Integer) map.get("lastPage"));
			dto.setPageNum((Integer) map.get("pageNum"));
			list = searchService.getSearchBoardSelectList(dto);
			
			
		} else if (selectValue.equals("b_title")) {
			
			count = Count.b_titleSearchBoardSelectCount(dto);
			map = paging(_pageNum, count, pagingValue);
			dto.setStart((Integer) map.get("start"));
			dto.setEnd((Integer) map.get("end"));
			dto.setFirstNo((Integer) map.get("firstNo"));
			dto.setLastNo((Integer) map.get("lastNo"));
			dto.setLastPage((Integer) map.get("lastPage"));
			dto.setPageNum((Integer) map.get("pageNum"));
			list = searchService.getB_titleSearchBoardSelectList(dto);
			// db 자유 게시글에서 검색어와 b_title 일치하는 거 가져옴
			
		} else if (selectValue.equals("b_content")) {
			
			count = Count.b_contentSearchBoardSelectCount(dto);
			map = paging(_pageNum, count, pagingValue);
			dto.setStart((Integer) map.get("start"));
			dto.setEnd((Integer) map.get("end"));
			dto.setFirstNo((Integer) map.get("firstNo"));
			dto.setLastNo((Integer) map.get("lastNo"));
			dto.setLastPage((Integer) map.get("lastPage"));
			dto.setPageNum((Integer) map.get("pageNum"));
			list = searchService.getB_contentSearchBoardSelectList(dto);
			// db 자유 게시글에서 검색어와 b_content 일치하는 거 가져옴
			
			
		} else if (selectValue.equals("nickName")) {
			
			count = Count.nickNameSearchBoardSelectCount(dto);
			map = paging(_pageNum, count, pagingValue);
			dto.setStart((Integer) map.get("start"));
			dto.setEnd((Integer) map.get("end"));
			dto.setFirstNo((Integer) map.get("firstNo"));
			dto.setLastNo((Integer) map.get("lastNo"));
			dto.setLastPage((Integer) map.get("lastPage"));
			dto.setPageNum((Integer) map.get("pageNum"));
			list = searchService.getNickNameSearchBoardSelectList(dto);
			// db 자유 게시글에서 검색어와 nickName 일치하는 거 가져옴
			
			
		} else if (selectValue.equals("boardSearch")) {
			
			count = Count.searchBoardSelectCount(dto);
			map = paging(_pageNum, count, pagingValue);
			dto.setStart((Integer) map.get("start"));
			dto.setEnd((Integer) map.get("end"));
			dto.setFirstNo((Integer) map.get("firstNo"));
			dto.setLastNo((Integer) map.get("lastNo"));
			dto.setLastPage((Integer) map.get("lastPage"));
			dto.setPageNum((Integer) map.get("pageNum"));
			list = searchService.getSearchBoardSelectList(dto);
			// db 자유 게시글에서 검색어 일치하는 거 가져옴
			
		
		} else {
			
			System.out.println("컨트롤러 boardSearch 메소드에서 getSelectList를 하는데 실패");
			
		}
		System.out.println("list" + list.size());
		view.setViewName("boardSearch");
		view.addObject("search", search);
		view.addObject("selectValue", selectValue);
		view.addObject("list", list);
		view.addObject("dto", dto);
		
		return view;
	}
	
	// -------------------------팀 검색----------------------------------	
	@RequestMapping(value="/teamSearch.do", method=RequestMethod.GET)
	public ModelAndView teamSearch(@RequestParam("search") String search
								   , HttpServletRequest request
								   , @RequestParam(value="pagingValue", defaultValue="15") int pagingValue) {
		
		System.out.println("Controller teamSearch.do");
		System.out.println(search);
		System.out.println(pagingValue);

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
		
		int count = Count.nickNameSearchUserSelectCount(dto);
		System.out.println("count : " + count);
		map = paging(_pageNum, count, pagingValue);
		dto.setStart((Integer) map.get("start"));
		dto.setEnd((Integer) map.get("end"));
		dto.setFirstNo((Integer) map.get("firstNo"));
		dto.setLastNo((Integer) map.get("lastNo"));
		dto.setLastPage((Integer) map.get("lastPage"));
		dto.setPageNum((Integer) map.get("pageNum"));
		List<SearchDTO> list = searchService.getT_nameSearchTeamSelectList(dto);
		// db에서 검색어가 없을 때 팀 목록 전체를 가져옴
		
		view.setViewName("teamSearch");
		view.addObject("search", search);
		view.addObject("list", list);
		view.addObject("dto", dto);
		
		return view;
	}
	
	// --------------------------------유저 검색-------------------------------
	@RequestMapping(value="/userSearch.do", method=RequestMethod.GET)
	public ModelAndView userSearch(@RequestParam("search") String search
								   , HttpServletRequest request
								   , @RequestParam(value="pagingValue", defaultValue="15") int pagingValue) {
		
		System.out.println("Controller userSearch.do");
		System.out.println(search);
		System.out.println(pagingValue);

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
		
		int count = Count.nickNameSearchUserSelectCount(dto);
		System.out.println("count : " + count);
		map = paging(_pageNum, count, pagingValue);
		dto.setStart((Integer) map.get("start"));
		dto.setEnd((Integer) map.get("end"));
		dto.setFirstNo((Integer) map.get("firstNo"));
		dto.setLastNo((Integer) map.get("lastNo"));
		dto.setLastPage((Integer) map.get("lastPage"));
		dto.setPageNum((Integer) map.get("pageNum"));
		List<SearchDTO> list = searchService.getNickNameSearchUserSelectList(dto);
		// db에서 검색어가 없을 때 팀 목록 전체를 가져옴
		
		view.setViewName("userSearch");
		view.addObject("search", search);
		view.addObject("list", list);
		view.addObject("dto", dto);

		return view;
	}
	
	public Map paging(int _pageNum, int count, int pagingValue) {
		
		System.out.println("Controller paging 입장");
		
		int pageNum = 1;		// 처음 페이지
		int countPerPage = 15;	// 한 페이지당 보여줄 글 개수
		countPerPage = pagingValue; // 사용자가 선택한 보기 개수
		pageNum = _pageNum;	    // 현재 페이지
		int lastPage = (int)Math.ceil(((double)count / countPerPage)); // 마지막 페이징번호 링크
		int section = 5; // 한 페이지에 보이는 a링크 페이지 수
		int sec_position = (int)Math.ceil(((double)pageNum / section));
		int firstNo = ((sec_position-1) * section) + 1; // 첫번째 a링크
		int lastNo = firstNo + section - 1; // 마지막 a링크
		int start = ((countPerPage * pageNum) - countPerPage) + 1;
		int end = countPerPage * pageNum;
		if(firstNo < 1){ firstNo = 1; }
		if(lastNo > lastPage){lastNo = lastPage;}
		
		System.out.println("pageNum : " + pageNum);
		System.out.println("lastPage : " + lastPage);
		System.out.println("section : " + section);
		System.out.println("firstNo : " + firstNo);
		System.out.println("lastNo : " + lastNo);
		System.out.println("start : " + start);
		System.out.println("end : " + end);
		
		Map map = new HashMap();
		
		map.put("start", start);
		map.put("end", end);
		map.put("firstNo", firstNo);
		map.put("lastNo", lastNo);
		map.put("lastPage", lastPage);
		map.put("pageNum", pageNum);
		
		return map;
	}
}
