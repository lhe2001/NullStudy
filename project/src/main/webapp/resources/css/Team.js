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
        const dele = confirm('수정 하시겠습니까?');
        console.log(dele);
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
    document.querySelector(".reivseWrite input[type='submit']").addEventListener("click",function(){
        const dele = confirm('수정 하시겠습니까?')
        console.log(dele);
    })
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

