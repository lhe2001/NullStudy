
//>>>>>>>>>>>>>>>>모든팀 목록>>>>>>>>>>>>>>>//
function teamPreview(t_key){
     // 로그인 버튼을 누르면 모달창 뜨기
        
    	let info = { 
    			t_key : t_key 
    	};
    	
    	$.ajax({
		url: "/project/teamRest/memberList.do",
		type: "post",
		contentType: "application/json",
		data: JSON.stringify(info),
		success: function(data){
			$("#modalMember tbody").empty();
			console.log(data)
			
			html ="";
			html+="<tr>";
	        html+="		<td>팀 이름</td>";
	        html+=" 	<td>"+data.teamInfo.t_name+"</td>";
	        html+="</tr>";
	        html+="<tr>";
	        html+="     <td>팀소개</td>";
	        html+="     <td>"+data.teamInfo.t_intro+"</td>";
	        html+="</tr>";
	        html+="<tr>";
	        html+="     <td>인원수</td>";
	        html+="     <td>"+data.teamInfo.t_number+"</td>";
	        html+="</tr>";
	        
	        $("#modalMember tbody").append(html);
	        
	        $("#memberPreview table").empty();
			
			for(let i=0; i<data.memberList.length;i++){
				html2 ="";
				html2+="<tr>";
		        html2+="		<td style='width:30px; border-radius: 50%;'>";
		        html2+=" 		<img src='https://ifh.cc/g/GCpQKq.png'>";
		        html2+="		</td>";
		        html2+="		<td>"+data.memberList[i].nickname+"</td>";
		        html2+="     	<td>"+data.memberList[i].intro+"</td>";
		        html2+="</tr>";
		        
		        $("#memberPreview table").append(html2);
			}
	        
        	$("#modal").css({'display':'flex'});
        	$("#modalMsg div").empty();
        	
	        if(data.isMyTeam == true){
	        	console.log("왜??");
	        	$(".joinBtn").attr("value","팀 페이지 가기");
	        	$(".joinBtn").attr("onClick","location.href='/project/team/teamDetail.do?t_key="+t_key+"'");
	        	$("#modalMsg div").prepend("이미 가입된 팀입니다");
	        }else {
	        	$(".joinBtn").attr("value","가입하기");	
	        	$(".joinBtn").attr("onClick","아니");
	        }
		},
		error:function(){
			alert("에러발생!!")
		}
		});
     
     //모달창 안의 x 버튼 누르면 모달창 꺼지기
     $("#modal button").off("click").on("click",function(){
         $("#modal").css({'display':'none'});
        });
    //모달창에서 바깥 영역을 클릭하면 모달창이 꺼지기.
    $("#modal").off("click").on("click",function(e){
        let target = e.target;
        if(target.classList.contains("modal")){
            $("#modal button").trigger("click");
        }
    });
}





