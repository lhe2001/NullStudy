package com.spring.teampro.search.dto;

import java.sql.Date;

public class BoardDTO {

	// db allmember 테이블 컬럼
	private int userKey;
	private String nickName;
	
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
	
	
	
	// db teamBoard 테이블 컬럼 getter, setter
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
	
	// db AllTeam 테이블 컬럼 getter, setter
	public String getT_name() {
		return t_name;
	}
	public void setT_name(String t_name) {
		this.t_name = t_name;
	}
	
	// db allmember 테이블 컬럼 getter, setter
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

	// db board 테이블 컬럼 getter, setter
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

}