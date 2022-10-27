
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
	        	$(".joinBtn").attr("value","가입요청");	
	        	$(".joinBtn").attr("onClick","requestJoin("+t_key+")");
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
//필드값으로 찾기
function allTeamFilter(){
	let t_field = $(".t_field").val();
	$("tbody").empty();
	let info = {
			t_field : t_field
	}
	$.ajax({
		url: "/project/teamRest/allTeamFilter.do",
		type: "post",
		contentType: "application/json",
		data: JSON.stringify(info),
		success: function(data){
			console.log(data);
			
			let html = "";
			if(data.length == 0){
				html += "<tr><td colspan='6' style='text-align:center;'>검색 결과가 없습니다.</td></tr>"
			}else{
				for(let i=0; i<data.length;i++){
					html +="<tr>";
					html += "<td style='max-width:30px;'><div>"+data[i].t_field2+"</div></td>";
	 				html += "<td style='max-width:100px;'><div>"+data[i].t_name+"</div></td>";
					html += "<td style='max-width:170px;'><div>"+data[i].t_intro+"</div></td>";
	 				html += "<td style='max-width:50px;'><div>"+data[i].t_number+"</div></td>";
	 				html += "<td style='max-width:50px;'><div>"+data[i].nickName+"</div></td>";
					html += '<td><button type="button" class="memberBtn" onClick="teamPreview('+data[i].t_key+')">팀 정보</button></td>';
					html +="</tr>";
					
				}
			}
					$("tbody").append(html);
		},
		error:function(){
			alert("에러발생!!")
		}
	});
}
//팀명 검색
function teamSearch(){
	let t_name = $(".tSearchText").val();
	
	$("tbody").empty();
	
	let info = {
			t_name : t_name
	}
	
	$.ajax({
		url: "/project/teamRest/allTeamFilter.do",
		type: "post",
		contentType: "application/json",
		data: JSON.stringify(info),
		success: function(data){
			console.log(data);
			console.log(data.length);
			
			$("tbody").empty();
			
			let html = "";
			if(data.length == 0){
				html += "<tr><td colspan='6' style='text-align:center;'>검색 결과가 없습니다.</td></tr>"
			}
				
			for(let i=0; i<data.length;i++){
				html +="<tr>";
				html += "<td style='max-width:30px;'><div>"+data[i].t_field2+"</div></td>";
 				html += "<td style='max-width:100px;'><div>"+data[i].t_name+"</div></td>";
				html += "<td style='max-width:170px;'><div>"+data[i].t_intro+"</div></td>";
 				html += "<td style='max-width:50px;'><div>"+data[i].t_number+"</div></td>";
 				html += "<td style='max-width:50px;'><div>"+data[i].nickName+"</div></td>";
				html += '<td><button type="button" class="memberBtn" onClick="teamPreview('+data[i].t_key+')">팀 정보</button></td>';
				html +="</tr>";
			}
				$("tbody").append(html);
		},
		error:function(){
			alert("에러발생!!")
		}
	});
	
}
//>>>>>>>>>>>>>>>>모달창 에서 가입요청>>>>>>>>>>>>>>>//

function requestJoin(t_key){
	
	let info = { 
    			t_key : t_key 
    	};
    	
    	$.ajax({
			url: "/project/teamRest/memberRequest.do",
			type: "post",
			contentType: "application/json",
			data: JSON.stringify(info),
			success: function(data){
				console.log(data)
				if(data >= 1){
					console.log('성공!')
					alert('가입요청이 전송되었습니다');
				}else {
					alert('이미 전송된 요청입니다. 기다려주세요');
				}
			},
			error:function(){
				alert("에러발생!!")
			}
		});

}




