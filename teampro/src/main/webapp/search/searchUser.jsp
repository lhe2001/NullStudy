
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="search.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>유저 찾기 창</title>

<link href="/teampro/Front/searchBoard.css" rel="stylesheet" >

</head>
<body>
<jsp:include page="/fix/header(logout).jsp"/>
	<div class="sbwrap">
		<form name="frmSearch" method="post" action="/teampro/Controller">
                    <h3 style="color: #69c7b5" class="searchTitle">유저찾아보기</h3>
                    <div class="searchLine">
		            <%
		            String keyword = request.getParameter("q");
		            if (keyword == null) { 
		            %>
		                <input type = "text" name = "q" placeholder="검색하기" required>
		                <button type="submit"><i class="fa-solid fa-magnifying-glass"></i></button>   
		            <%
		            } else {
		            %>
		            	<input type = "text" name = "q" class="searchBar" value="<%=keyword %>" required>
		                <button type="submit" class="searchButton"><i class="fa-solid fa-magnifying-glass"></i></button>  
		            <%
		            }
		            %>               
		    </fieldset>
		    <input type="hidden" name="searchUserHidden" value="searchUserHidden"> 	
		</form>
	</div>
	
	<%
	String search = request.getParameter("q");
	String size_0 = (String)request.getAttribute("size_0");
	if (size_0 != null) {
		%>
		<h5 class="searchW">해당 ' <%=search %> '에 대한 유저가 없습니다</h5>
		<!-- list 의 size가 0 일때 "해당 유저가 없습니다" 출력</h5> -->
		<%
	} 
	%>
	
	<div class="searchTbWrap">
		<table class="searchTb">
			<thead>
				<tr class="searchTr">
					<th class="searchTh1">순번</th>
					<th class="searchTh2">닉네임</th>
					<th class="searchTh3">이메일</th>
					<th class="searchTh4">가입일</th>
				</tr>
			</thead>							
			<tbody>						
				<%
				List<UserVO> list = (List<UserVO>)request.getAttribute("list2");
				System.out.println("searchUser.jsp (게시글 List<UserVO> list) : "+ list);
				int j = 1;
				for (int i=0; i < list.size(); i++) {	
					UserVO vo = list.get(i);
					%>
					<tr class="searchTr">
						<td><%= j %></td>
						<td class="searchTd"><%= vo.getNickName() %></td>
						<td class="searchTd"><%= vo.getEmail() %></td>
						<td><%= vo.getJoinDate() %></td>
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
