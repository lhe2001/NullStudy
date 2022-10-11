<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>uri : ${uri }</h1>
	<form action="/pro26/test/login.do" method="get">
	userID : <input type="text" name="userID"><br>
	userName : <input type="text" name="userName"><br>
	<input type="submit" value="get">
	</form>
	
	<form action="/pro26/test/login.do" method="post">
	userID : <input type="text" name="userID"><br>
	userName : <input type="text" name="userName"><br>
	<input type="submit" value="post">
	</form>
	
	<form action="/pro26/test/login2.do" method="post">
	userID : <input type="text" name="userID"><br>
	userName : <input type="text" name="userName"><br>
	<input type="submit" value="post">
	</form>
	
	<form action="/pro26/test/login2.do" method="post">
	userID : <input type="text" name="userID"><br>
	userName : <input type="text" name="userName"><br>
	email : <input type="text" name="email"><br>
	<input type="submit" value="post">
	</form>
	
	<form action="/pro26/test/login3.do" method="post">
	userID : <input type="text" name="userID"><br>
	userName : <input type="text" name="userName"><br>
	email : <input type="text" name="email"><br>
	<input type="submit" value="login3">
	</form>
	
	<form action="/pro26/test/login5.do" method="post">
	userID : <input type="text" name="userID"><br>
	userName : <input type="text" name="userName"><br>
	email : <input type="text" name="email"><br>
	<input type="submit" value="login5">
	</form>
</body>
</html>