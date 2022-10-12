package MyStudyServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import attendPack.AttendDAO;
import attendPack.AttendDTO;
import attendPack.CalDTO;
import signupin.SignUpInDTO;

@WebServlet("/mypage")
public class MyStudyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		SignUpInDTO userInfo = (SignUpInDTO)session.getAttribute("userInfo");
		int userkey = userInfo.getUserKey();
		
		
		//년,월,일의 필드값 설정
		Calendar cal = Calendar.getInstance();
		int year;
		int month;
		int day = cal.get(Calendar.DATE);
		
		try {
			year = Integer.parseInt(request.getParameter("year"));
			
		} catch (Exception e) {
			year = cal.get(Calendar.YEAR);
		}
		try {
			month = Integer.parseInt(request.getParameter("month"));
			if(month <= 0) {
				year--;
				month = 12;
			}else if(month > 12) {
				year++;
				month = 1;
			}
		} catch (Exception e) {
			month= cal.get(Calendar.MONTH)+1;
		}
		
		//해당하는 월의 출석 불러옴
		AttendDAO adao = new AttendDAO();
		
		CalDTO vo = new CalDTO();
		vo.setUserkey(userkey);
		vo.setYear(year);
		vo.setMonth(month);
		
		//해당월에 담긴 정보들을 list에 올려둠
		List<AttendDTO> list = adao.isMonth(vo);
		
		request.setAttribute("list", list);
		
		//1일의 요일을 가져온다
		int firstDay = weekDay(year, month, 1);
		//해당월의 마지막일 가져오기.
		int lastDate = lastDate(year, month);
		
		//달력 html 가져오기 
		request.setAttribute("year", year);
		request.setAttribute("month", month);
		request.setAttribute("day", day);
		
		//달력을 뽑기
		int weekLength = 5;
		if(firstDay >4 && lastDate >= 31 ){
			weekLength = 6;
		};
		
		String html = ""; 
		int tdplace = 0;
		int dayseq = 0;
		
		for(int i=0; i<weekLength; i++ ){
			html += "<tr>";
			for(int j=0; j<7; j++){
				html += "<td>";  
				if(firstDay <= tdplace && dayseq < lastDate){
					dayseq++;
					String fullDate = String.valueOf(year)+String.format("%02d", month)+String.format("%02d", dayseq);
					String inner = String.valueOf(dayseq);
					for(int k=0; k<list.size(); k++) {
						Date dateCheck = list.get(k).getA_date();
						SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
					    String str = format.format(dateCheck);
						if(str.equals(fullDate)) {
							inner ="<img src='https://ifh.cc/g/hNRPrl.jpg'>";   
						}
					}
					html +="<div>"+inner+"</div>";   
				}
				html += "</td>";  
				tdplace++;
			}
			html += "</tr>";
		}
		
		request.setAttribute("html", html);
		
		//출석률을 구해보자!!!!!
		double count = (double)list.size();
		double percent = (count/30)*100 ;
		
		double mypercent = Math.round(percent*10)/10.0;
		
		System.out.println(count);
		System.out.println(percent);
		System.out.println(lastDate);
		System.out.println(mypercent);
		
		request.setAttribute("mypercent", mypercent);
		
		RequestDispatcher dispatch = request.getRequestDispatcher("/myStudy/myPage.jsp");
		dispatch.forward(request, response);
	
	}
	
	//년도를 넘겨받아 윤년/ 평년을 판단해 윤년이면 true, 평년이면 false를 리턴하는 메서드
	public static boolean isLeapYear(int year) {
		return (year % 4 ==0) && (year % 100 !=0) ||(year % 400 ==0);
	}
	
	//년, 월을 넘겨받아 그 달의 마지막 날짜를 리턴하는 메서드
	public static int lastDate(int year ,int month ) {
		int[] m = {31,28,31,30,31,30,31,31,30,31,30,31};
		m[1]=isLeapYear(year)? 29:28;
		return m[month-1];
		
	}
	
	//년, 월, 일을 념겨받아 1년 1월 1일부터 지나온 날짜의 합계를 리턴하는 메서드	
	public static int totalDay(int year, int month, int day) {
		int sum = (year-1)*365 +(year-1)/4 - (year-1)/100 +(year-1)/400;
		for (int i = 1; i < month; i++) {
			sum += lastDate(year,i);
		}
		return sum + day;
	}
	
	//년, 월, 일을 넘겨받아 요일을 숫자로 리턴하는 메서드, 일요일(0),월요일(1)....토요일(6)
	public static int weekDay(int year, int month, int day) {
		return totalDay(year, month, day) % 7;
	}

}
