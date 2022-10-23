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
<script src="https://kit.fontawesome.com/f5483c894c.js" crossorigin="anonymous"></script>
</head>
<body>
<div id="allTeamWrapper">
	<h1>스터디 그룹 목록</h1>
    <div id="modal" class="modal">
        <div id="modalBox">
        <h2>팀 정보</h2>
            <div>
                <table id="modalMember">
                	<tbody>
                    </tbody>
                </table>
            </div>
            <div id="memberPreview">
                <table>
                </table>
            </div>
            <div><button type="button">x</button></div>
            <div id="modalMsg"><div></div><input type="button" class="joinBtn" onClick="" value="가입하기"></div>
        </div>
    </div>
	<div>
	 	<select class="t_field" onChange="allTeamFilter()">
	 		<option value="0">전체</option>
        	<option value="1">코딩</option>
        	<option value="2">자격증</option>
        	<option value="3">토익</option>
        	<option value="4">기타</option>
       	</select>
	</div>
	<div class="allTeamTableWrap ">
	<div id="allTeamTable">
	<table>
		<thead>
			<tr>
                <th style="max-width:30px;"><div>필드</div></th>
				<th style="max-width:100px;"><div>팀이름</div></th>
				<th style="max-width:170px;"><div>팀소개</div></th>
				<th style="max-width:50px;"><div>인원수</div></th>
				<th style="max-width:50px;"><div>팀장</div></th>
				<th><div>정보</div></th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="teamList" items="${allTeamList }" varStatus="loop" >
			<tr>
				<td style="max-width:30px;"><div>${teamList.t_field2}</div></td>
				<td style="max-width:100px;"><div>${teamList.t_name }</div></td>
				<td style="max-width:170px;"><div>${teamList.t_intro }</div></td>
				<td style="max-width:50px;"><div>${teamList.t_number }</div></td>
				<td style="max-width:50px;"><div>${teamList.nickName }</div></td>
				<td><button type="button" class="memberBtn" onClick="teamPreview(${teamList.t_key})">팀 정보</button></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
 	<div id="search">
		<input type="text" name="search" class="tSearchText" placeholder="팀명 검색">
		<button type="button" class="sBtn" onClick="teamSearch()"><i class="fa-solid fa-magnifying-glass"></i></button>
    </div>
	</div>
	<div class="newTeam" onclick="location.href='/project/team/createForm.do'">
        새로운팀 개설하러 가기<br>
    </div>
</div>
</body>
</html>