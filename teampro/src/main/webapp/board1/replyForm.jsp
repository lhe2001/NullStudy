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
<c:choose>
<c:when test="${not empty userInfo.userKey }">
<jsp:include page="/fix/header(logout).jsp"/>
</c:when>

<c:when test="${empty userInfo.userKey }">
<jsp:include page="/fix/header(login).jsp"/>
</c:when>
</c:choose>
<style type="text/css">
body {
	color : #99A799;
}


#wrrap {
	width: 100%;
	height : 100%;
	text-align: center;
	
 }
 
#container {
	margin-top : 150px;
	display: inline-block;
	width : 1030px;
	height: 800px;
	border : 1px solid #99A799;
	border-radius : 2px;
	background-color: #FEF5ED;
}

#text_a {
	resize: none;
	width : 1000px;
	height: 500px;
	padding:10px;
	border:none;
	outline: none;
	color : #99A799;
}

#articlewriter {
	margin-right : 10px;
}

#replyFileLg{
}
#last_btn {
	padding-top : 100px;
}

.filebox label {
  display: inline-block;
  padding: .5em .75em;
  color: #999;
  font-size: inherit;
  line-height: normal;
  vertical-align: middle;
  background-color: #fdfdfd;
  cursor: pointer;
  border: 1px solid #ebebeb;
  border-bottom-color: #e2e2e2;
  border-radius: .25em;
  float : left;
  margin-top:2px;
}

.filebox input[type="file"] {  /* 파일 필드 숨기기 */
  position: absolute;
  width: 1px;
  height: 1px;
  padding: 0;
  margin: -1px;
  overflow: hidden;
  clip:rect(0,0,0,0);
  border: 0;
  
}

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
	<h1 style="margin-top:20px; color : #1C6758;">여러분들의 소중한 답글작성 페이지 입니다.</h1>
	<hr>
	<form action="${contextPath }/board/addReply.do" name="frmReply" id="frmReply"  method="post" enctype="multipart/form-data">	
		
		<div id = "articlewriter" >
		<div style="float : left; border : 1px solid #ced4da; border-radius: 4px; background-color: #e9ecef; width:100px; height:30px;
		 line-height: 1.5;">
		글 작성자
		</div>
		 <span style ="background-color: #FEF5ED; float : left; margin-left: 3px; border : 0; margin-bottom: 10px;">${param.b_writer }</span><br>
		</div>
		
		<div class="input-group flex-nowrap">
  			<p class="input-group-text" id="addon-wrapping" style = "color : #99A799">제목</p>
		<input type="text" name="b_title" placeholder="제목을 입력하세요!!" style = "color : white; background-color: #A2B29F;"> 
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
		<input type="button" id="btn_re" class="btn btn-outline-light" style = "border : 1px solid #99A799; color : #99A799;" value="답글작성완료">
		 <input type="button" id="btn_can" class="btn btn-outline-light" style = "border : 1px solid #99A799; color : #99A799;" value="취소">
		</div>

	</form>
	</div>
	</div>
	<jsp:include page="/fix/footer.jsp"/>
</body>
</html>