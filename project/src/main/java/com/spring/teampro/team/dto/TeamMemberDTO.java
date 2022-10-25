package com.spring.teampro.team.dto;

import java.util.Date;

public class TeamMemberDTO {

	
	int t_key;
	int tm_key;
	Date tm_joindate;
	int userKey;
	String nickname;
	Date lastTime;
	String lastTime2;
	String intro;
	String photo;
	
	public int getT_key() {
		return t_key;
	}
	public void setT_key(int t_key) {
		this.t_key = t_key;
	}
	public int getTm_key() {
		return tm_key;
	}
	public void setTm_key(int tm_key) {
		this.tm_key = tm_key;
	}
	public Date getTm_joindate() {
		return tm_joindate;
	}
	public void setTm_joindate(Date tm_joindate) {
		this.tm_joindate = tm_joindate;
	}
	public int getuserKey() {
		return userKey;
	}
	public void setuserKey(int userKey) {
		this.userKey = userKey;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Date getLastTime() {
		return lastTime;
	}
	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getLastTime2() {
		return lastTime2;
	}
	public void setLastTime2(String lastTime2) {
		this.lastTime2 = lastTime2;
	}
	
	
	
}
