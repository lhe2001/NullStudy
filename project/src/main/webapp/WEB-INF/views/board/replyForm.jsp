<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>답글 쓰기 창</title>
<!-- bootstrap css -->
<link rel="stylesheet"
 href ="${pageContext.request.contextPath }/resources/css/bootstrap.min.css">
 <link rel="stylesheet"
 href ="${pageContext.request.contextPath }/resources/css/reply.css">
<%-- <c:choose> --%>
<%-- <c:when test="${not empty userInfo.userKey }"> --%>
<%-- <jsp:include page="/fix/header(logout).jsp"/> --%>
<%-- </c:when> --%>

<%-- <c:when test="${empty userInfo.userKey }"> --%>
<%-- <jsp:include page="/fix/header(login).jsp"/> --%>
<%-- </c:when> --%>
<%-- </c:choose> --%>
<style type="text/css">

</style>

<script type="text/javascript">
	window.addEventListener("load", boardonload);

    function boardonload(){
		let obj = document.querySelector("#frmReply");
		let btn = document.querySelector("#btn_re");
			btn.addEventListener("click", function() {
				obj.action = "${contextPath}/board/addReply.do";
				obj.submit();
		})

		let btn_1 = document.querySelector("#btn_can");
			btn_1.addEventListener("click", function() {
				history.back();
		})
	}
</script>
</head>
<body>
	<div id = "wrrap">
	<div id = "container">
	<h1 style="margin-top:20px; color : #353866;">여러분들의 소중한 답글작성 페이지 입니다.</h1>
	<hr>
	<form action="${contextPath }/board/addReply.do" name="frmReply" id="frmReply"  method="post" enctype="multipart/form-data">	
		
		<div id = "articlewriter" >
		<div id = "writer">
		글 작성자
		</div>
		 <span id = "writer2">${param.b_writer }</span><br>
		</div>
		
		<div class="input-group flex-nowrap">
  			<p class="input-group-text" id="addon-wrapping" style = "color : #353866; background-color: white;">제목</p>
		<input type="text" name="b_title" placeholder="제목을 입력하세요!!" style = "color : white; background-color: #d8b1b8;"> 
		</div>
		
		<div class="filebox">
  		<label for="ex_file">파일 업로드</label>
  		<input type="file"  name="b_imageFile"  id="ex_file"
			style = "border : none; background-color: #FEF5ED; color : #FEF5ED;" onchange="readURL(this);">
		</div>

		<Br>
		<textarea id = "text_a" rows="10" cols="70" name="b_content" placeholder="내용을 입력하세요."></textarea>
		<br>
		
		<div id = "last_btn">
		<input type = "hidden" name = "b_parentNo" value ="${b_articleNo }">
		<input type = "hidden" name = "b_field" value ="${b_field }">
		<input type="button" id="btn_re" class="color_btn btn btn-outline-light"  value="답글작성완료">
		 <input type="button" id="btn_can" class="color_btn btn btn-outline-light"  value="취소">
		</div>
	</form>
	</div>
	</div>
<%-- 	<jsp:include page="/fix/footer.jsp"/> --%>
</body>
</html>