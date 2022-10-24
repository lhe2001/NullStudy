<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  

<title>메모수정페이지</title>
	<link href="/project/resources/css/memo.css" rel="stylesheet"/> 
    <script src="https://kit.fontawesome.com/f5483c894c.js" crossorigin="anonymous"></script>
<body>
    <div class="memopopup-box show">
        <div class="memopopup">
            <div class="memocontent">
                <header class="memoheader">
                    <p>Modify Note</p>
                    <p id="mod_clsicon" onclick="history.back();">cancel</p>
                </header>
                <form method="get" action="updateMemo.do">
                    <div class="row title">
                        <label>Title</label>
                        <input type="text" name="m_memo_title" value="${oneDTO.m_memo_title }">
                    </div>
                    <div class="row description">
                        <label>Description</label>
                        <textarea name="m_memo_desc">${oneDTO.m_memo_desc }</textarea>
                    </div>
                    <input type="hidden" name="m_memo_key" value="${oneDTO.m_memo_key }" />
                    <button class="Modmemo_Btn">Modify Note</button>                    
                </form>
            </div>
        </div>
    </div>

</body>
