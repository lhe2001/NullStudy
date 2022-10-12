package search;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class UserDAO {
	// 필드
		private PreparedStatement pstmt;
		private DataSource dataFactory;
		private Connection con;
		
		// 생성자
		public UserDAO() {
			
			try {
				// 접속
				Context ctx = new InitialContext();
				Context envContext = (Context) ctx.lookup("java:/comp/env");
				dataFactory = (DataSource) envContext.lookup("jdbc/study");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public List<UserVO> listAllUsers(){
			System.out.println("Udao.listAllUsers() 실행");
			List<UserVO> list = new ArrayList<UserVO>();
			
			try {
				con = dataFactory.getConnection();
				
				String query = " SELECT a.nickName, a.email, a.joinDate ";
				query   += " FROM ALLMEMBER a ";
				
				pstmt = con.prepareStatement(query);
				
				ResultSet rs = pstmt.executeQuery();
				
				while (rs.next()) {
					String nickName = rs.getString("nickName");
					String email = rs.getString("email");
					Date joinDate = rs.getDate("joinDate");
					
					UserVO vo = new UserVO();
					vo.setNickName(nickName);
					vo.setEmail(email);
					vo.setJoinDate(joinDate);
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
		
		public List<UserVO> listUsers(String search){
			System.out.println("dao.listUsers() 실행");
			List<UserVO> list = new ArrayList<UserVO>();
			
			try {
				con = dataFactory.getConnection();
				
				String query = " SELECT nickName, email, joinDate ";
				query   += " FROM ALLMEMBER ";
				query   += " where lower(nickName) LIKE '%' || lower(?) || '%' ";
				
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, search);
				
				System.out.println(query);
				
				ResultSet rs = pstmt.executeQuery();
				
				while (rs.next()) {
					String nickName = rs.getString("nickName");
					String email = rs.getString("email");
					Date joinDate = rs.getDate("joinDate");
					
					UserVO vo = new UserVO();
					vo.setNickName(nickName);
					vo.setEmail(email);
					vo.setJoinDate(joinDate);
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
