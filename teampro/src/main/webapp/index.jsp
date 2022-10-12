<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
	import="signupin.*"
%>

<jsp:include page="fix/header(logout).jsp" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>


<body>
 로그인 후 화면<br>
 
 <%
 	//세션으로 전달해준 userInfo 작동확인
 	SignUpInDTO userInfo = new SignUpInDTO();
 	userInfo = (SignUpInDTO)session.getAttribute("userInfo");
 	if(userInfo == null){
 		//로그인되지 않은 상태로 index.jsp에 접근할경우
 		//userInfo는 null 상태 > main.jsp로 보냄
 		response.sendRedirect("main.jsp");
 	} else{
 		%>
 		세션에 바인딩해준 유저정보<br>
 		이름 : <%= userInfo.getName() %><br>
 		아이디 : <%= userInfo.getId() %><br>
 		유저키 : <%= userInfo.getUserKey() %><br>
 		이메일 : <%= userInfo.getEmail() %><br>
 		마지막접속시간 : <%= userInfo.getLastTime() %><br>
 		<%
 	}
 %>
 
  
 <form action=signout method="post">
 	<input type=submit value="로그아웃"></input>
 </form>
 
</body>
</html>

<jsp:include page="fix/footer.jsp" />