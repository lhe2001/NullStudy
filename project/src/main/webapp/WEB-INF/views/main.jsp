<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
	<div class="search">
	   <form action="/teampro/Controller" method="post">
	      <select name = "selectValue" id="selectValue">
	          <option value = "notSelect" selected disabled>검색기준</option>
	          <option value = "boardSearch">게시글 검색</option>
	          <option value = "userSearch">유저 검색</option>
	          <option value = "teamSearch">팀 검색</option>
	      </select>
	       <input type="text" name="q" placeholder="검색하세요" required>
	       <button type="submit"><i class="fa-solid fa-magnifying-glass"></i></button>
	       <input type="hidden" name="mainSearchH" value="mainSearchH">
	   </form>
	</div>
