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
    <link href="https://fonts.googleapis.com/css?family=Nanum+Pen+Script&display=swap" rel="stylesheet">
    <script src="https://kit.fontawesome.com/f5483c894c.js" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.6.1.js"></script>
<<<<<<< HEAD
    <script>
    $(function(){
    	bind();
    	idcheck();
    	<%-- 게시판 --%>
    	search();
    	select();
		link();
    })
    
    function bind(){
    	$("#login_btn").off("click").on("click", function(){
    		location.href = '/project/moveToSignIn.do'
    	})
    	
    	$("#signUp_btn").off("click").on("click", function(){
    		location.href = '/project/moveToSignUp.do'
    	})
    	
    }
    
    </script>
=======

>>>>>>> master
    
</head>
<body>
<div id="wrapper">
        <header class="mainHeader">
            <nav>
                <div class="logo" href="#home" onclick="location.href='/project/'"></div>
                <ul class="navtap_items">
                    <li class="navtap">My Study <i class="fa-solid fa-book-open"></i>
                        <div class="drop l1">
                            <ul>
                               <li>My Team</li>
                               <li>My Study</li>
                            </ul>
                        </div>
                    </li>
                    <li class="navtap">Community <i class="fa-regular fa-comment"></i>
                        <div class="drop l2">
                            <ul>
                                <li>Free Board</li>
                                <li></li>
                            </ul>
                        </div>
                    </li>
                    <li class="navtap">Team<i class="fa-solid fa-heart"></i>
                        <div class="drop l3">
                            <ul>
                                <li>All Team</li>
                                <li></li>
                            </ul>
                        </div>
                    </li>
                    <li><a href="/project/moveToSignIn.do"><button id="login_btn" type="button">로그인</button></a></li>
                    <li><a href="/project/moveToSignUp.do"><button id="signUp_btn" type="button">회원가입</button></a></li>
                </ul>
            </nav>
        </header>
</div>
</body>
</html>