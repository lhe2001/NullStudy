package com.spring.teampro.signupin.dto;

public class AdPageDTO {
	
	private int startPage; // 게시글 화면에 보여질 첫번째 번호
	
	private int endPage;  // 게시글 화면에 보여질 마지막 번호
	
	private boolean prev; // 이전버튼
	
	private boolean next; // 다음버튼
	
	private int pageNum; // 현재 조회하는 페이지번호
	
	private int amount = 10;  // 페이지당 게시글 수(10으로 고정)
	
	private int totalCount; // 전체게시글 수 

}
