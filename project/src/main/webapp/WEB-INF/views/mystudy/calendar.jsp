<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*"
    import="com.spring.teampro.mystudy.util.*"
    import="com.spring.teampro.mystudy.dto.*"
    %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  

<title>달력 페이지</title>
<script src="https://kit.fontawesome.com/f5483c894c.js" crossorigin="anonymous"></script>

<link href="/project/resources/css/calendar.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
<script type="text/javascript">
	
	//두자리 변환(자바와 동일한 역할)
	function isTwo(str){
		return str.length<2?"0"+str:str;
	}
	
	$(function(){
		$(".countView").hover(function(){
			
			var aObj = $(this);
			var year = $(".y").text().trim();
			var month = $(".m").text().trim();
			var date = $(this).text().trim();
			var m_schedule_date = year + "-" + isTwo(month) + "-" + isTwo(date);
			
			$.ajax({
				method:"post",
				url:"/project/mystudy/calCountAjax.do",
				data: {"m_schedule_date" : m_schedule_date },
				dataType: "text",
				async:false,
				success:function(val){
					aObj.after("<div class='cPreview'>"+val+"</div>");
					console.log(val);
				},
				error:function(){
					alert("서버통신실패xxx");
				}
			});
		},function(){
			$(".cPreview").remove();	//마우스가 나가면 해당엘리먼트 삭제
		});
	});
	
	
</script>
<%
	//달력의 날짜를 바꾸기 위해 전달된 year와 month 파라미터 받기(redirect는 안됨)
	String paramYear = request.getParameter("year");
	String paramMonth = request.getParameter("month");

	//현재 날짜의 년도와 월을 가져옴
	Calendar cal = Calendar.getInstance();
	int year = cal.get(Calendar.YEAR);
	int month = cal.get(Calendar.MONTH)+1;
	
	//호출 페이지에서 요청된 년도와 월의 값을 저장한다
	if(paramYear != null){
		year = Integer.parseInt(paramYear);
	}
	if(paramMonth != null){
		month = Integer.parseInt(paramMonth);
	}
	
	//월이 증가하다가 12보다 커질 때
	if(month>12){
		month=1;	//1월로 변경
		year++;		//다음해
	} 
	if(month<1){
		month=12;
		year--;
	}
	// 요청받은 년도와 월이 일자로 Calendar 클래스 세팅 
	cal.set(year, month-1, 1);
	
	//매월 1일의 요일
	int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
	
	//월의 마지막날
	int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	
	List<ScheduleDTO> clist = (List<ScheduleDTO>)request.getAttribute("clist");
%>
<body>

<div id="#calContainer">
<table id="calendarTable">
<caption id="calCap">
	<a href="calendar.do?year=<%=year%>&month=<%=month-1%>" data-id="alinkStyle"><i class="fa-regular fa-square-caret-left" data-id="alinkStyle"></i></a>&nbsp;
		<span class="y"><%=year %></span>&nbsp;.&nbsp;
		<span class="m"><%=month %></span>&nbsp;
	<a href="calendar.do?year=<%=year%>&month=<%=month+1%>" data-id="alinkStyle"><i class="fa-regular fa-square-caret-right"></i></a>
</caption>
<tr>
<td id="tdinfo" colspan="7"><i class="fa-regular fa-hand-point-up"></i>전체 일정을 확인하려면 숫자를 눌러주세요</td>
</tr>
<tr>
	<th class="calth">일</th>
	<th class="calth">월</th>
	<th class="calth">화</th>
	<th class="calth">수</th>
	<th class="calth">목</th>
	<th class="calth">금</th>
	<th class="calth">토</th>
</tr>
<tr>
<%
	//공백
	for(int i=0; i< (dayOfWeek-1); i++){
		out.print("<td class='caltd'>&nbsp;</td>");
	}
	//달력 일수
	for(int i=1; i<=lastDay; i++){
		%>
		<td class="caltd">
			<div class="dateLink" ><a class="countView" data-id="alinkStyle" href="calBoardList.do?year=<%=year %>&month=<%=month %>&date=<%= i %>" style="color:<%=Util.fontColor(dayOfWeek, i) %>;"><%= i %></a></div>
			<div>
				<%=getCalViewList(i, clist) %>
			</div>
			<a data-id="alinkStyle" href="insertCalForm.do?year=<%=year %>&month=<%=month %>&date=<%= i %>"><div class="insertLinkicon"><i class="fa-regular fa-pen-to-square"></i></div></a>
		</td>
		<% 
		//행을 바꿔주기 --> 현재일이 토요일인지 확인(7의배수 확인)
		if((dayOfWeek-1+i)%7==0){
			out.print("</tr><tr>");
			}
		}
		//나머지 공백 출력하는 for문
		int countNbsp = (7-(dayOfWeek-1+lastDay)%7)%7;
		for(int i=0; i<countNbsp;i++){
			out.print("<td class='caltd'>&nbsp;</td>");	
		}
		%>
</table>
</div>
<%!
	public String getCalViewList(int i, List<ScheduleDTO> clist){
		String d = Util.isTwo(i+"");
		String cList = "";
		for(ScheduleDTO sdto: clist){
			if(sdto.getM_schedule_date().substring(8, 10).equals(d)){
				cList += "<p class='clistTitle'>"
						+"<i class='fa-regular fa-bell'></i>&nbsp;&nbsp;"
					  +(sdto.getM_schedule_title().length()>7?sdto.getM_schedule_title().substring(0,7)+"..":sdto.getM_schedule_title())
					  +"</p>";
			}
		}
		return cList;
	}
%>
</body>
