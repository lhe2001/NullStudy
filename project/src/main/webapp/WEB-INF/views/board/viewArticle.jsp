<%@page import="javax.print.attribute.HashPrintRequestAttributeSet"%>
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
<title>글 상세목록 페이지</title>
<!-- bootstrap css -->
<link rel="stylesheet"
 href ="${pageContext.request.contextPath }/resources/css/bootstrap.min.css">
  <link rel="stylesheet"
 href ="${pageContext.request.contextPath }/resources/css/view.css">
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
			/*  목록으로 ~ */
			let obj = document.querySelector("#frmForm");
			console.log(obj);
			let btn = document.querySelector("#list_btn");
			btn.addEventListener("click", function() {
				obj.action = "${contextPath}/board/listArticles.do";
				obj.method = "get"
				obj.submit();
				
			})

			// 수정하기 버튼을 눌렀을때 수정페이지로 간다.
			let btn_1 = document.querySelector("#edit_btn");
			btn_1.addEventListener("click",function() {
				//수정할 수 있는 부분은 disabled를 풀어준다.
				document.querySelector("#edit_title").disabled = false;
				document.querySelector("#edit_content").disabled = false;
				document.querySelector("#real_edit_btn").style.display = "block";
				document.querySelector("#edit_btn").style.display = "none";					
				document.querySelector("#imageFile").disabled = false;
				});
			// 수정 버튼을 눌렀을때 updateArticle.do로 전달!!
			let btn_2 = document.querySelector("#real_edit_btn");
			btn_2.addEventListener("click", function() {
				obj.action = "${contextPath}/board/updateArticle.do";
				obj.submit();
			});

			// 삭제 버튼을 눌렀을때 deleteArticle.do로 전달
			let btn_3 = document.querySelector("#del_btn");
			btn_3.addEventListener("click", function() {
				obj.action = "${contextPath}/board/deleteArticle.do";
				obj.method = "GET";
				obj.submit();
			});

			// 답글 버튼을 눌렀을때 replyForm.do로 전달
			let btn_4 = document.querySelector("#rePly_btn");
			btn_4.addEventListener("click", function() {
				obj.action = "${contextPath}/board/replyForm.do";
				obj.method = "GET";
				obj.submit();
			});
		}
</script>
	<div class = "wrapp">
	<div class = "container">
		<h1 style = "margin-top : 30px; color : #1C6758">${view.nickName }님의 소중한 글입니다.</h1><hr>
	<div class = "field">
		<form name="frmName" id=frmForm action="${contextPath }" enctype="multipart/form-data" method="post"><br>
			<div id = "articleNo">
				<div style="float : left; border : 1px solid #ced4da; border-radius: 4px; background-color: #e9ecef; width:100px; height:30px;" >
					글번호
				</div> 
				<input type="text" value="${view.b_articleNo }"  style ="background-color: #FEF5ED; float : left;  margin-left: 3px; border : 0; color :#99A799;" disabled><br>
			</div>
				<!-- 	히든으로 넘겨줄 정보	-->
				<input type="hidden" name="b_parentNo" value="${view.b_parentNo }">
				<input type="hidden" name="b_articleNo" value="${view.b_articleNo }">
				<input type="hidden" name="b_writer" value="${view.nickName }">
				<input type="hidden" name="b_field" value="${view.b_field }">
			<div id = "articlewriter" >
				<div style="float : left; border : 1px solid #ced4da; border-radius: 4px; background-color: #e9ecef; width:100px; height:30px;
			 	line-height: 1.5;">
					글 작성자
			</div>
				<input type="text" name="b_writer" value="${view.nickName }" 
				style ="background-color: #FEF5ED; float : left; margin-left: 3px; border : 0; margin-bottom: 10px; color :#99A799;" disabled> 
				</div><br>
			<div id = "add">
				<div style="float : left; border : 1px solid #ced4da; border-radius: 4px; background-color: #e9ecef; width:100px; height:30px;
			 	line-height: 1.5;">
					등 록 일 
				</div>
				<input type="text" value="<fmt:formatDate value="${view.b_writeDate }"/> "name="b_writeDate" 
				style ="background-color: #FEF5ED; float : left; margin-left: 3px; border : 0; margin-bottom: 10px; color :#99A799;" disabled >
				</div>
	
			<div class="input-group flex-nowrap" >
	  			<span class="input-group-text" id="addon-wrapping" style = "color :#99A799;">제목</span>
				<input type="text" class="form-control" id="edit_title" value="${view.b_title }" 
				style = "background-color: #A2B29F; width :700px; color :white; " name="b_title" aria-label="Username" aria-describedby="addon-wrapping" disabled>
			</div><br>
			
			<textarea rows="10" cols="70" id="edit_content" name="b_content" 
				style ="background-color: white; resize : none; width : 1000px; height : 500px; border:0;"
				disabled> ${view.b_content } 
			</textarea><br>
			<%-- 이미지가 있을 경우 표시 --%>
			<div id = "image" >
				<c:if test="${not empty (view.b_imageFile) && view.b_imageFile != 'null'}">
					이 미 지 : 
				<%-- 이미지 수정에 대비해 지금 이미지 파일 이름을 저장 --%>
				<input type="hidden" id="edit_image" name="oriFileName"
					value="${view.b_imageFile }" />
				<img style = "width : 200px ; height : 200px;" src="${contextPath }/download.do?b_imageFile=${view.b_imageFile }
					&b_articleNo=${view.b_articleNo }" /><br>
				<%-- 수정된 이미지 파일 이름 전송 --%>
				<input type="file" name="b_imageFile" id="imageFile"
					onchange="readURL(this);" disabled />
				</c:if>
			</div><hr><br>
			<div id = "btn_list">
				<input type="button" id="list_btn"  class="btn btn-outline-light" value="목록으로" style = "float : right; margin-right : 5px; border : 1px solid #99A799; color : #99A799;">
<%-- 					<c:choose> --%>
<%-- 						로그인 --%>
<%-- 						<c:when test="${userInfo.userKey == view.userkey }"> --%>
						<input type="button" id="edit_btn" value="수정하기" class =" btn btn-outline-light"  style = "float : right; margin-right : 5px; border : 1px solid #99A799; color : #99A799;">
						<input type="button" id="del_btn" value="삭제하기" class =" btn btn-outline-light"  style = "float : right; margin-right : 5px; border : 1px solid #99A799; color : #99A799;"> 
						<input type="button" id="rePly_btn" value="답글쓰기" class =" btn btn-outline-light"  style = "float : right; margin-right : 5px; border : 1px solid #99A799; color : #99A799;">
<%-- 						</c:when> --%>
						<%--비로그인 --%>
<%-- 						<c:when test ="${empty userInfo.userKey }"> --%>
<!-- 						<input type="button" id="edit_btn" value="수정하기" class = "hidden btn btn-outline-light" > -->
<!-- 						<input type="button" id="del_btn" value="삭제하기" class = "hidden btn btn-outline-light">  -->
<!-- 						<input type="button" id="rePly_btn" value="답글쓰기" class = "hidden btn btn-outline-light"> -->
<%-- 						</c:when> --%>
<%-- 						<c:when test ="${not empty userInfo.userKey }"> --%>
<!-- 						<input type="button" id="edit_btn" value="수정하기" class="hidden btn btn-outline-light" style = "float : right; margin-right : 5px; border : 1px solid #99A799; color : #99A799;"> -->
<!-- 						<input type="button" id="del_btn" value="삭제하기" class="hidden btn btn-outline-light" style = "float : right; margin-right : 5px; border : 1px solid #99A799; color : #99A799;">  -->
<!-- 						<input type="button" id="rePly_btn" class="btn btn-outline-light" value="답글쓰기"  style = "float : right; margin-right : 5px; border : 1px solid #99A799; color : #99A799;"> -->
<%-- 						</c:when> --%>
<%-- 					</c:choose> --%>
			<input type="button" id="real_edit_btn" value="수정" class="btn btn-outline-danger">
		</div>
	</form>
</div>
</div>
</div>
<%-- <jsp:include page="/fix/footer.jsp"/> --%>
</body>
</html>