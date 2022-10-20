package com.spring.teampro.team.dto;

import java.util.Date;

public class TeamInfoDTO {
	
	private int t_key;
	private String t_name;
	private String t_intro;
	private int t_field;
	private String t_field2;
	private int t_number;
	private String t_lMemo;
	private Date tcreate;
	private int userKey;
	private String nickName;
	private String t_day;
	
	public int getT_key() {
		return t_key;
	}
	public void setT_key(int t_key) {
		this.t_key = t_key;
	}
	public int getUserKey() {
		return userKey;
	}
	public void setUserKey(int userKey) {
		this.userKey = userKey;
	}
	public String getT_name() {
		return t_name;
	}
	public void setT_name(String t_name) {
		this.t_name = t_name;
	}
	public String getT_intro() {
		return t_intro;
	}
	public void setT_intro(String t_intro) {
		this.t_intro = t_intro;
	}
	public int getT_field() {
		return t_field;
	}
	public void setT_field(int t_field) {
		this.t_field = t_field;
	}
	public int getT_number() {
		return t_number;
	}
	public void setT_number(int t_number) {
		this.t_number = t_number;
	}
	public String getT_lMemo() {
		return t_lMemo;
	}
	public void setT_lMemo(String t_lMemo) {
		this.t_lMemo = t_lMemo;
	}
	public Date getTcreate() {
		return tcreate;
	}
	public void setTcreate(Date tcreate) {
		this.tcreate = tcreate;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getT_field2() {
		return t_field2;
	}
	public void setT_field2(String t_field2) {
		this.t_field2 = t_field2;
	}
	public String getT_day() {
		return t_day;
	}
	public void setT_day(String t_day) {
		this.t_day = t_day;
	}
	
	
}
