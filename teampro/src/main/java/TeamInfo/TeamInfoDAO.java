package TeamInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class TeamInfoDAO {

	private Connection con;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	public TeamInfoDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context)ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/study");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	// 팀정보 수정하는 곳 (팀인사말 & 필드)
	public void reviseTeamInfo(TeamInfoDTO dto) {
		try {
			con = dataFactory.getConnection();
			
			String teamIntro = dto.getTeamIntro();
			int teamField = dto.getTeamField();
			int teamKey = dto.getTeamKey();
			
			String query = "update AllTeam";
			query += " set t_intro=?,t_field=?";
			query += " where t_key=?";
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, teamIntro);
			pstmt.setInt(2, teamField);
			pstmt.setInt(3, teamKey);
			
			System.out.println(query);
			
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//팀키로 팀의 정보를 가져오기.
	public TeamInfoDTO viewInfo(int t_key) {
		
		TeamInfoDTO dto = new TeamInfoDTO();
		
		try {
			con = dataFactory.getConnection();
			
			String query = " SELECT a.T_KEY ,a.T_NAME ,a.T_INTRO ,a.T_FIELD ,a.T_NUMBER ,a.T_LMEMO ,a.T_CREATE ,a.USERKEY ,a2.NICKNAME ,a2.id  FROM ALLTEAM a ,allmember a2";
					query += " WHERE a.USERKEY = a2.USERKEY and t_key = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, t_key);
			
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			String name = rs.getString("t_name");
			String intro = rs.getString("t_intro");
			int field =rs.getInt("t_field");
			int number = rs.getInt("t_number");
			String lmemo = rs.getString("t_lmemo");
			int leader = rs.getInt("userkey");
			Date tcreate = rs.getDate("t_create");
			String leaderNick = rs.getString("nickname");
			String leaderId = rs.getString("id");
			
			dto.setTeamName(name);
			dto.setTeamIntro(intro);
			dto.setTeamField(field);
			dto.setTeamNumber(number);
			dto.setTeamMemo(lmemo);
			dto.setTeamLeader(leader);
			dto.setTeamKey(t_key);
			dto.setTcreate(tcreate);
			dto.setLeaderNick(leaderNick);
			dto.setLeaderId(leaderId);
			
			pstmt.close();
			rs.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	
	//리더Memo 수정하기 
	public void reviseLeaderMemo(TeamInfoDTO dto) {
		
		try {
			con = dataFactory.getConnection();
			
			String leWrite = dto.getTeamMemo();
			int t_key = dto.getTeamKey();
			
			String query = "update AllTeam";
			query += " set t_lmemo=?";
			query += " where t_key=?";
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, leWrite);
			pstmt.setInt(2, t_key);
			pstmt.executeUpdate();
			
			pstmt.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//TeamName 중복체크
	public boolean isExistTeam(String teamName) {
		
		boolean result = false;
		teamName = teamName.trim();
		
		try {
			con = dataFactory.getConnection();
			
					
			String query = " select count(*) from allteam";
			query += " where t_name=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, teamName);
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			int count = rs.getInt("count(*)");
			
			if(count > 0) {
				result = true;
			}
			
			pstmt.close();
			rs.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//새로운팀 개설!
	public void newTeam(TeamInfoDTO dto) {
		
		try {
			con = dataFactory.getConnection();
			
			int leader = dto.getTeamLeader();
			String teamname = dto.getTeamName();
			String teamIntro = dto.getTeamIntro();
			int field = dto.getTeamField();
			
			String query = "insert into AllTeam";
			query += " (t_key,t_name,t_intro,t_field,t_number,t_lmemo,userkey)";
			query += " values(t_key_seq.nextval,?,?,?,1,'수정해주세요',?)";
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, teamname);
			pstmt.setString(2, teamIntro);
			pstmt.setInt(3, field);
			pstmt.setInt(4, leader);
			pstmt.executeUpdate();
			
			//currval 확인하기
			String query3 = "select * from allteam";
					query3 += " where t_name=?";
			pstmt = con.prepareStatement(query3);
			pstmt.setString(1, teamname);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			int teamkey = rs.getInt("t_key");
			
			//member에 넣기
			String query2 = "insert into TEAMMEMBER";
			query2 += " (tm_key,t_key,userkey)";
			query2 += " values(tm_key_seq.nextval,?,?)";
			pstmt = con.prepareStatement(query2);
			pstmt.setInt(1, teamkey);
			pstmt.setInt(2, leader);
			pstmt.executeUpdate();
			
			pstmt.close();
			rs.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
		//팀 삭제
		public void delTeam(TeamInfoDTO dto) {
			
			try {
				con = dataFactory.getConnection();
				
				int leaderid = dto.getTeamLeader();
				int teamkey = dto.getTeamKey();
				
				//board삭제
				String query3 = "DELETE FROM teamBoard";
				query3 += " WHERE t_key=?";
				pstmt = con.prepareStatement(query3);
				pstmt.setInt(1, teamkey);
				pstmt.executeUpdate();
				pstmt.close();
				
				//member삭제
				String query2 = "DELETE FROM teammember";
				query2 += " WHERE t_key=?";
				pstmt = con.prepareStatement(query2);
				pstmt.setInt(1, teamkey);
				pstmt.executeUpdate();
				pstmt.close();
				
				//팀 삭제
				String query = "DELETE FROM allteam";
					query += " WHERE t_key=?";
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, teamkey);
				pstmt.executeUpdate();
				pstmt.close();
				
				con.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	//모든 팀 정보 보기
	public List<TeamInfoDTO> allTeamList(){
		
		List<TeamInfoDTO> list = new ArrayList();
		
		try {
			con = dataFactory.getConnection();
			
			String query = "SELECT a.T_KEY ,a.T_NAME ,a.T_INTRO ,a.T_NUMBER ,a.T_FIELD ,a.T_LMEMO,u.nickname,u.USERKEY  FROM  ALLTEAM a, allmember u";
			query += " WHERE a.userkey= u.USERKEY";
			query += " ORDER BY a.t_key";
			
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int t_key = rs.getInt("t_key");
				String teamName = rs.getString("t_name");
				String teamIntro = rs.getString("t_intro");
				int t_number = rs.getInt("t_number");
				int teamField = rs.getInt("t_field");
				int userkey = rs.getInt("userkey");
				String leaderNick = rs.getString("nickname");
				
				TeamInfoDTO dto = new TeamInfoDTO();
				dto.setLeaderNick(leaderNick);
				dto.setTeamField(teamField);
				dto.setTeamIntro(teamIntro);
				dto.setTeamKey(t_key);
				dto.setTeamLeader(userkey);
				dto.setTeamName(teamName);
				dto.setTeamNumber(t_number);
				
				list.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	
	
	

}
