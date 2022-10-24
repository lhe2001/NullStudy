package com.spring.teampro.team.dto;

public class ChallengeDTO {

	private int userKey;
	private int t_key;
	private int tc_key;
	private String tc_title;
	private int tcs_key;
	private String tcs_summary;
	private int length;
	private String tcs_date;
	
	
	public int getUserKey() {
		return userKey;
	}
	public void setUserKey(int userKey) {
		this.userKey = userKey;
	}
	public int getT_key() {
		return t_key;
	}
	public void setT_key(int t_key) {
		this.t_key = t_key;
	}
	public int getTc_key() {
		return tc_key;
	}
	public void setTc_key(int tc_key) {
		this.tc_key = tc_key;
	}
	public String getTc_title() {
		return tc_title;
	}
	public void setTc_title(String tc_title) {
		this.tc_title = tc_title;
	}
	public int getTcs_key() {
		return tcs_key;
	}
	public void setTcs_key(int tcs_key) {
		this.tcs_key = tcs_key;
	}
	public String getTcs_summary() {
		return tcs_summary;
	}
	public void setTcs_summary(String tcs_summary) {
		this.tcs_summary = tcs_summary;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public String getTcs_date() {
		return tcs_date;
	}
	public void setTcs_date(String tcs_date) {
		this.tcs_date = tcs_date;
	}
	
	
}
