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
	<link href="/project/resources/css/allMystudy.css" rel="stylesheet"/> 
    <script src="https://kit.fontawesome.com/f5483c894c.js" crossorigin="anonymous"></script>
</head>
<body>
<div class="cal_pl_wrap">
        <!-- 달력 -->
        <div class="calendar">
            <div class="year-month"></div>
            <div class="header">
                <button class="add-btn" id="add-schedule-btn" onclick="">일정 추가</button>
                <div class="nav">
                    <button class="nav-btn go-prev" onclick="prevMonth()">&lt;</button>
                    <button class="nav-btn go-today" onclick="goToday()">Today</button>
                    <button class="nav-btn go-next" onclick="nextMonth()">&gt;</button>
                </div>
            </div>
            <div class="main">
                <div class="days">
                    <div class="day">일</div>
                    <div class="day">월</div>
                    <div class="day">화</div>
                    <div class="day">수</div>
                    <div class="day">목</div>
                    <div class="day">금</div>
                    <div class="day">토</div>
                </div>
                <div class="dates"></div>
            </div>
        </div>

        <!-- 등록폼 모달 -->
        <form method="get" action="${pageContext.request.contextPath}/mystudy/insertSchedule">
        <div class="modal" id="modal">
            <div class="modal_body">
                <div class="m_head">
                    <div class="modal_title">Add Schedule</div>
                    <div class="close_btn" id="close_btn">X</div>
                </div>
                <div class="m_body">
                    <div class="info"> *하루에 최대 4개의 스케줄만 등록할 수 있습니다.</div>
                    <div class="modal_label">날짜</div>
                    <input type="date" name="date" class="input_box">
                    <div class="modal_label">일정제목</div>
                    <input type="text" name="m_schedule_title" class="input_box" >
                    <div class="modal_label">세부내용</div>
                    <input type="text" name="m_schedule_desc" class="input_box" >
                </div>
                <div class="m_footer">
                    <div class="modal_btn cancle" id="close_btn">CANCLE</div>
                    <button class="modal_btn save" id="save_btn">SAVE</button>
                </div>
        	</div>
    	</div>
        </form>
</div>


<div id="MemoWrapper">
    <div class="memopopup-box">
        <div class="memopopup">
            <div class="memocontent">
                <header class="memoheader">
                    <p>Add a new Note</p>
                    <i class="fa-solid fa-xmark" id="clsicon"></i>
                </header>
                <form method="get" action="${pageContext.request.contextPath}/mystudy/insertMemo">
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
                        <a href="${pageContext.request.contextPath}/mystudy/selectOneMemo?m_memo_key=${mDTO.m_memo_key }"><li class="memo_edit"><i class="fa-solid fa-pencil"></i>Edit</li></a>
                        <a href="${pageContext.request.contextPath}/mystudy/deleteMemo?m_memo_key=${mDTO.m_memo_key }"><li class="memo_del"><i class="fa-regular fa-trash-can"></i>Delete</li></a>
                    </ul>
                </div>
            </div>
          </li>          
        </c:forEach>
    </div>
    
    <div id="memopagebar">
   	total : ${total } <br>
   	
<%--    		<c:if test="${startIdx != 1}"> --%>
<%-- 			<a href="/project/mystudy/memolist?viewPage=${viewPage - 1}&countPerPage=${countPerPage}&userkey=${userkey}" style=" font-weight: bold;" > << </a> &nbsp; --%>
<%-- 		</c:if> --%>
   	
   	<c:choose>
   		 <c:when test="${ viewPage == 1}"> <<  &nbsp; </c:when>
		 <c:when test="${ viewPage != 1}">
			<a href="/project/mystudy/memolist?viewPage=${viewPage - 1}&countPerPage=${countPerPage}&userkey=${userkey}" style=" font-weight: bold;" > << </a> &nbsp;
		 </c:when>
   	</c:choose>
   	

     <c:forEach var="i" begin="1" end="${totalPage }">
     <c:if test="${viewPage eq i }">
     	<a href="/project/mystudy/memolist?viewPage=${i }&countPerPage=${countPerPage}&userkey=${userkey}" style="color:#606187; font-weight: bold;"> ${i } </a> &nbsp;     
     </c:if>
     <c:if test="${viewPage ne i }">
     	<a href="/project/mystudy/memolist?viewPage=${i }&countPerPage=${countPerPage}&userkey=${userkey}"> ${i } </a> &nbsp;     
     </c:if>
     </c:forEach>
     
     <c:choose>
     <c:when test="${viewPage eq totalPage }"> >>  &nbsp; </c:when>
     <c:when test="${viewPage != totalPage }">
		<a href="/project/mystudy/memolist?viewPage=${viewPage + 1}&countPerPage=${countPerPage}" style=" font-weight: bold;" > >> </a> &nbsp;
	 </c:when>
     </c:choose>
<%--      <c:if test="${endIdx != totalPage }"> --%>
<%-- 		<a href="/project/mystudy/memolist?viewPage=${viewPage + 1}&countPerPage=${countPerPage}" style=" font-weight: bold;" > >> </a> &nbsp; --%>
<%-- 	 </c:if> --%>


    </div>

	 
</div>

	<script src="/project/resources/js/cal.js"></script>
	<script src="/project/resources/js/memo.js"></script>
	<script type="text/javascript">
			
	</script>
</body>
</html>