<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<link rel="stylesheet" href="resources/css/admin(modForm).css" />

<div class="wrap">
	<div class="tbWrap">
		<br><br><br>
		<h1>[ ${modUser.id } ] 회원정보 수정 </h1>
		<form action="/project/modMember.do">
			<table id="modTb">
				<tr>
					<td class="modTitle">아이디</td>
					<td>
					<input type="hidden" name="id" value="${modUser.id }">${modUser.id }
					</td>
				</tr>
				<tr>
					<td class="modTitle">비밀번호</td>
					<td><input type="text" name="pw"></td>
				</tr>
				<tr>
					<td class="modTitle">이름</td>
					<td>${modUser.name }</td>
				</tr>
				<tr>
					<td class="modTitle">닉네임</td>
					<td><input type="text" name="nickName"></td>
				</tr>
				<tr>
					<td class="modTitle">성별</td>
					<td>
						<select name="sex">
				    		<option value="choice">선택</option>
				    		<option value="man">남자</option>
				    		<option value="woman">여자</option>
				    	</select>
					</td>
				</tr>
				<tr>
					<td class="modTitle">이메일</td>
					<td><input type="text" name="email"></td>
				</tr>
			</table>
			<input type="submit"  onclick="return confirm('정말로 수정하시겠습니까?');" class="submit" value="정보수정">
		</form><form action = "/project/memberList.do">
			<input type=submit class="submit" value="취소">
		</form>
	</div>
</div>