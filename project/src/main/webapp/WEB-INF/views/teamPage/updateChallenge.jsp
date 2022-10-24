<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/project/resources/css/team.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://kit.fontawesome.com/f5483c894c.js" crossorigin="anonymous"></script>
<script>
	function upChallenge(t_key){
		const con = confirm("현재 챌린지의 타이틀을 수정하시겠습니까?");
		if(con == true){
			let tc_key = ${current.tc_key};
			let tc_title = $(".tc_title").val();
			console.log(tc_key+'.'+tc_title)
			
			if(tc_title.trim() == ''){
				alert('새로운 타이틀을 입력해주세요');
			}else{
				let info = {
						tc_key : tc_key,
						tc_title : tc_title
				}
				
				$.ajax({
					url: "/project/teamRest/updateChallengeTitle.do",
					type: "post",
					contentType: "application/json",
					data: JSON.stringify(info),
					success: function(data){
						location.reload();
						opener.location.reload();
					},
					error:function(){
						alert("에러발생!!")
					}
				});
			}
		}
		
	}
	function newChallenge(){
		const con = confirm("새로운 챌린지로 변경 하시겠습니까? 기존의 챌린지는 자동으로 종료됩니다.");
		if(con == true){
		let t_key = $(".t_key").val();
		let tc_title = $(".newtc_title").val();
			let info = {
					t_key : t_key,
					tc_title : tc_title
			}
			$.ajax({
				url: "/project/teamRest/newChallenge.do",
				type: "post",
				contentType: "application/json",
				data: JSON.stringify(info),
				success: function(data){
					opener.location.reload();
					location.reload();
				},
				error:function(){
					alert("에러발생!!")
				}
			});
		}
	}
</script>
</head>
<body>
<body>
    <div id="updateChalWrap">
           <table class="chalTable">
               <tr>
                   <td>현재 타이틀</td>
                   <td><input type="text" value="${current.tc_title }" disabled></td>
               </tr>
               <tr>
                   <td>새 타이틀</td>
                   <td><input type="text" class="tc_title"></td>
               </tr>
               <tr>
                   <td colspan="2"><input type="button" onClick="upChallenge(${t_key})" value="수정"></td>
               </tr>
           </table>
         <input type="hidden" class="t_key" value="${t_key }">
            <table class="chalTable">
                <tr>
                    <td>타이틀</td>
                    <td><input type="text" class="newtc_title"></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="button" value="New챌린지" onClick="newChallenge()"></td>
                </tr>
            </table>
    </div>
</body>
</body>
