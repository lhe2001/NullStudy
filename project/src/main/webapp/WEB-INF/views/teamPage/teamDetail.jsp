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
<link href="/project/resources/css/team.css" rel="stylesheet">
</head>
<script src="/project/resources/js/Team.js" ></script>
</head>
<body>
	<div id="team_Wrapper">
	    <div id="fstWrapper">
            <div id="teamInfo" class="teamInfoBox ">
            <form name="frmDel">
		    <div class="deleteTeamBtn" onClick="delTeam()">현재팀 삭제</div>
		    <input type="hidden" name="t_key" class="t_key" value="${teamInfo.t_key }">
		    </form>
                <table>
                    <tr>
                        <td>그룹 이름</td>
                        <td>${teamInfo.t_name}</td>
                    </tr>
                    <tbody>
                    <tr>
                        <td>인사말</td>
                        <td>${teamInfo.t_intro}</td>
                    </tr>
                    <tr>
                        <td>분야</td>
                        <td>${teamInfo.t_field2 }</td>
                    </tr>
                    </tbody>
                    <tr>
                        <td>스터디장</td>
                        <td>${teamInfo.nickName }</td>
                    </tr>
                </table>
                <button type="button" class="reviseTeamInfo revise" ><i class="fa-solid fa-pencil"></i></button>
            </div>
            <div id="reviseTeam" class="hide teamInfoBox ">
	            <table>
	                <tr>
	                    <td>팀 이름</td>
	                    <td>${teamInfo.t_name}</td>
	                </tr>
	                <tr>
	                    <td>인사말</td>
	                    <td><input type="text" class="t_intro" required></td>
	                </tr>
	                <tr>
	                    <td>분야</td>
	                    <td> 
		                    <select class="t_field" name="t_field">
			                	<option value="1">코딩</option>
			                	<option value="2">자격증</option>
			                	<option value="3">토익</option>
			                	<option value="4">기타</option>
			                </select>
	            		</td>
	                </tr>
	                <tr>
	                    <td>팀장</td>
	                    <td>
	                    	<select name="t_leader" class="t_leader" >
	                    	  <c:forEach var="members" items="${MemberInfo}" varStatus="loop">
	                    		<option value="${members.userKey }">${members.nickname }</option>
                    		 </c:forEach>
	                    	</select>
                    	</td>
	                </tr>
	            </table>
                <input type="submit" value="수정">
                <button type="button" class=" reviseTeamInfo back" >취소</button>
        </div>
        <div id="dDay">
            <table>
                <tr>
                    <td style="text-decoration:underline;">D-DAY</td>
                </tr>
                <tr>
                    <td class="t_day" style="font-size:13px;">${dDay}</td>
                </tr>
                <tr>
                    <td class="dDate" style="font-size:40px; color:#ff580b;"></td>
                </tr>
            </table>
        </div>
        <div id="teamMenu">
            <ul>
                <li>그룹 게시판</li>
                <li id="newMemberAlert" onClick="reviseDday(${teamInfo.t_key})">D-Day 수정</li>
                <li onClick="resetChallenge(${teamInfo.t_key})">챌린지 수정 & 리셋</li>
                <li>투표하기</li>
                <c:if test="${anyAlarm == 0 }">
                <li id="newMemberAlert" onClick="newMemberRequest(${teamInfo.t_key})">스터디원 신청
                </c:if>
                <c:if test="${anyAlarm > 0 }">
                <li id="newMemberAlert" onClick="newMemberRequest(${teamInfo.t_key})" style="color:red; font-weight:bold">팀원 신청
                	<div id="alarm">${anyAlarm }</div>
                </c:if>
                </li>
                <c:if test="${userInfo.userKey eq teamInfo.userKey}">
                  <li onClick="leaveTeam(-1)">탈퇴하기</li>
                </c:if>
                <c:if test="${userInfo.userKey ne teamInfo.userKey}">
                <li onClick="leaveTeam(${teamInfo.t_key})">탈퇴하기</li>
                </c:if>
            </ul>
        </div>
    </div>
        <div class="LeaderMenu leadersWrite ">
           <div> ${teamInfo.nickName }님의 공지사항: ${teamInfo.t_lMemo }</div>
            <button type="button" class="reviseW rBtn"><i class="fa-solid fa-pencil"></i></button>
        </div>
        <div class="LeaderMenu reivseWrite hide">
                ${teamInfo.nickName }님의 공지사항: <input type="text" class="lMemo" required><br>
                <input type="button" class="rBtn2" value="수정">
                <input type="button" class="leBack" value="취소">
        </div>
        <div id="chWrapper">
            <div id="chaWrap">
                <div id="chaList">
                    <table>
                        <thead>
                            <tr>
                                <td colspan="2">나의 지난 팀 챌린지 보관함</td>
                            </tr>
                            <tr>
                                <td>타이틀</td>
                                <td>나의 달성률</td>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>매일 2L씩 물마시기</td>
                                <td>20/21</td>
                            </tr>
                            <tr>
                                <td>매일 2L씩 물마시기</td>
                                <td>20/21</td>
                            </tr>
                            <tr>
                                <td>매일 2L씩 물마시기</td>
                                <td>20/21</td>
                            </tr>
                            <tr>
                                <td>매일 2L씩 물마시기</td>
                                <td>20/21</td>
                            </tr>
                            <tr>
                                <td>매일 2L씩 물마시기</td>
                                <td>20/21</td>
                            </tr>
                            <tr>
                                <td>매일 2L씩 물마시기</td>
                                <td>20/21</td>
                            </tr>
                            <tr>
                                <td>매일 2L씩 물마시기</td>
                                <td>20/21</td>
                            </tr>
                            <tr>
                                <td>매일 2L씩 물마시기</td>
                                <td>20/21</td>
                            </tr>
                            <tr>
                                <td>매일 2L씩 물마시기</td>
                                <td>20/21</td>
                            </tr>
                            <tr>
                                <td>매일 2L씩 물마시기</td>
                                <td>20/21</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div id="memberDetail">
                <div class="photo"> <img src="https://ifh.cc/g/GCpQKq.png"><br>
                    <div><strong>${userInfo.nickName }</strong></div>
                </div>
                <div class="challenge">
                    <table>
                    	<input type="hidden" class="tc_key" value="${challenge.tc_key }">
                        <caption style="font-size:15px; text-decoration:underline">${challenge.tc_title }</caption>
	                      	<tr>
	                      	<c:forEach var="i" begin="0" end="6">
	                      		<c:if test="${myCurrent[i].tcs_key != null }">
	                      			<td class="confirm" onClick="showSummary(${myCurrent[i].tcs_key})"></td>
	                      		</c:if>
	                      		<c:if test="${myCurrent[i].tcs_key == null }">
	                      			<td></td>
	                      		</c:if>
	                      	</c:forEach>
	                      	</tr>
	                      	<tr>
	                      	<c:forEach var="i" begin="7" end="13">
	                      		<c:if test="${myCurrent[i].tcs_key != null }">
	                      			<td class="confirm" onClick="showSummary(${myCurrent[i].tcs_key})"></td>
	                      		</c:if>
	                      		<c:if test="${myCurrent[i].tcs_key == null }">
	                      			<td></td>
	                      		</c:if>
	                      	</c:forEach>
	                      	</tr>
	                      	<tr>
	                      	<c:forEach var="i" begin="14" end="20">
	                      		<c:if test="${myCurrent[i].tcs_key != null }">
	                      			<td class="confirm" onClick="showSummary(${myCurrent[i].tcs_key})"></td>
	                      		</c:if>
	                      		<c:if test="${myCurrent[i].tcs_key == null }">
	                      			<td></td>
	                      		</c:if>
	                      	</c:forEach>
	                      	</tr>
	                      </tbody>
                        <tr>
                            <td colspan="7"><input type="button" onClick="attendChallenge(${teamInfo.t_key})" value="SUBMIT"></td>
                        </tr>
                    </table>
                </div>
                <div id="TeamDaily">
                    <table class="TeamDailyMemo">
                    	<thead>
	                        <tr>
	                            <td>오늘의 요약!</td>
	                        </tr>
                        </thead>
                        <tbody>
                        
                        <tr>
                            <td style="font-size:13px;height:85px;max-height:85px;">
                            ${summary.tcs_summary}
                            </td>
                        <tr>
                            <td><input class="dailyRevBtn" type="button" value="수정하기"></td>
                        </tr>
                        </tbody>
                    </table>
                    <table class="TeamDailyRevise hide">
                    	<thead>
                        <tr>
                            <td>오늘의 요약!</td>
                        </tr>
                        <tr>
                            <td><textarea class="todaySummary"></textarea></td>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td><input type="button" class="returnBtn" value="취소"><input type="submit" value="수정"></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div id="memberWrap">
            <div id="memberInfo">
          <c:forEach var="member" items="${MemberInfo}" varStatus="loop">
            <div class="member">
              <c:if test="${member.userKey eq teamInfo.userKey }" >
                    <div class="king"><img src="https://ifh.cc/g/6bBq87.png"></div>
                </c:if>
                    <div class="photo">
                        <img src="https://ifh.cc/g/GCpQKq.png">
                    </div>
                    <div class="info leaderInfo">
                        <table>
                        <tr onClick="memberSummary(${member.userKey})">
                            <td style="max-width:90px; min-width:90px;">
                            	<strong>${member.nickname }</strong>
                            	<input type="hidden" class="tm_key" value="${member.tm_key }">
                            </td>
                            <td style="min-width:200px; max-width:200px;">${member.intro }</td>
                            <td style="min-width:50px; max-width:50px;">${PercentList.get(loop.index)}%</td>
                            <td style="min-width:100px; max-width:100px;">${member.lastTime2 }</td>
                            <td style="min-width:20px;">
                            <c:if test="${member.userKey ne teamInfo.userKey }" >
                                <button  class="kickOut" type="button" onclick="kickMemberOut(${member.tm_key})">
                                 <i class="fa-solid fa-user-slash"></i>
                                </button>
                            </c:if>
                            </td>
                        </tr>
                    </table>
                    </div>
                </div>
              </c:forEach>
            </div>
            <div id="memberSummary">
                    <div class="memberPhoto">
                        <img src="https://ifh.cc/g/GCpQKq.png">
                        <div style="text-align:center;"><strong>채여니</strong></div>
                    </div>
                    <div class="memberChallenge">
                        <table>
                            <caption style="font-size:15px; text-decoration:underline">${challenge.tc_title }</caption>
                            <tbody>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        <div id="teamSchedule">
            <div class="tCalendar">달력</div>
            <div class="tTimeLine">일정</div>
            <div class="tVote">
                <div class="vote1">투표1</div>
                <div class="vote2">투표2</div>
            </div>
            <div id="tSmallBoard">
                <table class="overflow" border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>제목</th>
                            <th>작성자</th>
                            <th>작성일</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>2</td>
                            <td class="overflow">음 너무 졸린데? 엽떡먹고 Lorem ipsum dolor sit amet consectetur, adipisicing elit. Voluptatibus, alias laudantium! Voluptate pariatur facere vitae adipisci modi odit fugiat sapiente porro maxime, soluta odio quisquam, ad tempora unde ab corporis? </td>
                            <td>Annie</td>
                            <td>2022.10.14 11:49AM</td>
                        </tr>
                        <tr>
                            <td>1</td>
                            <td class="overflow">음 너무 졸린데? 엽떡먹고 Lorem ipsum dolor sit amet consectetur, adipisicing elit. Voluptatibus, alias laudantium! Voluptate pariatur facere vitae adipisci modi odit fugiat sapiente porro maxime, soluta odio quisquam, ad tempora unde ab corporis? </td>
                            <td>Annie</td>
                            <td>2022.10.14 11:49AM</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
   	</div>
</body>
</html>