

window.onload = function(){
	console.log("> > > 2 >", window.onload)
    header();
    postit();
    bluePostIt();
    searchSelect();
    YellowPostIt();
	 AddTodo();
}

function header(){
    let navList = document.querySelectorAll(".navtap")
    for(let i=0; i<navList.length; i++){
        
        navList[i].addEventListener("click",function(e){
            event.stopPropagation()
            
            let drop =  navList[i].querySelector(".drop");
            
            //이미 block일땐, 한번 더 클릭 시 없애기
            if(drop.style.display == "block"){
                drop.style.display = "none";
            } else {
                // 기본적으로 none으로 바꾸기.
                for(let j=0; j<navList.length;j++){
                    drop.style.height = "0px"
                    navList[j].querySelector(".drop").style.display = "none";
                    let li = drop.querySelectorAll("li")
                        for(let t=0; t<li.length;t++){
                            li[t].style.opacity = "0";
                        }
                }
                // 클릭 시, block으로 바꾸기.
                drop.style.display = "block";
                // height 1~300까지 변하기
                for(let j=0; j<=100;j++){
                    setTimeout(function(){
                        let height = j
                        drop.style.height = height + "px";
                        let li = drop.querySelectorAll("li")
                        for(let t=0; t<li.length;t++){
                            li[t].style.opacity = "1";
                        }
                    },0);
                }
            }
        })
    }
}

// aside click 이벤트
function postit(){
    let postButtonList = document.querySelectorAll("#postitBox button")
    let postList = document.querySelectorAll(".postit")
    let shadow = document.querySelector("#shadow")

    for(let i=0; i<postButtonList .length; i++){
        postButtonList[i].addEventListener("click",function(){

            if(postList[i].classList.contains("none")){
                for(let j=0; j<postList.length; j++){
                    postList[j].classList.add("none");
                    shadow.classList.add("none");
                }
                postList[i].classList.remove("none");
                shadow.classList.remove("none");
                
            } else {
                postList[i].classList.add("none");
                shadow.classList.add("none");
            }
        })
    }
}

function bluePostIt(){

	$(".teamListBtn").off("click").on("click",function(){
	
		$.ajax({
			url: "/project/teamRest/myTeamList.do",
			type: "get",
			contentType: "application/json",
			success: function(data){
				console.log("list :", data);
				
				$("#TeamInfoP table tbody").empty();
				
				let html = "";
				
				if(data.length == 0){
					html += "<tr>";
					html += "	<td>팀이 없습니다</td>";
					html += "<tr>";
				}else {
					for(let i=0; i<data.length; i++){
					html += "<tr>";
					html += "	<td><span> ❤ </span><a href='/project/team/teamDetail.do?t_key="+data[i].t_key+"'>"+data[i].t_name+"</a></td>";
					html += "<tr>";
					}
				}
				$("#TeamInfoP table tbody").append(html);
			},
			error:function(){
				alert("에러발생!!")
			}
		});
	});
}

function YellowPostIt(){
	$(".myTodoBtn").off("click").on("click",function(){
	
		$.ajax({
			url: "/project/mystudy/myTodoList.do",
			type: "get",
			contentType: "application/json",
			success: function(data){
				console.log("list :", data);
				
				$("#todoList table tbody").empty();
				
				let html = "";
				
				if(data.length == 0){
					html += "<tr>";
					html += "	<td>투두리스트가 없습니다</td>";
					html += "<tr>";
				}else {
					for(let i=0; i<data.length; i++){
					html += "<tr>";
					html += "	<td class='smallList'> <span> <i class='fa-solid fa-check'></i> </span>"+data[i].m_td_desc+"<span> <input type='button' name='todoDel' value='X' onClick='delTodo()'> </span><input type='hidden' name='m_td_key' value='"+data[i].m_td_key+"' ></td>";
					html += "<tr>";
					}
				}
				$("#todoList table tbody").append(html);
			},
			error:function(){
				alert("투두리스트 에러발생!!")
			}
		});
	});
}


function searchSelect(){
    $("#selectValue").off("change").on("change",function(){
        let selected = $("#selectValue").val();
        console.log(selected)

        if(selected == "boardSearch" || selected == "userSearch" || selected == "teamSearch"){

            $(".searchNoInput").addClass("hide");
            $(".searchInput").removeClass("hide");  
            $(".submitButton").removeClass("hide");  

        }

    })
}

