package TeamBoard;

import java.util.Date;

public class TBoardDTO {

	private int t_key;
	private String tb_memo;
	private Date tb_memotime;
	private int userKey;
	private String nickName;
	String userId;
	int tb_key;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getTb_key() {
		return tb_key;
	}
	public void setTb_key(int tb_key) {
		this.tb_key = tb_key;
	}
	public int getT_key() {
		return t_key;
	}
	public void setT_key(int t_key) {
		this.t_key = t_key;
	}
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

	
	
}
