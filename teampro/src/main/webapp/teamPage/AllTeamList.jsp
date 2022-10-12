<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/teampro/Front/team.css" rel="stylesheet">
</head>
<body>
<%@ include file="/fix/header(logout).jsp" %>
<div id="allTeamWrapper">
	<h1>모든 팀 목록</h1>
	<div class="allTeamTableWrap ">
	<table>
		<thead>
			<tr>
				<th>필드</th>
				<th>팀이름</th>
				<th>팀소개</th>
				<th>인원수</th>
				<th>팀장</th>
				<th>가입 신청하기</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="TeamList" items="${allTeamList }" varStatus="loop" >
			<tr>
				<td>
					<c:choose>
		            	<c:when test="${TeamList.teamField eq 1 }" >
		            	코딩
		            	</c:when>
		              	<c:when test="${TeamList.teamField eq 2 }" >
		            	자격증
		            	</c:when>
		             	 <c:when test="${TeamList.teamField eq 3 }" >
		           		토익
		            	</c:when>
		              	<c:when test="${TeamList.teamField eq 4 }" >
		            	기타
            			</c:when>
            		</c:choose>
				</td>
				<td>${TeamList.teamName }</td>
				<td>${TeamList.teamIntro }</td>
				<td>${TeamList.teamNumber }</td>
				<td>${TeamList.leaderNick }</td>
				<td><button type="button" onclick="location.href='/teampro/teamInfo/addMember?teamkey=${TeamList.teamKey}'">가입</button></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
	<div class="newTeam" onclick="location.href='CreateTeam.jsp'">
        새로운팀 개설하러 가기<br>
    </div>
</div>
	<%@ include file="/fix/footer.jsp" %>
</body>
</html>