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
                <button type="button" class="accept" disabled style='cursor:not-allowed;'>수락</button>
                <button type="button" class="reject" disabled style='cursor:not-allowed;'>거절</button>
           </div>
         </c:forEach>
        </div>
    </div>
</body>
</html>