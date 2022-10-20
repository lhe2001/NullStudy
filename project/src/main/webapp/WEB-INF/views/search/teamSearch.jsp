<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
   	import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<body>

<div class="sbwrap">

	<form name="frmListAfter" method="get" action="${pageContext.request.contextPath}/teamSearch.do">
        <h3 style="color: #69c7b5" class="searchTitle">팀찾아보기</h3>
        <div class="searchLine">    
        										<!-- 이전 검색어 -->
            <input type="text" name="search" value="${search}" class="searchBar" required>   
            <button type="submit" class="searchButton"><i class="fa-solid fa-magnifying-glass"></i></button>      
        
        </div>    	
	</form>
	
</div>

<c:if test="${listNull == 1}">
	<h5 class="searchW">해당 ' ${search} '에 대한 팀이 없습니다</h5>
	<!-- 검색어에 대한 조회 데이터가 없을 때 출력 -->
</c:if>

<div class="searchTbWrap">

	<table class="searchTb">
		<thead>
			<tr class="searchTr">
					<th class="searchTh1">순번</th>
					<th class="searchTh2">팀 이름</th>
					<th class="searchTh3">팀장</th>
					<th class="searchTh4">개설일</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="dto" items="${list}" varStatus="status">
				<tr class="searchTr">
					<td>${status.count}</td>
					<td class="searchTd">${dto.t_name }</td>
					<td class="searchTd">${dto.nickName }</td>
					<td>${dto.t_create }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
</div>

</body>