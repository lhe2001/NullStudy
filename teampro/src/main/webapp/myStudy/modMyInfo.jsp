<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*"
%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>update info</title>

<link href="/teampro/Front/mypage.css" rel="stylesheet">

</head>
<body>
<%@ include file="/fix/header(logout).jsp" %>
<div class="modwrap">
<form action="${contextPath }/mod" method="post" name="updateForm">
<table  id="modtable">
	<tr>
		<td>아이디</td>
		<td><input type="text" class="mod_inputs" name="id" value="${userInfo.id }" disabled></td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td><input type="text" class="mod_inputs" name="pw" value="${userInfo.pw }"></td>
	</tr>
	<tr>
		<td>닉네임</td>
		<td><input type="text" class="mod_inputs" name="nickname" value="${userInfo.nickName }"></td>
	</tr>
	<tr>
		<td>이메일</td>
		<td><input type="text" class="mod_inputs" name="email" value="${userInfo.email }"></td>
	</tr>
	<tr>
		<td>인트로</td>
		<td><input type="text" class="mod_inputs" name="intro" value="${userInfo.intro }"></td>
	</tr>
</table>
<input type="button" value="취소" onclick="history.back()"  class="mod_btn">
<input type="submit" value="수정하기" class="mod_btn">
</form>
</div>

<%@ include file="/fix/footer.jsp" %>
</body>
</html>