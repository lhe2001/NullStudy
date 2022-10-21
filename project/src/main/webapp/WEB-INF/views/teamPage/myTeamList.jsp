<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/project/resources/css/team.css" rel="stylesheet">
<script>
function cancleRequest(tr_key){
	const con = confirm('요청을 취소하시겠습니까?');
	if(con == true){
		let info = { tr_key: tr_key };
		$.ajax({
			url: "/project/teamRest/cancleRequest.do",
			type: "post",
			contentType: "application/json",
			data: JSON.stringify(info),
			success: function(data){
				console.log(data)
				location.reload();
			},
			error:function(){
				alert("에러발생!!")
			}
		});
	}
}
function deleteRequest(tr_key){
	const con = confirm('삭제하시겠습니까?');
	if(con == true){
		let info = { tr_key: tr_key };
		$.ajax({
			url: "/project/teamRest/cancleRequest.do",
			type: "post",
			contentType: "application/json",
			data: JSON.stringify(info),
			success: function(data){
				console.log(data)
				location.reload();
			},
			error:function(){
				alert("에러발생!!")
			}
		});
	}
}
function goTeamPage(t_key){
	location.href='/project/team/teamDetail.do?t_key='+t_key;
}
</script>
</head>
<body>
	<div id="MyteamWrapper">
	 <h1>${userInfo.nickName} 님의 현재 Study 그룹 현황</h1>

	<div class="MyTeamTableWrap">
	 <table class="myteamList" >
        <thead>
            <tr>
                <td colspan="4">현재 활동중인 모임</td>
            </tr>
            <tr>
                <td>분야</td>
                <td>팀명</td>
                <td>팀원</td>
                <td>팀 가입 날짜</td>
            </tr>
        </thead>
 	 	<c:forEach var="myTeam" items="${myTeamList}" varStatus="loop">
	 	<tr onClick="goTeamPage(${myTeam.t_key})">
	 		<td>${myTeam.t_field2}</td>
	 		<td>${myTeam.t_name}</td>
            <td>${myTeam.t_number}</td>
            <td>${myTeam.tm_joinDate}</td>
	 	</tr>
 		</c:forEach>
	 </table>
	 </div>
     <div id="requestStatus">
        <table id="waiting">
            <thead>
                <tr>
                    <td colspan="3">현재 대기중인 요청</td>
                </tr>
                <tr>
                    <td>분야</td>
                    <td>팀명</td>
                    <td>대기</td>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="waiting" items="${requestMap.waitingList}" varStatus="loop">
                <tr>
                    <td>${waiting.t_field2}</td>
                    <td>${waiting.t_name}</td>
                    <td><button type="button" onClick="cancleRequest(${waiting.tr_key})">취소</button></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <table id="reject">
            <thead>
                <tr>
                    <td colspan="3">거절된 요청</td>
                </tr>
                <tr>
                    <td>분야</td>
                    <td>팀명</td>
                    <td>거절</td>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="reject" items="${requestMap.rejectList}" varStatus="loop">
                <tr>
                    <td>${reject.t_field2}</td>
                    <td>${reject.t_name}</td>
                    <td><button type="button" onClick="deleteRequest(${reject.tr_key})">삭제</button></td>
                </tr>
            	</c:forEach>
        </table>
        </tbody>
     </div>
	 </div>
</body>
</html>