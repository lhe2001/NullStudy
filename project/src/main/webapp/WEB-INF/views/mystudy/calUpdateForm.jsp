<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정폼</title>
</head>
<body>
<form action="calUpdate.do" method="post">
<input type="hidden" name="m_schedule_key" value="${sdto.m_schedule_key }"/>
<table border="1">
	<tr>
		<td colspan="2"><input type="hidden" name="userkey" value="${sdto.m_schedule_key }" /></td>
	</tr>
	<tr>
		<th>일정요일</th>
		<td>
			<select name="year" >
				<c:set var="year" value="${fn:substring(sdto.m_schedule_date,0,4)}" />	
				<c:forEach var="i" begin="${year-5}" end="${year+5}" step="1">
					<option ${year == i?"selected":"" } value="${i}">${i}</option>
				</c:forEach>
			</select>년
			<select name="month" >
				<c:set var="month" value="${fn:substring(sdto.m_schedule_date,5,7)}" />	
				<c:forEach var="i" begin="1" end="12" step="1">
					<option ${month == i?"selected":"" } value="${i}">${i}</option>
				</c:forEach>
			</select>월
			<select name="date" >
				<c:set var="date" value="${fn:substring(sdto.m_schedule_date,8,10)}" />	
				<c:forEach var="i" begin="1" end="31" step="1">
					<option ${date == i?"selected":"" } value="${i}">${i}</option>
				</c:forEach>
			</select>일
		</td>
	</tr>
	<tr>
		<th>제목</th>
		<td><input type="text" name="m_schedule_title" value="${sdto.m_schedule_title }"/></td>
	</tr>
	<tr>
		<th>내용</th>
		<td><textarea name="m_schedule_desc" rows="10" cols="60">${sdto.m_schedule_desc }</textarea></td>
	</tr>
	<tr>
		<td colspan="2">
			<button type="button" onclick="history.back()">목록(취소)</button>
			<button>수정</button>
			<button type="button" onclick="location.href='calBoardList.do'">목록가기</button>
			<button type="button" onclick="location.href='calendar.do?year=${sessionScope.ymd.year }&month=${sessionScope.ymd.month}'">달력</button>
		</td>	
	</tr>
</table>
</form>
</body>
</html>