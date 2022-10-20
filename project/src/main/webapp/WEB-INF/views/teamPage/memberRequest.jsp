<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/project/resources/css/team.css" rel="stylesheet">
</head>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script>
 function requestAccept(userKey,t_key){
	const con = confirm('수락하시겠습니까?');
	if(con == true){
    	let info = { 
    			userKey : userKey,
    			t_key : t_key,
    	};
    	
    	$.ajax({
		url: "/project/teamRest/acceptMember.do",
		type: "post",
		contentType: "application/json",
		data: JSON.stringify(info),
		success: function(data){
			if(data > 0 ){
				alert('수락되었습니다.');
			}
			
			location.reload();
			opener.location.reload();
		},
		error:function(){
			alert("에러발생!!")
		}
		});
    }
 }
 function requestReject(userKey,t_key){
	const rej = confirm('거절하시겠습니까?');
	if(rej == true){
    	let info = { 
    			userKey : userKey,
    			t_key : t_key,
    	};
    	
    	$.ajax({
		url: "/project/teamRest/rejectMember.do?",
		type: "post",
		contentType: "application/json",
		data: JSON.stringify(info),
		success: function(data){
			if(data > 0 ){
				alert('거절되었습니다.');
			}
			
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
<body>
  <div id="memberRequestWrap">
        <div id="memberRequest">
        <c:if test="${empty requestList }">
        아직 아무도 가입신청을 하지 않았네요
        <div><img src="https://ifh.cc/g/ByGMO4.png"></div>
        </c:if>
        <c:forEach var="request" items="${requestList }">
           <div class="newRequest">
                ${request.nickName } : 안녕하세요 팀원 신청합니다.
                <button type="button" class="accept" onClick="requestAccept(${request.userKey },${request.t_key })">수락</button>
                <button type="button" class="reject" onClick="requestReject(${request.userKey },${request.t_key })">거절</button>
           </div>
         </c:forEach>
        </div>
    </div>
</body>
</html>