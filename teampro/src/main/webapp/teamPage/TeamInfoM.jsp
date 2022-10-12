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
<body>
	<%@ include file="/fix/header(logout).jsp" %>
	<div id="team_Wrapper">
	 <a class="backList" href='/teampro/teamCheck/teamList'><i class="fa-solid fa-share"></i></a><br>
	 <c:forEach var="m" items="${memberList}" >
	 	<c:if test="${m.userKey == mydto.userKey }" >
	 <button type="button" onclick="location.href='/teampro/teamInfo/delMember?tmkey=${m.tm_key}'">
                탈퇴하기</button>
	 	</c:if>
	 </c:forEach>
	 <div id="Wrapper">
        <div id="teamInfo" class="teamInfoBox ">
          <table>
                <tr>
                    <td>팀 이름</td>
                    <td>${teamInfo.teamName}</td>
                </tr>
                <tr>
                    <td>인사말</td>
                    <td>${teamInfo.teamIntro}</td>
                </tr>
                <tr>
                    <td>분야</td>
                    <td> 
                    <c:choose>
		            	<c:when test="${teamInfo.teamField eq 1 }" >
		            	코딩
		            	</c:when>
		              	<c:when test="${teamInfo.teamField eq 2 }" >
		            	자격증
		            	</c:when>
		             	 <c:when test="${teamInfo.teamField eq 3 }" >
		           		토익
		            	</c:when>
		              	<c:when test="${teamInfo.teamField eq 4 }" >
		            	기타
		            	</c:when>
            		</c:choose>
            		</td>
                </tr>
                <tr>
                    <td>팀장</td>
                    <td>${teamInfo.leaderNick }</td>
                </tr>
            </table>
        </div>
        <div class="LeaderMenu leadersWrite ">
            ${teamInfo.leaderNick }님의 공지사항: ${teamInfo.teamMemo }<br>
        </div>
	  	<div id="memberInfo">
	  	<c:forEach var="memberList" items="${memberList}" varStatus="loop">
	      <div class="member">
	      <div class="tape"></div>
	      <c:if test="${memberList.userKey eq teamInfo.teamLeader }" >
	      <div class="king"><img src="https://ifh.cc/g/6bBq87.png"></div>
	      </c:if>
            	<div class="photo">
                	<img src="https://ifh.cc/g/GCpQKq.png">
           		</div>
            	<div class="info">
            	     <table>
                    <tr>
                        <td>이름</td>
                        <td>${memberList.nickname }</td>
                    </tr>
                    <tr>
                        <td>한마디</td>
                        <td>${memberList.intro }</td>
                    </tr>
                    <tr>
                        <td>출석률</td>
                        <td>${PercentList.get(loop.index)}%</td>
                    </tr>
                    <tr>
                        <td>마지막 접속</td>
                        <td>${memberList.lastTime }</td>
                    </tr>
                </table>
            	</div>
        	</div>
        </c:forEach>
        </div>
        <div id="teamBoard_Wrapper">
       		<div id="tBoard">
            	<table>
                	<thead>
                    	<tr>
	                        <th style="width:100px;">작성자</th>
	                        <th style="width:800px;">한마디</th>
	                        <th style="width:100px;">작성시간</th>
                    	</tr>
                	</thead>
                	<tbody>
                	<c:forEach var="boardList" items="${boardList}" varStatus="loop">
               		   <tr>
	                       	<td>${boardList.nickName}</td>
	                       	<c:if test="${boardList.userKey == userInfo.userKey }" >
	                       	<td>${boardList.tb_memo}<button class='delBtn' type="button" onclick="location.href='/teampro/teamInfo/delBoard?tbkey=${boardList.tb_key}'"><i class="fa-regular fa-trash-can"></i></button></td>
	                       	</c:if>
                       		<c:if test="${boardList.userKey != userInfo.userKey }" >
	                       	<td>${boardList.tb_memo}</td>
	                       	</c:if>
	                       	<td>${boardList.tb_memotime}</td>
                       </tr>
                    </c:forEach>
               		</tbody>
            	</table>
        	</div>
        	<div id="Write_teamBoard">
            <form method="post" action="teamInfo/addBoard">
                <input type="text" name="boardWrite">
                <input type="submit" value="글쓰기">
            </form>
        </div>
       	</div>
   	</div>
   	</div>
   	<%@ include file="/fix/footer.jsp" %>
</body>
</html>