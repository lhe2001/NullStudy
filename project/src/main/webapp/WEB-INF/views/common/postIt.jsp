<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>       
<title>사이드포스트잇</title>
    <link href="https://fonts.googleapis.com/css?family=Nanum+Pen+Script&display=swap" rel="stylesheet">
    <script src="https://kit.fontawesome.com/f5483c894c.js" crossorigin="anonymous"></script>
<body>

	<!-- 사이드바 -->
   <div id="shadow"></div>
   <aside>
	    <!-- <button type="button" class="asideHide">Hide</button> -->
	    <div id="postitBox">
	        <button type="button" class="pink">My</button>
	        <button type="button" class="yellow planList">Plan</button>
	        <button type="button" class="blue teamListBtn">Team</button>
	    </div>
	    <div class="postit pink">
	        <div class="left"></div>
	        <div id="userInfo">
	            <div id="userPT" onClick="changeProfile()">
                        <c:if test="${userInfo.photo != null}">
		            		<img style="width:95px; height:95px; border-radius:50%;" src='/project/team/download.do?userkey=${userInfo.userKey }'>
                        </c:if>
	            </div>
	            <h1 class="mainNickName">${sessionScope.userInfo.nickName}</h1>
	            <p class="mainIntro">인사말<br>${sessionScope.userInfo.intro}</p>
	            
	            <form action="profileUpdate.do" method="get">
	            	<p><button type="submit" id="userDataChange">프로필 수정</button></p>
	            </form>
	            
	        </div>
	    </div>
	    <div class="postit yellow none">
	        <div class="left"></div>
	        <div id="todoList">
	            <div id="pListTB">
	                <table>
	                    <thead>
	                        <tr>
	                            <th><i class="fa-regular fa-calendar-check">&nbsp;My Plan</i></th>
	                        </tr>
	                    </thead>
	                    <tbody>
	                    </tbody>
	                </table>
	            </div>
	            <button id="goCalBtn" onclick="location.href='/project/mystudy/calendar.do';">달력</button>
	        </div>
	    </div>
	   <div class="postit blue none">
      	<div class="left"></div>
           <div id="TeamInfoP">
			 <table>
			 	<thead>
				 	<tr>
				 		<th>현재 내가 속한 팀 목록</th>
				 	</tr>
				 </thead>
				 <tbody>
				</tbody>
			 </table>
         </div>
         </div>
         
         
	</aside>
</body>
