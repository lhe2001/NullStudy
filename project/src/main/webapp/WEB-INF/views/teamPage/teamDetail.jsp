<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
   <script>
    window.addEventListener("load", teamInfoOnload);
   //window.onload = function(){
	   function teamInfoOnload(){
    	console.log(111)
       reviseTeamInfo();
        leWrite();
        dailyMemoRevise();
	   }
    
  //팀정보 수정
    function reviseTeamInfo(){
        
    	let button = document.querySelectorAll(".reviseTeamInfo")
    	
    	for(let i=0; i<button.length; i++){
		  button[i].addEventListener("click",function(){
	    	console.log("클릭")
        	let teamInfo = document.querySelector("#teamInfo");
        	
        	if(teamInfo.classList.contains("hide") == true ){
        		console.log("true 작동")
        		document.querySelector("#teamInfo").classList.remove("hide");
	            document.querySelector("#reviseTeam").classList.add("hide");
        	} else{
            document.querySelector("#teamInfo").classList.add("hide");
            document.querySelector("#reviseTeam").classList.remove("hide");
        	}
        })
   	}
   }
    //조장한마디 수정
    function leWrite(){
        document.querySelector(".reviseW").addEventListener("click",function(){
            document.querySelector(".reivseWrite").classList.remove("hide");
            document.querySelector(".leadersWrite").classList.add("hide");
        })
    }
    //오늘의 요약 수정
    function dailyMemoRevise(){
        document.querySelector(".dailyRevBtn").addEventListener("click",function(){
            document.querySelector(".TeamDailyMemo").classList.add("hide");
            document.querySelector(".TeamDailyRevise").classList.remove("hide");
        })
        document.querySelector(".returnBtn").addEventListener("click",function(){
            document.querySelector(".TeamDailyMemo").classList.remove("hide");
            document.querySelector(".TeamDailyRevise").classList.add("hide");
        })
    }

</script>
</head>
<body>
	<div id="team_Wrapper">
	    <div id="Wrapper">
            <div id="teamInfo" class="teamInfoBox ">
		    <a class="deleteTeamBtn"  href='/teampro/teamcd/delTeam'>현재팀 삭제</a>
                <table>
                    <tr>
                        <td>팀 이름</td>
                        <td>${teamInfo.teamName}</td>
                    </tr>
                    <tr>
                        <td>인사말</td>
                        <td>${teamInfo.teamIntro}</td>
                    </tr>
                    <tr>
                        <td>분야</td>
                        <td> 
                        <c:choose>
                            <c:when test="${teamInfo.teamField eq 1 }" >
                            코딩
                            </c:when>
                            <c:when test="${teamInfo.teamField eq 2 }" >
                            자격증
                            </c:when>
                            <c:when test="${teamInfo.teamField eq 3 }" >
                            토익
                            </c:when>
                            <c:when test="${teamInfo.teamField eq 4 }" >
                            기타
                            </c:when>
                        </c:choose>
                        </td>
                    </tr>
                    <tr>
                        <td>팀장</td>
                        <td>${teamInfo.leaderNick }</td>
                    </tr>
                </table>
                <button type="button" class="reviseTeamInfo reviese" ><i class="fa-solid fa-pencil"></i></button>
            </div>
            <div id="reviseTeam" class="hide teamInfoBox ">
            <form method="post" action="teamInfo/reviseTeamInfo" >
	            <table>
	                <tr>
	                    <td>팀 이름</td>
	                    <td>${teamInfo.teamName}</td>
	                </tr>
	                <tr>
	                    <td>인사말</td>
	                    <td><input type="text" name="teamHi" required></td>
	                </tr>
	                <tr>
	                    <td>분야</td>
	                    <td> 
		                    <select name="field">
			                	<option value="1">코딩</option>
			                	<option value="2">자격증</option>
			                	<option value="3">토익</option>
			                	<option value="4">기타</option>
			                </select>
	            		</td>
	                </tr>
	                <tr>
	                    <td>팀장</td>
	                    <td>${teamInfo.leaderNick }</td>
	                </tr>
	            </table>
                <input type="submit" value="수정">
                <button type="button" class=" reviseTeamInfo back" >취소</button>
            </form>
        </div>
        <div class="LeaderMenu leadersWrite ">
           <div> ${teamInfo.leaderNick }님의 공지사항: ${teamInfo.teamMemo }djfkdjfljdlkfjkldjfkldjlkfjkldjfkljdklfjkldjfkljdklfjklfglfkjgkfjlkgjklfgklfjglkjfklflgkjflkjglkfjklg</div>
            <button type="button" class="reviseW rBtn"><i class="fa-regular fa-pen-to-square"></i></button>
        </div>
        <div class="LeaderMenu reivseWrite hide">
            <form method="post" action="teamInfo/leaderMemo">
                ${teamInfo.leaderNick }님의 공지사항: <input type="text" name="leWrite" required><br>
                <input type="submit" class="rBtn2" value="수정">
            </form>
        </div>
        <div id="memberWrapper">
            <div id="memberInfo">
            <!-- <c:forEach var="member" items="${memberList}" varStatus="loop"> -->
            <div class="member">
                <!-- <c:if test="${member.userKey eq teamInfo.teamLeader }" > -->
                    <div class="king"><img src="https://ifh.cc/g/6bBq87.png"></div>
                <!-- </c:if> -->
                    <div class="photo">
                        <img src="https://ifh.cc/g/GCpQKq.png">
                    </div>
                    <div class="info leaderInfo">
                        <table>
                        <tr>
                            <td style="max-width:90px; min-width:90px;"><strong>${member.nickname }</strong></td>
                            <td style="min-width:300px; max-width:300px;">${member.intro }</td>
                            <td style="min-width:90px; max-width:90px;">${PercentList.get(loop.index)}%</td>
                            <td style="min-width:132px; max-width:132px;">${member.lastTime }</td>
                        </tr>
                    </table>
                    <!-- <c:if test="${member.userKey ne userInfo.userKey }" > -->
                        <!-- <button  class="kickOut" type="button" onclick="location.href='/teampro/teamInfo/delMember?tmkey=${member.tm_key}'">
                            <i class="fa-solid fa-arrow-right-from-bracket"></i></button> -->
                            <!-- </c:if> -->
                    </div>
                </div>
                <div class="member">
                    <!-- <c:if test="${member.userKey eq teamInfo.teamLeader }" > -->
                <!-- </c:if> -->
                    <div class="photo">
                        <img src="https://ifh.cc/g/GCpQKq.png">
                        </div>
                    <div class="info">
                        <table>
                        <tr>
                            <td style="max-width:90px; min-width:90px;"><strong>혜으니</strong></td>
                            <td style="min-width:300px; max-width:300px;">반갑~~~오늘도화이팅 가보자고!!!ㅎㅇ로어롸오러와러ㅗ</td>
                            <td style="min-width:90px; max-width:90px;">30%</td>
                            <td style="min-width:132px; max-width:132px;">2022-10-10 3:00pm</td>
                        </tr>
                    </table>
                    <!-- <c:if test="${member.userKey ne userInfo.userKey }" > -->
                    <!-- <button  class="kickOut" type="button" onclick="location.href='/teampro/teamInfo/delMember?tmkey=${member.tm_key}'">
                    <i class="fa-solid fa-arrow-right-from-bracket"></i></button> -->
                    <!-- </c:if> -->
                    </div>
                </div>
                <div class="member">
                    <!-- <c:if test="${member.userKey eq teamInfo.teamLeader }" > -->
                <!-- </c:if> -->
                    <div class="photo">
                        <img src="https://ifh.cc/g/GCpQKq.png">
                        </div>
                    <div class="info">
                        <table>
                        <tr>
                            <td style="max-width:90px; min-width:90px;"><strong>혜으니</strong></td>
                            <td style="min-width:300px; max-width:300px;">반갑~~~오늘도화이팅 가보자고!!!ㅎㅇ로어롸오러와러ㅗ</td>
                            <td style="min-width:90px; max-width:90px;">30%</td>
                            <td style="min-width:132px; max-width:132px;">2022-10-10 3:00pm</td>
                        </tr>
                    </table>
                    <!-- <c:if test="${member.userKey ne userInfo.userKey }" > -->
                    <!-- <button  class="kickOut" type="button" onclick="location.href='/teampro/teamInfo/delMember?tmkey=${member.tm_key}'">
                    <i class="fa-solid fa-arrow-right-from-bracket"></i></button> -->
                    <!-- </c:if> -->
                    </div>
                </div>
                <div class="member">
                    <!-- <c:if test="${member.userKey eq teamInfo.teamLeader }" > -->
                <!-- </c:if> -->
                    <div class="photo">
                        <img src="https://ifh.cc/g/GCpQKq.png">
                        </div>
                    <div class="info">
                        <table>
                        <tr>
                            <td style="max-width:90px; min-width:90px;"><strong>혜으니</strong></td>
                            <td style="min-width:300px; max-width:300px;">반갑~~~오늘도화이팅 가보자고!!!ㅎㅇ로어롸오러와러ㅗ</td>
                            <td style="min-width:90px; max-width:90px;">30%</td>
                            <td style="min-width:132px; max-width:132px;">2022-10-10 3:00pm</td>
                        </tr>
                    </table>
                    <!-- <c:if test="${member.userKey ne userInfo.userKey }" > -->
                    <!-- <button  class="kickOut" type="button" onclick="location.href='/teampro/teamInfo/delMember?tmkey=${member.tm_key}'">
                    <i class="fa-solid fa-arrow-right-from-bracket"></i></button> -->
                    <!-- </c:if> -->
                    </div>
                </div>
                <div class="member">
                    <!-- <c:if test="${member.userKey eq teamInfo.teamLeader }" > -->
                <!-- </c:if> -->
                    <div class="photo">
                        <img src="https://ifh.cc/g/GCpQKq.png">
                        </div>
                    <div class="info">
                        <table>
                        <tr>
                            <td style="max-width:90px; min-width:90px;"><strong>혜으니</strong></td>
                            <td style="min-width:300px; max-width:300px;">반갑~~~오늘도화이팅 가보자고!!!ㅎㅇ로어롸오러와러ㅗ</td>
                            <td style="min-width:90px; max-width:90px;">30%</td>
                            <td style="min-width:132px; max-width:132px;">2022-10-10 3:00pm</td>
                        </tr>
                    </table>
                    <!-- <c:if test="${member.userKey ne userInfo.userKey }" > -->
                    <!-- <button  class="kickOut" type="button" onclick="location.href='/teampro/teamInfo/delMember?tmkey=${member.tm_key}'">
                    <i class="fa-solid fa-arrow-right-from-bracket"></i></button> -->
                    <!-- </c:if> -->
                    </div>
                </div>
                <div class="member">
                    <!-- <c:if test="${member.userKey eq teamInfo.teamLeader }" > -->
                <!-- </c:if> -->
                    <div class="photo">
                        <img src="https://ifh.cc/g/GCpQKq.png">
                        </div>
                    <div class="info">
                        <table>
                        <tr>
                            <td style="max-width:90px; min-width:90px;"><strong>혜으니</strong></td>
                            <td style="min-width:300px; max-width:300px;">반갑~~~오늘도화이팅 가보자고!!!ㅎㅇ로어롸오러와러ㅗ</td>
                            <td style="min-width:90px; max-width:90px;">30%</td>
                            <td style="min-width:132px; max-width:132px;">2022-10-10 3:00pm</td>
                        </tr>
                    </table>
                    <!-- <c:if test="${member.userKey ne userInfo.userKey }" > -->
                    <!-- <button  class="kickOut" type="button" onclick="location.href='/teampro/teamInfo/delMember?tmkey=${member.tm_key}'">
                    <i class="fa-solid fa-arrow-right-from-bracket"></i></button> -->
                    <!-- </c:if> -->
                    </div>
                </div>
            <!-- </c:forEach> -->
            </div>
            <div id="memberDetail">
                <div class="photo"> <img src="https://ifh.cc/g/GCpQKq.png"><br>
                    <div><strong>혜으니</strong></div>
                </div>
                <div class="challenge">
                    <table>
                        <tr>
                            <td class="confirm"></td>
                            <td class="confirm"></td>
                            <td class="confirm"></td>
                            <td class="confirm"></td>
                            <td class="confirm"></td>
                            <td class="confirm"></td>
                            <td class="confirm"></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td colspan="7"><input type="button" value="SUBMIT"></td>
                        </tr>
                    </table>
                </div>
                <div id="TeamDaily">
                    <table class="TeamDailyMemo">
                        <tr>
                            <td>오늘의 요약!</td>
                        </tr>
                        <tr>
                            <td style="font-size:13px;">오fffffffffffffffffffffffffffffffgggggggggggggggggggggggggfffff늘은 좀 힘드네..대충하자 내일부터는 다시 열심히!</td>
                        </tr>
                        <tr>
                            <td><input class="dailyRevBtn" type="button" value="수정하기"></td>
                        </tr>
                    </table>
                    <table class="TeamDailyRevise hide">
                        <tr>
                            <td>오늘의 요약!</td>
                        </tr>
                        <tr>
                            <td><textarea></textarea></td>
                        </tr>
                        <tr>
                            <td><input type="button" class="returnBtn" value="취소"><input type="submit"></td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
        <div id="teamBoard_Wrapper">
       		<div id="tBoard">
            	<table>
                	<thead>
                    	<tr>
	                        <th>작성자</th>
	                        <th>한마디</th>
	                        <th>작성시간</th>
                    	</tr>
                	</thead>
                	<tbody>
                	<c:forEach var="boardList" items="${boardList}" varStatus="loop">
               		   <tr>
	                       	<td>${boardList.nickName}</td>
	                       	<!-- <c:if test="${boardList.userKey == userInfo.userKey }" > -->
	                       	<td>${boardList.tb_memo}<button class='delBtn' type="button" onclick="location.href='/teampro/teamInfo/delBoard?tbkey=${boardList.tb_key}'">
	                       	<i class="fa-regular fa-trash-can"></i></button>
	                       	</td>
	                       	<!-- </c:if> -->
                       		<!-- <c:if test="${boardList.userKey != userInfo.userKey }" > -->
	                       	<!-- <td>${boardList.tb_memo}</td> -->
	                       	<!-- </c:if> -->
	                       	<td>${boardList.tb_memotime}</td>
                       </tr>
                    </c:forEach>
               		</tbody>
            	</table>
        	</div>
        	<div id="Write_teamBoard">
            <form method="post" action="teamInfo/addBoard">
                <input type="text" name="boardWrite">
                <input type="submit" value="글쓰기">
            </form>
        </div>
       	</div>
   	</div>
   	</div>
</body>
</html>