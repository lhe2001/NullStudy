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
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.teampro.mystudy.dto.MemoDTO;
import com.spring.teampro.mystudy.service.MemoService;

@Controller
public class MyMemoController {
	
	private static final Logger logger = LoggerFactory.getLogger(MyMemoController.class);
	
	@Autowired
	MemoService memoService;
	
	//메모 페이징으로
	@RequestMapping(value="/mystudy/memo.do", method= {RequestMethod.GET, RequestMethod.POST} )
	public String memolist(
			Model model
			,HttpServletRequest request
			,@ModelAttribute MemoDTO memoDTO
			) {
		logger.info("memolist 실행");
		HttpSession session = request.getSession();
		
		int userkey = (Integer) session.getAttribute("userKey");
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
		
		return "memo";
	}
	//메모 작성하기
	@RequestMapping(value="/mystudy/insertMemo.do", method= {RequestMethod.GET, RequestMethod.POST} )
	public String insertMemo(
			Model model
			,HttpServletRequest request
			,@ModelAttribute MemoDTO memoDTO
			) {
		
		logger.info("insertMemo 실행");
		
		//유저키 가져와서 세팅
		
		HttpSession session = request.getSession();		
		int userkey = (Integer)session.getAttribute("userKey");
		logger.info(">>insertMemo--userkey"+userkey);
		memoDTO.setUserkey(userkey);
		
		int result = memoService.insertNewMemo(memoDTO);
		
		if(result == 1) {
			return "redirect:/mystudy/memo.do";
		} else {
			model.addAttribute("msg", "메모등록실패!");
			return "error";
		}
	}
	
	//메모 삭제하기
	@RequestMapping(value="/mystudy/deleteMemo.do", method= {RequestMethod.GET, RequestMethod.POST} )
	public String deleteMemo(
			Model model,
			HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="m_memo_key") int m_memo_key
			) {
		logger.info("deleteMemo 실행");
		
		int result = memoService.deleteMemo(m_memo_key);
		
		if(result == 1) {
			return "redirect:/mystudy/memo.do";
		} else {
			model.addAttribute("msg", "메모등록실패!");
			return "error";
		}
	}
	
	//메모 수정 전 해당dto 불러오기
	@RequestMapping(value="/mystudy/selectOneMemo.do", method= {RequestMethod.GET, RequestMethod.POST} )
	public String selectOneMemo(
			Model model, 
			HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="m_memo_key") int m_memo_key
			) {
			
		logger.info("selectOneMemo 실행");	
		MemoDTO memDTO = memoService.selectOneMemo(m_memo_key);
		model.addAttribute("oneDTO", memDTO);
		
		return "modMemo";
	}
	
	//메모 수정(update)하기
	@RequestMapping(value="/mystudy/updateMemo.do", method= {RequestMethod.GET, RequestMethod.POST} )
	public String updateMemo(
			Model model,
			HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute MemoDTO memoDTO
			) {
		
		logger.info("updateMemo 실행");
		
		//유저키 가져와서 세팅
		HttpSession session = request.getSession();		
		int userkey = (Integer)session.getAttribute("userKey");
		logger.info(">>updateMemo--userkey"+userkey);
		memoDTO.setUserkey(userkey);
		
		int result = memoService.updateMemo(memoDTO);
		
		logger.info(">>update한 result: "+result);
		
		if(result == 1) {			
			return "redirect:/mystudy/memo.do";
		} else {
			model.addAttribute("msg", "메모등록실패!");
			return "error";
		}
		
	}
	
}
