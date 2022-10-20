<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<form action="/project/modMember.do">
<table>
	<tr>
		<td>아이디</td>
		<td>
		<input type="hidden" name="id" value="${modUser.id }">${modUser.id }
		</td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td><input type="text" name="pw"></td>
	</tr>
	<tr>
		<td>이름</td>
		<td>${modUser.name }</td>
	</tr>
	<tr>
		<td>닉네임</td>
		<td><input type="text" name="nickName"></td>
	</tr>
	<tr>
		<td>성별</td>
		<td>
			<select name="sex">
	    		<option value="choice">선택</option>
	    		<option value="man">남자</option>
	    		<option value="woman">여자</option>
	    	</select>
		</td>
	</tr>
	<tr>
		<td>이메일</td>
		<td><input type="text" name="email"></td>
	</tr>
</table>
<input type="submit" value="정보수정">
</form>