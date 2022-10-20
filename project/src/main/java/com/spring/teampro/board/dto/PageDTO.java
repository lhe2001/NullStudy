package com.spring.teampro.board.dto;

public class PageDTO {
	
	private int startPage; // 게시글 화면에 보여질 첫번째 번호
	
	private int endPage;  // 게시글 화면에 보여질 마지막 번호
	
	private boolean prev; // 이전버튼
	
	private boolean next; // 다음버튼
	
	private int pageNum; // 현재 조회하는 페이지번호
	
	private int amount = 10;  // 페이지당 게시글 수(10으로 고정)
	
	private int totalCount; // 전체게시글 수 
	
	// 생성자
	public PageDTO(int pageNum, int amount, int totalCount) {
			// 전달인자값 전달
			this.pageNum = pageNum;
			this.amount = amount;
			this.totalCount = totalCount;
			
			// startPage결정
			this.startPage = ((this.pageNum-1)*amount) + 1;
			// endPage 자바영역(controller에서 처리해도 된다.)
			this.endPage = ((int)Math.ceil((double)this.pageNum / 10)) * 10;
			// realEnd(진짜 마지막 페이지 번호)
			int realEnd = (int)Math.ceil(this.totalCount / (double)this.amount);
			System.out.println("realEnd =" + realEnd + "endPage = " + this.endPage);
			/* 
			 마지막페이지 도달했을 때 보여져야 하는 끝번호 셋팅
			 ex) 131개 게시물
			 1번 페이지 클릭시 -> endPage = 10, realEnd = 14 ( endPage로 세팅 )
			 11번 페이지 클릭시 -> endPage = 20, realEnd = 14 ( realEnd로 세팅 )
			 */
			if(this.endPage > realEnd) {
				this.endPage = realEnd;
			}
			
			// prev(이전페이지) 시작페이지의 번호는 1(일경우 없음), 11, 21... 
			this.prev = this.startPage > 1;
			
			/* next(다음페이지)
		 	 ex: 131개 게시물
			 1~10 클릭시 endPage = 10, realEnd = 14 -> 다음버튼 true
			 11 클릭시 endPage = 14 , realEnd = 14 -> 다음버튼 false
			 */
			this.next = this.endPage < realEnd;
			
			// 확인
			System.out.println("pageNum = " + pageNum);
			System.out.println("시작페이지:" + this.startPage + ", 끝페이지:" + this.endPage);
		}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
}
