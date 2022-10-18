<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <link href="https://fonts.googleapis.com/css?family=Nanum+Pen+Script&display=swap" rel="stylesheet">
    <script src="https://kit.fontawesome.com/f5483c894c.js" crossorigin="anonymous"></script>
</head>
<body>
	<!-- 사이드바 -->
   <div id="shadow"></div>
   <aside>
	    <!-- <button type="button" class="asideHide">Hide</button> -->
	    <div id="postitBox">
	        <button type="button" class="pink">My</button>
	        <button type="button" class="yellow">To Do</button>
	        <button type="button" class="blue teamListBtn">Team</button>
	    </div>
	    <div class="postit pink none">
	        <div class="left"></div>
	        <div id="userInfo">
	            <div id="userPT"></div>
	            <h1>Annie</h1>
	            <p>Team : Null</p>
	            <p><button type="button" id="logOut">로그아웃</button></p>
	        </div>
	    </div>
	    <div class="postit yellow">
	        <div class="left"></div>
	        <div id="todoList">
	            <div id="list">
	                <table>
	                    <thead>
	                        <tr>
	                            <th><input type="checkbox"></th>
	                            <th>To Do List</th>
	                        </tr>
	                    </thead>
	                    <tbody>
	                        <tr>
	                            <td><input type="checkbox" name="todo"></td>
	                            <td>웹페이지 만들기</td>
	                        </tr>
	                        <tr>
	                            <td><input type="checkbox" name="todo"></td>
	                            <td>자바 공부하기</td>
	                        </tr>
	                        <tr>
	                            <td><input type="checkbox" name="todo"></td>
	                            <td>오라클 공부하기</td>
	                        </tr>
	                    </tbody>
	                </table>
	            </div>
	            <button type="button" id="addBtn" >추가</button>
	            <button type="button" id="deleteBtn">삭제</button>
	            <input type="text" id="todoText" placeholder="추가List">
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
	</aside>
</body>
</html>