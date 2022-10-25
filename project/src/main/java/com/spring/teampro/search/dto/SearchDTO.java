package com.spring.teampro.search.dto;

import java.sql.Date;

public class SearchDTO {
	
	// db allmember 테이블 컬럼
	private int userKey;
	private String nickName;
	private String email;
	private String intro;
	private Date joinDate;
	
	// db board 테이블 컬럼
	private int b_key;
	private int b_articleNo;
	private String b_title;
	private String b_content;
	private Date b_writedate;
	
	// db teamBoard 테이블 컬럼
	private String tb_memo;
	private Date tb_memotime;
	
	// db AllTeam 테이블 컬럼
	private String t_name;
	private Date t_create;
	
	// search
	private String search;
	
	// Paging
	private int start;
	private int end;
	private int firstNo;
	private int lastNo;
	private int lastPage;
	private int pageNum;
	private int count;
	

	
	// db allmember 테이블 컬럼 getter setter
	public int getUserKey() {
		return userKey;
	}
	public void setUserKey(int userKey) {
		this.userKey = userKey;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	
	// db board 테이블 getter setter
	public int getB_key() {
		return b_key;
	}
	public void setB_key(int b_key) {
		this.b_key = b_key;
	}
	public int getB_articleNo() {
		return b_articleNo;
	}
	public void setB_articleNo(int b_articleNo) {
		this.b_articleNo = b_articleNo;
	}
	public String getB_title() {
		return b_title;
	}
	public void setB_title(String b_title) {
		this.b_title = b_title;
	}
	public String getB_content() {
		return b_content;
	}
	public void setB_content(String b_content) {
		this.b_content = b_content;
	}
	public Date getB_writedate() {
		return b_writedate;
	}
	public void setB_writedate(Date b_writedate) {
		this.b_writedate = b_writedate;
	}
	
	// db teamBoard 테이블 컬럼 getter setter
	public String getTb_memo() {
		return tb_memo;
	}
	public void setTb_memo(String tb_memo) {
		this.tb_memo = tb_memo;
	}
	public Date getTb_memotime() {
		return tb_memotime;
	}
	public void setTb_memotime(Date tb_memotime) {
		this.tb_memotime = tb_memotime;
	}
	
	// db AllTeam 테이블 컬럼 getter setter
	public String getT_name() {
		return t_name;
	}
	public void setT_name(String t_name) {
		this.t_name = t_name;
	}
	public Date getT_create() {
		return t_create;
	}
	public void setT_create(Date t_create) {
		this.t_create = t_create;
	}
	
	// search
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	
	// Paging
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public int getFirstNo() {
		return firstNo;
	}
	public void setFirstNo(int firstNo) {
		this.firstNo = firstNo;
	}
	public int getLastNo() {
		return lastNo;
	}
	public void setLastNo(int lastNo) {
		this.lastNo = lastNo;
	}
	public int getLastPage() {
		return lastPage;
	}
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

}