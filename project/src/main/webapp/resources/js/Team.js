window.addEventListener("load", teamInfoOnload);
   function teamInfoOnload(){
    console.log(111)
   	reviseTeamInfo();
    leWrite();
    dailyMemoRevise();
    dDay();
    getMemberSummary();
    preClick();
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
		$(document).on('click',".dailyRevBtn",function(){
			let tcs_key = $(".tcs_key").val();
			if(tcs_key == undefined){
				alert('챌린지를 먼저 진행해주세요!');
			}else{
		        document.querySelector(".TeamDailyMemo").classList.add("hide");
		        document.querySelector(".TeamDailyRevise").classList.remove("hide");
		    }
	    })
	    $(document).on('click',".returnBtn",function(){
	        document.querySelector(".TeamDailyMemo").classList.remove("hide");
	        document.querySelector(".TeamDailyRevise").classList.add("hide");
	    })
	    $(document).on('click',".TeamDailyRevise input[type='submit']",function(){
	        const dele = confirm('수정 하시겠습니까?')
	        if(dele == true){
	        	let tcs_key = $(".tcs_key").val();
	        	let tcs_summary = $(".todaySummary").val();
	        	
	        	if(tcs_summary.trim() == ''){
	        		alert('입력해주세요');
	        	}else {
		        	let info = {
		        		tcs_key : tcs_key,
		        		tcs_summary : tcs_summary
		        	};
		        	
		        	$.ajax({
						url: "/project/teamRest/reviseSummary.do",
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
//미리 클릭해두기
function preClick(){
	let mySummary = $(".challenge table .confirm");
	let total = mySummary.length;
	if(total > 0){
		let lastOne = (total-1);
		mySummary[lastOne].click();
	}
	
	let member = $(".member table tr");
	member[0].click();
}
//서머리 보기
function showSummary(tcs_key,e){

	$(".challenge table td").css('border','1px solid #b5b5e4');
	$(e).css('border','1px solid #412498');
	
	let info = { 
        			tcs_key : tcs_key
        	};
        	
        	$.ajax({
			url: "/project/teamRest/showSummary.do",
			type: "post",
			contentType: "application/json",
			data: JSON.stringify(info),
			success: function(data){
				$(".TeamDailyMemo tbody #todaySummary").empty();
				
				let html = "";
				html += "<td style='font-size:13px;height:85px;max-height:85px;'>";
				html += "<input type='hidden' class='tcs_key' value='"+data.tcs_key+"'>";
				html +=  data.tcs_summary;
				html += "</td>";
				
				$(".TeamDailyMemo tbody #todaySummary").append(html);
				
			},
			error:function(){
				alert("에러발생!!")
			}
			});
}
//지난 챌린지 보관함 click 시
function history(tc_key,e){
	let tc_title = e.querySelector(".history_title").innerText;
	
	$(".challenge caption").empty();
	$(".challenge caption").append(tc_title);

	let info = { 
        			tc_key : tc_key
        	};
        	
        	$.ajax({
				url: "/project/teamRest/history.do",
				type: "post",
				contentType: "application/json",
				data: JSON.stringify(info),
				success: function(data){
					$(".challenge table tbody").empty();
				
				html = "";
				//html = "<input type='hidden' class='tc_key' value="+data[0].tc_key+">";
				//html = "<caption style='font-size:15px; text-decoration:underline'>dddd</caption>";
				html +="<tr>";
				for(let i=0; i<7; i++){
					if(data[i] != undefined){
						html+="<td class='confirm' onClick='showSummary("+data[i].tcs_key+",this)'></td>";
					}else {
						html+="<td>";
					}
					html+="</td>";
				}
				html += "</tr>";
				html += "<tr>";
				for(let i=7; i<14; i++){
					if(data[i] != undefined){
						html+="<td class='confirm' onClick='showSummary("+data[i].tcs_key+",this)'></td>";
					}else {
						html+="<td>";
					}
					html+="</td>";
				}
				html += "</tr>";
				html += "<tr>";
				for(let i=14; i<21; i++){
					if(data[i] != undefined){
						html+="<td class='confirm' onClick='showSummary("+data[i].tcs_key+",this)'></td>";
					}else {
						html+="<td>";
					}
					html+="</td>";
				}
				html += "</tr>";
				html += "<tr>";
				html += "<td colspan='7'><input type='button' value='SUBMIT'disabled></td>";
				html += "</tr>";
	            
					$(".challenge table tbody").append(html);
					$(".dailyRevBtn").prop("disabled",true);
					
				},
				error:function(){
					alert("에러발생!!")
				}
			});
}

//멤버 챌린지 현황
function memberSummary(userKey,e){
	let tc_key = $(".tc_key").val();
	let nickName = e.querySelector("strong").innerText;
	
	let parent = e.parentNode.parentNode.parentNode.parentNode;
	let img = parent.querySelector(".photo img");
	console.log(img.src);
	
	$("#memberSummary img").attr("src",img.src);
	

	let info = { 
        			userKey : userKey,
        			tc_key : tc_key
        	};
				$("#memNick").empty();
				$("#memNick").append(nickName);
				
        	$.ajax({
				url: "/project/teamRest/memberSummary.do",
				type: "post",
				contentType: "application/json",
				data: JSON.stringify(info),
				success: function(data){
				
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
							if(data[i] != undefined){
								html+="<td class='confirm sum' value='"+data[i].tcs_key+"'>";
							}else {
								html+="<td>";
							}
							html+="</td>";
						}
						html += "</tr>";
						html += "<tr>";
						for(let i=7; i<14; i++){
							if(data[i] != undefined){
								html+="<td class='confirm sum' value='"+data[i].tcs_key+"'>";
							}else {
								html+="<td>";
							}
							html+="</td>";
						}
						html += "</tr>";
						for(let i=14; i<21; i++){
							if(data[i] != undefined){
								html+="<td class='confirm sum' value='"+data[i].tcs_key+"'>";
							}else {
								html+="<td>";
							}
							html+="</td>";
						}
						html += "</tr>";
						html += "<tr class='memberSum' ><td colspan='7' style='height:40px;text-align: center;'>클릭해보세요</td></tr>";
						
						$(".memberChallenge table tbody").prepend(html);
					}
				},
				error:function(){
					alert("에러발생!!")
				}
			});
}
//멤버서머리 가져오기
function getMemberSummary(){
	$(document).on("click",".memberChallenge .sum",function(e){
		$(".memberChallenge table td").css('border','1px solid #838282');
		
		e.target.style.border = '1px solid brown';
		let tsc_key = e.target.getAttribute('value');
		console.log("sumary"+e.target);
		
		
		let info = { 
        			tcs_key : tsc_key
        	};
        	
        	
        	$.ajax({
				url: "/project/teamRest/showSummary.do",
				type: "post",
				contentType: "application/json",
				data: JSON.stringify(info),
				success: function(data){
					$(".memberChallenge .memberSum").empty();
					html ="";
					html += "<td class='memberSum' colspan='7' style='height:40px;text-align: center;'>"+data.tcs_summary+"</td>";
					
					$(".memberChallenge .memberSum").append(html);
				},
				error:function(){
					alert("에러발생!!")
				}
			});
	});
};
//팀원 신청
function newMemberRequest(t_key){
	window.open('/project/team/newRequest.do?t_key='+t_key,'pop','location=no,width=500,height=300,top=100,left=100,history=no,resizable=no,status=no,scrollbars=yes,menubar=no');
};

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
   console.log(now);
   let t_day = $(".t_day").text();
   if(t_day == ''){
   	t_day= now;
   }
   let then = new Date(t_day);
   let gap = now.getTime() - then.getTime();
   gap = Math.floor(gap / (1000 * 60 * 60 * 24));
   console.log(gap);
   $("#dDay .dDate").text(gap);
   //시분초를 없애보자. 
   
}

//디데이 수정
function reviseDday(t_key){
	window.open('/project/team/reviseDday.do?t_key='+t_key,'pop','location=no,width=540,height=300,top=100,left=50,history=no,resizable=no,status=no,scrollbars=yes,menubar=no');
}
//챌린지 수정
function resetChallenge(t_key){
	window.open('/project/team/updateChallenge.do?t_key='+t_key,'pop','location=no,width=500,height=400,top=100,left=50,history=no,resizable=no,status=no,scrollbars=yes,menubar=no');
}
//챌린지 출석
function attendChallenge(t_key){
	let tc_key = $(".tc_key").val();
	if(tc_key == 0){
		alert('챌린지를 먼저 설정해주세요!');
	}else{
		let mySummary = $(".challenge table .confirm");
		let total = mySummary.length;
		
		if(total >= 21){
			$(".challenge table input[type='button'] ").prop('disabled',true);
			alert('챌린지를 완료했어요!!대단하네요!!');
		}else {
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
					if(data == -1){
						alert('오늘은 이미 챌린지를 완료하였어요!');
					}else {
						alert('오늘의 챌린지 완료!');
						location.reload();
					}
				},
				error:function(){
					alert("에러발생!!")
				}
			});
		}
	}
}

