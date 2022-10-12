package signupin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class SignUpInDAO {
	
	private Connection con;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	 
	public SignUpInDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/study");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	//모든 회원의 정보(DTO)를 list에 담아주는 메소드
	public List<SignUpInDTO> listMembers(){
		List<SignUpInDTO> list = new ArrayList<SignUpInDTO>();
		try {
			//db접속해서 select * from allmember 실행
			con=dataFactory.getConnection();
			String query =" select * from allmember";
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			
			//쿼리결과 한줄씩 dto에 답아준뒤 list로 넣어주고 list return
			while(rs.next()) {
				SignUpInDTO dto = new SignUpInDTO();
				dto.setUserKey(rs.getInt("userkey"));
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setSex(rs.getString("sex"));
				dto.setNickName(rs.getString("nickname"));
				dto.setEmail(rs.getString("email"));
				dto.setJoinDate(rs.getDate("joindate"));
				dto.setIntro(rs.getString("intro"));
				dto.setLastTime(rs.getDate("lasttime"));
				dto.setPhoto(rs.getInt("photo"));

				list.add(dto);
			}
			
			rs.close();
			pstmt.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
			
	//회원가입시 아이디 중복여부 체크하는 메소드
	public int idCheck(SignUpInDTO dto) {
		
		try { 
			String query = " select id from allmember where lower(id) = lower(?)"; 
			con = dataFactory.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, dto.getId());
			
			ResultSet rs = pstmt.executeQuery();
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return -1; // 동일한 아이디 존재
			}
			return 1; // 사용가능 아이디
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -2; //db오류
	}
	
	//회원가입시 이메일 중복여부 체크하는 메소드
	public int emailCheck(SignUpInDTO dto) {
		
		try { 
			String query = " select email from allmember where lower(email) = lower(?)"; 
			con = dataFactory.getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, dto.getEmail());
			
			ResultSet rs = pstmt.executeQuery();
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return -1; // 동일한 이메일 존재
			}
			return 1; // 사용가능 이메일
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -2; //db오류
	}
	
	//회원가입시 비밀번호,비밀번호확인값 일치 체크하는 메소드
	public int pwCheck(String pw, String rePw) {
		if(pw.equals(rePw)) {
			//비번 ,비번확인 값 같음
			return 1; 
		} else {
			//비번, 비번확인 값 다름
			return -1;
		}
	}
	
	//회원가입 시 입력된 데이터를 DB로 전달하는 메소드
	public int addMember(SignUpInDTO dto) {
		
		try {
			con=dataFactory.getConnection();
			String query =" insert into allmember";
			query 		+=" (userkey, id, pw, name, sex, nickname, email, joindate)";
			//db에 시퀄 생성해줘야함
			query 		+=" values (seq_userkey.nextval, ?, ?, ?, ?, ?, ?, sysdate)";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getSex());
			pstmt.setString(5, dto.getNickName());
			pstmt.setString(6, dto.getEmail());
			
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
			
			return 1; // 가입성공 메세지 > signin페이지로 이동
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; //가입실패 메세지 > signup페이지로 이동
	}
	
	//로그인 메소드
	public int signIn(String id, String pw) {
		if("admin".equals(id)) {
			try {
				// 전달받은 아이디로 매칭되는 pw 조회한 뒤에 전달받은 pw와 동일한지 확인
				String query = " select pw from allmember where lower(id) = lower(?)"; 
				con = dataFactory.getConnection();
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, id);
				
				ResultSet rs = pstmt.executeQuery();
				
				rs = pstmt.executeQuery();
				if(rs.next()) {
					if(rs.getString("pw").equals(pw)) {
						// 이렇게 다 닫아줘야하는게 맞는지? 확인
						rs.close();
						pstmt.close();
						con.close();
						return 99; // 관리자 로그인 성공
					} else {
						rs.close();
						pstmt.close();
						con.close();
						return 0; // 비밀번호 불일치 메세지 전달
					}
				}
				rs.close();
				pstmt.close();
				con.close();
				return -1; //아이디 존재하지 않음 메세지 전달 
				
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
			return -2;	//DB 오류
		} else {
			
			try {
				// 전달받은 아이디로 매칭되는 pw 조회한 뒤에 전달받은 pw와 동일한지 확인
				String query = " select pw from allmember where lower(id) = lower(?)"; 
				con = dataFactory.getConnection();
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, id);
				
				ResultSet rs = pstmt.executeQuery();
				
				rs = pstmt.executeQuery();
				if(rs.next()) {
					if(rs.getString("pw").equals(pw)) {
						// 이렇게 다 닫아줘야하는게 맞는지? 확인
						rs.close();
						pstmt.close();
						con.close();
						return 1; // 로그인 성공 > 세션에 db의 개인정보 담아서 메인페이지로 이동하도록
					} else {
						rs.close();
						pstmt.close();
						con.close();
						return 0; // 비밀번호 불일치 메세지 전달
					}
				}
				rs.close();
				pstmt.close();
				con.close();
				return -1; //아이디 존재하지 않음 메세지 전달 
				
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
			return -2;	//DB 오류 
		}
	}
	
	//signIn 성공시 session에 담아줄 개인 DTO 생성하는 메소드
	public SignUpInDTO userInfo(String id){
		SignUpInDTO dto = new SignUpInDTO();
		
		try {
			con=dataFactory.getConnection();
			String query =" select * from allmember where lower(id) = lower(?)";
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();

			if(rs.next()) {
				
				dto.setUserKey(rs.getInt("userkey"));
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setSex(rs.getString("sex"));
				dto.setNickName(rs.getString("nickname"));
				dto.setEmail(rs.getString("email"));
				dto.setJoinDate(rs.getDate("joindate"));
				dto.setIntro(rs.getString("intro"));
				dto.setLastTime(rs.getDate("lasttime"));
				dto.setPhoto(rs.getInt("photo"));
			}
				
			rs.close();
			pstmt.close();
			con.close();
			
			return dto;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//로그인이 성공할경우 해당 유저 db에 로그인한 시간 업데이트하는 메소드
	public void lastTime(String id) {
		
		try {
			con=dataFactory.getConnection();
			String query =" UPDATE ALLMEMBER SET lasttime=SYSDATE where lower(id)=lower(?)";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//관리자용 메소드
	
	//관리자 회원 검색용 메소드 
			public List<SignUpInDTO> searchMember(String Keyword){
				List<SignUpInDTO> list = new ArrayList<SignUpInDTO>();
				try {
					//db접속해서 select * from allmember 실행
					con=dataFactory.getConnection();
					String query =" select * from allmember";
					query 		+=" where name like '%' || ? || '%'";
					query		+=" or id like '%' || ? || '%'";
							
					pstmt = con.prepareStatement(query);
					pstmt.setString(1, Keyword);
					pstmt.setString(2, Keyword);
					ResultSet rs = pstmt.executeQuery();
					
					//쿼리결과 한줄씩 dto에 답아준뒤 list로 넣어주고 list return
					while(rs.next()) {
						SignUpInDTO dto = new SignUpInDTO();
						dto.setUserKey(rs.getInt("userkey"));
						dto.setId(rs.getString("id"));
						dto.setPw(rs.getString("pw"));
						dto.setName(rs.getString("name"));
						dto.setSex(rs.getString("sex"));
						dto.setNickName(rs.getString("nickname"));
						dto.setEmail(rs.getString("email"));
						dto.setJoinDate(rs.getDate("joindate"));
						dto.setIntro(rs.getString("intro"));
						dto.setLastTime(rs.getDate("lasttime"));
						dto.setPhoto(rs.getInt("photo"));

						list.add(dto);
					}
					
					rs.close();
					pstmt.close();
					con.close();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				return list;
			}

	//관리자가 유저정보 삭제하는 메소드 		
	public void delMember(String id) {
		
		try {
			con=dataFactory.getConnection();
			String query =" delete from allmember";
			query 		+=" where lower(id) = lower(?)";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, id);
			
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//관리자가 유저정보 수정하는 메소드 
	public void modMember(SignUpInDTO dto) {
		try {
			con=dataFactory.getConnection();
			String query =" UPDATE ALLMEMBER SET ";
			query 		+=" pw=?,";
			query 		+=" nickname=?,";
			query 		+=" sex=?,";
			query 		+=" email=?";
			query 		+=" where lower(id)=lower(?)";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, dto.getPw());
			pstmt.setString(2, dto.getNickName());
			pstmt.setString(3, dto.getSex());
			pstmt.setString(4, dto.getEmail());
			pstmt.setString(5, dto.getId());
			
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//회원정보 수정하는 메소드
	   public SignUpInDTO modUserInfo(SignUpInDTO udto) {
	      
	      String id = udto.getId();
	      String pw = udto.getPw();
	      String nickname = udto.getNickName();
	      String email = udto.getEmail();
	      String intro = udto.getIntro();
	      
	      try {
	         System.out.println("mod 유저info작동");
	         con = dataFactory.getConnection();
	         
	         String query = "update allmember set pw=?, nickname=?, email=?, intro=?";
	         query += " where id=?";
	         
	         pstmt = con.prepareStatement(query);
	         pstmt.setString(1, pw);
	         pstmt.setString(2, nickname);
	         pstmt.setString(3, email);
	         pstmt.setString(4, intro);
	         pstmt.setString(5, id);
	         
	         pstmt.executeUpdate();
	         
	         pstmt.close();
	         con.close();
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      return udto;
	   }
	
}
