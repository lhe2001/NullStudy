<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<title>update info</title>

<link href="" rel="stylesheet">

<body>

<div class="modwrap">

<form action="update.do" method="get" name="updateForm">
<table  id="modtable">
	<tr><h3 style="text-align:center">프로필 수정</h3></tr>
	<br>
	<tr>
		<td>아이디</td>
		<td><input type="text" class="mod_inputs" name="id" value="${userInfo.id }" readonly></td>
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
<div class="inputModwrap">
	<input type="button" value="취소" onclick="history.back()"  class="mod_btn">
	<input type="submit" value="수정하기" class="mod_btn">
</div>
</form>

</div>
</body>