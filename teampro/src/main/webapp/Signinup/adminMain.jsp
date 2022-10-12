<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="signupin.*"
    import="java.util.*"
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>

	.admin_page{
		text-align: center;
	}
	
	.admin_search{
		width: 400px;
		height: 20px;
	}
	table{
		margin: auto;
	}
	table, th, td{
	border: solid 1px;;
	}

	th{
	background-color: grey;
	}
</style>
<body>

<%--
관리자가 해당페이지로 들어온것인지 아닌지를 확인하기 위해서
로그인되면서 세션에 바인딩해준 userInfo를 활용
userInfo.getId()가 admin일때만 회원 리스트 보이도록
세션에 userInfo가 없거나 다른 아이디의 유저가 접속할 경우
메인으로 보낸
 --%>
 <section class="admin_page">
<%
	SignUpInDTO userInfo = new SignUpInDTO();
	userInfo = (SignUpInDTO)session.getAttribute("userInfo");
	
	if(userInfo==null || !("admin".equals(userInfo.getId())) ){
		%>
		<script>
			location.href='/teampro/Main.jsp'
		</script>
		<%
	} else if("admin".equals(userInfo.getId())){
		%>
		<h1>회원정보관리 페이지</h1>
		<form method="post" action="/teampro/searchMember" encType="utf-8">
		<input class="admin_search" type="text" name="keyWord">
		<input type="submit" value="찾기"><br><br>
		* 검색어 없이 찾기 누를 경우 모든회원 조회<br>
		* 검색범위: 이름, 아이디<br><br>
		</form>
	<table>
		<thead>
			<tr>
				<th>USERKEY</th>
				<th>ID</th>
				<th>PW</th>
				<th>NAME</th>
				<th>SEX</th>
				<th>NICKNAME</th>
				<th>EMAIL</th>
				<th>JOINDATE</th>
				<th>INTRO</th>
				<th>LASTTIME</th>
				<th>PHOTO</th>
				<th>수정</th>
				<th>삭제</th>
			</tr>
		</thead>
		<tbody>
		<%
			String key=request.getParameter("keyword");
		
			List searchList = new ArrayList();
			searchList = (List)request.getAttribute("searchList");
			
			System.out.println(key==null);
			System.out.println(key);
			
			if(searchList == null){
				SignUpInDAO dao = new SignUpInDAO();
				List listMembers = dao.listMembers();
				System.out.println("1실행");
				for(int i=0;i<listMembers.size();i++){
					SignUpInDTO dto = new SignUpInDTO(); 
					dto = (SignUpInDTO)listMembers.get(i);
					%>
					<tr>
					<td><%= dto.getUserKey() %></td>
					<td><%= dto.getId()%></td>
					<td><%= dto.getPw()%></td>
					<td><%= dto.getName()%></td>
					<td><%= dto.getSex()%></td>
					<td><%= dto.getNickName()%></td>
					<td><%= dto.getEmail()%></td>
					<td><%= dto.getJoinDate()%></td>
					<td><%= dto.getIntro()%></td>
					<td><%= dto.getLastTime()%></td>
					<td><%= dto.getPhoto()%></td>
					<td> <a href='/teampro/Signinup/modForm.jsp?id=<%= dto.getId()%>'> 수정</a></td>
					<td> <a href='/teampro/delmember?id=<%= dto.getId()%>'> 삭제 </a></td>
					</tr>
					<%
				}
				%>
				<%
			} else if(searchList.size()==0){
				System.out.println("2실행");
				%>
				<tr>
				<td colspan="13">검색결과 없음</td>
				</tr>
				<%
			}
			
			else{
				System.out.println("3실행");
				for(int i=0;i<searchList.size();i++){
					SignUpInDTO dto = new SignUpInDTO(); 
					dto = (SignUpInDTO)searchList.get(i);
				%>
				<tr>
					<td><%= dto.getUserKey() %></td>
					<td><%= dto.getId()%></td>
					<td><%= dto.getPw()%></td>
					<td><%= dto.getName()%></td>
					<td><%= dto.getSex()%></td>
					<td><%= dto.getNickName()%></td>
					<td><%= dto.getEmail()%></td>
					<td><%= dto.getJoinDate()%></td>
					<td><%= dto.getIntro()%></td>
					<td><%= dto.getLastTime()%></td>
					<td><%= dto.getPhoto()%></td>
					<td> <a href='/teampro/Signinup/modForm.jsp?id=<%= dto.getId()%>'> 수정</a></td>
					<td> <a href='/teampro/delmember?id=<%= dto.getId()%>'> 삭제 </a></td>
				</tr>
				<%
				}
			}
		%>
		</tbody>
		</table><br><br>
	<%
	}
%>
	<form action="/teampro/signout" method="post">
		<input type="submit" value="관리자 로그아웃"></input>
	</form>
	
</section>
</body>
</html>