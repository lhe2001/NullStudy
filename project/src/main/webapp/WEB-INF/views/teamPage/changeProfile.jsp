<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	div{
		text-align:center;
		background-color:#f4c4d685;
		padding-top:20px;
	}
	form input[type="submit"] {
	
		margin: 30px 0;
		border: 1px solid grey;
		background-color:#f4c4d6;
		width: 300px;
		height:25px;
		cursor:pointer;
		border-radius: 5px;
	}
	form input[type="file"] {
		margin: 30px 0;
		border: 1px solid grey;
		width: 300px;
		height:25px;
		cursor:pointer;
		border-radius: 5px;
		text-align:center;
	}
	
	::-webkit-file-upload-button { 
    	cursor:pointer; 
    	background-color:#f4c4d6;
    	border:none;
    	height:25px;
	}


</style>
</head>
<body>
	<div>
		<form 
		    onSubmit="opener.location.reload()"
			action="/project/team/upload.do" 
			method="post" 
			enctype="multipart/form-data"
			accept-charset="utf-8">
			프로필 사진 수정<br> <input type="file" name="file1"><br>
			<input type="submit" value="업로드">
		</form>
	</div>
</body>
</html>