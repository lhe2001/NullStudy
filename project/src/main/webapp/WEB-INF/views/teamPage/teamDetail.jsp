<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
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
                        <td>팀 이름</td>
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
                        <td>팀장</td>
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
		                    <select class="t_field">
			                	<option value="1">코딩</option>
			                	<option value="2">자격증</option>
			                	<option value="3">토익</option>
			                	<option value="4">기타</option>
			                </select>
	            		</td>
	                </tr>
	                <tr>
	                    <td>팀장</td>
	                    <td>${teamInfo.nickName }</td>
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
                    <td style="font-size:40px; color:#ff580b;">-280</td>
                </tr>
            </table>
        </div>
        <div id="teamMenu">
            <ul>
                <li>팀 게시판</li>
                <li>일정등록</li>
                <li>D-Day 수정</li>
                <li>투표하기</li>
                <li>챌린지 수정</li>
                <c:if test="${anyAlarm == 0 }">
                <li id="newMemberAlert" onClick="newMemberRequest(${teamInfo.t_key})">팀원 신청
                </c:if>
                <c:if test="${anyAlarm > 0 }">
                <li id="newMemberAlert" onClick="newMemberRequest(${teamInfo.t_key})" style="color:red; font-weight:bold">팀원 신청
                	<div id="alarm">${anyAlarm }</div>
                </c:if>
                </li>
                <li>탈퇴하기</li>
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
        <div id="memberWrapper">
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
                        <tr>
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
            <div id="memberDetail">
                <div class="photo"> <img src="https://ifh.cc/g/GCpQKq.png"><br>
                    <div><strong>혜으니</strong></div>
                </div>
                <div class="challenge">
                    <table>
                        <caption style="font-size:15px; text-decoration:underline">매일 물 한잔</caption>
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
                            <td class="confirm"></td>
                            <td class="confirm"></td>
                            <td class="confirm"></td>
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
                            <td style="font-size:13px;height:85px;max-height:85px;">ure. Sapiente iure non nulla autem, ipsa ut inventore minima pariatur labore.</td>
                        </tr>
                        <tr>
                            <td><input class="dailyRevBtn" type="button" value="수정하기"></td>
                        </tr>
                    </table>
                    <form>
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
                    </form>
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