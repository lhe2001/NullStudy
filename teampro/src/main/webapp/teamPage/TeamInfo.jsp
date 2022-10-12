<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
	import="TeamInfo.*"
	import="TMemberInfo.*"
	import="UserInfo.*"
	import="TeamBoard.*"
	import="java.util.*"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/teampro/Front/team.css" rel="stylesheet">
<script>
    
    window.addEventListener("load", teamInfoOnload);
   //window.onload = function(){
	   function teamInfoOnload(){
    	console.log(111)
       reviseTeamInfo();
        leWrite();
	   }
    
  //팀정보 수정
    function reviseTeamInfo(){
        
    	let button = document.querySelectorAll(".reviseTeamInfo")
    	
    	for(let i=0; i<button.length; i++){
		  button[i].addEventListener("click",function(){
	    	console.log("클릭")
        	let teamInfo = document.querySelector("#teamInfo");
        	
        	if(teamInfo.classList.contains("hide") == true ){
        		console.log("true 작동")
        		document.querySelector("#teamInfo").classList.remove("hide");
	            document.querySelector("#reviseTeam").classList.add("hide");
        	} else{
            document.querySelector("#teamInfo").classList.add("hide");
            document.querySelector("#reviseTeam").classList.remove("hide");
        	}
        })
   	}
   }

    //조장한마디 수정
    function leWrite(){
        document.querySelector(".reviseW").addEventListener("click",function(){
            document.querySelector(".reivseWrite").classList.remove("hide");
            document.querySelector(".leadersWrite").classList.add("hide");
        })
    }
</script>
</head>
<body>
	<%@ include file="/fix/header(logout).jsp" %>
	<div id="team_Wrapper">
	 <a class="backList" href='/teampro/teamCheck/teamList'><i class="fa-solid fa-share"></i></a>
	 <div id="Wrapper">
        <div id="teamInfo" class="teamInfoBox ">
		 <a class="deleteTeamBtn"  href='/teampro/teamcd/delTeam'>현재팀 삭제</a>
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
            <button type="button" class="reviseTeamInfo reviese" ><i class="fa-regular fa-pen-to-square"></i></button>
        </div>
        <div id="reviseTeam" class="hide teamInfoBox ">
            <form method="post" action="teamInfo/reviseTeamInfo" >
	            <table>
	                <tr>
	                    <td>팀 이름</td>
	                    <td>${teamInfo.teamName}</td>
	                </tr>
	                <tr>
	                    <td>인사말</td>
	                    <td><input type="text" name="teamHi" required></td>
	                </tr>
	                <tr>
	                    <td>분야</td>
	                    <td> 
		                    <select name="field">
			                	<option value="1">코딩</option>
			                	<option value="2">자격증</option>
			                	<option value="3">토익</option>
			                	<option value="4">기타</option>
			                </select>
	            		</td>
	                </tr>
	                <tr>
	                    <td>팀장</td>
	                    <td>${teamInfo.leaderNick }</td>
	                </tr>
	            </table>
                <input type="submit" value="수정">
                <button type="button" class=" reviseTeamInfo back" >취소</button>
            </form>
        </div>
        <div class="LeaderMenu leadersWrite ">
            ${teamInfo.leaderNick }님의 공지사항: ${teamInfo.teamMemo }<br>
            <button type="button" class="reviseW rBtn"><i class="fa-regular fa-pen-to-square"></i></button>
        </div>
        <div class="LeaderMenu reivseWrite hide">
            <form method="post" action="teamInfo/leaderMemo">
                ${teamInfo.leaderNick }님의 공지사항: <input type="text" name="leWrite" required><br>
                <input type="submit" class="rBtn2" value="수정">
            </form>
        </div>
	  	<div id="memberInfo">
	  	<c:forEach var="member" items="${memberList}" varStatus="loop">
	      <div class="member">
       	   <div class="tape"></div>	
       	    <c:if test="${member.userKey eq teamInfo.teamLeader }" >
	      		<div class="king"><img src="https://ifh.cc/g/6bBq87.png"></div>
	      	</c:if>
            	<div class="photo">
                	<img src="https://ifh.cc/g/GCpQKq.png">
           		</div>
            	<div class="info">
            	     <table>
                    <tr>
                        <td>이름</td>
                        <td>${member.nickname }</td>
                    </tr>
                    <tr>
                        <td>한마디</td>
                        <td>${member.intro }</td>
                    </tr>
                    <tr>
                        <td>출석률</td>
                        <td>${PercentList.get(loop.index)}%</td>
                    </tr>
                    <tr>
                        <td>마지막 접속</td>
                        <td>${member.lastTime }</td>
                    </tr>
                </table>
                <c:if test="${member.userKey ne userInfo.userKey }" >
                <button  class="kickOut" type="button" onclick="location.href='/teampro/teamInfo/delMember?tmkey=${member.tm_key}'">
                <i class="fa-solid fa-arrow-right-from-bracket"></i></button>
                </c:if>
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
	                       	<td>${boardList.tb_memo}<button class='delBtn' type="button" onclick="location.href='/teampro/teamInfo/delBoard?tbkey=${boardList.tb_key}'">
	                       	<i class="fa-regular fa-trash-can"></i></button>
	                       	</td>
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