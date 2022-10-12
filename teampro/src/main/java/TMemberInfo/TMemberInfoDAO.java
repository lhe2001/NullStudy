package TMemberInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import JoinDTO.MemberUserDTO;
import JoinDTO.MyTeamListDTO;
import TeamInfo.TeamInfoDTO;

public class TMemberInfoDAO {

	private Connection con;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	public TMemberInfoDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context)ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/study");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//내가 팀이 있는가?? 없는가??? 
	public boolean includeTeam(int userkey) {
		boolean result = false;
	
		try {
			con = dataFactory.getConnection();
			
			String query = "SELECT count(*) from TEAMMEMBER";
			query += " where userkey=?";
	
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, userkey);	
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			int count = rs.getInt("count(*)");
			
			if(count >0) {
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
	
	//해당팀 멤버의 유저정보 가져오기 
	public List<MemberUserDTO> viewMemberInfo(int t_key) {
		
		List<MemberUserDTO> list = new ArrayList();
		
		try {
			con = dataFactory.getConnection();
			
			String query = " SELECT m.T_KEY, m.TM_KEY , m.TM_JOINDATE ,m.USERKEY , a.id ,a.NICKNAME ,a.LASTTIME ,a.INTRO,a.PHOTO  FROM teammember m, allmember a";
					query += " WHERE m.USERKEY = a.USERKEY AND m.T_KEY = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, t_key);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				MemberUserDTO tmdto = new MemberUserDTO();
				int tm_Key = rs.getInt("tm_key");
				Date joinDate = rs.getDate("tm_joindate");
				int userkey = rs.getInt("userkey");
				String userid = rs.getString("id");
				String nickName = rs.getString("nickname");
				Date lastTime = rs.getDate("lasttime");
				String intro = rs.getString("intro");
				int photo = rs.getInt("photo");
				
				tmdto.setIntro(intro);
				tmdto.setLastTime(lastTime);
				tmdto.setNickname(nickName);
				tmdto.setPhoto(photo);
				tmdto.setT_key(t_key);
				tmdto.setTm_joindate(joinDate);
				tmdto.setTm_key(tm_Key);
				tmdto.setUserid(userid);
				tmdto.setuserKey(userkey);
				
				list.add(tmdto);
			}
			
			pstmt.close();
			rs.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	//내 유저key로 검색해서 나온 팀 이름 & 팀키!!
	public List<MyTeamListDTO> myteamList(int userkey) {
		
		List<MyTeamListDTO> list = new ArrayList();
		
		try {
			con = dataFactory.getConnection();
			
			String query = " SELECT a.T_KEY ,a.T_NAME ,t.USERKEY  FROM TEAMMEMBER t , ALLTEAM a";
					query += " WHERE t.T_KEY = a.T_KEY AND t.userkey= ?";
			
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, userkey);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				MyTeamListDTO dto = new MyTeamListDTO();
				int t_Key = rs.getInt("t_key");
				String t_name = rs.getString("t_name");
				
				dto.setTeamkey(t_Key);
				dto.setTeamName(t_name);
				dto.setUserkey(userkey);
				
				list.add(dto);
			}
			
			pstmt.close();
			rs.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//팀 멤버 추가 하기
	public void addMember(TMemberInfoDTO dto) {
		
		try {
			System.out.println("addMember");
			con = dataFactory.getConnection();
			
			int t_key = dto.getT_key();
			int userkey = dto.getId_key();
			
			String query = "insert into teammember";
			query += " (tm_key,t_key,tm_joindate,userkey)";
			query += " values(tm_key_seq.nextval,?,sysdate,?)";
			
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, t_key);
			pstmt.setInt(2, userkey);
			pstmt.executeUpdate();
			pstmt.close();
			
			String query3 = " SELECT count(*) FROM TEAMMEMBER";
					query3 += " WHERE t_key = ?";
			pstmt = con.prepareStatement(query3);
			pstmt.setInt(1, t_key);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			int number = rs.getInt("count(*)");
			pstmt.close();
			
			String query2 = "update allteam";
			query2 += " set t_number=?";
			query2 += " where t_key=?";
			
			pstmt = con.prepareStatement(query2);
			pstmt.setInt(1, number);
			pstmt.setInt(2, t_key);
			pstmt.executeUpdate();
			pstmt.close();
			
			rs.close();
			con.close();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
		//팀 멤버 삭제 하기
		public void delMember(int tmkey,int teamkey) {
			
			try {
				System.out.println("delMember");
				con = dataFactory.getConnection();
								
				String query = "DELETE FROM teammember";
					query +=  " WHERE tm_key=?";
				
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, tmkey);
				pstmt.executeUpdate();
				pstmt.close();
				
				String query3 = " SELECT count(*) FROM TEAMMEMBER";
				query3 += " WHERE t_key = ?";
				pstmt = con.prepareStatement(query3);
				pstmt.setInt(1, teamkey);
				ResultSet rs = pstmt.executeQuery();
				rs.next();
				int number = rs.getInt("count(*)");
				pstmt.close();
								
				String query2 = "update allteam";
				query2 += " set t_number=?";
				query2 += " where t_key=?";
				pstmt = con.prepareStatement(query2);
				pstmt.setInt(1, number);
				pstmt.setInt(2, teamkey);
				pstmt.executeUpdate();
				pstmt.close();
				
				rs.close();
				con.close();
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	
		
		
		
		
}
