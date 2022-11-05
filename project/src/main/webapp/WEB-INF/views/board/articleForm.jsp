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
 href ="${pageContext.request.contextPath }/resources/css/article.css">
<style type="text/css">
</style>
</head>
<body>
<div id = "wrapp">
<div id = "container">
	<h1 style = "margin-top : 30px; color : #353866">자유 게시판 글쓰기</h1><hr>
	 <div class = "field">
		<form name="frmName" id="frmForm" action="${contextPath }/board/addArticle.do" method="post" enctype="multipart/form-data">
			<div class="input-group flex-nowrap">
	  			<span class="input-group-text" id="addon-wrapping" style = "color : #99A799">제목</span>
	  			<input type="text" id = "b_title" name="b_title" class="form-control" placeholder="제목을 입력해 주세요." style = "background-color : #e1c1ec; color : white;" aria-label="Username" aria-describedby="addon-wrapping">
			</div>
				<div id = "file_flex">
				 <select id = "select_list" class = "btn-outline-info" style = "float : left; margin-left : 10px; height : 30px; margin : 5px;" name="b_field">
					<option value="10">질문</option>
					<option value="20">잡담</option>
					<option id = "secret" value="30" style="color:tomato;">비밀글</option>
					<option value="40">유우머</option>
					<option id="notice" class = "hidden" value="50" style ="color:#ea7f27;">공지</option>
				</select>
				<div class = "pww">
					<span id = "pw" class = "hidden">비밀번호!</span>
					<input type = "text" id = "pwd" name = "b_articlePwd" class = "hidden">
				</div>
			<div class="filebox">
  				<label for="ex_file">파일 업로드</label>
  				<input type="file"  name="b_imageFile"  id="ex_file"  class="color_btn"  onchange="readURL(this);" />
			</div>
		</div>
			<textarea rows="10" cols="70" name="b_content" id = "article_content" placeholder = "내용을 입력해 주세요!!!" style = "padding-top : 10px; padding-left : 5px;"></textarea><br><hr>
	</div>
</div>
</div>
			<div id = "listbtn">
		 		<input type="button" id="list_btn" class="color_btn btn btn-outline-light" value="목록으로"  onclick= 'location.href="${contextPath}/board/listArticles.do"' style = "float : left; margin-left : 10px;"/>
				<input type="submit" id = "write_btn" value="글쓰기" class="color_btn btn btn-outline-light" style = "float : right; margin-right : 50px; "/>
			</div>
	</form>
</body>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script type="text/javascript">
		// 	function backToList(obj){
		// 		obj.action = "${contextPath}/board/listArticles.do";
		// 		obj.submit();
		// 	}
	window.addEventListener("load", boardonload);

    function boardonload(){

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
			
			var userkey  = ${userInfo.userKey};
			if(userkey==1){
				document.querySelector("#notice").classList.remove('hidden');
			}else{
				document.querySelector("#notice").classList.add('hidden');
			}
		}
</script>
</html>