
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
   	import="java.util.*"
    %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.spring.teampro.search.dto.*" %>

<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>게시판 찾기 창</title>
	
<link href="/css/searchBoard.css" rel="stylesheet" >
	
	</head>
	<body>
	<%
	String selectValue = request.getParameter("selectValue");
// 	선택된 select option value 을 잡고
	
	if (selectValue == null){
	%>
		<div class="sbwrap">
            <h3 style="color: #69c7b5" class="searchTitle">자유게시판</h3>
                <div class="searchLine">
					<form name="frmSearch" method="post" action="/teampro/Controller">
                        <select name = "selectValue" id="selectValue">
                            <option value = "all" selected>전체</option>
                            <option value = "b_title">제목</option>
                            <option value = "b_content">내용</option>
                            <option value = "nickName">작성자</option>
                        </select>
                        <input type = "text" name = "q" class="searchBar" required>
                        <button type="submit" class="searchButton"><i class="fa-solid fa-magnifying-glass"></i></button>
					</form>
              	</div>                
		</div>
	<%
	} else {
	%>
		<div class="sbwrap">
			<form name="frmListAfter" method="post" action="/teampro/Controller">
                    <h3 style="color: #69c7b5" class="searchTitle">자유게시판</h3>
                    <div class="searchLine">
                        <select name = "selectValue" id="selectValue">
                            <option value = boardSearch <%=selectValue.equals("all")?"selected":""%> >전체</option>
                            <option value = b_title <%=selectValue.equals("b_title")?"selected":""%> >제목</option>
                            <option value = b_content <%=selectValue.equals("b_content")?"selected":""%> >내용</option>
                            <option value = nickName <%=selectValue.equals("nickName")?"selected":""%> >작성자</option>
                            <!-- 위에서 선택된 select option value 을 잡은 것을 equals 선택된 것을 selected 해준다 선택되지 않았으면 안해준다 -->
                        </select>
                    <%
                    String keyword = request.getParameter("q");
                    if (keyword == null) { 
                    	%>
                        <input type = "text" name = "q" placeholder="검색하기" class="searchBar" required>
                        <button type="submit" class="searchButton"><i class="fa-solid fa-magnifying-glass"></i></button>   
                        <%
                    } else {
                    	%>
                    	<input type = "text" name = "q" value="<%=keyword %>" class="searchBar" required>
                        <button type="submit" class="searchButton"><i class="fa-solid fa-magnifying-glass"></i></button> 
                    	<%
                    }
                    %>               
                <input type="hidden" name="searchBoardHidden" value="searchBoardHidden">  
                </div>    	
			</form>
		</div>	
	<%
 	}
	%>
		<%
		String search = request.getParameter("q");
		String size_0 = (String)request.getAttribute("size_0");
		if (size_0 != null) {
			%>
			<h5 class="searchW">해당 ' <%=search %> '에 대한 게시글이 없습니다</h5>
			<%
		} 
		%>
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
					<%
											List<BoardDTO> list = (List<BoardDTO>)request.getAttribute("list");
														System.out.println("searchBoard.jsp (게시글 List<BoardVO> list) : "+ list);
															int j = 1;
															for (int i=0; i < list.size(); i++) {
																BoardDTO vo = list.get(i);
											%>
							<tr class="searchTr">
								<td><%= j %></td>
								<td class="searchTd"><a href="/teampro/board/viewArticle.do?b_articleNo=<%=vo.getB_articleNo() %>">
										<%= vo.getB_title() %></a></td>
								<td class="searchTd"><%= vo.getNickName() %></td>
								<td><%= vo.getB_writedate() %></td>
							</tr>
							<%
							j 	 += 1;
						}
						%>	
				</tbody>
			</table>
			
			<%
				Map map = (Map)request.getAttribute("map"); // Controller에서 보낸 map을 받는다
				int pageNum = (int)map.get("pageNum"); // 현재 페이지 넘버
				int countPerPage = (int)map.get("countPerPage"); // 보여줄 페이지 개수
				int count = (int)map.get("count");	// 페이지들 총 데이터 개수 [ 예) 게시판의 게시글 총 개수 ]
				int lastPage = (int)Math.ceil(((double)count / countPerPage)); // 마지막 페이지 번호 구하기
				// Math.ceil = 올림;
				int section = 5; // 한 페이지에 보이는 a링크 페이지 수
				int sec_position = (int)Math.ceil(((double)pageNum / section));
				int firstNo = ((sec_position-1) * section) + 1; 
				// 현재 페이지에 따라 보여줄 첫번째 a링크
				int lastNo = firstNo + section - 1;
				// 현재 페이지에 따라 보여줄 마지막 a링크
				if(firstNo < 1){ firstNo = 1; }
				if(lastNo > lastPage){ lastNo = lastPage; }
				// 방어 코딩 //
			%>
			
			<c:if test="<%=firstNo != 1%>">
				<a href="/pro17/page?pageNum=<%=firstNo-1 %>&countPerPage=1" style="color:red;font-weight:bold;">[이전]</a>&nbsp;
			</c:if>
			
			<c:forEach var="i" begin="<%=firstNo %>" end="<%=lastNo %>">
				<c:if test="${ map.pageNum eq i }">
					<a href="/pro17/page?pageNum=${i }&countPerPage=1" style="color:red;font-weight:bold;">[${i }]</a>&nbsp;
				</c:if>
				<c:if test="${ not (map.pageNum eq i) }">
					<a href="/pro17/page?pageNum=${i }&countPerPage=1">[${i }]</a>&nbsp;
				</c:if>
			</c:forEach>
			
			<c:if test="<%=lastNo != lastPage%>">
				<a href="/pro17/page?pageNum=<%=lastNo+1 %>&countPerPage=1" style="color:red;font-weight:bold;">[다음]</a>&nbsp;
			</c:if>
			
		</div>
	</body>
</html>

