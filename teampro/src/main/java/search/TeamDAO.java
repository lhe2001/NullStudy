package search;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;

public class TeamDAO extends HttpServlet {
	// 필드
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	private Connection con;
	
	// 생성자
	public TeamDAO() {
		
		try {
			// 접속
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/study");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<TeamVO> listAllTeams(){
		System.out.println("Tdao.listTeams() 실행");
		List<TeamVO> list = new ArrayList<TeamVO>();
		
		try {
			con = dataFactory.getConnection();
			
			String query = " SELECT a.nickName, t.T_NAME, T_create ";
			query       += " FROM ALLMEMBER a, ALLTEAM t ";;
			query       += " WHERE a.USERKEY = t.USERKEY ";;
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String t_name = rs.getString("t_name");
				String nickName = rs.getString("nickName");
				Date t_create = rs.getDate("t_create");
				
				TeamVO vo = new TeamVO();
				vo.setT_name(t_name);
				vo.setNickName(nickName);
				vo.setT_create(t_create);
				list.add(vo);
			}
			rs.close();
			pstmt.close();
			con.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list; 
	}
	
	public List<TeamVO> listTeams(String search){
		System.out.println("Tdao.listTeams() 실행");
		List<TeamVO> list = new ArrayList<TeamVO>();
		
		try {
			con = dataFactory.getConnection();
			
			String query = " SELECT a.nickName, t.T_NAME, T_create ";
			query       += " FROM ALLMEMBER a, ALLTEAM t ";;
			query       += " where a.USERKEY = t.USERKEY  ";;
			query       += " and lower(t_name) LIKE '%' || lower(?) || '%'  ";;
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, search);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String t_name = rs.getString("t_name");
				String nickName = rs.getString("nickName");
				Date t_create = rs.getDate("t_create");
				
				TeamVO vo = new TeamVO();
				vo.setT_name(t_name);
				vo.setNickName(nickName);
				vo.setT_create(t_create);
				list.add(vo);
			}
			rs.close();
			pstmt.close();
			con.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list; 
	}
	
}
