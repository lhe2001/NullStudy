package com.spring.teampro.mystudy.dto;

import java.util.Date;

public class MemoDTO {
	private int userkey;
	private int m_memo_key;	
	private String m_memo_title;
	private String m_memo_desc;
	private Date m_memo_date;
	
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
	public int getM_memo_key() {
		return m_memo_key;
	}
	public void setM_memo_key(int m_memo_key) {
		this.m_memo_key = m_memo_key;
	}
	public String getM_memo_title() {
		return m_memo_title;
	}
	public void setM_memo_title(String m_memo_title) {
		this.m_memo_title = m_memo_title;
	}
	public String getM_memo_desc() {
		return m_memo_desc;
	}
	public void setM_memo_desc(String m_memo_desc) {
		this.m_memo_desc = m_memo_desc;
	}
	public Date getM_memo_date() {
		return m_memo_date;
	}
	public void setM_memo_date(Date m_memo_date) {
		this.m_memo_date = m_memo_date;
	}
	
}
