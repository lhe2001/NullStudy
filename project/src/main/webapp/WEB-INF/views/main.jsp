<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<style>
	#teamBoard tr, #topBoard tr{
	text-align: center;
	}
		.articleA{
	text-decoration: none;
	color: black;
	
	}
	.articleA:visited{
	color: black;
	}
</style>
<script>
$(document).ready( function() {
 	getMainTeamList(); 
	moveToAllTeam();
 	getMainTopArticles();
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

function getMainTopArticles(){
	
	$.ajax({
		url: "/project/topArticles.do",
		type: "post",
		contentType: "application/json",
		success: function(data){
			console.log(data);
			$("#topBoard tbody").empty();
			
			let html="";
			for(let i=0; i<data.length; i++){
				let date = new Date(data[i].b_writeDate);
					
					html += "<tr>";
					html += "<td>" + (i+1) + "</td>";
					html += "<td>" + data[i].b_fieldName + "</td>";
					html += "<td>" + data[i].nickName + "</td>";
					html += "<td>";
					html += "<a class='articleA' href='/project/board/viewArticle.do?b_articleNo=";
					html += data[i].b_articleNo;
					html += "'>"
					html += data[i].b_title;
					html += "</a>"
					html += "</td>";
					html += '<td>' + date.getFullYear()+'년'+ (date.getMonth()+1) + '월' + date.getDate() + '일'+'</td>';
					html += "<td>";
					html += data[i].b_view;
					html += "</td>";
					html += "</tr>";
			}
			$("#topBoard tbody").append(html);
			alretTopArcticle();
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

function alretTopArcticle(){
	$(document).on("click",".articleA",function(e){
		e.preventDefault();
		let userkey = ${userInfo.userKey-1};
		console.log(userkey);
		if(userkey ==-1){
			alert('로그인을 먼저 해주세요!');
		}else{
			location.href=$(this).attr("href")
		}
	});
}

</script>
<body>

	<c:if test="${searchLoginDo=='searchLoginDo'}">
		<script>alert("로그인 후 사용해주세요.");</script>
	</c:if>

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
                            	<thead>
                            		<tr>
                            			<th>글번호</th>
                            			<th>분야</th>
                            			<th>작성자</th>
                            			<th>제목</th>
                            			<th>작성일</th>
                            			<th>조회수</th>
                            		</tr>
                            	</thead>
                            	<tbody>
                            	</tbody>
                            	
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
