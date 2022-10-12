package TeamInfo;

import java.util.Date;

public class TeamInfoDTO {
	
	int teamKey;
	String teamName;
	String teamIntro;
	int teamField;
	int teamNumber;
	String teamMemo;
	Date tcreate;
	int teamLeader;
	String leaderNick;
	String leaderId;
	
	
	
	public String getLeaderId() {
		return leaderId;
	}
	public void setLeaderId(String leaderId) {
		this.leaderId = leaderId;
	}
	public String getLeaderNick() {
		return leaderNick;
	}
	public void setLeaderNick(String leaderNick) {
		this.leaderNick = leaderNick;
	}
	public Date getTcreate() {
		return tcreate;
	}
	public void setTcreate(Date tcreate) {
		this.tcreate = tcreate;
	}
	public int getTeamNumber() {
		return teamNumber;
	}
	public void setTeamNumber(int teamNumber) {
		this.teamNumber = teamNumber;
	}
	
	public String getTeamIntro() {
		return teamIntro;
	}
	public void setTeamIntro(String teamIntro) {
		this.teamIntro = teamIntro;
	}
	public int getTeamKey() {
		return teamKey;
	}
	public void setTeamKey(int teamKey) {
		this.teamKey = teamKey;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getTeamMemo() {
		return teamMemo;
	}
	public void setTeamMemo(String teamMemo) {
		this.teamMemo = teamMemo;
	}
	public int getTeamField() {
		return teamField;
	}
	public void setTeamField(int teamField) {
		this.teamField = teamField;
			
	}
	public int getTeamLeader() {
		return teamLeader;
	}
	public void setTeamLeader(int teamLeader) {
		this.teamLeader = teamLeader;
	}
	
	
}
