package com.spring.teampro.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.teampro.mystudy.dto.MemoDTO;
import com.spring.teampro.mystudy.service.MemoService;

@Controller
public class MystudyController {
	
	private static final Logger logger = LoggerFactory.getLogger(MystudyController.class);
	
	@Autowired
	MemoService memoService;
	
	//메모 페이징으로
	@RequestMapping(value="/mystudy/memolist", method= {RequestMethod.GET, RequestMethod.POST} )
	public String memolist(
			Model model, 
			HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute MemoDTO memoDTO
			) {
		logger.info("memolist 실행");
		HttpSession session = request.getSession();
		
		int userkey = (int)session.getAttribute("userKey");
		logger.info("userkey"+userkey);
		
		int viewPage = memoDTO.getViewPage();
		int countPerPage = memoDTO.getCountPerPage();
		
		int total = memoService.selectlistCount(userkey);
		int totalPage = (int) Math.ceil( (double)total/ countPerPage );
		
		int startIdx = ( (viewPage - 1) * countPerPage ) + 1;
		int endIdx =  viewPage * countPerPage;
		
		memoDTO.setStartIdx(startIdx);
		memoDTO.setEndIdx(endIdx);
		memoDTO.setUserkey(userkey);
		
		logger.info("페이징용>>"+startIdx+","+endIdx);
		
		logger.info(">>"+memoDTO.getUserkey());
		
		List<MemoDTO> list = memoService.selectPagingList(memoDTO);		
		logger.info("리스트 사이즈: "+list.size());
		
		model.addAttribute("userkey", userkey);
		model.addAttribute("viewPage", viewPage);
		model.addAttribute("countPerPage", countPerPage);
		
		model.addAttribute("total", total);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("resultList",list);
		
		return "myStudy";
	}
//	
//	
//	//메모 추가하기
//	@RequestMapping(value="/my/insertMemo", method= {RequestMethod.GET, RequestMethod.POST} )
//	public String insertMemo(Model model, 
//			@ModelAttribute MemoDTO memDTO
//			) {
//		System.out.println("insertMemo 실행");
//
//		int result = memoService.getAddmemo(memDTO);
//		
//		if(result == 1) {			
//			int userkey = 10;			
//			System.out.println(userkey);
//			
//			List list = memoService.getMemoList(userkey);
//			model.addAttribute("mlist",list);
//			return "./mystudy/cal";
//		}else {
//			return "./test";			
//		}
//		
//	}
//	//400에러는 안들어간게 있어서 들어오는 것
//	//메모 수정 전 값 보여주기
//	@RequestMapping(value="/my/selectMemoByIdx", method= {RequestMethod.GET, RequestMethod.POST} )
//	public String selectMemoByIdx(Model model, 
//			@RequestParam(value="idx") int idx
//			) {
//		System.out.println("selectMemoByIdx 실행");
//		MemoDTO memDTO = memoService.getMemoOne(idx);
//		model.addAttribute("oneDTO",memDTO);
//		
//		return "./mystudy/modMemo";
//	}
//	
//	//메모 수정(update)하기
//	@RequestMapping(value="/my/updateMemo", method= {RequestMethod.GET, RequestMethod.POST} )
//	public String updateMemo(Model model, 
//			@ModelAttribute MemoDTO memDTO
//			) {
//		System.out.println("updateMemo 실행");
//
//		int result = memoService.getModmemo(memDTO);
//		
//		if(result == 1) {			
//			int userkey = 10;			
//			System.out.println(userkey);
//			
//			List list = memoService.getMemoList(userkey);
//			model.addAttribute("mlist",list);
//			return "./mystudy/cal";
//		}else {
//			return "./test";			
//		}
//		
//	}
//	
//	//메모 삭제
//	@RequestMapping(value="/my/deleteMemo", method= {RequestMethod.GET, RequestMethod.POST} )
//	public String deleteMemo(Model model, 
//			@RequestParam(value="idx") int idx
//			) {
//		System.out.println("deleteMemo 실행");
//		int result = memoService.getDelmemo(idx);
//		
//		if(result == 1) {			
//			int userkey = 10;			
//			System.out.println(userkey);
//			
//			List list = memoService.getMemoList(userkey);
//			model.addAttribute("mlist",list);
//			return "./mystudy/cal";
//		}else {
//			return "./test";			
//		}
//	}
	
	
}
