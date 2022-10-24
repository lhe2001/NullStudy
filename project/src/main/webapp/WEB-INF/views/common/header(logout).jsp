<%@ page 
	language="java" 
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
%>

    <title>로그아웃헤더</title>
    <link href="https://fonts.googleapis.com/css?family=Nanum+Pen+Script&display=swap" rel="stylesheet">
    <script src="https://kit.fontawesome.com/f5483c894c.js" crossorigin="anonymous"></script>
<body>
<div id="wrapper">
        <header class="mainHeader">
            <nav>
                <div class="logo" onclick="location.href='/project/moveToMain.do'"></div>
                <ul class="navtap_items">
                    <li class="navtap">My Study <i class="fa-solid fa-book-open"></i>
                        <div class="drop l1">
                            <ul>
                               <li><a href="/project/mystudy/calendar.do">Calendar</a></li>
                               <li><a href="/project/mystudy/memo.do">Memo</a></li>
                            </ul>
                        </div>
                    </li>
                    <li class="navtap">Community <i class="fa-regular fa-comment"></i>
                        <div class="drop l2">
                            <ul>
                                <li><a href="/project/board/listArticles.do">Board</a></li>
                                <li></li>
                            </ul>
                        </div>
                    </li>
                    <li class="navtap">Team<i class="fa-solid fa-heart"></i>
                        <div class="drop l3">
                            <ul>
                                  <li><a href="/project/team/allTeamList.do">All Team</a></li>
                                <li><a href="/project/team/myTeamList.do">My Team</a></li>
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
