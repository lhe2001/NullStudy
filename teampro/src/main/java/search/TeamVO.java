package search;

import java.sql.Date;

public class TeamVO {

	// db allmember 테이블 컬럼
	private int userKey;
	private String nickName;
	
	// db ALLTEAM 테이블 컬럼
	private String t_name;
	private Date t_create;
	
	
	
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
}
