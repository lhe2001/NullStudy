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
	userID : ${userID }<br>
	userName : ${userName }<br>
	msg : ${msg }<br>
	<c:if test="${empty email }">
	email : 값이 없음 null<br>
	</c:if>
	<c:if test="${!(empty email) }">
	email : ${email }<br>
	</c:if>
	<hr>
	info.userID : ${info.userID }<br>
	info.userName : ${info.userName }<br>
	info.email : ${info.email }<br>
</body>
</html>