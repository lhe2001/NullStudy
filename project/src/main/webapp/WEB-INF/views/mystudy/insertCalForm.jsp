<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*"
    import="java.lang.*"
    
 %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일정추가</title>
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
</head>

<body>
	<form action="insertCalBoard.do" method="post">
<!-- 			<input type="hidden" name="userkey" /> -->
<!-- 			<input type="hidden" name="m_schedule_date" /> -->
<!-- 			<input type="hidden" name="m_schedule_write" /> -->
		<table border="1">
			<tr>
				<th>일정 요일</th>
				<td>
					<select name="year" >
					<% 
						for(int i=year-5; i<year+5; i++){
					%>
						<option <%=year==i?"selected":"" %> value="<%=i %>"><%=i %></option>
					<% 
						}
					%>
					</select>년
					<select name="month" >
					<% 
						for(int i=1; i<=12; i++){
					%>
						<option <%=month==i?"selected":"" %> value="<%=i %>"><%=i %></option>
					<% 
						}
					%>
					</select>월
					<select name="date" >
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
				<th>제목</th>
				<td><input type="text" name="m_schedule_title" /></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea name="m_schedule_desc" rows="10" cols="60"></textarea></td>
			</tr>
			<tr>
				<td colspan="2">
					<button type="button" onclick="calendar()">cancel</button>
					<button>save</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>