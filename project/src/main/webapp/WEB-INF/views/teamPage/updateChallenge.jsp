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
		
	}
	function newChallenge(){
		const con = confirm("새로운 챌린지로 변경 하시겠습니까? 기존의 챌린지는 자동으로 종료됩니다.");
		if(con == true){
		let t_key = $(".t_key").val();
		let tc_title = $(".tc_title").val();
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
					alert(data);
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
                   <td>기존 타이틀</td>
                   <td><input type="text"></td>
               </tr>
               <tr>
                   <td>새 타이틀</td>
                   <td><input type="text"></td>
               </tr>
               <tr>
                   <td colspan="2"><input type="button" onClick="upChallenge(${t_key})" value="수정"></td>
               </tr>
           </table>
         <input type="hidden" class="t_key" value="${t_key }">
            <table class="chalTable">
                <tr>
                    <td>타이틀</td>
                    <td><input type="text" class="tc_title"></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="button" value="New챌린지" onClick="newChallenge()"></td>
                </tr>
            </table>
        <button type="button"><i class="fa-solid fa-arrow-rotate-left"></i><br>
            현재 챌린지 리셋
        </button>
    </div>
</body>
</body>
</html>