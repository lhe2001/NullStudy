package TMemberInfo;

import java.util.Date;

public class TMemberInfoDTO {

	int tm_key;
	int t_key;
	int id_key;
	Date joinDate;
	String nickName;
	
	
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public int getTm_key() {
		return tm_key;
	}
	public void setTm_key(int tm_key) {
		this.tm_key = tm_key;
	}
	public int getT_key() {
		return t_key;
	}
	public void setT_key(int t_key) {
		this.t_key = t_key;
	}
	public int getId_key() {
		return id_key;
	}
	public void setId_key(int id_key) {
		this.id_key = id_key;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	
}
