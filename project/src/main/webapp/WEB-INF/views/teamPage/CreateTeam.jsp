<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/project/resources/css/team.css" rel="stylesheet">
<script src="/project/resources/js/allTeam.js" ></script>
</head>
<body>
<div class="crewrap">
	<form name="createTeam" method="post" action="/project/team/newTeam.do">
		<table id="creatteamtable">
		    <tr>
		        <th colspan="2">스터디만들기</th>
		    </tr>
		    <tr>
		        <td>
		            본인 ID
		        </td>
		        <td>
		            <input type="text" class="cre_inputs"  name="id" value="${userInfo.id }" disabled>
		            <input type="hidden" name="userKey" value="${userInfo.userKey }">
		        </td>
		    </tr>
		    <tr>
		        <td>
		            스터디명
		        </td>
		        <td>
		            <input type="text" class="cre_inputs" id="teamName" name="t_name" required >
		        </td>
		    </tr>
		    <tr>
		        <td>
		            10자 소개
		        </td>
		        <td>
		            <input type="text" class="cre_inputs" name="t_intro" >
		        </td>
		    </tr>
		    <tr>
		        <td>
		            분야
		        </td>
		        <td>
		            <select name="t_field">
		                <option value="1">코딩</option>
		                <option value="2">자격증</option>
		                <option value="3">토익</option>
		                <option value="4">기타</option>
		            </select>
		        </td>
		    </tr>
		</table>
		<input type="button" value="취소" onclick="history.back()" class="cre_btn">
		<input type="submit" class="cre_btn" value="개설">
	</form>
</div>
</body>