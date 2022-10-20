package com.spring.teampro.team.dto;

public class MemberRequestDTO {

	private int tr_key;
	private int userKey;
	private String nickName;
	private String intro;
	private String tr_result;
	private int t_key;
	
	public int getTr_key() {
		return tr_key;
	}
	public void setTr_key(int tr_key) {
		this.tr_key = tr_key;
	}
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
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getTr_result() {
		return tr_result;
	}
	public void setTr_result(String tr_result) {
		this.tr_result = tr_result;
	}
	public int getT_key() {
		return t_key;
	}
	public void setT_key(int t_key) {
		this.t_key = t_key;
	}
	
	
	
}
