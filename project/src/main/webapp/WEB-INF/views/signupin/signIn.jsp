<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>

<body>

	<div class="main_login">
		<section class="login_wrap">
			<div class="logo">
			</div>
			<br><br>
			<form name="frmSignIn" method="post" action="/project/signIn.do" encType="UTF-8">
			<div class="box_wrap">
				<input type="text" name="id" placeholder="아이디" class="inputs" value="id3"></input>
			</div>
			<div class="box_wrap">
				<input type="password" name="pw" placeholder="비밀번호" class="inputs" value="id3"></input>
			</div>
			<div class="btn_wrap">
				<input type="submit" class="login_btn" value="로그인"></input>
			</div>
			</form>
		</section>
	</div>
</body>
</html>


