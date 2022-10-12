package attendPack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class AttendDAO {
	
	private Connection con;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	public AttendDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/study");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateAttend(int userkey) {
		
		try {
			System.out.println("update 출석 작동");
			con = dataFactory.getConnection();
			
			String query = "insert into attendance (a_key, userkey, a_date )";
			query += " values(seq_atnd.nextval, ?, sysdate)";
						
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, userkey);
			
			pstmt.executeUpdate();
			
			pstmt.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	public List<AttendDTO> viewAttend2(int userkey) {
		
		List<AttendDTO> alist = new ArrayList();
		 
		try {
			System.out.println("view attendance작동");
			con = dataFactory.getConnection();
			
			String query = "select * from attendance";
			query += " where userkey=?";
			query += " order by a_date desc";
			
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, userkey);
			
			ResultSet rs = pstmt.executeQuery();
			
			while( rs.next() ) {
				
				Date a_date = rs.getDate("a_date");
				
				AttendDTO adto = new AttendDTO();
				adto.setUserkey(userkey);
				adto.setA_date(a_date);
				
				alist.add(adto);
			}
			
			rs.close();
			pstmt.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return alist;
	}	
	

	public boolean isAttend(int userkey) {
		boolean result = false;
		// 오늘 날짜
        Date date = new Date();	
     	// 바꾸기
    	SimpleDateFormat today = new SimpleDateFormat("yyyy-MM-dd");	
     	System.out.println(today.format(date));
		
		try {
			System.out.println("isAttend작동");
			con = dataFactory.getConnection();
			
			String query = "SELECT count(*) AS cnt FROM attendance";
			query += " WHERE to_char(a_date, 'yyyy-mm-dd') = ?";
			query += " AND userkey = ?";
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, today.format(date) );
			pstmt.setInt(2, userkey);
			
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			int count = rs.getInt("cnt");
			
			if(count >= 1 ) {
				result = true;
			}
						
			rs.close();
			pstmt.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
	//해당월 
	public List<AttendDTO> isMonth(CalDTO cdto) {
		
		List<AttendDTO> list = new ArrayList();
		
		try {
			System.out.println("isMonth작동");
			con = dataFactory.getConnection();
			
			int year = cdto.getYear();
			int month = cdto.getMonth();
			int userkey = cdto.getUserkey();
			
			String yearmon = String.valueOf(year)+"-"+String.format("%02d", month);
			System.out.println(yearmon);
				
			String query = " SELECT * FROM attendance";
					query += " WHERE to_char(a_date, 'yyyy-MM') = ?";
					query += " AND userkey = ?";
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, yearmon);
			pstmt.setInt(2, userkey);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				
				Date a_date = rs.getDate("a_date");
				int a_key = rs.getInt("a_key");
				
				AttendDTO adto = new AttendDTO();
				adto.setA_date(a_date);
				adto.setUserkey(userkey);
				adto.setA_key(a_key);
				
				list.add(adto);
			};
		    
			rs.close();
			pstmt.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	

	
		
}
