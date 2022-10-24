<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
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
<script id = "test1234" src="https://code.jquery.com/jquery-3.6.1.js"></script>
<script type="text/javascript">
// 		window.addEventListener("load", teamInfoOnload);

// 	      function teamInfoOnload(){
// 	    	
			
	  		
// 	  		function select(){
// 	  		let sel = document.querySelector("#select");
// 				sel.addEventListener("change", function() {
// 			let obj = document.querySelector("#frmSelect");
// 				obj.action = "${contextPath}/board/selectField.do";
// 				obj.method = "post"
// 				obj.submit();
// 				})
// 			}
// 			function link (){
// 			let link = document.querySelector("#link_a");
// 				link.addEventListener("click", function() {
// 			let obj = document.querySelector("#frmSelect");
// 				obj.action = "${contextPath}/board/password.do";
// 				obj.method = "post"
// 				obj.submit();
// 				})
// 			}
		$(function(){
				let obj = document.querySelector("#frmId");
		  		let btn = document.querySelector("#search_btn");
		  		btn.addEventListener("click", function() {
		  			console.log('this :: ', this);
		  			search();
		  		});
		  		let pageAmount = $("#pageAmount").val();
		  		console.log("pageAmount ==" , pageAmount);
		  		setPageEvent(pageAmount);
		})
   		  		
	      	function search(index, amount){
// 	    		$("#search_btn").off("click").on("click", function(){
					console.log('index :: ', index);
	    			let field = $("#field").val();
	    			let search_bar = $("#search_bar").val();
	    			let sel = $("#select").val();
	    			let pageAmount2 = $("#pageAmount").val();
	    			console.log("pageAmount ::", pageAmount2);
					if(sel == '글머리'){
						alert('글머리 이외의 것을 선택해 주세요!')
						location.href="${contextPath}/board/listArticles.do"
					}
	    			console.log("sel" , sel);
			  		let sel2= document.querySelector("#select").value;
			  		console.log('sel2', sel2);
	    			let info = {
	    				b_field : field,
	    				search_bar : search_bar,
	    				pageNum : index,
	    				amount : pageAmount2,
	    				b_field2 : sel
	    				
	    			}
	    			 //아작스
	    			$.ajax({
	    				url: "/project/board/searchArticle.do",
	    				type: "post",
	    				contentType : "application/json",
	    				data: JSON.stringify(info),
	    				success: function(data){
	    					
	    					console.log("map :", data);
	    					console.log("pageDTO",data.pageDTO.startPage);
	    					// 게시글
	    					$("#list_tbody").empty();
    						
	    					let html = "";
	    					
	    					if(data.length < 0){
    								
	    						html +='<h1 style ="text-align : center; margin-left : 20px; margin-top : 20px; color : #1C6758">' + '등록된 글이 없어요' +'</h1>';
    						
	    					} else{
	    						
    							for(let i = 0; i< data.searchList.length; i++){
//     								console.log(data.searchList.length);
//     								console.log(data.searchList[i].b_fieldName);
    								html +=	'<tr>';
    								html += '<td>' + (i+1) + '</td>';
									if(data.searchList[i].b_fieldName == '비밀글'){
										html += '<td style = "color : tomato;">' + data.searchList[i].b_fieldName + '</td>';
									} else {
										html += '<td >' + data.searchList[i].b_fieldName + '</td>';
									}
										html += '<td>' + data.searchList[i].nickName + '</td>';
									
									<%--답변을 구분해야 한다 --%>
									html += '<td align="left" width="30%">';
									<%--왼쪽 들여쓰기--%> 
									html += '<span style="padding-right: 30px">'+'</span>'; 
									<%-- level값이 1보다 큰경우 자식글이므로 
									 부모글 밑에 공백으로 들여쓰기해서 자식글인걸 티내자 
									 분기를 한번 더 타자--%> 
									
									if(data.searchList[i].level > 1){
										for(let i = 1; i< data.searchList[i].level; i++){
											html += '<span style="padding-left: 25px">'+'</span>';
										}
										<%-- 제목앞에 답글인걸 표시하는 표시 하나추가 --%>
										html += '<span>'+'[답변]'+'</span>';
										<%-- 마지막으로 제목을 누르면 상세 출력 페이지 이동 a태그하나 --%>
										html += '<a href="${contextPath}/board/viewArticle.do?b_articleNo=' +data.searchList[i].b_articleNo+ '">'+
										data.searchList[i].b_title + '</a>';
									}
									
									if(data.searchList[i].b_fieldName == '비밀글'){
										html +=
											'<a id = "link_a" href = "${contextPath}/board/password.do?b_articleNo=' + data.searchList[i].b_articleNo+ '">'+
											data.searchList[i].b_title+ '</a>';
									} else {
										html += '<a href="${contextPath}/board/viewArticle.do?b_articleNo=' +data.searchList[i].b_articleNo+ '">'+
										data.searchList[i].b_title + '</a>';
									}
										html += '</td>'
										html += '<td>' + data.searchList[i].b_writeDate + '</td>';
										html += '<td>' + data.searchList[i].b_view + '</td>';
										html += '</tr>';
    							
    							}
    							$("#list_tbody").append(html);
    						}
	    					
// 	    					console.log(data.searchList[1].b_field);
	    					
	    					//페이징
	    					$("#paging").empty();
	    					
	    					let html2 = "";
	    					if(data.pageDTO.prev){
	    					html2 += '<span>' + '<a href="${contextPath }/board/listArticles.do?pageNum='+ (data.pageDTO.startPage - 1) +'&amount=' + data.pageDTO.amount + '" class="p_btn" >' + '이전' + '</a></span>'
	    					}
	    					
	    					for(let i = data.pageDTO.startPage; i< data.pageDTO.endPage; i++){
	    					html2 += '<input type="button" value="'+i+'" class="p_btn">'
	    					}
	    					
	    					if(data.pageDTO.prev){
	    					html2 += '<span>' + '<a href="${contextPath }/board/listArticles.do?pageNum='+ (data.pageDTO.endpage+1) +'&amount=' + data.pageDTO.amount + '" class="p_btn" >' + '다음' + '</a></span>'
	    					}
	    					$("#paging").append(html2);
	    					
	    					setPageEvent(data.pageDTO.amount);
	    				},
	    				
	    				error:function(){
	    					alert("에러발생!!")
	    				}
	    			});
// 	    		})
	    	}
		
   		  		function setPageEvent(amount) {
	   		  		let pageBtn = document.querySelectorAll(".p_btn");
	   		  		for(var i = 0; i < pageBtn.length; i++) {
	   		  			pageBtn[i].addEventListener("click", function() {
		   		  			console.log('this :: ', this);
		   		  			search($(this).val(), amount);
		   		  		})
	   		  		}
   		  		}
   		  		
	    		function setSelect(){
		  			let sel = document.querySelector("#select");
		  				console.log("sel",sel);
		  				sel.addEventListener("change",function(){
		  					console.log(this.value)
		  				})
		  				
	    		}
	    		
				function link (){
				let link = document.querySelector("#link_a");
					link.addEventListener("click", function() {
				let obj = document.querySelector("#frmSelect");
					obj.action = "${contextPath}/board/password.do";
					obj.method = "post"
					obj.submit();
					})
				}
				
</script>
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
	
	<div id = "page_select">
		<select id = "pageAmount" class="form-select btn-outline-info" 
		style = "border-radius : 4px; margin-top : 7px; float: right; margin-right : 20px; " aria-label="Default select example">
			<option value="10" selected>10개씩 보기</option>
			<option value="20" >20개씩 보기</option>
			<option value="50" >50개씩 보기</option>
			<option value="100" >100개씩 보기</option>
		</select>
	</div>
	
	<div id = "article_flex">
	<form id="frmSelect" name="frmSelectName" method = "post"> 
		<select id="select" name="list_sel" class="form-select btn-outline-info" 
		style = "border-radius : 4px; margin-top : 7px; " aria-label="Default select example" >
			<option value = "-1" selected>글머리</option> 
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
		<thead>
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
		</thead>
		<%--여기서 분기나 탑시다--%>
		<tbody id = "list_tbody">
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
								 분기를 한번 더 타자--%> 
								 <c:choose>
								<c:when test="${article.level >1 }">
									<%-- 부모글 기준으로 레벨 값 만큼 들여쓰기하자 --%>
									<c:forEach begin="1" end="${article.level }" step="1">
										<span style='padding-left: 25px'></span>
									</c:forEach>
									<%-- 제목앞에 답글인걸 표시하는 표시 하나추가 --%>
									<span>[답변]</span>
									<%-- 마지막으로 제목을 누르면 상세 출력 페이지 이동 a태그하나 --%>
									<a href="${contextPath}/board/viewArticle.do?b_articleNo=${article.b_articleNo}">
										${article.b_title}(${article.comment_cnt }) </a>
								</c:when>
								<c:when test="${article.b_fieldName eq '비밀글' }">
									<a id = "link_a" href = "${contextPath}/board/password.do?b_articleNo=${article.b_articleNo}">
										${article.b_title} (${article.comment_cnt }) </a>
								</c:when>
								<c:otherwise>
									<a href="${contextPath}/board/viewArticle.do?b_articleNo=${article.b_articleNo}">
										${article.b_title} (${article.comment_cnt }) </a>
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
		</tbody>
	</table>
			<div id="paging"> 
				<!--이전버튼 활성화 여부 -->
				 <c:if test="${pageDTO.prev }">
		         	<span><a href="${contextPath }/board/listArticles.do?pageNum=${pageDTO.startPage - 1 }&amount=${pageDTO.amount}" class="btn" >이전</a></span>
				</c:if>
				<%-- 페이지번호 처리 --%>
		   		<c:forEach var="num" begin="${pageDTO.startPage }" end="${pageDTO.endPage }">
		    		<a href="${contextPath }/board/listArticles.do?pageNum=${num }&amount=${pageDTO.amount}" class="btn">${num }</a>
		   		</c:forEach>
		   		<!-- 다음버튼 활성화 여부 -->
		             <c:if test="${pageDTO.next }">
		            <span><a href="${contextPath }/board/listArticles.do?pageNum=${pageDTO.endPage + 1 }&amount=${pageDTO.amount}" class="btn">다음</a></span>
		            </c:if>
       		</div>
		</div>
</div>
<div id = "search_">
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
				<select id="field" name="field" class = "btn-outline-info" style = "border-radius : 4px;  height : 34px; ">
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
			<input type="button" id="list_btn" class="btn btn-outline-light" value="목록으로"  onclick= 'location.href="${contextPath}/board/listArticles.do"' style = "float : left; margin-left : 10px; border : 1px solid #99A799; color : #99A799;"/>
		</c:when>
		</c:choose>	
	</form>
</div>	
<%-- <jsp:include page="/fix/footer.jsp"/> --%>
</body>
