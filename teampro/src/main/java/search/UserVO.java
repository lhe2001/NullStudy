package search;

import java.sql.Date;

public class UserVO {
	
	// db allmember 테이블 컬럼
	private int userKey;
	private String nickName;
	private String email;
	private String intro;
	private Date joinDate;
	private int b_articleNo;
	
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
	
	// db board 테이블 컬럼 getter, setter
	public int getB_articleNo() {
		return b_articleNo;
	}
	public void setB_articleNo(int b_articleNo) {
		this.b_articleNo = b_articleNo;
	}
	
	
}
