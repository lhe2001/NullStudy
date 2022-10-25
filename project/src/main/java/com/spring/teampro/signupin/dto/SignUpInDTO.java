package com.spring.teampro.signupin.dto;

import java.sql.Date;

public class SignUpInDTO {
	
	private int userKey;
	private String id;
	private String pw;
	private String rePw;
	private String name;
	private String sex;
	private String nickName;
	private String email;
	private Date joinDate;
	private String intro;
	private Date lastTime;
	private String photo;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getUserKey() {
		return userKey;
	}
	public void setUserKey(int userKey) {
		this.userKey = userKey;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getRePw() {
		return rePw;
	}
	public void setRePw(String rePw) {
		this.rePw = rePw;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
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
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public Date getLastTime() {
		return lastTime;
	}
	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	@Override
	public String toString() {
		return "SignUpInDTO [userKey=" + userKey + ", id=" + id + ", pw=" + pw + ", rePw=" + rePw + ", name=" + name
				+ ", sex=" + sex + ", nickName=" + nickName + ", email=" + email + ", joinDate=" + joinDate + ", intro="
				+ intro + ", lastTime=" + lastTime + ", photo=" + photo + "]";
	}

}
