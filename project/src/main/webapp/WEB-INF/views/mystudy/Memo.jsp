<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
  
<title>메모페이지</title>
<link href="/project/resources/css/memo.css" rel="stylesheet"/> 
<script src="https://kit.fontawesome.com/f5483c894c.js" crossorigin="anonymous"></script>
<body>

<div id="MemoWrapper">
    <div class="memopopup-box">
        <div class="memopopup">
            <div class="memocontent">
                <header class="memoheader">
                    <p>Add a new Note</p>
                    <i class="fa-solid fa-xmark" id="clsicon"></i>
                </header>
                <form method="get" action="insertMemo.do">
                    <div class="row title">
                        <label>Title</label>
                        <input type="text" name="m_memo_title">
                    </div>
                    <div class="row description">
                        <label>Description</label>
                        <textarea name="m_memo_desc"></textarea>
                    </div>
                    <button>Add Note</button>                    
                </form>
            </div>
        </div>
    </div>
        
    <div class="memo_wrapper">
       <li class="memoadd-box">
            <div class="micon"><i class="fa-solid fa-plus"></i></div>
            <p>Add new note <i class="fa-regular fa-note-sticky"></i></p>
        </li>
        <c:forEach var="mDTO" items="${resultList }">
        <li class="note">
            <div class="details">
                <p>${mDTO.m_memo_title }</p>
                <span>${mDTO.m_memo_desc }</span>
            </div>
            <div class="bottom-content">
                <span><fmt:formatDate value="${mDTO.m_memo_date }" pattern="yyyy-MM-dd" /></span>
                <div class="settings">
                    <i class="fa-solid fa-ellipsis"></i>
                    <ul class="memomenu">
                        <a data-name="addalink" href="selectOneMemo.do?m_memo_key=${mDTO.m_memo_key }"><li class="memo_edit"><i class="fa-solid fa-pencil"></i>Edit</li></a>
                        <a data-name="addalink" href="deleteMemo.do?m_memo_key=${mDTO.m_memo_key }"><li class="memo_del"><i class="fa-regular fa-trash-can"></i>Delete</li></a>
                    </ul>
                </div>
            </div>
          </li>          
        </c:forEach>
    </div>
    
    <div id="memopagebar">
   	total : ${total } <br>
   	
   	<c:choose>
   		 <c:when test="${ viewPage == 1}"> <<  &nbsp; </c:when>
		 <c:when test="${ viewPage != 1}">
			<a data-id="amyStyle" href="/project/mystudy/memo.do?viewPage=${viewPage - 1}&countPerPage=${countPerPage}&userkey=${userkey}" style=" font-weight: bold;" > << </a> &nbsp;
		 </c:when>
   	</c:choose>
   	

     <c:forEach var="i" begin="1" end="${totalPage }">
     <c:if test="${viewPage eq i }">
     	<a data-id="amyStyle" href="/project/mystudy/memo.do?viewPage=${i }&countPerPage=${countPerPage}&userkey=${userkey}" style="color:#606187; font-weight: bold;"> ${i } </a> &nbsp;     
     </c:if>
     <c:if test="${viewPage ne i }">
     	<a data-id="amyStyle" href="/project/mystudy/memo.do?viewPage=${i }&countPerPage=${countPerPage}&userkey=${userkey}"> ${i } </a> &nbsp;     
     </c:if>
     </c:forEach>
     
     <c:choose>
     <c:when test="${viewPage eq totalPage }"> >>  &nbsp; </c:when>
     <c:when test="${viewPage != totalPage }">
		<a data-id="amyStyle" href="/project/mystudy/memo.do?viewPage=${viewPage + 1}&countPerPage=${countPerPage}" style=" font-weight: bold;" > >> </a> &nbsp;
	 </c:when>
     </c:choose>

    </div>

	 
</div>

	<script src="/project/resources/js/memo.js"></script>
	<script type="text/javascript"></script>
</body>
