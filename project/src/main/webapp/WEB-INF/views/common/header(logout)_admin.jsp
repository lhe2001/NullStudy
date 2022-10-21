<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link href="/teampro/Front/simple.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Nanum+Pen+Script&display=swap" rel="stylesheet">
    <script src="https://kit.fontawesome.com/f5483c894c.js" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.6.1.js"></script>

<!-- window.onload = function(){
            header();
} -->
</head>
<body>
<div id="wrapper">
        <header class="mainHeader">
            <nav>
                <div class="logo" onclick="location.href='/project/moveToMain.do'"></div>
                <ul class="navtap_items">
                    <li class="navtap">관리메뉴<i class="fa-solid fa-book-open"></i>
                        <div class="drop l1">
                            <ul>
                               <li id="memberview"><a href="/project/memberList.do">회원관리</a></li>
                               <li id="studyview"><a href="/project/studyList.do">스터디관리</a></li>
                            </ul>
                        </div>
                    </li>
                    <li class="navtap">Community <i class="fa-regular fa-comment"></i>
                        <div class="drop l2">
                            <ul>
                                <li><a href="/teampro/board/listArticles.do">Board</a></li>
                                <li></li>
                            </ul>
                        </div>
                    </li>
                    <li class="navtap">Team<i class="fa-solid fa-heart"></i>
                        <div class="drop l3">
                            <ul>
                                  <li><a href="/project/team/allTeamList.do">All Team</a></li>
                                <li></li>
                            </ul>
                        </div>
                    </li>
                    <form action="/project/signOut.do" method="post">
                    <li><button class="logout" type="submit">로그아웃</button></li>
                    </form>
                </ul>
            </nav>
        </header>
</div>
</body>
</html>