<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/project/resources/css/team.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script>
function updateDday(t_key){
	const con = confirm('수정하시겠습니까?');
	if(con == true){
		let newDay = $(".newDay").val();
    	let info = { 
    			t_key : t_key,
    			t_day : newDay
    	};
    	
    	$.ajax({
			url: "/project/teamRest/updatedDay.do",
			type: "post",
			contentType: "application/json",
			data: JSON.stringify(info),
			success: function(data){
				location.reload();
				opener.location.reload();
			},
			error:function(){
				alert("에러발생!!")
			}
		});
	}
}
</script>
</head>
<body>
    <div class="popDday">
        <div>현재</div>
        <input type="date" name="dDay" value="${dDay }" disabled>
        <form name="frmDday">
            <div>수정</div>
            <input class="newDay" type="date" name="dDay">
            <input type="button" value="수정하기" onClick="updateDday(${t_key })">
        </form>
    </div>
</body>
</html>