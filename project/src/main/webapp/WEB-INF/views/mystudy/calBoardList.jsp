<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*"
    import="com.spring.teampro.mystudy.dto.*"
    %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  


<title>일정 목록 보기</title>
<script src="https://kit.fontawesome.com/f5483c894c.js" crossorigin="anonymous"></script>
<link href="/project/resources/css/calList.css" rel="stylesheet">

<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
<script>
//전체선택 자동체크와해지
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
<body>
        <div id="calBoardWrap">
        <form action="calMuldel.do" method="post">
        <input type="hidden" name="year" value="${param.year }"/>
        <input type="hidden" name="month" value="${param.month }"/>
        <input type="hidden" name="date" value="${param.date }"/>
            <div id="allList">
                <div id="calListTop">
                    <div id="TopNumber">
                        <span id="tyear">${sessionScope.ymd.year }년</span>
                        <span id="tmonth">${sessionScope.ymd.month}월</span>
                        <span id="tdate">${sessionScope.ymd.date}일</span>
                    </div>
                    <div id="TopText"><i class="fa-regular fa-calendar"></i>&nbsp;일정목록&nbsp;<i class="fa-regular fa-calendar"></i></div>
                </div>
                <div id="allCheckBox">
                    <span id="chkspan">
                        <input type="checkbox" name="all" onclick="allSel(this)" id="cballchk"/>
                        <label for="cballchk"></label>
                    </span>
                </div>
    
                <c:if test="${list.size() == 0 }" >
                    <div id="nonecb">목록이 없습니다.</div>
                </c:if>
                <c:forEach var="calList" items="${list }">
                <div class="calListMiddle">
                    <div class="calsmallchek">
                        <input type="checkbox" name="seq" value="${calList.m_schedule_key }" onclick="smallSel()"/>
                    </div>
                    <div class="calTitle">
                        <a data-id="cblink" href="calDetail.do?m_schedule_key=${calList.m_schedule_key }"> ${calList.m_schedule_title } </a>
                    </div>
                    <div class="calWrite">
                        <span>
                            작성일
                        </span>
                        <span>
                            ${calList.m_schedule_write }
                        </span>
                    </div>
                </div>
                </c:forEach>	
                
                
                <div id="cbdelWrap">
                    <button id="ListDelbtn">삭제</button>
                </div>
                <div id="calListBottom">
                    <a data-id="cblink" href="calendar.do?year=${sessionScope.ymd.year }&month=${sessionScope.ymd.month}">달력 돌아가기</a>
                </div>
            </div>
        </div>
	</form>

</body>
