package com.spring.teampro.mystudy.dto;

import java.util.Date;

public class MemoDTO {
	private int userkey;
	private int memo_idx;	
	private String memo_title;
	private String memo_desc;
	private Date memo_date;
	
	private int viewPage = 1;
	public int getViewPage() {
		return viewPage;
	}
	public void setViewPage(int viewPage) {
		this.viewPage = viewPage;
	}
	
	private int countPerPage = 4;
	public int getCountPerPage() {
		return countPerPage;
	}
	public void setCountPerPage(int countPerPage) {
		this.countPerPage = countPerPage;
	}
	
	private int startIdx = 1;
	private int endIdx = 4;
	
	public int getStartIdx() {
		return startIdx;
	}
	public void setStartIdx(int startIdx) {
		this.startIdx = startIdx;
	}
	public int getEndIdx() {
		return endIdx;
	}
	public void setEndIdx(int endIdx) {
		this.endIdx = endIdx;
	}
	
	
	public int getUserkey() {
		return userkey;
	}
	public void setUserkey(int userkey) {
		this.userkey = userkey;
	}
	public int getMemo_idx() {
		return memo_idx;
	}
	public void setMemo_idx(int memo_idx) {
		this.memo_idx = memo_idx;
	}
	public String getMemo_title() {
		return memo_title;
	}
	public void setMemo_title(String memo_title) {
		this.memo_title = memo_title;
	}
	public String getMemo_desc() {
		return memo_desc;
	}
	public void setMemo_desc(String memo_desc) {
		this.memo_desc = memo_desc;
	}
	public Date getMemo_date() {
		return memo_date;
	}
	public void setMemo_date(Date memo_date) {
		this.memo_date = memo_date;
	}
}
