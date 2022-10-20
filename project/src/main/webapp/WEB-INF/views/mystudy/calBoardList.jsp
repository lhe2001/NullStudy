<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*"
    import="com.spring.teampro.mystudy.dto.*"
    %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일정 목록 보기</title>

<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
<script>
// window.addEventListener("load", calOnload);

// function calOnload(){
// 	allSel(val);
   
// }

//전체선택 자동체크와해지
//onclick="smallSel()"
function smallSel(){
	let chks = document.getElementsByName("seq");
	for(let i=0; i<chks.length; i++){
		console.log("small click");
			let checkedObjs = document.querySelectorAll("input[name=seq]:checked");
			if(checkedObjs.length == chks.length){
				document.getElementsByName("all")[0].checked = true; 
			} else{
				document.getElementsByName("all")[0].checked = false; 
			}
		}
}

//체크된 것 value 변환
function allSel(e) {
	console.log("all click");
	let chks = document.getElementsByName("seq");
	if(e){		
		for(let i=0; i<chks.length;i++){
			chks[i].checked=e.checked;
		}
	}
}

$(function(){	
//form태그에서 submit이벤트가 발생하면 함수실행
	$("form").submit(function(){
		let bool = true;
		let count = $(this).find("input[name=seq]:checked").length;
		if(count==0){
			alert("최소하나 이상 체크하세요!!");
			bool = false;
		} else if(confirm("정말 삭제할 건가요?")){
			bool = true;
		} else{
			bool = false;
		}
		return bool;
	})
})


</script>
</head>
<body>
<form action="calMuldel.do" method="post">
<input type="hidden" name="year" value="${param.year }"/>
<input type="hidden" name="month" value="${param.month }"/>
<input type="hidden" name="date" value="${param.date }"/>


<table border="1">
	<col width="50px">
	<col width="50px">
	<col width="300px">
	<col width="250px">
	<col width="250px">
	<tr>
		<th><input type="checkbox" name="all" onclick="allSel(this)"/></th>
		<th>번호</th>
		<th>제목</th>
		<th>일정요일</th>
		<th>작성일</th>
	</tr>
	<c:if test="${list.size() == 0 }" >
		<tr>
			<td colspan="5">목록이 없습니다.</td>
		</tr>
	</c:if>
	<c:forEach var="calList" items="${list }">
		<tr>
		<td><input type="checkbox" name="seq" value="${calList.m_schedule_key }" onclick="smallSel()" /></td>
		<td>${calList.m_schedule_key }</td>
		<td><a href="calDetail.do?m_schedule_key=${calList.m_schedule_key }"> ${calList.m_schedule_title } </a></td>
		<td>${calList.m_schedule_date }</td>
		<td>${calList.m_schedule_write }</td>
		</tr>
	</c:forEach>
	
	<tr>
		<td colspan="5"><a href="calendar.do?year=${sessionScope.ymd.year }&month=${sessionScope.ymd.month}">달력 돌아가기</a>
		<button>삭제</button>
	</tr>
</table>
</form>
</body>
</html>