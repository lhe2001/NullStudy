<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>  
<%--  <c:choose> --%>
<%-- 	<c:when test="${not empty userInfo.userKey }"> --%>
<%-- 	<jsp:include page="/fix/header(logout).jsp"/> --%>
<%-- </c:when> --%>

<%-- <c:when test="${empty userInfo.userKey }"> --%>
<%-- 	<jsp:include page="/fix/header(login).jsp"/> --%>
<%-- 	</c:when> --%>
<%-- </c:choose> --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호를 입력하세요</title>
<style type="text/css">
#form { 
	margin-top : 200px;
}
#div_1 {
	text-align: center;
}
</style>

<!-- bootstrap css -->
<link rel="stylesheet"
 href ="${pageContext.request.contextPath }/resources/css/bootstrap.min.css">
</head>
<body>
	<div id = "div_1">
	<form id = "form" name = "frmname" action="${contextPath }/board/checkPw.do" method = "post">
		<div class="col-auto">
		<input type = "password" name = "pw"  class="form-control" id="inputPassword2" placeholder="Password" style = "width : 200px; display: inline-block;">
		</div>
		 <div class="col-auto">
		<input type = "submit" class="btn btn-outline-light"  value = "비밀번호 확인" style = " margin-right : 50px; border : 1px solid #99A799; color : #1C6758;">
		</div>
		<input type = "hidden" name = "b_articleNo" value = ${view.b_articleNo }>
	</form>
	</div>	
<%-- 	<jsp:include page="/fix/footer.jsp"/> --%>
</body> 
</html>                                            