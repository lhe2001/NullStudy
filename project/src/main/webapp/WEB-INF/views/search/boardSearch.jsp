<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
   	import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<body>

<div class="sbwrap">

	<form name="frmListAfter" method="get" action="${pageContext.request.contextPath}/boardSearch.do">
        <h3 style="color: #69c7b5" class="searchTitle">자유게시판</h3>
        <div class="searchLine">
        	
        	<select name = "pagingValue" id="pagingValue">
            
                <option value="15" selected disabled>보기</option>
                <option value="10" 
                <c:if test="${pagingValue == '10'}">selected</c:if>
                >10개</option>
                <option value="15"
                <c:if test="${pagingValue == '15'}">selected</c:if>
                >15개</option>
                <option value="30"
                <c:if test="${pagingValue == '30'}">selected</c:if>
                >30개</option>
                <option value="50"
                <c:if test="${pagingValue == '50'}">selected</c:if>
                >50개</option>
                
            </select>
        	
            <select name = "selectValue" id="selectValue">
            
                <option value = all
                <c:if test="${selectValue == 'all'}">selected</c:if>
                >전체</option>         <!-- 옵션 선택 유지 -->
                
                <option value = b_title 
              	<c:if test="${selectValue == 'b_title'}">selected</c:if>
                >제목</option>
                
                <option value = b_content
                <c:if test="${selectValue == 'b_content'}">selected</c:if>
                >내용</option>
                
                <option value = nickName 
				<c:if test="${selectValue == 'nickName'}">selected</c:if>
                >작성자</option>
                
            </select>
            									<!-- 이전 검색어들을 다음 -->
            <input type="text" name="search" value="${search}" class="searchBar" required>
            <button type="submit" class="searchButton"><i class="fa-solid fa-magnifying-glass"></i></button>      
        
        </div>    	
	</form>
	
</div>

<c:if test="${list.size() == 0}">
	<h5 class="searchW block">해당 ' ${search} '에 대한 게시글이 없습니다</h5>
	<!-- 검색어에 대한 조회 데이터가 없을 때 출력 -->
</c:if>
	
<div class="searchTbWrap">
	<table class="searchTb">
		<thead>
			<tr class="searchTr">
				<th class="searchTh1">순번</th>
				<th class="searchTh2">제목</th>
				<th class="searchTh3">작성자</th>
				<th class="searchTh4">작성시간</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${list!=null}">
				<c:forEach var="dto" items="${list}" varStatus="status">
					<tr class="searchTr">
						<td>${status.count}</td>
						<td class="searchTd">
						<a href="${pageContext.request.contextPath}/board/viewArticle.do?b_articleNo=${dto.b_articleNo }">${dto.b_title }</a>
						</td>
						<td class="searchTd">${dto.nickName }</td>
						<td>${dto.b_writedate }</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${list.size() == 0}">
				<tr class="listNullTr searchTr">
					<td colspan="4">[ 조회된 내용이 없습니다 ]</td>
				<tr>
			</c:if>
		</tbody>
	</table>
</div>

<c:if test="${dto.lastPage != 1}">
	<div class="pagingDiv">
		<c:set var="dto" value="${dto}"/>
		<!-- paging -->
		<c:if test="${dto.firstNo gt 5}">
			<a href="/project/boardSearch.do?pageNum=${dto.firstNo-1}&search=${search}&selectValue=${selectValue}" font-weight:bold; class="pagingA"> << </a>&nbsp;
		</c:if>
		
		<c:if test="${dto.firstNo ne 1}">
			<a href="/project/boardSearch.do?pageNum=${dto.pageNum-1}&search=${search}&selectValue=${selectValue}" font-weight:bold; class="pagingA"> < </a>
		</c:if>
	
		<c:forEach var="i" begin="${dto.firstNo}" end="${dto.lastNo}">
			<c:if test="${dto.pageNum eq i }">
				<a href="/project/boardSearch.do?pageNum=${i }&search=${search}&selectValue=${selectValue}" style="color:red;font-weight:bold;">${i}</a>&nbsp;
			</c:if>
			<c:if test="${ not (dto.pageNum eq i) }">
				<a href="/project/boardSearch.do?pageNum=${i }&search=${search}&selectValue=${selectValue}">${i }</a>&nbsp;
			</c:if>
		</c:forEach>
	
		<c:if test="${dto.lastNo ne dto.lastPage}">
			<a href="/project/boardSearch.do?pageNum=${dto.pageNum+1}&search=${search}&selectValue=${selectValue}" font-weight:bold; class="pagingA"> > </a>&nbsp;
		</c:if>
		<c:if test="${dto.lastNo ne dto.lastPage}">
			<a href="/project/boardSearch.do?pageNum=${dto.lastNo+1}&search=${search}&selectValue=${selectValue}" font-weight:bold; class="pagingA"> >> </a>
		</c:if>
		
	</div>
</c:if>

</body>