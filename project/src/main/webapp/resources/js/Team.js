window.addEventListener("load", teamInfoOnload);
   function teamInfoOnload(){
    console.log(111)
   	reviseTeamInfo();
    leWrite();
    dailyMemoRevise();
    dDay();
   }

//팀정보 수정
function reviseTeamInfo(){

    document.querySelector(".revise").addEventListener("click",function(){
        console.log("click")
        document.querySelector("#reviseTeam").classList.remove("hide");
        document.querySelector("#teamInfo").classList.add("hide");
    })
    document.querySelector(".back").addEventListener("click",function(){
        document.querySelector("#reviseTeam").classList.add("hide");
        document.querySelector("#teamInfo").classList.remove("hide");
    })
    document.querySelector("#reviseTeam input[type='submit']").addEventListener("click",function(){
        const revise = confirm('수정 하시겠습니까?');
    	let t_key = $(".t_key").val();
    	let t_intro = $(".t_intro").val();
    	let t_field = $(".t_field").val();
    	let userKey = $(".t_leader").val();
    	
        if(revise == true){
        	if(t_intro.trim() == ''){
        		alert('빈칸을 입력해주세요');
        	}else {
	        	let info = { 
	        			t_key : t_key,
	        			t_intro : t_intro,
	        			t_field : t_field,
	        			userKey : userKey 
	        	};
	        	$.ajax({
					url: "/project/teamRest/updateTeamInfo.do",
					type: "post",
					contentType: "application/json",
					data: JSON.stringify(info),
					success: function(data){
						location.reload();
					},
					error:function(){
						alert("에러발생!!")
					}
				});
        	}
        }
    })
   }

//조장한마디 수정
function leWrite(){
    document.querySelector(".reviseW").addEventListener("click",function(){
        document.querySelector(".reivseWrite").classList.remove("hide");
        document.querySelector(".leadersWrite").classList.add("hide");
    })
    document.querySelector(".leBack").addEventListener("click",function(){
        document.querySelector(".reivseWrite").classList.add("hide");
        document.querySelector(".leadersWrite").classList.remove("hide");
    })
    document.querySelector(".reivseWrite .rBtn2").addEventListener("click",function(event){
        event.preventDefault();
        const revise2 = confirm('수정 하시겠습니까???')
        if(revise2 == true){
        	let t_key = $(".t_key").val();
        	let t_lMemo = $(".lMemo").val();
        	
        	let info = { 
        			t_key : t_key,
        			t_lMemo : t_lMemo 
        	};
        	
        	$.ajax({
			url: "/project/teamRest/updateTeamInfo.do",
			type: "post",
			contentType: "application/json",
			data: JSON.stringify(info),
			success: function(data){
				location.reload();
			},
			error:function(){
				alert("에러발생!!")
			}
			});
        }
    })
}

//멤버강퇴
function kickMemberOut(tm_key){
	 const revise3 = confirm('정말 강퇴 시키시겠습니까?')
	 let t_key = $(".t_key").val();
	 if(revise3 == true){
        	console.log(tm_key);
        	let info = { 
        			tm_key : tm_key,
        			t_key : t_key
        	};
        	
        	$.ajax({
			url: "/project/teamRest/removeMember.do",
			type: "post",
			contentType: "application/json",
			data: JSON.stringify(info),
			success: function(data){
				location.reload();
			},
			error:function(){
				alert("에러발생!!")
			}
			});
        }
}

//오늘의 요약 수정
function dailyMemoRevise(){
    document.querySelector(".dailyRevBtn").addEventListener("click",function(){
        document.querySelector(".TeamDailyMemo").classList.add("hide");
        document.querySelector(".TeamDailyRevise").classList.remove("hide");
    })
    document.querySelector(".returnBtn").addEventListener("click",function(){
        document.querySelector(".TeamDailyMemo").classList.remove("hide");
        document.querySelector(".TeamDailyRevise").classList.add("hide");
    })
    document.querySelector(".TeamDailyRevise input[type='submit']").addEventListener("click",function(){
        const dele = confirm('수정 하시겠습니까?')
        console.log(dele);
    })
}


//팀원 신청
function newMemberRequest(t_key){
	window.open('/project/team/newRequest.do?t_key='+t_key,'pop','location=no,width=500,height=300,top=100,left=100,history=no,resizable=no,status=no,scrollbars=yes,menubar=no');
}

//팀삭제
function delTeam(){
	const del = confirm('정말 이 팀을 삭제하시겠습니까?');
	
	if(del == true){
		let frmDel = document.frmDel;
		frmDel.method="post";
		frmDel.action="/project/team/delTeam.do";
		frmDel.submit();
	}
}

//팀 탈퇴하기
function leaveTeam(t_key){
	if(t_key == -1){
		alert('스터디장은 탈퇴 하실 수 없습니다. 스터디장을 변경해주세요');
	}else {
		const leave = confirm('정말 탈퇴하시겠습니까?');
		if(leave == true){
	    	location.href='/project/team/leaveTeam.do?t_key='+t_key;
	    }
	}
}

//디데이
function dDay(){
   let now = new Date();
   let t_day = $(".t_day").text();
   if(t_day == ''){
   	t_day= now;
   }
   let then = new Date(t_day);
   let gap = then.getTime() - now.getTime();
   gap = Math.floor(gap / (1000 * 60 * 60 * 24)) * -1;
   $("#dDay .dDate").text(gap);
}

//디데이 수정
function reviseDday(t_key){
	window.open('/project/team/reviseDday.do?t_key='+t_key,'pop','location=no,width=540,height=300,top=100,left=50,history=no,resizable=no,status=no,scrollbars=yes,menubar=no');
}

//챌린지 수정
function resetChallenge(t_key){
	window.open('/project/team/updateChallenge.do?t_key='+t_key,'pop','location=no,width=500,height=400,top=100,left=50,history=no,resizable=no,status=no,scrollbars=yes,menubar=no');
}
//챌린지에 출석하기
function attendChallenge(t_key){
	let tc_key = $(".tc_key").val();
	let info = { 
        			t_key : t_key,
        			tc_key : tc_key
        	};
        	
        	$.ajax({
			url: "/project/teamRest/attendChallenge.do",
			type: "post",
			contentType: "application/json",
			data: JSON.stringify(info),
			success: function(data){
				alert('오늘의 챌린지 완료!');
				location.reload();
			},
			error:function(){
				alert("에러발생!!")
			}
			});
}
//챌린지 선택
function attendChallenge(t_key){
	let tc_key = $(".tc_key").val();
	let info = { 
        			t_key : t_key,
        			tc_key : tc_key
        	};
        	
        	$.ajax({
			url: "/project/teamRest/attendChallenge.do",
			type: "post",
			contentType: "application/json",
			data: JSON.stringify(info),
			success: function(data){
				alert('오늘의 챌린지 완료!');
				location.reload();
			},
			error:function(){
				alert("에러발생!!")
			}
			});
}
//서머리 보기
function showSummary(tcs_key){
	let info = { 
        			tcs_key : tcs_key
        	};
        	
        	$.ajax({
			url: "/project/teamRest/showSummary.do",
			type: "post",
			contentType: "application/json",
			data: JSON.stringify(info),
			success: function(data){
				$(".TeamDailyMemo tbody").empty();
				
				let html = "";
				html += "<tr>";
				html += "<td style='font-size:13px;height:85px;max-height:85px;'>";
				html +=  data.tcs_summary;
				html += "</td>";
				html += "</tr>";
				html += "<tr>";
				html += "<td><input class='dailyRevBtn' type='button' value='수정하기' ></td>";
				html += "</tr>";
				
				$(".TeamDailyMemo tbody").append(html);
				
			},
			error:function(){
				alert("에러발생!!")
			}
			});
}
//멤버 서머리 보기 
function memberSummary(userKey){
	let tc_key = $(".tc_key").val();
	let info = { 
        			userKey : userKey,
        			tc_key : tc_key
        	};
        	
        	$.ajax({
				url: "/project/teamRest/memberSummary.do",
				type: "post",
				contentType: "application/json",
				data: JSON.stringify(info),
				success: function(data){
				console.log(data);
				
					$(".memberChallenge table tbody").empty();
					
					let html="";
					if(data.length == 1 && data[0] == -1){
						for(let i=0; i<3; i++){
							html +="<tr>";
							for(let j=0; j<7; j++){
								html +="<td></td>";
							}
							html +="</tr>";
						}
					html += "<tr><td colspan='7' style='height:40px;text-align: center;'>아직 시작하지 않았어요</td></tr>";
						
						$(".memberChallenge table tbody").prepend(html);
						
					}else {
						
						html += "<tr>";
						for(let i=0; i<7; i++){
							html+="<td>";
							if(data[i] != undefined){
								html+="<input type='hidden' class='memberTcs' value='"+data[i].tcs_key+"'>";
							}
							html+="</td>";
						}
						html += "</tr>";
						html += "<tr>";
						for(let i=7; i<14; i++){
							html+="<td>";
							if(data[i] != undefined){
								html+="<input type='hidden' class='memberTcs' value='"+data[i].tcs_key+"'>";
							}
							html+="</td>";
						}
						html += "</tr>";
						for(let i=14; i<21; i++){
							html+="<td>";
							if(data[i] != undefined){
								html+="<input type='hidden' class='memberTcs' value='"+data[i].tcs_key+"'>";
							}
							html+="</td>";
						}
						html += "</tr>";
						html += "<tr><td colspan='7' style='height:40px;text-align: center;'>시작했어요</td></tr>";
						
						$(".memberChallenge table tbody").prepend(html);
					}
				},
				error:function(){
					alert("에러발생!!")
				}
			});
}
