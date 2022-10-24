<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*"
    import="java.lang.*"
    
 %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<title>일정추가</title>
<script src="https://kit.fontawesome.com/f5483c894c.js" crossorigin="anonymous"></script>
<link href="/project/resources/css/calList.css" rel="stylesheet">
<%
	String pyear = request.getParameter("year");
	int year = Integer.parseInt(pyear);
	String pmonth = request.getParameter("month");
	int month = Integer.parseInt(pmonth);
	String pdate = request.getParameter("date");
	int date = Integer.parseInt(pdate);

%>
<script>
	function calendar(){
		location.href="calendar.do?year=<%=year %>&month=<%=month %>&date=<%= date %>"		
	}
</script>

<body>
	<div class="calinupWrap">
	<div class="calinupContent">
	<form action="insertCalBoard.do" method="post">
		<table class="calinupTB">
		<caption>*일정 제목은 10자 이하로 해주세요</caption>
			<tr>
				<th><p>일정 요일</p></th>
			</tr>
			<tr>
				<td>
					<select name="year" class="cselect">
					<% 
						for(int i=year-5; i<year+5; i++){
					%>
						<option <%=year==i?"selected":"" %> value="<%=i %>"><%=i %></option>
					<% 
						}
					%>
					</select>년
					<select name="month" class="cselect">
					<% 
						for(int i=1; i<=12; i++){
					%>
						<option <%=month==i?"selected":"" %> value="<%=i %>"><%=i %></option>
					<% 
						}
					%>
					</select>월
					<select name="date" class="cselect">
					<% 
						for(int i=1; i<=31; i++){
					%>
						<option <%=date==i?"selected":"" %> value="<%=i %>"><%=i %></option>
					<% 
						}
					%>					
					</select>일
				</td>
			</tr>
			<tr>
				<th><p>제목</p></th>
			</tr>
			<tr>
				<td><input type="text" name="m_schedule_title" class="ctform" /></td>
			</tr>
			<tr>
				<th><p>내용</p></th>
			</tr>
			<tr>
				<td><textarea name="m_schedule_desc" rows="10" cols="50" class="cttextarea" ></textarea></td>
			</tr>
			<tr>
				<td colspan="2">
					<button type="button" onclick="calendar()" class="insertpagebtn">cancel</button>
					<button class="insertpagebtn">save</button>
				</td>
			</tr>
		</table>
	</form>
	</div>
	</div>
</body>
