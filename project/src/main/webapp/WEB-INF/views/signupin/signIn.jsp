<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>

<body>
		<c:set var="result" value="${result }"/>
		<c:if test="${result=='가입성공'}">
    		<script>
    			alert("가입성공, 로그인해주세요")
    		</script>
    	</c:if>
    	
	<div class="main_login">
		<section class="login_wrap">
			<div class="logo">
			</div>
			<br><br>
			<form name="frmSignIn" method="post" action="/project/signIn.do" encType="UTF-8">
			<div class="box_wrap">
				<input type="text" name="id" placeholder="아이디" class="inputs"></input>
			</div>
			<div class="box_wrap">
				<input type="password" name="pw" placeholder="비밀번호" class="inputs"></input>
			</div>
			<div class="btn_wrap">
				<input type="submit" class="login_btn" value="로그인"></input>
			</div>
			</form>
		</section>
	</div>
</body>
</html>


