<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<title>일정 상세보기</title>
<script src="https://kit.fontawesome.com/f5483c894c.js" crossorigin="anonymous"></script>
<link href="/project/resources/css/calList.css" rel="stylesheet">
<body>
<div id="calDetailWrap">
	<div id="calDeContent">
		<table id="tbdetail">
			<tr class="detr">
				<th><p>일정요일</p></th>
			</tr>
			<tr class="detr">
				<td>${sdto.m_schedule_date }</td>
			</tr>
			<tr class="detr">
				<th><p>제목</p></th>
			</tr>
			<tr class="detr">
				<td>${sdto.m_schedule_title }</td>
			</tr>
			<tr class="detr">
				<th><p>내용</p></th>
			</tr> 
			<tr class="detr">
				<td>${sdto.m_schedule_desc }</td>
			</tr> 
			<tr class="detr">
				<th><p>작성일</p></th>
			</tr> 
			<tr class="detr">
				<td>${sdto.m_schedule_write }</td>
			</tr>
			<tr>
				<td>
					<button class="Detailbtn" type="button" 
							onclick="location.href='updateForm.do?m_schedule_key=${sdto.m_schedule_key }'">수정</button>
					<button  class="Detailbtn" type="button" onclick="location.href='calMuldel.do?seq=${sdto.m_schedule_key }'">삭제</button>
					<button  class="Detailbtn" type="button" onclick="location.href='calBoardList.do'">목록</button>
					<button  class="Detailbtn" type="button" onclick="location.href='calendar.do?year=${sessionScope.ymd.year }&month=${sessionScope.ymd.month}'">달력</button>
				</td>
			<tr>		
		</table>
	</div>
</div>
</body>
