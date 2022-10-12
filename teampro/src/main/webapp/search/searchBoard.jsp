
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="search.*" %>

<jsp:include page="/fix/header(logout).jsp"/>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>게시판 찾기 창</title>
	
<link href="/teampro/Front/searchBoard.css" rel="stylesheet" >
	
	</head>
	<body>
	<%
	String selectValue = request.getParameter("selectValue");
// 	선택된 select option value 을 잡고
	
	if (selectValue == null){
	%>
		<div class="sbwrap">
			<form name="frmSearch" method="post" action="/teampro/Controller">
                    <h3 style="color: #69c7b5" class="searchTitle">자유게시판</h3>
                    <div class="searchLine">
                        <select name = "selectValue" id="selectValue">
                            <option value = "all" selected>전체</option>
                            <option value = "b_title">제목</option>
                            <option value = "b_content">내용</option>
                            <option value = "nickName">작성자</option>
                        </select>
                        <input type = "text" name = "q" class="searchBar" required>
                        <button type="submit" class="searchButton"><i class="fa-solid fa-magnifying-glass"></i></button>
                    </div>                
			</form>
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
					List<BoardVO> list = (List<BoardVO>)request.getAttribute("list");
					System.out.println("searchBoard.jsp (게시글 List<BoardVO> list) : "+ list);
						int j = 1;
						for (int i=0; i < list.size(); i++) {
							BoardVO vo = list.get(i);
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
		</div>
	</body>
</html>

<jsp:include page="/fix/footer.jsp" />
