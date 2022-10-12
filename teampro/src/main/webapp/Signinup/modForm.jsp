<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="signupin.*"
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	String id = request.getParameter("id");
	SignUpInDTO userInfo = new SignUpInDTO(); 
	SignUpInDAO dao = new SignUpInDAO();
	userInfo = dao.userInfo(id);
	%>
	<form action="/teampro/modmember">
	<table>
		<tr>
			<td>ID</td>
			<td><input type="hidden" name="id" value="<%= userInfo.getId() %>"><%= userInfo.getId() %></td>
		</tr>
		<tr>
			<td>PW</td>
			<td><input type="text" name="pw"></td>
		</tr>
		<tr>
			<td>NAME</td>
			<td><%= userInfo.getName() %></td>
		</tr>
		<tr>
			<td>NICKNAME</td>
			<td><input type="text" name="nickName"></td>
		</tr>
		<tr>
			<td>SEX</td>
			<td>
				<select name="sex" class="s_inputs">
    				<option value="choice">선택</option>
    				<option value="man">남자</option>
    				<option value="woman">여자</option>
    			</select>
    		</td>
		</tr>
		<tr>
			<td>EMAIL</td>
			<td><input type="text" name="email"></td>
		</tr>
	</table>
	<input type="submit" value="수정하기">
	</form>
	<a href ="/teampro/Signinup/adminMain.jsp"><button>뒤로가기</button></a>

</body>
</html>