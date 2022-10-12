package board;

import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.jsp.tagext.TryCatchFinally;
import javax.sql.DataSource;

import org.apache.catalina.User;
import org.apache.el.parser.AstDotSuffix;

import signupin.SignUpInDTO;

public class BoardDAO {
	/*
	 * TODO DAO클래스에선 selectAllArticles() 메소드를 실행하게 되면 계층형 구조로 이루어진 sql문을 이용해 전체 게시글
	 * 목록 조회한 후 list로 return
	 */

	// 필드에 db에 접속에 필요한 녀석들을 셋팅해주자.
	private PreparedStatement pstmt;
	private Connection con;
	private DataSource dataFactory;

	// 생성자에 셋팅해주자
	public BoardDAO() {
		try {
			// JNDI에 접근하기 위해 기본 경로(java:/comp/env 지정
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/study");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// selectAllArticles() 메소드 만들자!!! return 타입은 List <전체 목록 조회>
	public List<BoardDTO> selectAllArticles() {

		// list에 담아야하니 객체 초기화 해준다.
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		try {
			// 연결 합시다!
			con = dataFactory.getConnection();
			// 계층형 오라클 쿼리문을 작성하자
			String query = "select level, b.B_key, b.B_ArticleNo, b.B_parentNo, B_title , B_content, ";
			query += " b.B_field, b.B_writeDate, b.B_view, b.UserKey, a.nickName ";
			query += " from Board b ";
			query += " , ALLMEMBER a ";
			query += " where b.userkey = a.userkey ";
			query += " Start with B_parentNo = 0 ";
			query += " connect by prior B_ArticleNo = B_parentNo ";
			query += " order siblings by B_ArticleNo desc ";
//				   System.out.println("계층형 쿼리 : " + query);
			
			// 객체에 쿼리를 담는다.
			pstmt = con.prepareStatement(query);
			// select 문이니 executeQuery를 호출해 sql문 실행
			ResultSet rs = pstmt.executeQuery();
			// ResultSet타입의 rs 에 결과값이 있는 동안(while문 실행)
			while (rs.next()) {
				int level = rs.getInt("level");
				int b_key = rs.getInt("b_key");
				int b_articleNo = rs.getInt("b_articleNo");
				int b_parentNo = rs.getInt("b_parentNo");
				String b_title = rs.getString("b_title");
				String b_content = rs.getString("b_content");
				String nickName = rs.getString("nickName");
				int b_field = rs.getInt("b_field");
				Date b_writeDate = rs.getDate("b_writeDate");
				int b_view = rs.getInt("b_view");
				int userkey = rs.getInt("userkey");
				// 담을 dto타입의 객체 생성!!
				BoardDTO dto = new BoardDTO();
				// dto에 셋팅해주자
				dto.setLevel(level);
				dto.setB_key(b_key);
				dto.setB_articleNo(b_articleNo);
				dto.setB_parentNo(b_parentNo);
				dto.setB_title(b_title);
				dto.setB_content(b_content);
				dto.setB_field(b_field);
				dto.setB_writeDate(b_writeDate);
				dto.setB_view(b_view);
				dto.setUserkey(userkey);
				dto.setNickName(nickName);
				// List로 리턴해야 하니 list.add로 담아주자
				list.add(dto);
			}
			// 종료해주자
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/*
	 * addNewArticle() 메소드를 작성해야 하는데.. 새로 추가되는 글의 번호는 현재 목록에 있는 글번호 보다 1이 커야되는데
	 * max함수를 사용해 가장 큰 글의 번호를 구해서 +1 을 리턴해주는 메소드(ex. getMaxArticleNO())를 하나 만들어서
	 * return값을 ArticleNO에 집어넣자.
	 */

	// max함수 이용하여 getMaxArticleNO() 메소드 먼저 생성 여기서만 쓰고 버릴 것 이므로 private로 만들자
	// return값이 숫자이므로 int로 받자
	private int getMaxArticleNO() {
		try {
			// db연결
			con = dataFactory.getConnection();
			// 가장 큰 글번호 조회 query
			String query = " select max(b_articleNo) from Board ";
			System.out.println("maxQuery : " + query);
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				// max값에서 1을 더하여 리턴으로 돌려주자
				return (rs.getInt(1) + 1);
			}
			// 이거 안닫으면 큰일나요..
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0; // 아닐경우(없겠지만) 랜덤숫자 0 돌려주자
	}

	// addNewArticle() 메소드 작성 <Board 에 글 추가>
	public int addNewArticle(BoardDTO dto) {

		int b_articleNo = getMaxArticleNO();// 새 글 번호는 메소드 리턴값으로 쓰자

		try {
			con = dataFactory.getConnection();
			// 넣을 값을 dto에서 가져와야 하기때문에 매개변수로 BoardDTO타입의 dto를 쓰자
			int b_parentNo = dto.getB_parentNo(); // 진짜 후 이거때문에 2시간....날렸다 자식이랑 부모 바뀌어서 아...
			String b_content = dto.getB_content();
			String b_title = dto.getB_title();
			int b_field = dto.getB_field();
			String b_imageFile = dto.getB_imageFile();
			int user_key = dto.getUserkey();
			
			String query = " insert into Board (B_key, B_ArticleNo, B_parentNo , B_title,B_content, B_field,  UserKey, B_imageFile) ";
			query += " values(key_b.nextval,?,?,?,?,?,?,?)";
			System.out.println("addNewArticle query : " + query);
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, b_articleNo);
			pstmt.setInt(2, b_parentNo);
			pstmt.setString(3, b_title);
			pstmt.setString(4, b_content);
			pstmt.setInt(5, b_field);
			pstmt.setInt(6, user_key);
			pstmt.setString(7, b_imageFile);
			pstmt.executeUpdate();// 진짜 씨x 욕나온다 이거 못찾아서 1시간 버렸네......아 스트레스
			pstmt.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return b_articleNo;
	}

	// viewArticle() 메소드 작성 <글 상세 목록 view>
	// 여기서 수정 해야 할 것은 글 내용에 어떤 내용들을 보여줄지 생각...

	public BoardDTO selectViewArticle(int b_articleNo) {

		BoardDTO dto = new BoardDTO();

		try {
			con = dataFactory.getConnection();

			String query = " select b.b_articleNo, b.b_parentNo, b.b_title, b.b_content, b.b_writeDate , b.userkey, b.b_imageFile , a.nickName ";
			query += " from Board b, ";
			query += " ALLMEMBER a ";
			query += " where b.userkey = a.userkey ";
			query += " and b_articleNo = ? ";
			
			System.out.println("viewAritcle query = " + query);
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, b_articleNo);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			
			// 위에서 변수로 정해서 쓰고 있어서 변수명을 2로 바꿈
			int b_articleNo2 = rs.getInt("b_articleNo");
			int b_parentNo = rs.getInt("b_parentNo");
			String b_title = rs.getString("b_title");
			String b_content = rs.getString("b_content");
			String nickName = rs.getString("nickName");
			Date b_writeDate = rs.getDate("b_writeDate");
			int userkey = rs.getInt("userkey");
			String b_imageFile = rs.getString("b_imageFile");

			dto.setB_articleNo(b_articleNo2);
			dto.setB_parentNo(b_parentNo);
			dto.setB_title(b_title);
			dto.setB_content(b_content);
			dto.setNickName(nickName);
			dto.setB_writeDate(b_writeDate);
			dto.setUserkey(userkey);
			dto.setB_imageFile(b_imageFile);

			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	// 글 수정하는 메소드
	public void modifyArticle(BoardDTO dto) {
		int b_articleNo = dto.getB_articleNo();
		String b_title = dto.getB_title();
		String b_content = dto.getB_content();
		String b_imageFile = dto.getB_imageFile();

		try {
			con = dataFactory.getConnection();

			// 제목과 내용을 먼저 수정하고
			String query = " update Board set ";
			query += " b_title = ?, ";
			query += " b_content = ? ";

			/*
			 * 이미지가 있을 경우와 아닌 경우 쿼리를 추가해주고
			 */
			if (b_imageFile != null && b_imageFile.trim().length() != 0) {
				query += " ,b_imageFile = ? ";
			}
			query += " where b_articleNo = ? ";

			pstmt = con.prepareStatement(query);
			// 셋팅을 해주자
			pstmt.setString(1, b_title);
			pstmt.setString(2, b_content);
			// 이미지를 수정할 경우
			if (b_imageFile != null && b_imageFile.trim().length() != 0) {
				pstmt.setString(3, b_imageFile);
				pstmt.setInt(4, b_articleNo);
			} else {
				pstmt.setInt(3, b_articleNo);
			}
			// 업뎃해주고
			pstmt.executeUpdate();
			pstmt.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 삭제하는 메소드
	public void deleteArticle(int b_articleNo) {
		try {
			con = dataFactory.getConnection();
			String query = " delete from board ";
			query += " where b_articleNo in ( ";
			query += " select b_articleNo from board  ";
			query += " start with b_articleNo = ? ";
			query += " connect by prior b_articleNo = b_parentNo ) ";
			System.out.println("delete = " + query);
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, b_articleNo);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 삭제할 글 번호 가져오는 메소드
	public List<Integer> selectRemovedArticle(int b_articleNo) {
		List<Integer> list = new ArrayList<Integer>();
		try {
			con = dataFactory.getConnection();

			String query = " select b_articleNo from board ";
			query += " start with b_articleNo = ? ";
			query += " connect by prior b_articleNo = b_parentNo ";
			System.out.println("list query =" + query);
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, b_articleNo);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println("rs.getInt('b_articleNo') = " + rs.getInt("b_articleNo"));
				b_articleNo = rs.getInt("b_articleNo");
				list.add(b_articleNo);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// 전체로 검색하는 메소드
	public List<BoardDTO> searchAllArticle(BoardDTO dto) {

		String search_bar = dto.getSearch_bar();
		int setSearch_field = dto.getSearch_field();
		System.out.println("search_bar ===>> " + search_bar);
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		try {
			con = dataFactory.getConnection();
			String query = " select * from board b ";
			query += " , allmember a ";
			query += " where b.userkey = a.userkey ";
			if(setSearch_field == 1) {
				query += " and lower(b_title) like lower(?) ";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, "%" + search_bar + "%");
			} else if (setSearch_field == 2) {
				query += " and lower(b_content) like lower(?) ";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, "%" + search_bar + "%");
			} else if (setSearch_field == 3) {
				query += " and lower(nickName) like lower(?) ";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, "%" + search_bar + "%");
			} else if(setSearch_field == 4){
				query += " and (lower(b_title) like lower(?) ";
				query += " or lower(b_content) like lower(?) ";
				query += " or lower(nickName) like lower(?)) ";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, "%" + search_bar + "%");
			pstmt.setString(2, "%" + search_bar + "%");
			pstmt.setString(3, "%" + search_bar + "%");
			}
			System.out.println("query   ---->" + query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int b_key = rs.getInt("b_key");
				int b_articleNo = rs.getInt("b_articleNo");
				int b_parentNo = rs.getInt("b_parentNo");
				String b_title = rs.getString("b_title");
				String b_content = rs.getString("b_content");
				String nickName = rs.getString("nickName");
				int b_field = rs.getInt("b_field");
				Date b_writeDate = rs.getDate("b_writeDate");
				int b_view = rs.getInt("b_view");
				int userkey = rs.getInt("userkey");
				// 담을 dto타입의 객체 생성!!
					dto = new BoardDTO();
					// dto에 셋팅해주자
					dto.setSearch_bar(search_bar);
					dto.setB_key(b_key);
					dto.setB_articleNo(b_articleNo);
					dto.setB_parentNo(b_parentNo);
					dto.setB_title(b_title);
					dto.setB_content(b_content);
					dto.setNickName(nickName);
					dto.setB_field(b_field);
					dto.setB_writeDate(b_writeDate);
					dto.setB_view(b_view);
					dto.setUserkey(userkey);
					// List로 리턴해야 하니 list.add로 담아주자
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

	// 말머리에서 선택했을때 리스트 출력시킬 메소드
	public List<BoardDTO> wiewAllArticle(BoardDTO dto) {

		int list_sel = dto.getList_sel();
	
		System.out.println("search_bar ===>> " + list_sel);
	
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		try {
			con = dataFactory.getConnection();
			String query = " select * from board b ";
			query+= " , allmember a ";
			query+= " where b.userkey = a.userkey ";
			if(list_sel !=0) {
				System.out.println("list_sel 0값 실행");
				query += " and b_field =  ? ";
				pstmt = con.prepareStatement(query);
				pstmt.setInt(1, list_sel);
			}else {
				pstmt = con.prepareStatement(query);
			}
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int b_key = rs.getInt("b_key");
				int b_articleNo = rs.getInt("b_articleNo");
				int b_parentNo = rs.getInt("b_parentNo");
				String b_title = rs.getString("b_title");
				String nickName = rs.getString("nickName");
				String b_content = rs.getString("b_content");
				int b_field = rs.getInt("b_field");
				Date b_writeDate = rs.getDate("b_writeDate");
				int b_view = rs.getInt("b_view");
				int userkey = rs.getInt("userkey");
					// 담을 dto타입의 객체 생성!!
					dto = new BoardDTO();
					// dto에 셋팅해주자
					dto.setList_sel(list_sel);
					dto.setB_key(b_key);
					dto.setB_articleNo(b_articleNo);
					dto.setB_parentNo(b_parentNo);
					dto.setB_title(b_title);
					dto.setNickName(nickName);
					dto.setB_content(b_content);
					dto.setB_field(b_field);
					dto.setB_writeDate(b_writeDate);
					dto.setB_view(b_view);
					dto.setUserkey(userkey);
					// List로 리턴해야 하니 list.add로 담아주자
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
}