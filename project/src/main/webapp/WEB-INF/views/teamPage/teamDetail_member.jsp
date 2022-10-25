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
<script src="/project/resources/js/Team_member.js" ></script>
</head>
<body>
	<div id="team_Wrapper">
	    <div id="fstWrapper">
            <div id="teamInfo" class="teamInfoBox ">
		    <input type="hidden" name="t_key" class="t_key" value="${teamInfo.t_key }">
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
                <li id="newMemberAlert" onClick="reviseDday(${teamInfo.t_key})">D-Day 수정</li>
                <li onClick='alert("스터디장만 할 수 있습니다.")' style='cursor:not-allowed;'>챌린지 수정 & 리셋</li>
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
                        <c:forEach var="history" items="${myHistory }">
                            <tr onClick="history(${history.tc_key},this)">
                                <td class="history_title">${history.tc_title}</td>
                                <td>${history.length}/21</td>
                            </tr>
                         </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <div id="memberDetail">
                <div class="photo"> 
                <c:if test="${userInfo.photo == null}">
                        <img src="https://ifh.cc/g/GCpQKq.png"><br>
                        </c:if>
                        <c:if test="${userInfo.photo != null}">
                        <img src="/project/team/download.do?userkey=${userInfo.userKey }"><br>
                        </c:if>
                    <div><strong>${userInfo.nickName }</strong></div>
                </div>
                <div class="challenge">
                    <table>
                    	<input type="hidden" class="tc_key" value="${challenge.tc_key }">
                        <caption style="font-size:15px; text-decoration:underline">${challenge.tc_title }</caption>
                        <tbody>
	                      	<tr>
	                      	<c:forEach var="i" begin="0" end="6">
	                      		<c:if test="${myCurrent[i].tcs_key != null }">
	                      			<td class="confirm" onClick="showSummary(${myCurrent[i].tcs_key},this)"></td>
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
                        <tr>
                            <td colspan="7"><input type="button" onClick="attendChallenge(${teamInfo.t_key})" value="SUBMIT"></td>
                        </tr>
	                      </tbody>
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
                        	<tr id="todaySummary">
                        	</tr>
                        	<tr>
								<td><input class='dailyRevBtn' type='button' value='수정하기' ></td>
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
                        </tr>
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
                    	<c:if test="${member.photo == null}">
                        <img src="https://ifh.cc/g/GCpQKq.png">
                        </c:if>
                        <c:if test="${member.photo != null}">
                        <img src="/project/team/download.do?userkey=${member.userKey }">
                        </c:if>
                    </div>
                    <div class="info leaderInfo">
                        <table>
                        <tr onClick="memberSummary(${member.userKey},this)">
                            <td style="max-width:90px; min-width:90px;">
                            	<strong>${member.nickname }</strong>
                            	<input type="hidden" class="tm_key" value="${member.tm_key }">
                            </td>
                            <td style="min-width:200px; max-width:200px;">${member.intro }</td>
                            <td style="min-width:100px; max-width:100px;">${member.lastTime2 }</td>
                            <td style="min-width:20px;">
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
                        <div style="text-align:center;"><strong id="memNick"></strong></div>
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
       
   	</div>
</body>
</html>