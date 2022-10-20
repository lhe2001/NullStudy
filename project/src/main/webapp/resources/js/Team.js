window.addEventListener("load", teamInfoOnload);
//window.onload = function(){
   function teamInfoOnload(){
    console.log(111)
   	reviseTeamInfo();
    leWrite();
    dailyMemoRevise();
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
        if(revise == true){
        	let t_key = $(".t_key").val();
        	let t_intro = $(".t_intro").val();
        	let t_field = $(".t_field").val();
        	
        	let info = { 
        			t_key : t_key,
        			t_intro : t_intro,
        			t_field : t_field 
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

//멤버탈퇴
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


//뉴 멤버요청
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

