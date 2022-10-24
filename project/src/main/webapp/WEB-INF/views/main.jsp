<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<style>
	td{
	text-align: center;
	}
</style>
<script>
$(document).ready( function() {
	getMainTeamList();
	moveToAllTeam();
});
function getMainTeamList(){
	
	$.ajax({
		url: "/project/teamRest/getMainTeamList.do",
		type: "post",
		contentType: "application/json",
		success: function(data){
			console.log(data);
			$("#teamBoard tbody").empty();
			
			let html="";
			for(let i=0; i<data.length; i++){
				html += "<tr>";
				html += "<td>";
				html += (i+1);
				html += "</td>";
				html += "<td>";
				html += data[i].t_field2;
				html += "</td>";
				html += "<td>";
				html += data[i].t_name;
				html += "</td>";
				html += "<td>";
				html += data[i].t_number;
				html += "</td>";
				html += "</tr>";
			}
			$("#teamBoard tbody").append(html);
		},
		error:function(){
			alert("에러발생!!")
		}
	});
}
function moveToAllTeam(){
	$(document).on("click","#teamBoard tr",function(e){
		let userkey = ${userInfo.userKey-1};
		console.log(userkey);
		if(userkey != -1){
			location.href='/project/team/allTeamList.do';
		}else{
			alert('로그인을 먼저 해주세요!');
		}
	});
}
</script>
<body>

<%-- 	<c:if test="${searchLoginDo=='searchLoginDo'}"> --%>
<!-- 		<script>alert("로그인 후 사용해주세요.");</script> -->
<%-- 	</c:if> --%>

    <div>
    <div id="wrapper">

        <!-- contents 시작 -->
        <div id="conWrap">
             <!-- 메인시작  -->
           <section>
                <div>
                    <div id="adimg">
                        <div class="search">
                           <form action="${pageContext.request.contextPath}/mainSearch.do" method="get">
                              <select name = "selectValue" id="selectValue">
                                  <option value = "notSelect" selected disabled>검색기준</option>
                                  <option value = "boardSearch">게시글 검색</option>
                                  <option value = "userSearch">유저 검색</option>
                                  <option value = "teamSearch">팀 검색</option>
                              </select>
                              
                               <input type="text" name="search" class="searchInput boardSearch userSearch teamSearch hide" required>
                               <input type="text" name="search" class="searchNoInput" disabled placeholder="검색기준를 선택해주세요">
                               <button class="submitButton hide" type="submit"><i class="fa-solid fa-magnifying-glass"></i></button>
                           </form>
                        </div>
                    </div>
                </div>
                <div id="contents">
                    <div id="boardWrap">
                        <div id="topBoard">
                            <h3>인기글<i class="fa-brands fa-hotjar"></i></h3>
	  <table>
		<tr>
			<th>글 번호</th>
			<%--<th>부모 글 번호</th> --%>
			<th>분야</th>
			<th>작성자</th>
			<th>제목</th>
			<th>작성일</th>
			<%--<th>조회수(임시)</th> --%>
			<%--<th>유저 키</th> --%>
			<%--(join으로 nick가져올거임)  --%>
		</tr>
		<%--여기서 분기나 탑시다--%>
		<c:choose>
			<%-- forward된 list가 비어있을때!! --%>
			<c:when test="${empty articlesList}">
				<h1 style ="text-align : center; margin-left : 20px; margin-top : 20px; color : #1C6758">등록된 글이 없어요....</h1>
			</c:when>
			<%-- forward된 list에 내용이 있을때!! --%>
			<c:when test="${not empty articlesList }">
				<%-- forEach로 변수에 담아 jstl로 출력해준다.. --%>
				<c:forEach var="article" items="${articlesList }" varStatus="num">
					<tr>
						<%--<td>${article.level }</td> --%>
						<%--<td>${article.b_key }</td> --%>
						<td>${num.count }</td>
						<%-- varStatus의 count를 사용해서 글번호 1부터 자동 --%>
						<%-- <td>${article.b_parentNo}</td> --%>
						<c:choose>
						<c:when test="${article.b_field eq '10'}">
						<td>질문</td>
						</c:when>
						<c:when test="${article.b_field eq '20'}">
						<td>잡담</td>
						</c:when>
						<c:when test="${article.b_field eq '30'}">
						<td>비밀글</td>
						</c:when>
						<c:when test="${article.b_field eq '40'}">
						<td>나도몰라</td>
						</c:when>
						<c:otherwise>
						<td >${article.b_field }</td>
						</c:otherwise>
						</c:choose>
						<td>${article.nickName} </td>
						
						<%--답변을 구분해야 한다 --%>
						<td align='left' width='30%'>
							<%--왼쪽 들여쓰기--%> <span style='padding-right: 30px'></span> <%-- level값이 1보다 큰경우 자식글이므로 
								 부모글 밑에 공백으로 들여쓰기해서 자식글인걸 티내자 
								 분기를 한번 더 타자--%> <c:choose>
								<c:when test="${article.level >1 }">
									<%-- 부모글 기준으로 레벨 값 만큼 들여쓰기하자 --%>
									<c:forEach begin="1" end="${article.level }" step="1">
										<span style='padding-left: 25px'></span>
									</c:forEach>
									<%-- 제목앞에 답글인걸 표시하는 표시 하나추가 --%>
									<span>[답변]</span>
									<%-- 마지막으로 제목을 누르면 상세 출력 페이지 이동 a태그하나 --%>
										${article.b_title}
								</c:when>
								<c:otherwise>
										${article.b_title} 
								</c:otherwise>
							</c:choose>
						</td>
						<td>${article.b_writeDate}</td>
						<%--<td>${article.b_view}</td> --%>
						<%-- <td>${article.userkey}</td> --%>
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>
	</table>
                        </div>
                          <div id="teamBoard"><h3>최근 개설된 팀<i class="fa-solid fa-hand-back-fist"></i></h3>
                          	<table>
                        		<thead >
                        			<tr>
                        				<th>No.</th>
                        				<th>필드</th>
                        				<th>팀이름</th>
                        				<th>인원수</th>
                       				</tr>
                        		</thead>
                        		<tbody>
                        		</tbody>
                        	</table>
                        </div>
                    </div>
                </div>
            </section>
        </div> 
        <!-- 콘텐츠 끝 -->
       
    </div>
</div>
</body>
