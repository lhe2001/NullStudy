<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일정 상세보기</title>
</head>
<body>
<table border="1">
	<tr>
		<th>userkey</th>
		<td>${sdto.userkey }</td>
	</tr>
	<tr>
		<th>제목</th>
		<td>${sdto.m_schedule_title }</td>
	</tr>
	<tr>
		<th>내용</th>
		<td>${sdto.m_schedule_desc }</td>
	</tr>
	<tr>
		<th>일정요일</th>
		<td>${sdto.m_schedule_date }</td>
	</tr>
	<tr>
		<th>작성일</th>
		<td>${sdto.m_schedule_write }</td>
	</tr>
	<tr>
		<td colspan="2">
			<button type="button" 
					onclick="location.href='updateForm.do?m_schedule_key=${sdto.m_schedule_key }'">수정폼가기</button>
			<button type="button" onclick="location.href='calBoardList.do'">목록가기</button>
			<button type="button" onclick="location.href='calendar.do?year=${sessionScope.ymd.year }&month=${sessionScope.ymd.month}'">달력</button>
			<button type="button" onclick="location.href='calMuldel.do?seq=${sdto.m_schedule_key }'">삭제</button>
		</td>	
	</tr> 
</table>
</body>
</html>