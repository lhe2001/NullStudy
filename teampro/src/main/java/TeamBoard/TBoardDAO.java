package TeamBoard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import TMemberInfo.TMemberInfoDTO;

public class TBoardDAO {

	private Connection con;
	private PreparedStatement pstmt;
	private DataSource dataFactory;

	
	public TBoardDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context)ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/study");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	// 보드정보 & userNickName 가져오기 
	public List<TBoardDTO> viewBoard(int t_key) {
		
		List<TBoardDTO> list = new ArrayList();
		
		try {
			System.out.println("viewBoard 작동");
			con = dataFactory.getConnection();
			
			String query = " SELECT t.TB_KEY ,t.T_KEY ,t.TB_MEMO ,t.TB_MEMOTIME ,t.USERKEY ,a.NICKNAME,a.id  FROM TEAMBOARD t ,allmember a";
					query += " WHERE t.USERKEY = a.USERKEY AND t_key = ?";
					query += " order by tb_key";
			
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, t_key);
			
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				TBoardDTO tbdto = new TBoardDTO();
				int tb_key = rs.getInt("tb_key");
				String tb_memo = rs.getString("tb_memo");
				Date createTime = rs.getDate("tb_memotime");
				int userkey = rs.getInt("userkey");
				String nickName = rs.getString("nickname");
				String userId = rs.getString("id");
				
				tbdto.setNickName(nickName);
				tbdto.setT_key(t_key);
				tbdto.setTb_key(tb_key);
				tbdto.setTb_memo(tb_memo);
				tbdto.setTb_memotime(createTime);
				tbdto.setUserKey(userkey);
				tbdto.setUserId(userId);
			
				list.add(tbdto);
			}
			
			pstmt.close();
			rs.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//보드에 추가 하기 
	public void addBoard(TBoardDTO dto) {
		
		try {
			System.out.println("addBoard 실행");
			con = dataFactory.getConnection();
			
			int t_key = dto.getT_key();
			String tb_memo = dto.getTb_memo();
			int userKey = dto.getUserKey();
					
			String query = "insert into TeamBoard";
			query += " (tb_key,t_key,tb_memo,tb_memotime,userkey)";
			query += " values(tb_key_seq.nextval,?,?,sysdate,?)";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, t_key);
			pstmt.setString(2, tb_memo);
			pstmt.setInt(3, userKey);
			
			pstmt.executeUpdate();
			
			pstmt.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Board글 삭제하기 
	public void delBoard(int tbKey) {
		
		try {
			System.out.println("delBoard 실행");
			con = dataFactory.getConnection();
			
			String query = "delete from TeamBoard";
			query += " where tb_key=?";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, tbKey);
			
			pstmt.executeUpdate();
			
			pstmt.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
