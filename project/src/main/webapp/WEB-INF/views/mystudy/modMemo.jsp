<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<c:set var="path" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link href="${path}/resources/css/allMystudy.css" rel="stylesheet"/> 
    <script src="https://kit.fontawesome.com/f5483c894c.js" crossorigin="anonymous"></script>
</head>
<body>
    <div class="memopopup-box show">
        <div class="memopopup">
            <div class="memocontent">
                <header class="memoheader">
                    <p>Modify Note</p>
                    <p id="mod_clsicon" onclick="history.back();">cancel</p>
                </header>
                <form method="get" action="${pageContext.request.contextPath}/my/updateMemo">
                    <div class="row title">
                        <label>Title</label>
                        <input type="text" name="memo_title" value="${oneDTO.memo_title }">
                    </div>
                    <div class="row description">
                        <label>Description</label>
                        <textarea name="memo_desc">${oneDTO.memo_desc }</textarea>
                    </div>
                    memo_idx<input type="text" name="memo_idx" >
                    <button class="Modmemo_Btn">Modify Note</button>                    
                </form>
            </div>
        </div>
    </div>

	<script src="${path}/resources/js/memo.js"></script>
</body>
</html>