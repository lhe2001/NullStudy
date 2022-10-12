<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="TeamInfo.*"
	import="TMemberInfo.*"
	import="UserInfo.*"
	import="TeamBoard.*"
	import="java.util.*"
   %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/teampro/Front/team.css" rel="stylesheet"> 
</head>

<%@ include file="/fix/header(logout).jsp" %>

<body>
<div class="crewrap">
<form name="createTeam" method="post">
<table id="creatteamtable">
    <tr>
        <th colspan="2">스터디만들기</th>
    </tr>
    <tr>
        <td>
            본인 ID
        </td>
        <td>
            <input type="text" class="cre_inputs" value="${userInfo.id }" disabled>
        </td>
    </tr>
    <tr>
        <td>
            스터디명
        </td>
        <td>
            <input type="text" class="cre_inputs" id="teamName" name="teamName" required >
        </td>
    </tr>
    <tr>
        <td>
            10자 소개
        </td>
        <td>
            <input type="text" class="cre_inputs" name="teamIntro" >
        </td>
    </tr>
    <tr>
        <td>
            분야
        </td>
        <td>
            <select name="field">
                <option value="1">코딩</option>
                <option value="2">자격증</option>
                <option value="3">토익</option>
                <option value="4">기타</option>
            </select>
        </td>
    </tr>
</table>

<input type="button" value="취소" onclick="history.back()"  class="cre_btn">
<button type="submit" class="cre_btn" value="addTeam" onclick="javascript: createTeam.action='/teampro/teamcd/addTeam';">개설</button>
</form>
</div>
</body>
<%@ include file="/fix/footer.jsp" %>
