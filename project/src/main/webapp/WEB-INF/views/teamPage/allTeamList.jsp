<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/project/resources/css/team.css" rel="stylesheet">
<script src="/project/resources/css/Team.js" ></script>
</head>
<body>
<div id="allTeamWrapper">
	<h1>모든 팀 목록</h1>
    <div id="modal" class="modal">
        <div id="modalBox">
        <h2>팀정보 입니다</h2>
            <div>
                <table id="modalMember">
                   <tr>
                        <td>팀 이름</td>
                        <td>NULL</td>
                   </tr>
                   <tr>
                        <td>팀소개</td>
                        <td>안녕하세요? 얼른 들어오세요! 빨리빨리</td>
                   </tr>
                   <tr>
                        <td>인원수</td>
                        <td>10명</td>
                    </tr>
                </table>
            </div>
            <div id="memberPreview">
                <table>
                    <tr>
                        <td style="width:30px; border-radius: 50%;">
                            <img src="https://ifh.cc/g/GCpQKq.png">
                        </td>
                        <td>이름</td>
                        <td>자기소개</td>
                    </tr>
                    <tr>
                        <td style="width:30px; border-radius: 50%;">
                            <img src="https://ifh.cc/g/GCpQKq.png">
                        </td>
                        <td>이름</td>
                        <td>자기소개</td>
                    </tr>
                    <tr>
                        <td style="width:30px; border-radius: 50%;">
                            <img src="https://ifh.cc/g/GCpQKq.png">
                        </td>
                        <td>이름</td>
                        <td>자기소개</td>
                    </tr>
                    <tr>
                        <td style="width:30px; border-radius: 50%;">
                            <img src="https://ifh.cc/g/GCpQKq.png">
                        </td>
                        <td>이름</td>
                        <td>자기소개</td>
                    </tr>
                    <tr>
                        <td style="width:30px; border-radius: 50%;">
                            <img src="https://ifh.cc/g/GCpQKq.png">
                        </td>
                        <td>이름</td>
                        <td>자기소개</td>
                    </tr>
                    <tr>
                        <td style="width:30px; border-radius: 50%;">
                            <img src="https://ifh.cc/g/GCpQKq.png">
                        </td>
                        <td>이름</td>
                        <td>자기소개</td>
                    </tr>
                </table>
            </div>
            <div><button type="button">x</button></div>
            <div><input type="button" value="가입하기"></div>
        </div>
    </div>
	<div class="allTeamTableWrap ">
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
			<tr onClick="teamPreview()">
				<td style="max-width:30px;">
					<div>
						<c:choose>
		            	<c:when test="${teamList.t_field eq 1 }" >
		            	코딩
		            	</c:when>
		              	<c:when test="${teamList.t_field eq 2 }" >
		            	자격증
		            	</c:when>
		             	 <c:when test="${teamList.t_field eq 3 }" >
		           		토익
		            	</c:when>
		              	<c:when test="${teamList.t_field eq 4 }" >
		            	기타
            			</c:when>
            		</c:choose>
					</div>
				</td>
				<td style="max-width:100px;"><div>${teamList.t_name }</div></td>
				<td style="max-width:170px;"><div>${teamList.t_intro }</div></td>
				<td style="max-width:50px;"><div>${teamList.t_number }</div></td>
				<td style="max-width:50px;"><div>${teamList.nickName }</div></td>
				<td><button type="button" class="memberBtn">팀 정보</button></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
	<div class="newTeam" onclick="location.href='CreateTeam.jsp'">
        새로운팀 개설하러 가기<br>
    </div>
</div>
</body>
</html>