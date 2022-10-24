package com.spring.teampro.controller;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.teampro.mystudy.dto.ScheduleDTO;
import com.spring.teampro.mystudy.service.ScheduleService;
import com.spring.teampro.mystudy.util.Util;

@Controller
public class MyCalendarController {
	private static final Logger logger = LoggerFactory.getLogger(MyCalendarController.class);
	
	@Autowired
	ScheduleService scheduleService;
	
	@RequestMapping(value = "/mystudy/calendar.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String calendar(
			Model model
			, String year
			, String month
			, @ModelAttribute ScheduleDTO sdto
			, HttpServletRequest request
			) {
		logger.info("달력보기");
		//일자별로 3개만 보여주기
		
		//유저키 세션으로 잡아오기
		HttpSession session = request.getSession();
		Integer userkey = (Integer) session.getAttribute("userKey");
		logger.info(">> calendar--userkey"+userkey);
		sdto.setUserkey(userkey);
		
		//달력 요청할 때 년,월의 값을 전달하지 않으면 현재달을 보여줌
		if(year==null || month==null) {
			Calendar cal = Calendar.getInstance();
			year = cal.get(Calendar.YEAR)+"";
			month = cal.get(Calendar.MONTH)+1+"";
		} else {
			//크기를 비교하기 위해 정수형으로 변환
			int yearInt = Integer.parseInt(year);
			int monthInt = Integer.parseInt(month);
			
			//월이 증가하다가 12보다 커질 때
			if( monthInt >12){
				monthInt = 1;	//1월로 변경
				yearInt++;		//다음해
			} 
			if(monthInt<1){
				monthInt = 12;
				yearInt--;
			}
			
			//스트링처리하는 두가지 방법
			year = yearInt+"";
			month = String.valueOf(monthInt);
		}
		
		String yyyyMM = year+"-"+Util.isTwo(month);
		sdto.setM_schedule_date(yyyyMM);
		
		List<ScheduleDTO> clist = scheduleService.calViewList(sdto);
		model.addAttribute("clist", clist);
		
		return "calendar";
	}
	
	@RequestMapping(value = "/mystudy/calCountAjax.do", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody public String calCountAjax(
			Model model
			,@RequestParam String m_schedule_date
			,@ModelAttribute ScheduleDTO sdto
			, HttpServletRequest request
			) {
		logger.info("아작스 통신");
		
		//유저키 세션으로 잡아오기
		HttpSession session = request.getSession();
		Integer userkey = (Integer) session.getAttribute("userKey");
		logger.info(">> calCountAjax--userkey"+userkey);
		sdto.setUserkey(userkey);
		sdto.setM_schedule_date(m_schedule_date);
		
		int count = scheduleService.calCount(sdto);
		logger.info(">> calCountAjax--count"+count);
		
		return count+"";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/mystudy/calBoardList.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String calBoardList(
			Model model
			,@ModelAttribute ScheduleDTO sdto
			,@RequestParam Map<String, String> ymd
			,HttpServletRequest request
			) {
		logger.info("일정목록");
		
		HttpSession session=request.getSession();
		if(ymd == null || ymd.get("year")==null) { //일정목록페이지로 들어온 상태이기 때문에 ymd 필요없음.
			ymd = (Map<String, String>) session.getAttribute("ymd");
		} else {
			session.setAttribute("ymd", ymd);
		}
		
		//8자리 만들어주기
		String yyyyMMdd = ymd.get("year")+ "-" +Util.isTwo(ymd.get("month"))+ "-" +Util.isTwo(ymd.get("date"));
		logger.info("요일"+yyyyMMdd);
		
		//유저키 세션으로 잡아오기
		Integer userkey = (Integer) session.getAttribute("userKey");
		logger.info(">> calBoardList--userkey"+userkey);
		sdto.setUserkey(userkey);
		sdto.setM_schedule_date(yyyyMMdd);
		
		List<ScheduleDTO> list = scheduleService.calBoardList(sdto);
		model.addAttribute("list", list);
		
		return "calBoardList";
	}
	
	@RequestMapping(value = "/mystudy/insertCalForm.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String insertCalForm(
			Model model
			) {
		logger.info("일정추가폼 이동");
		
		return "insertCalForm";
	}
	
	@RequestMapping(value = "/mystudy/insertCalBoard.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String insertCal(
			Model model
			,@ModelAttribute ScheduleDTO sdto
			,HttpServletRequest request
			) {
		logger.info("일정추가 실행");
		
		//유저키 세션으로 잡아오기
		HttpSession session=request.getSession();
		Integer userkey = (Integer) session.getAttribute("userKey");
		logger.info(">> insertCal--userkey"+userkey);
		sdto.setUserkey(userkey);		
		
		
		//db에 저장할 형식을 만들어줘야함
		String yyyyMMdd = sdto.getYear() + "-" + Util.isTwo(sdto.getMonth()) + "-" + Util.isTwo(sdto.getDate());
		sdto.setM_schedule_date(yyyyMMdd);
		logger.info(">> insertCal--yyyyMMdd"+yyyyMMdd);

		
		boolean isS = scheduleService.insertCal(sdto);
		
		if(isS) {
			return "redirect:calendar.do?year="+sdto.getYear()+"&month="+sdto.getMonth();
		} else {
			model.addAttribute("msg", "일정등록실패!");
			return "error";
		}
	}
	
	@RequestMapping(value = "/mystudy/calMuldel.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String calMuldel(
			Model model
			,@RequestParam String[] seq
			) {
		logger.info("일정삭제 이동");
		
		for(int i=0;i<seq.length;i++) {
			System.out.println("체크된키값"+seq[i]);
		}
			
		boolean isS = scheduleService.calMuldel(seq);
		
		if(isS) {
			return "redirect:calBoardList.do";
		} else {
			model.addAttribute("msg", "일정삭제 실패");
			return "error";
		}
	}
	
	
	@RequestMapping(value = "/mystudy/calDetail.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String calDetail(
			Model model
			,@RequestParam int m_schedule_key
			,@ModelAttribute ScheduleDTO sdto
			, HttpServletRequest request
			) {
		logger.info("상세내용 보기");
		
		//유저키 세션으로 잡아오기
		HttpSession session=request.getSession();
		Integer userkey = (Integer) session.getAttribute("userKey");
		logger.info(">> calDetail--userkey"+userkey);
		sdto.setUserkey(userkey);
		
		sdto = scheduleService.calDetail(m_schedule_key);
		model.addAttribute("sdto", sdto);
		
		return "calDetail";
	}
	
	@RequestMapping(value = "/mystudy/updateForm.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String updateForm(
			Model model
			,@RequestParam int m_schedule_key
			,@ModelAttribute ScheduleDTO sdto
			) {
		logger.info("수정폼 이동");
		
		sdto = scheduleService.calDetail(m_schedule_key);
		model.addAttribute("sdto", sdto);
		
		return "calUpdateForm";
	}
		
	
	@RequestMapping(value = "/mystudy/calUpdate.do", method = {RequestMethod.GET, RequestMethod.POST})
	public String calUpdate(
			Model model
			,@ModelAttribute ScheduleDTO sdto
			) {
		logger.info("수정하기");
		
		String yyyyMMdd = sdto.getYear() + "-" + Util.isTwo(sdto.getMonth()) + "-" + Util.isTwo(sdto.getDate());
		sdto.setM_schedule_date(yyyyMMdd);
		
		boolean isS = scheduleService.calUpdate(sdto);
		
		if(isS) {
			return "redirect:calDetail.do?m_schedule_key="+sdto.getM_schedule_key();
		} else {
			model.addAttribute("msg", "일정수정실패!");
			return "error";
		}
	}
	
	
	//메인용 일정List
	@RequestMapping(value="/mystudy/myPlanList.do", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody public List myPlanList(
		HttpServletRequest request
		,@ModelAttribute ScheduleDTO sdto
		,String year, String month, String date
			) {
		HttpSession session = request.getSession();
		int userkey  = (Integer) session.getAttribute("userKey");
		logger.info("userkey"+userkey);
		sdto.setUserkey(userkey);
		
		//요청할 때 
		if(year==null || month==null) {
			Calendar cal = Calendar.getInstance();
			year = cal.get(Calendar.YEAR)+"";
			month = cal.get(Calendar.MONTH)+1+"";
			date = cal.get(Calendar.DATE)+"";
		} else {
			//크기를 비교하기 위해 정수형으로 변환
			int yearInt = Integer.parseInt(year);
			int monthInt = Integer.parseInt(month);
			int dateInt =  Integer.parseInt(date);
			
			//월이 증가하다가 12보다 커질 때
			if( monthInt >12){
				monthInt = 1;	//1월로 변경
				yearInt++;		//다음해
			} 
			if(monthInt<1){
				monthInt = 12;
				yearInt--;
			}
			
			year = yearInt+"";
			month = String.valueOf(monthInt);
			date = dateInt+"";
		}
		
		String yyyyMMdd = year+"-"+Util.isTwo(month)+"-"+Util.isTwo(date);
		sdto.setM_schedule_date(yyyyMMdd);
		logger.info("plan날짜"+yyyyMMdd);
		
		int count = scheduleService.calCount(sdto);
		logger.info(">> planCount--count"+count);
		
		List list = scheduleService.calBoardList(sdto);
		
		return list;
	}
	
	
	
	
	
	
	
}
