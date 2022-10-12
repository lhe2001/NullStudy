<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.*, java.text.SimpleDateFormat"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="attendPack.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<c:set var="contextPath" value="${pageContext.request.contextPath }"/>  

<%
Calendar cal = Calendar.getInstance();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자바달력</title>
<link href="/teampro/Front/mypage.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Nanum+Pen+Script&display=swap" rel="stylesheet">

<script type="text/javascript">
		window.addEventListener("load", myPageOnload);

    function myPageOnload(){
     chart();
       
    }
	function chart(){
	
		const chart1 = document.querySelector('.doughnut1');
		
		const makeChart = (percent, classname, color) => {
		  let i = 1;
		  let chartFn = setInterval(function() {
		    if (i < percent) {
		      colorFn(i, classname, color);
		      i++;
		    } else {
		      clearInterval(chartFn);
		    }
		  }, 10);
		}

		const colorFn = (i, classname, color) => {
		  classname.style.background = "conic-gradient(" + color + " 0% " + i + "%, #dedede " + i + "% 100%)";
		}
		
		makeChart(${mypercent}, chart1, '#699DD1');

	}
</script>
</head>
<body>
<%@ include file="/fix/header(logout).jsp" %>

<div id="mypagewrap">
    <div class="Infowrap" id="myInfo">
        <div id="left">
            <div id="profile"></div>
        </div>
        <div id="right"> 
            <div class="topname" id="nick">${userInfo.nickName }</div>
            <div class="txt">${userInfo.intro }</div>
           <div id="Infomod"><a href="myStudy/modMyInfo.jsp"><button id="gomod">수정하기</button></a></div>
        </div>
    </div>
	<div class="cal_wrap">
		<!-- 달력 만들기 -->
		<table id="cal_table">
			<thead>
				<tr>
					<th class="fir"  colspan="2"><button type="button"  class="calbtn"
							onclick="location.href='/teampro/mypage?month=${month-1 }'"><i class="fa-regular fa-circle-left fa-2x"></i></button></th>
					<th class="sec" colspan="3">${year }년${month }월</th>
					<th class="fir"  colspan="2"><button type="button"  class="calbtn"
							onclick="location.href='/teampro/mypage?month=${month+1 }'"><i class="fa-regular fa-circle-right fa-2x"></i></button></th>
				</tr>
				<tr>
					<th class="thir">일</th>
					<th class="thir">월</th>
					<th class="thir">화</th>
					<th class="thir">수</th>
					<th class="thir">목</th>
					<th class="thir">금</th>
					<th class="thir">토</th>
				</tr>
			</thead>
			<tbody>${html }
			</tbody>
		</table>
		
	</div>
	<div class='cwrap'>
	    <table id="bottom_table">
	    <tr>
	    <td>
	        <div class="chart doughnut1">
	            <span class="center">${mypercent}%</span>
	        </div>
	        <div id="chartLabel">${month }월 출석률</div>
	    </td>
	    <td>
	        <div id="attendCheck">
	            <form method="post" action="/teampro/add">
	                <div class="btn-container">
	                    <input type="submit" value="출석" class="btn-3d blue">
	                </div>
	            </form>
	        </div>
	    
	    </td>
	    </tr>
	    </table>
	</div>
</div>
<%@ include file="/fix/footer.jsp" %>
</body>
</html>