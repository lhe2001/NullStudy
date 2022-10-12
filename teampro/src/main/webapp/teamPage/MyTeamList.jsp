<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="TeamInfo.*"
	import="TMemberInfo.*"
	import="UserInfo.*"
	import="TeamBoard.*"
	import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/teampro/Front/team.css" rel="stylesheet">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="/fix/header(logout).jsp" %>
	<div id="MyteamWrapper">
	 <h1>${userInfo.nickName}님이 속한 팀 목록</h1>
	<c:if test="${myteamList == null }">
		<strong>${msg}</strong><br>
	</c:if>
	<c:if test='${myteamList != null }'>
	<div class="MyTeamTableWrap yellow">
	 <table class="myteamList">
 	 <c:forEach var="myteamList" items="${myteamList}" varStatus="loop">
	 	<tr>
	 		<td><a href="/teampro/teamdetail?teamkey=${myteamList.teamkey }">${myteamList.teamName}</a></td>
	 	</tr>
 	</c:forEach>
	 </table>
	 </div>
	 </c:if>
	 </div>
	 <%@ include file="/fix/footer.jsp" %>
</body>
</html>