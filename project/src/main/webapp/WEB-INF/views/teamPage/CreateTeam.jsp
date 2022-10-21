<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<head>
<link href="/project/resources/css/team.css" rel="stylesheet">
<script>
function checkt_name(){
	let t_name = $("#teamName").val().trim();
	if(t_name == ''){
		alert('스터디명을 입력해주세요');
	}else{
		let info = {
				t_name : t_name
		}
		$.ajax({
			url: "/project/teamRest/checkTeamName.do",
			type: "post",
			contentType: "application/json",
			data: JSON.stringify(info),
			success: function(data){
				if(data){
					alert('이미 있는 이름입니다.');
				}else{
					alert('사용 가능한 이름입니다.');
					$("input[type='submit']").prop("disabled",false);
					$("#t_nameCheck").prop("disabled",true);
				}
			},
			error:function(){
				alert("에러발생!!")
			}
			});
	}
}
function resetBtn(){
	$("input[type='submit']").prop("disabled",true);
	$("#t_nameCheck").prop("disabled",false);
}
</script>
</head>
<body>
<div class="crewrap">
	<form name="createTeam" method="post" action="/project/team/newTeam.do">
		<table id="creatteamtable" >
		    <tr>
		        <th colspan="2">스터디만들기</th>
		    </tr>
		    <tr>
		        <td>
		            본인 ID
		        </td>
		        <td>
		            <input type="text" class="cre_inputs"  name="id" value="${userInfo.id }" disabled>
		            <input type="hidden" name="userKey" value="${userInfo.userKey }">
		        </td>
		    </tr>
		    <tr>
		        <td>
		            스터디명
		        </td>
		        <td>
		            <input type="text" class="cre_inputs" onChange="resetBtn()" id="teamName" name="t_name" placeholder="대소문자 구분"required >
		            <input type="button" id="t_nameCheck" onClick="checkt_name()" value="중복체크">
		        </td>
		    </tr>
		    <tr>
		        <td>
		            10자 소개
		        </td>
		        <td>
		            <input type="text" class="cre_inputs" name="t_intro" >
		        </td>
		    </tr>
		    <tr>
		        <td>
		            분야
		        </td>
		        <td>
		            <select name="t_field">
		                <option value="1">코딩</option>
		                <option value="2">자격증</option>
		                <option value="3">토익</option>
		                <option value="4">기타</option>
		            </select>
		        </td>
		    </tr>
		</table>
		<input type="button" value="취소" onclick="history.back()" class="cre_btn">
		<input type="submit" class="cre_btn" value="개설" disabled>
	</form>
</div>
</body>