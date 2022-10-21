<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<link rel="stylesheet" href="resources/css/admin(member).css" />
    
 <script>
 window.addEventListener("load", memberListOnload);

  function memberListOnload(){
	  select();
  }
 
 
        function select(){
            $(".searchbox").off("change").on("change",function(){
                let selected = $(".searchbox").val();
                console.log(selected)

                if(selected == "name"){

                    $(".searchDefault").addClass("hide")
                    $(".searchName").removeClass("hide");
                    $(".searchId").addClass("hide")
                    $(".searchBoth").addClass("hide")   

                }else if(selected == "id"){

                    $(".searchDefault").addClass("hide")
                    $(".searchName").addClass("hide");
                    $(".searchId").removeClass("hide")
                    $(".searchBoth").addClass("hide")

                }else if(selected == "both"){

                    $(".searchDefault").addClass("hide")
                    $(".searchName").addClass("hide");
                    $(".searchId").addClass("hide")
                    $(".searchBoth").removeClass("hide")

                }

            })
        }

    </script>   
    
<div class="mlWrap">
	<div class="selectWrap">
		<select class="searchbox" name="selectSearch">	
			<option value="default" selected disabled>선택</option>
			<option value="name">이름</option>
			<option value="id">아이디</option>
			<option value="both">이름+아이디+닉네임</option>
		</select>
	
		<form class="searchbar searchDefault">
			<input type="text" class="input" name="default" disabled placeholder="검색범위를 선택해주세요">
		</form>
		
		<form class="searchbar searchName hide" action="/project/searchByName.do" >
			<input type="text" class="input"  name="name" placeholder="">
			<input type="submit" class="submit" value="검색">
		</form>
		
		<form class="searchbar searchId hide" action="/project/searchById.do" >
			<input type="text" class="input" name="id">
			<input type="submit" class="submit" value="검색">
		</form>
		
		<form class="searchbar searchBoth hide" action="/project/searchByBoth.do" >
			<input type="text" class="input" name="both" placeholder="">
			<input type="submit" class="submit" value="검색">
		</form>
		<form action="/project/memberList.do">
			<input type="submit" class="submitAll" value="전체조회">
		</form>
	</div>
<br><br>
	<div class="tbWrap">
    <table>
    	<thead>
    		<tr>
	    	<th>USERKEY</th>
			<th>ID</th>
			<th>NAME</th>
			<th>SEX</th>
			<th>NICKNAME</th>
			<th>EMAIL</th>
			<th>JOINDATE</th>
			<th>INTRO</th>
			<th>LASTTIME</th>
			<th>수정</th>
			<th>삭제</th>
			</tr>
    	</thead>
    	<tbody>
    	<c:if test="${empty list}">
    		<script>
    			alert("검색결과가 없습니다.")
    			location.href='/project/memberList.do';
    		</script>
    	</c:if>
    	<c:forEach var="memberList" items="${list }">
    		<tr>
    		<td>${memberList.userKey }</td>
    		<td>${memberList.id }</td>
    		<td>${memberList.name }</td>
    		<td>${memberList.sex }</td>
    		<td>${memberList.nickName }</td>
    		<td>${memberList.email }</td>
    		<td>${memberList.joinDate }</td>
    		<td>${memberList.intro }</td>
    		<td>${memberList.lastTime }</td>
    		<td><a href='/project/modForm.do?id=${memberList.id}'>수정</a></td>
    		<td><a href='/project/delMember.do?id=${memberList.id}' onclick="return confirm('정말로 삭제하시겠습니까?');">삭제</a></td>
    		
    		</tr>
    	</c:forEach>
    	</tbody>
    </table>
    </div>
</div>