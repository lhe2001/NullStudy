<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
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
	        <button type="button" class="yellow myTodoBtn">Info</button>
	        <button type="button" class="blue teamListBtn">Team</button>
	    </div>
	    <div class="postit pink">
	        <div class="left"></div>
	        <div id="userInfo">
	            <div id="userPT"></div>
	            <h1>Annie</h1>
	            <p>Team : Null</p>
	            <p><button type="button" id="logOut">로그아웃</button></p>
	        </div>
	    </div>

	    <div class="postit yellow none">
	        <div class="left"></div>
	        <div id="todoList">
	        	<div id="todoBtn">
	        	<form id="createTodo" method="post" action="/project/mystudy/AddTodo.do">
	            	<input type="text" id="todoText" placeholder="추가List" name="m_td_desc">
	            	<input type="submit" id="tdaddBtn" class="tdbtncss" value="추가" />
<!-- 	            	<button type="button" id="tdaddBtn" class="tdbtncss">추가</button> -->
<!-- 	            	<button type="button" id="tddeleteBtn" class="tdbtncss">삭제</button> -->
	        	</form>
	        	</div>
	            <div id="list">
	            	<form name="frmTDDel">
	                <table>
	                    <thead>
	                        <tr>
	                            <th colspan="2" id="tdTBth">To Do List</th>
	                        </tr>
	                    </thead>
	                    <tbody>
	                    </tbody>
	                </table>
	                </form>
	            </div>
	        </div>
	    </div>	    
	    
	    
<!-- 	    <div class="postit yellow none"> -->
<!-- 	        <div class="left"></div> -->
<!-- 	        <div id="todoList"> -->
<!-- 	        	<div id="todoBtn"> -->
<!-- 	        	<form id="createTodo" method="post" action="/project/mystudy/AddTodo.do"> -->
<!-- 	            	<input type="text" id="todoText" placeholder="추가List" name="m_td_desc"> -->
<!-- 	            	<input type="submit" id="tdaddBtn" class="tdbtncss" value="추가" /> -->
<!-- <!-- 	            	<button type="button" id="tdaddBtn" class="tdbtncss">추가</button> --> -->
<!-- <!-- 	            	<button type="button" id="tddeleteBtn" class="tdbtncss">삭제</button> --> -->
<!-- 	        	</form> -->
<!-- 	        	</div> -->
<!-- 	            <div id="list"> -->
<!-- 	            	<form name="frmTDDel"> -->
<!-- 	                <table> -->
<!-- 	                    <thead> -->
<!-- 	                        <tr> -->
<!-- 	                            <th colspan="2" id="tdTBth">To Do List</th> -->
<!-- 	                        </tr> -->
<!-- 	                    </thead> -->
<!-- 	                    <tbody> -->
<!-- 	                    </tbody> -->
<!-- 	                </table> -->
<!-- 	                </form> -->
<!-- 	            </div> -->
<!-- 	        </div> -->
<!-- 	    </div> -->
	    
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