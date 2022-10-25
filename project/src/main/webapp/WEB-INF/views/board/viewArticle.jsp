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
    
    
//     아작스
    $(function(){
		addComment();
		deleteComment();
		update();
		updateComment();
	})
		// 댓글 추가
      	function addComment(){
    			 $("#add_comment").off("click").on("click", function(){
	    			let comment = $("#comment").val();
	    			let b_key = $("#b_key").val();
	    			let userKey = $("#userkey").val();
	    			
    			 let info = {
    					b_c_comment : comment,
    					b_key : b_key,
    					userKey : userKey
 	    			}
    			
    			 $.ajax({
    				url: "/project/board/addComment.do",
    				type: "post",
    				contentType : "application/json",
    				data: JSON.stringify(info),
    				success: function(data){
    					console.log("list :", data);
    					$("#view_comment").empty();
    					alert("댓글이 작성되었습니다.");
    					if(data.length > 0){
        					let html = "";
        					for(let i = 0; i<data.length; i++){
        						html += '<input type = "text" id = "view_com" value = "'+ data[i].b_c_comment + '" readonly>';
    							html += '작성자 : ' + data[i].nickName;
    							html += '작성일 : ' + data[i].b_c_date
        					}
    					$("#view_comment").append(html);
    					}
    					location.reload();
    				},
    				error:function(){
    					alert("댓글을 입력해 주세요!!")
    				}
    			});
    		})
    	}
    	
 	// 댓글 삭제
  	function deleteComment(){
			 $(".delete_comment").off("click").on("click", function(){
				 
				 $(this).attr("data-b_c_key");
				 $(this).parent().find(".b_c_key").val();
// 				 this.parentNode().querySeletor(".b_c_key").value
				 
				 let b_c_key = $(this).parent().find(".b_c_key").val();
				 console.log("b_c_key" , b_c_key);
				 
				 let info = { b_c_key : b_c_key }
				 
			$.ajax({
				url: "/project/board/deleteComment.do",
				type: "post",
				contentType : "application/json",
				data: JSON.stringify(info),
				success: function(data){
					console.log("data :", data);
					alert("댓글이 삭제되었습니다.");
					location.reload();
					},
				error:function(){
					alert("에러발생!!")
				}
			});
		})
	}
 
 // 댓글 수정
</script>
	<div class = "wrapp">
	<div class = "container">
		<h1 style = "margin-top : 30px; color : #353866">${view.nickName }님의 소중한 글입니다.</h1><hr>
	<div class = "field">
		<form name="frmName" id=frmForm action="${contextPath }" enctype="multipart/form-data" method="post"><br>
			<div id = "articleNo">
				<div class ="color_btn" style="float : left;  border-radius: 4px; background-color: white; width:100px; height:30px;" >
					글번호
				</div> 
				<input type="text" value="${view.b_articleNo }"  style ="background-color: #FEF5ED; float : left;  margin-left: 3px; border : 0; color :#353866;" disabled><br>
			</div>
				<!-- 	히든으로 넘겨줄 정보	-->
				<input type="hidden" name="b_parentNo" value="${view.b_parentNo }">
				<input type="hidden" name="b_articleNo" value="${view.b_articleNo }">
				<input type="hidden" name="b_writer" value="${view.nickName }">
				<input type="hidden" name="b_field" value="${view.b_field }">
				
				
			<div id = "articlewriter" >
				<div class="color_btn" style="float : left; border-radius: 4px; background-color: white; width:100px; height:30px;
			 	line-height: 1.5;">
					글 작성자
			</div>
				<input type="text" name="b_writer" value="${view.nickName }" 
				style ="background-color: #FEF5ED; float : left; margin-left: 3px; border : 0; margin-bottom: 10px; color :#353866;" disabled> 
				</div><br>
			<div id = "add">
				<div class="color_btn" style="float : left;  border-radius: 4px; background-color : white; width:100px; height:30px;
			 	line-height: 1.5;">
					등 록 일 
				</div>
				<input type="text" value="<fmt:formatDate value="${view.b_writeDate }"/> "name="b_writeDate" class = "color_btn"
				style ="background-color: #FEF5ED; float : left; margin-left: 3px; border : 0; margin-bottom: 10px; color :#353866;" disabled >
				</div>
	
			<div class="input-group flex-nowrap" >
	  			<span class="color_btn input-group-text" id="addon-wrapping" style = "background-color: white;">제목</span>
				<input type="text" class="form-control" id="edit_title" value="${view.b_title }" 
				style = "background-color: #e1c1ec; width :700px; color :white; " name="b_title" aria-label="Username" aria-describedby="addon-wrapping" disabled>
			</div><br>
			
			<textarea rows="10" cols="70" id="edit_content" name="b_content" 
				style ="background-color: white; resize : none; width : 1000px; height : 500px; border:0;"
				disabled> ${view.b_content } 
			</textarea><br>
			<%-- 이미지가 있을 경우 표시 --%>
			<div id = "image" >
				<c:if test="${not empty (view.b_imageFile) && view.b_imageFile != 'null'}">
					image : 
				<%-- 이미지 수정에 대비해 지금 이미지 파일 이름을 저장 --%>
				<input type="hidden" id="edit_image" name="oriFileName"
					value="${view.b_imageFile }" />
				<img style = "width : 200px ; height : 200px;" src="${contextPath }/board/download.do?b_imageFile=${view.b_imageFile }
					&b_articleNo=${view.b_articleNo }" /><br>
				<%-- 수정된 이미지 파일 이름 전송 --%>
				<input type="file" name="b_imageFile" id="imageFile"
					onchange="readURL(this);" disabled />
				</c:if>
			</div><hr><br>
			<!-- 댓글 추가 div -->
	<div id = "wrap_comment">
		<h4 style = "color:#353866">comment</h4>
			<textarea rows="4" cols="70" name = "b_c_comment"id ="comment" placeholder = "댓글을 입력해 주세요!!!" style = "padding-top : 10px; padding-left : 5px;"></textarea>
			<input type="button" id="add_comment" value="댓글쓰기" class ="color_btn btn btn-outline-light"  style = "float : right; margin-right : 5px;">
		</div>
			<br><br><hr>
			<!-- 댓글이 들어갈 곳 -->
			<h3 style = "color:#353866"> 댓글 입니다. </h3>
		<div id = "view_comment">
			<c:forEach var="comment" items="${comment }" varStatus="num">
					<c:if test="${comment.b_key == view.b_key }">
					<div>
						<input type = "text" id = "view_com" class= "view_com" value = "${comment.b_c_comment }" readonly>
							<div id = "comment_name" style ="float : left; margin-left : 20px;  ">
								작성자 : ${comment.nickName }	${comment.b_c_date }
							</div>
						<c:if test="${userInfo.userKey == comment.userKey}">
						<input type = "button"  value = "댓글삭제" data-b_c_key = "${comment.b_c_key }" class ="delete_comment color_btn btn btn-outline-light"  style = "float : right; margin-right : 5px; ">
						<input type="button" id="edit_comment" value="수정" data-b_c_key_edit = "${comment.b_c_key }"  class ="edit_comment color_btn btn btn-outline-light"  style = "float : right; margin-right : 5px;">
						</c:if>
						<input type = "hidden" class = "b_c_key" name = "b_c_key" value = "${comment.b_c_key }"/>
						</c:if>
					</div>
			</c:forEach>
		</div>
				<!-- 히든으로 commentcontroller에 줄 값 -->
				<input type ="hidden" name="b_key" id ="b_key" value= "${view.b_key }"/>
				<input type ="hidden" name="userkey" id ="userkey" value = "${userInfo.userKey }"/><br><hr>
		
			<div id = "btn_list">
				<input type="button" id="list_btn"  class="color_btn btn btn-outline-light" value="목록으로" style = "float : right; margin-right : 5px; ">
					<c:choose>
						<c:when test="${userInfo.userKey == view.userkey }">
<!-- 						로그인 -->
						<input type="button" id="edit_btn" value="수정하기" class ="color_btn btn btn-outline-light"  style = "float : right; margin-right : 5px;">
						<input type="button" id="del_btn" value="삭제하기" class ="color_btn btn btn-outline-light"  style = "float : right; margin-right : 5px; "> 
						<input type="button" id="rePly_btn" value="답글쓰기" class ="color_btn btn btn-outline-light"  style = "float : right; margin-right : 5px; ">
						</c:when>
						<c:when test ="${empty userInfo.userKey }">
<!-- 						비로그인 -->
						<input type="button" id="edit_btn" value="수정하기" class = "color_btn hidden btn btn-outline-light" >
						<input type="button" id="del_btn" value="삭제하기" class = "color_btn hidden btn btn-outline-light"> 
						<input type="button" id="rePly_btn" value="답글쓰기" class = "color_btn hidden btn btn-outline-light">
						</c:when>
						<c:when test ="${not empty userInfo.userKey }">
						<input type="button" id="edit_btn" value="수정하기" class="color_btn hidden btn btn-outline-light" style = "float : right; margin-right : 5px;">
						<input type="button" id="del_btn" value="삭제하기" class="color_btn hidden btn btn-outline-light" style = "float : right; margin-right : 5px;"> 
						<input type="button" id="rePly_btn" class="color_btn btn btn-outline-light" value="답글쓰기"  style = "float : right; margin-right : 5px;">
						</c:when>
					</c:choose>
			<input type="button" id="real_edit_btn" value="수정" class="btn btn-outline-danger">
		</div>
	</form>
</div>
</div>
</div>
<%-- <jsp:include page="/fix/footer.jsp"/> --%>
</body>
</html>