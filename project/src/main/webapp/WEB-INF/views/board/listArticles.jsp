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
<title>글 목록 출력창</title>

<!-- bootstrap css -->
<link rel="stylesheet"
 href ="${pageContext.request.contextPath }/resources/css/bootstrap.min.css">
<link rel="stylesheet"
 href ="${pageContext.request.contextPath }/resources/css/list.css">

<%-- <c:choose> --%>
<%-- 	<c:when test="${not empty userInfo.userKey }"> --%>
<%-- 	<jsp:include page="/fix/header(logout).jsp"/> --%>
<%-- </c:when> --%>

<%-- <c:when test="${empty userInfo.userKey }"> --%>
<%-- 	<jsp:include page="/fix/header(login).jsp"/> --%>
<%-- 	</c:when> --%>
<%-- </c:choose> --%>

<style>

</style>

<script type="text/javascript">
		window.addEventListener("load", teamInfoOnload);

	      function teamInfoOnload(){
	    	let obj = document.querySelector("#frmId");
	  		let btn = document.querySelector("#search_btn");
	  		btn.addEventListener("click", function() {
	  			obj.action = "${contextPath}/board/searchArticle.do";
	  			obj.method = "post"
	  			obj.submit();
	  		})
	  		
	  		let sel = document.querySelector("#select");
				sel.addEventListener("change", function() {
			let obj = document.querySelector("#frmSelect");
				obj.action = "${contextPath}/board/selectField.do";
				obj.method = "post"
				obj.submit();
				})
	      	
			function link (){
			let link = document.querySelector("#link_a");
				link.addEventListener("click", function() {
			let obj = document.querySelector("#frmSelect");
				obj.action = "${contextPath}/board/password.do";
				obj.method = "post"
				obj.submit();
				})
				}
	      }
	//window.onload = function() {
	//}
</script>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
	<%-- contextPath = ${contextPath } --> /Project_JH --%>
	<%-- 여기에 forward 된 articlesList를 가지고 놀아야지 --%>

	<%-- <th>레벨</th>--%>
	<%-- <th>게시판 키</th>--%>
	<div id = "wrapp">
	<div id = "container">
	<h1 style ="text-align : left; margin-left : 20px; margin-top : 20px; color : #1C6758">자유게시판</h1>
	<h5 style ="text-align : left; margin-left : 20px;"> 자유롭게 글을 작성해 주세요!!</h5>
	<div id = "article_flex">
	<form id="frmSelect" name="frmSelectName" method = "post"> 
		<select id="select" name="list_sel" class="form-select btn-outline-info" 
		style = "border-radius : 4px; margin-top : 7px; " aria-label="Default select example" >
			<option selected>글머리</option> 
			<option value="0" >전체</option>
			<option value="10">질문</option>
			<option value="20">잡담</option>
			<option value="30" style ="color : tomato;">비밀글</option>
			<option value="40">나도몰라</option>
		</select>
	</form>
	<div style = " margin-left : 10px; font-size : 20px; font-weight :bold; " >
	글갯수 : ${articlesList.size()} 개
	</div>
</div>

	<table id="tb" class = "table table-hover table-bordered" style = "color : #1C6758">
		<tr style = "background-color: #A2B29F">
			<th>글 번호</th>
			<%--<th>부모 글 번호</th> --%>
			<th>분야</th>
			<th>작성자</th>
			<th>제목</th>
			<th>작성일</th>
			<th>조회수</th>
			<%--<th>유저 키</th> --%>
			<%--(join으로 nick가져올거임)  --%>
		</tr>
		<%--여기서 분기나 탑시다--%>
		<c:choose>
			<%-- forward된 list가 비어있을때!! --%>
			<c:when test="${empty articlesList}">
				<h1 style ="text-align : center; margin-left : 20px; margin-top : 20px; color : #1C6758">등록된 글이 없어요....</h1>
			</c:when>
			<%-- forward된 list에 내용이 있을때!! --%>
			<c:when test="${not empty articlesList }">
				<%-- forEach로 변수에 담아 jstl로 출력해준다.. --%>
				<c:forEach var="article" items="${articlesList }" varStatus="num">
					<tr>
						<%--<td>${article.level }</td> --%>
						<%--<td>${article.b_key }</td> --%>
						<td>${num.count }</td>
						<%-- varStatus의 count를 사용해서 글번호 1부터 자동 --%>
						<%-- <td>${article.b_parentNo}</td> --%>
						<c:choose>
						<c:when test="${article.b_fieldName eq '비밀글'}">
						<td style = "color : tomato;">${article.b_fieldName }</td>
						</c:when>
						<c:otherwise>
						<td >${article.b_fieldName }</td>
						</c:otherwise>
						</c:choose>
						<td>${article.nickName} </td>
						
						<%--답변을 구분해야 한다 --%>
						<td align='left' width='30%'>
							<%--왼쪽 들여쓰기--%> <span style='padding-right: 30px'></span> <%-- level값이 1보다 큰경우 자식글이므로 
								 부모글 밑에 공백으로 들여쓰기해서 자식글인걸 티내자 
								 분기를 한번 더 타자--%> <c:choose>
								<c:when test="${article.level >1 }">
									<%-- 부모글 기준으로 레벨 값 만큼 들여쓰기하자 --%>
									<c:forEach begin="1" end="${article.level }" step="1">
										<span style='padding-left: 25px'></span>
									</c:forEach>
									<%-- 제목앞에 답글인걸 표시하는 표시 하나추가 --%>
									<span>[답변]</span>
									<%-- 마지막으로 제목을 누르면 상세 출력 페이지 이동 a태그하나 --%>
									<a href="${contextPath}/board/viewArticle.do?b_articleNo=${article.b_articleNo}">
										${article.b_title} </a>
								</c:when>
								<c:when test="${article.b_fieldName eq '비밀글' }">
									<a id = "link_a" href = "${contextPath}/board/password.do?b_articleNo=${article.b_articleNo}">
										${article.b_title} </a>
								</c:when>
								<c:otherwise>
									<a href="${contextPath}/board/viewArticle.do?b_articleNo=${article.b_articleNo}">
										${article.b_title} </a>
								</c:otherwise>
							</c:choose>
						</td>
						<td>${article.b_writeDate}</td>
						<td>${article.b_view}</td>
						<%-- <td>${article.userkey}</td> --%>
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>
	</table>
</div>
</div>

<div id = "search">
<%-- 글쓰기 영역 --%>
	<form method="get" action="${contextPath }/board/articleForm.do"
		name="frmName" id="frmId">
		<c:choose>
			<c:when test="${empty userInfo.userKey }">
				<input type="text" name="search_bar" id = "search_bar" data-id ="hidden" class = "form-control"  /> 
				<input type="button" id="search_btn" class="btn btn-primary "  name="search" value=검색하기 data-id ="hidden" /> 
				<input type="submit" value=글쓰러가기  class="btn btn-primary" id = "write_btn" data-id ="hidden" />
			</c:when>
				
		<c:when test="${!empty userInfo.userKey }">
		<div class ="div_wrap">
			<div id = "field_contain" class = "div_wrap2">
				<select name="field" class = "btn-outline-info" style = "border-radius : 4px;  height : 34px; ">
					<option value="1">제목</option>
					<option value="2">내용</option>
					<option value="3">글 작성자</option>
					<option value="4">전체</option>
				</select> 
				<input type="text" name="search_bar" id = "search_bar" class = "form-control" style = "width : 300px; padding:0 10px; margin : 0 2px;" /> 
				<input type="button" id="search_btn" class="btn btn-outline-light" name="search" value=검색하기 style = "margin-bottom : 6px; border : 1px solid #99A799; color : #99A799;"> 
			</div>
		</div>
			<input type="submit" value=글쓰러가기  class="btn btn-outline-light" id = "write_btn" style = "border : 1px solid #99A799; color : #99A799;">
		</c:when>
		</c:choose>	
	</form>
</div>	
<%-- <jsp:include page="/fix/footer.jsp"/> --%>
</body>
</html>