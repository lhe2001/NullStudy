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
<title>글쓰기창이요~</title>
<link rel="stylesheet"
 href ="${pageContext.request.contextPath }/resources/css/bootstrap.min.css">
 <link rel="stylesheet"
 href ="${pageContext.request.contextPath }/resources/css/projectNull.css">
 <c:choose>
<c:when test="${not empty userInfo.userKey }">
<jsp:include page="/fix/header(logout).jsp"/>
</c:when>

<c:when test="${empty userInfo.userKey }">
<jsp:include page="/fix/header(login).jsp"/>
</c:when>
</c:choose>

<style type="text/css">
#wrapp {
	width : 100%;
	height : 100%;
	text-align: center;
}

#container {
	margin-top : 150px;
	display : inline-block;
	width: 1030px;
	height : 800px;
	border : 1px solid #99A799;
	border-radius : 2px;
	background-color: #FEF5ED;
}

#article_content{
width : 1000px; 
height : 500px; 
resize : none;
border : none;
outline : none;
}
#write_btn {
}
#select_list{
border-radius: 4px;
}
#file_flex{
display : flex;
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

.hidden {
	display : none;
 }
 #pwd {
 	width : 100px;
 	height : 30px;
 }
 .pww {
 	line-height: 2.6;
 }
 #pw {
 	color : #1C6758;
 }
 #pwd {
 	margin-right : 10px;
 	color : #1C6758;
 	border : 1px solid #1C6758;
 	outline : none;
 	border-radius: 2px;
 }
</style>
</head>
<body>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script type="text/javascript">
		// 	function backToList(obj){
		// 		obj.action = "${contextPath}/board/listArticles.do";
		// 		obj.submit();
		// 	}
	window.addEventListener("load", boardonload);

    function boardonload(){

			let obj = document.querySelector("#frmForm");
			let btn = document.querySelector("#list_btn");
			btn.addEventListener("click", function() {
				obj.action = "${contextPath}/board/listArticles.do";
				obj.submit();
			})
			
			let select = document.querySelector("#select_list");
			let pwd = document.querySelector("#pwd");
			let pw = document.querySelector("#pw");
			select.addEventListener("change",function(){
				let value = select.value;
					if(value == 30){
					pwd.classList.remove('hidden');
					pw.classList.remove('hidden');
					} else {
						pwd.classList.add('hidden');
						pw.classList.add('hidden');
					}
			})
	}
</script>
<div id = "wrapp">
<div id = "container">
	<h1 style = "margin-top : 30px; color : #1C6758">자유 게시판 글쓰기</h1><hr>
	 <div class = "field">
		<form name="frmName" id="frmForm" action="${contextPath }/board/addArticle.do" method="post" enctype="multipart/form-data">
			<div class="input-group flex-nowrap">
	  			<span class="input-group-text" id="addon-wrapping" style = "color : #99A799">제목</span>
	  			<input type="text" name="b_title" class="form-control" placeholder="제목을 입력해 주세요." style = "background-color : #A2B29F; color : white;" aria-label="Username" aria-describedby="addon-wrapping">
			</div>
				<div id = "file_flex">
				 <select id = "select_list" class = "btn-outline-info" style = "float : left; margin-left : 10px; height : 30px; margin : 5px;" name="a_field">
					<option value="10">질문</option>
					<option value="20">잡담</option>
					<option id = "secret" value="30" style="color:tomato;">
					비밀글
					</option>
					<option value="40">나도몰라</option>
				</select>
				<div class = "pww">
					<span id = "pw" class = "hidden">비밀번호!</span>
					<input type = "text" id = "pwd" name = "b_articlePwd" class = "hidden">
				</div>
			
			<div class="filebox">
  				<label for="ex_file">파일 업로드</label>
  				<input type="file"  name="b_imageFile"  id="ex_file" style = "border : none; background-color: #FEF5ED; color : #FEF5ED;" onchange="readURL(this);">
			</div>
		</div>
			<textarea rows="10" cols="70" name="b_content" id = "article_content" placeholder = "내용을 입력해 주세요!!!" style = "padding-top : 10px; padding-left : 5px;"></textarea><br><hr>
		 		<input type="button" id="list_btn" class="btn btn-outline-light" value="목록으로"  style = "float : left; margin-left : 10px; border : 1px solid #99A799; color : #99A799;"/>
				<input type="submit" id = "write_btn" value="글쓰기" class="btn btn-outline-light" style = "float : right; margin-right : 50px; border : 1px solid #99A799; color : #99A799;"/>
		</form>
	</div>
</div>
</div>
	<%-- 목록 으로가는건 이런것도 있다. location.href='${contextPath}/board/listArticles.do' --%>
<jsp:include page="/fix/footer.jsp"/>
</body>
</html>