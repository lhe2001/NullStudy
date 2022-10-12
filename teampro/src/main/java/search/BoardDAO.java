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

public class BoardDAO extends HttpServlet {
	// 필드
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	private Connection con;
	
	// 생성자
	public BoardDAO() {
		
		try {
			// 접속
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/study");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	// 모든 것 조회
	public List<BoardVO> listBoards(){
		System.out.println("Bdao.listBoards() 실행");
		List<BoardVO> list = new ArrayList<BoardVO>();
		
		try {
			con = dataFactory.getConnection();
			
			String query = " SELECT b.b_title, a.nickName, b.b_articleNo, b.b_writedate ";
			query       += " FROM ALLMEMBER a, BOARD b ";
			query       += " where a.USERKEY = b.USERKEY ";
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int b_articleNo = rs.getInt("b_articleNo");
				String b_title = rs.getString("b_title");
				String nickName = rs.getString("nickName");
				Date b_writedate = rs.getDate("b_writedate");
				
				BoardVO vo = new BoardVO();
				vo.setB_articleNo(b_articleNo);
				vo.setB_title(b_title);
				vo.setNickName(nickName);
				vo.setB_writedate(b_writedate);
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
	
	// where 조건에 맞게 조회
	public List<BoardVO> listBoardAll(String search, String selectValue) {
		System.out.println("dao.listBoardAll() 실행");
		// Controller 서블릿에서 보낸 input 입력 값, select의 option value 값 받아서
		
		List<BoardVO> list = new ArrayList<BoardVO>();
		
		try {
			con = dataFactory.getConnection();
			
			String query = " SELECT b.b_title, a.nickName, b.b_articleNo, b.b_writedate ";
			query       += " FROM ALLMEMBER a, BOARD b ";
			query       += " WHERE a.USERKEY = b.USERKEY ";
			
//			if(selectValue == null) {
//				selectValue = "미선택";
//			}
			// 선택을 안해서 null일때 NullPointerException을 피하기 위해 임의 값 부여
			
			if (selectValue.equals("boardSearch")) {
			// select의 option value 값의 equals조건 같으면 해당 if문의 where조건 완성
				query   += " and (lower(b_title) LIKE '%' || lower(?) || '%' ";
				query   += " OR lower(nickName) LIKE '%' || lower(?) || '%' ";
				query   += " OR lower(b_content) LIKE '%' || lower(?) || '%') ";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, search);
				pstmt.setString(2, search);
				pstmt.setString(3, search);
				
				
			} else if (selectValue.equals("b_title")) {
				
				query   += " and lower(b_title) LIKE '%' || lower(?) || '%' ";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, search);
				
				
			} else if (selectValue.equals("b_content")) {
				
				query   += " and lower(b_content) LIKE '%' || lower(?) || '%' ";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, search);
				
				
			} else if (selectValue.equals("nickName")) {
				
				query   += " and lower(nickname) LIKE '%' || lower(?) || '%' ";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, search);
			}
			
//			else {
//				pstmt = con.prepareStatement(query);
//				pstmt.setString(1, search);
//			}
			// 모든 if가 false일 때 else 아직 미지정
			
			System.out.println(query);
			
			
			ResultSet rs = pstmt.executeQuery();
			while( rs.next() ) {
				int b_articleNo = rs.getInt("b_articleNo");
				String b_title = rs.getString("b_title");
				String nickName = rs.getString("nickName");
				Date b_writedate = rs.getDate("b_writedate");
				
				BoardVO vo = new BoardVO();
				vo.setB_articleNo(b_articleNo);
				vo.setB_title(b_title);
				vo.setNickName(nickName);
				vo.setB_writedate(b_writedate);
				list.add(vo);
				// class 를 list으로 저장
			}
			// rs.next()로 다음줄, 다음줄이 없을 때까지 반복
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		// 메소드를 부른 곳 list 쏴주고
	}

}
