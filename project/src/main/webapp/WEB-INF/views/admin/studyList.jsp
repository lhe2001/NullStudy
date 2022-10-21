<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<link rel="stylesheet" href="resources/css/admin(study).css" />
 
 <script>
 window.addEventListener("load", memberListOnload);

 function memberListOnload(){
	  select();
 }
        function select(){
            $(".searchbox").off("change").on("change",function(){
                let selected = $(".searchbox").val();
                console.log(selected)

                if(selected == "teamName"){

                    $(".searchDefault").addClass("hide")
                    $(".searchTeamName").removeClass("hide");
                    $(".searchTeamLeader").addClass("hide")
                    $(".searchTeamInfo").addClass("hide")   

                }else if(selected == "teamleader"){

                    $(".searchDefault").addClass("hide")
                    $(".searchTeamName").addClass("hide");
                    $(".searchTeamLeader").removeClass("hide")
                    $(".searsearchTeamInfochBoth").addClass("hide")

                }else if(selected == "teamInfo"){

                    $(".searchDefault").addClass("hide")
                    $(".searchTeamName").addClass("hide");
                    $(".searchTeamLeader").addClass("hide")
                    $(".searchTeamInfo").removeClass("hide")

                }

            })
        }

    </script>   
    
<div class = "slWrap">

	<div class = "selectWrap">
		<select class="searchbox" name="selectSearch">	
			<option value="default" selected disabled>선택</option>
			<option value="teamName">스터디명</option>
			<option value="teamleader">조장(아이디or이름)</option>
			<option value="teamInfo">팀소개</option>
		</select>
		
		<form class="searchbar searchDefault">
			<input type="text" class="input" name="default" disabled placeholder="검색범위를 선택해주세요">
		</form>
		    
		<form class="searchbar searchTeamName hide" action="/project/searchByTeamName.do" >
			<input type="text" class="input" name="teamName" placeholder="">
			<input type="submit" class="submit" value="검색">
		</form>
		    
		<form class="searchbar searchTeamLeader hide" action="/project/searchByTeamLeader.do" >
			<input type="text" class="input" name="teamLeader">
			<input type="submit" class="submit" value="검색">
		</form>
		
		<form class="searchbar searchTeamInfo hide" action="/project/searchByTeamInfo.do" >
			<input type="text" class="input" name="teamInfo" placeholder="">
			<input type="submit" class="submit" value="검색">
		</form>
		<form action="/project/studyList.do">
			<input type="submit" class="submitAll" value="전체조회">
		</form>
	</div>

<br><br>

	<div class="tbWrap">	
	    <table>
	    	<thead>
	    		<tr>
		    	<th>팀명</th>
				<th>팀장</th>
				<th>팀장아이디</th>
				<th>팀소개</th>
				<th>팀페이지</th>
				</tr>
	    	</thead>
	    	<tbody>
	    	<c:if test="${empty list}">
	    		<script>
	    			alert("검색결과가 없습니다.")
	    			location.href='/project/studyList.do';
	    		</script>
	    	</c:if>
	    	<c:forEach var="studyList" items="${list }">
	    		<tr>
	    		<td>${studyList.t_name }</td>
	    		<td>${studyList.name }</td>
	    		<td>${studyList.id }</td>
	    		<td>${studyList.t_intro }</td>
	    		<td><a href="/project/team/teamDetail.do?t_key=${studyList.t_key}">이동</a></td>
	    		</tr>
	    	</c:forEach>
	    	</tbody>
	    </table>
	</div>
</div>