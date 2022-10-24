<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<title>수정폼</title>
<script src="https://kit.fontawesome.com/f5483c894c.js" crossorigin="anonymous"></script>
<link href="/project/resources/css/calList.css" rel="stylesheet">
<body>
<div class="calinupWrap">
<div class="calinupContent">
<form action="calUpdate.do" method="post">
<input type="hidden" name="m_schedule_key" value="${sdto.m_schedule_key }"/>
<input type="hidden" name="userkey" value="${sdto.m_schedule_key }" />
<table class="calinupTB">
	<caption>*일정 제목은 10자 이하로 해주세요</caption>
	<tr>
		<th><p>일정요일</p></th>
	</tr>
	<tr>
		
		<td>
			<select name="year" class="cselect">
				<c:set var="year" value="${fn:substring(sdto.m_schedule_date,0,4)}" />	
				<c:forEach var="i" begin="${year-5}" end="${year+5}" step="1">
					<option ${year == i?"selected":"" } value="${i}">${i}</option>
				</c:forEach>
			</select>년
			<select name="month" class="cselect">
				<c:set var="month" value="${fn:substring(sdto.m_schedule_date,5,7)}" />	
				<c:forEach var="i" begin="1" end="12" step="1">
					<option ${month == i?"selected":"" } value="${i}">${i}</option>
				</c:forEach>
			</select>월
			<select name="date" class="cselect">
				<c:set var="date" value="${fn:substring(sdto.m_schedule_date,8,10)}" />	
				<c:forEach var="i" begin="1" end="31" step="1">
					<option ${date == i?"selected":"" } value="${i}">${i}</option>
				</c:forEach>
			</select>일
		</td>
	</tr>
	<tr>
		<th><p>제목</p></th>
	</tr>
	<tr>
		<td><input type="text" name="m_schedule_title" value="${sdto.m_schedule_title }" class="ctform"/></td>
	</tr>
	<tr>
		<th><p>내용</p></th>
	</tr>
	<tr>
		<td><textarea name="m_schedule_desc" rows="10" cols="60" class="cttextarea">${sdto.m_schedule_desc }</textarea></td>
	</tr>
	<tr>
		<td>
			<button type="button" onclick="history.back()" class="calupBtn">취소</button>
			<button type="button" onclick="location.href='calBoardList.do'"  class="calupBtn">목록</button>
			<button type="button" onclick="location.href='calendar.do?year=${sessionScope.ymd.year }&month=${sessionScope.ymd.month}'"  class="calupBtn">달력</button>
			<button class="calupBtn">수정</button>
		</td>	
	</tr>
</table>
</form>
</div>
</div>
</body>
