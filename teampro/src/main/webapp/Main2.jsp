<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="signupin.*"
%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="Front/simple.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Nanum+Pen+Script&display=swap" rel="stylesheet">
    <script src="https://kit.fontawesome.com/f5483c894c.js" crossorigin="anonymous"></script>
</head>
<script>
window.addEventListener("load", mainOnload);

function mainOnload(){
	postit();
    todoAdd();
    todoDelete();
    allcheck();
   
}

//aside click 이벤트
function postit(){
    let postButtonList = document.querySelectorAll("#postitBox button")
    let postList = document.querySelectorAll(".postit")
    let shadow = document.querySelector("#shadow")

    for(let i=0; i<postButtonList .length; i++){
        postButtonList[i].addEventListener("click",function(){

            if(postList[i].classList.contains("none")){
                for(let j=0; j<postList.length; j++){
                    postList[j].classList.add("none");
                    shadow.classList.add("none");
                }
                postList[i].classList.remove("none");
                shadow.classList.remove("none");
                
            } else {
                postList[i].classList.add("none");
                shadow.classList.add("none");
            }
            
        })
    }

}

 // todoList 추가버튼 활성화
 function todoAdd(){
    let add = document.querySelector("#addBtn");
    add.addEventListener("click",function(){
        let text = document.querySelector("#todoText").value;
        let tbody = document.querySelector("tbody");
        let tr = document.querySelectorAll("tbody tr")
        // 6개까지 추가로 제한하기. 나중에
        if(text != "" && tr.length <= 6){
            let html = tbody.innerHTML;
            html += '<tr>';
            html += '   <td><input type="checkbox" name="todo"></td>';
            html += '   <td>'+text+'</td>';
            html += '</tr>';
            tbody.innerHTML = html;
        }else if(tr.length > 6){
            alert("7개까지만 추가 가능합니다.");
        }
    })
}
// todoList 삭제버튼 활성화
function todoDelete(){
    let del = document.querySelector("#deleteBtn");
    del.addEventListener("click",function(){
        let checked = document.querySelectorAll("input[name='todo']:checked");
        for(let i=0; i<checked.length; i++){
            checked[i].parentNode.parentNode.remove("tr");
        }
    })
}
//checkbox 전체 선택하기 기능
function allcheck(){
    let allck = document.querySelector("th input[type='checkbox']");
    allck.addEventListener("change",function(e){
        let check = document.querySelectorAll("input[name='todo']");
        for(let i=0; i<check.length;i++){
            check[i].checked = e.target.checked;
        }
    })
}

</script>

<body>
<%@ include file="/fix/header(logout).jsp" %>
    <div>
    <div id="wrapper">
        
        <!-- contents 시작 -->
        <div id="conWrap">
        <!-- 사이드바 -->
        <div id="shadow"></div>
            <aside>
                <!-- <button type="button" class="asideHide">Hide</button> -->
                <div id="postitBox">
                    <button type="button" class="pink">My</button>
                    <button type="button" class="yellow">To Do</button>
                    <button type="button" class="blue">Team</button>
                </div>
                <div class="postit pink">
                    <div class="left"></div>
                    <div id="userInfo">
                        <div id="userPT"></div>
                        <h1>${userInfo.nickName }</h1>
                        <p>${userInfo.intro }</p>
                    </div>
                </div>
                <div class="postit yellow none">
                    <div class="left"></div>
                    <div id="todoList">
                        <div id="list">
                            <table>
                                <thead>
                                    <tr>
                                        <th><input type="checkbox"></th>
                                        <th>To Do List</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td><input type="checkbox" name="todo"></td>
                                        <td>웹페이지 만들기</td>
                                    </tr>
                                    <tr>
                                        <td><input type="checkbox" name="todo"></td>
                                        <td>자바 공부하기</td>
                                    </tr>
                                    <tr>
                                        <td><input type="checkbox" name="todo"></td>
                                        <td>오라클 공부하기</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <button type="button" id="addBtn" >추가</button>
                        <button type="button" id="deleteBtn">삭제</button>
                        <input type="text" id="todoText" placeholder="추가List">
                    </div>
                </div>
                <div class="postit blue none">
                    <div class="left"></div>
                   <div id="TeamInfoP">
                    <c:if test="${myteamList == null }">
						<strong>${msg}</strong><br>
					</c:if>
					<c:if test='${myteamList != null }'>
					 <table>
					 	<tr>
					 		<th>현재 내가 속한 팀 목록</th>
					 	</tr>
				 	 <c:forEach var="myteamList" items="${myteamList}" varStatus="loop">
					 	<tr>
					 		<td><span> ❤ </span> <a href="/teampro/teamdetail?teamkey=${myteamList.teamkey }">${myteamList.teamName}</a></td>
					 	</tr>
				 	</c:forEach>
					 </table>
					 </c:if>
                    </div>
            </aside>
             <!-- 메인시작  -->
           <section>
                <div>
                    <div id="adimg">
                        <div class="search">
                           <form action="/teampro/Controller" method="post">
                              <select name = "selectValue" id="selectValue">
                                  <option selected disabled>검색기준</option>
                                  <%-- disabled 시 value 이 있어도 null 로 넘어감 --%>
                                  <option value = "boardSearch">게시글 검색</option>
                                  <option value = "userSearch">유저 검색</option>
                                  <option value = "teamSearch">팀 검색</option>
                              </select>
                               <input type="text" name="q" placeholder="검색하세요" required>
                               <button type="submit"><i class="fa-solid fa-magnifying-glass"></i></button>
                               <input type="hidden" name="mainSearchH" value="mainSearchH">
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
						<c:when test="${article.b_fieldName eq '비밀글'}">
						<td style = "color : tomato;">${article.b_fieldName }</td>
						</c:when>
						<c:otherwise>
						<td >${article.b_fieldName }</td>
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
										<a href="${contextPath}/board/viewArticle.do?b_articleNo=${article.b_articleNo}">
										${article.b_title} </a>
								</c:when>
								<c:otherwise>
										<a href="${contextPath}/board/viewArticle.do?b_articleNo=${article.b_articleNo}">
										${article.b_title} </a>
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
                        
                         <div id="teamBoard"><h3>팀 구함<i class="fa-solid fa-hand-back-fist"></i></h3>
                        	<table onclick="location.href='/teampro/teamPage/AllTeamList.jsp'">
                        		<thead >
                        			<tr>
                        				<th>필드</th>
                        				<th>팀이름</th>
                        				<th>인원수</th>
                       				</tr>
                        		</thead>
                        		<tbody>
                        		<c:forEach var="allTeamList" items="${allTeamList}" >
                        			<tr>
                        				<td>
                        					<c:choose>
								            	<c:when test="${allTeamList.teamField eq 1 }" >
								            	코딩
								            	</c:when>
								              	<c:when test="${allTeamList.teamField eq 2 }" >
								            	자격증
								            	</c:when>
								             	 <c:when test="${allTeamList.teamField eq 3 }" >
								           		토익
								            	</c:when>
								              	<c:when test="${allTeamList.teamField eq 4 }" >
								            	기타
						            			</c:when>
						            		</c:choose>
					            		</td>
                        				<td>${allTeamList. teamName}</td>
                        				<td>${allTeamList. teamNumber}</td>
                       				</tr>
                     			</c:forEach>
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
<%@ include file="/fix/footer.jsp" %>

</body>

</html>