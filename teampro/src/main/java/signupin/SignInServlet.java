package signupin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.BoardDTO;
import board.BoardService;

@WebServlet("/signin")
public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		String id = request.getParameter("id");
		String pw = request.getParameter("pw");		
		SignUpInDAO dao = new SignUpInDAO();
		BoardService service = new BoardService();
		BoardDTO dto;
		
		int result = dao.signIn(id, pw);	// 1:login성공//0:pw틀림//-1:id없음//-2:db오류
		
		if(result == 1) {
			//세션에 해당 회원의 정보를 DTO에 담아서 메인페이지로 이동
			HttpSession session = request.getSession();
			SignUpInDTO userInfo = new SignUpInDTO();
			//로그인이 되면 lasttime을 업데이트해준다
			dao.lastTime(id);
			
			
			//게시판 가져오기
			List<BoardDTO> articlesList = new ArrayList<BoardDTO>();
			articlesList = service.listArticles();
			for (int i = 0; i < articlesList.size(); i++) {
				dto = articlesList.get(i);
				switch (dto.getB_field()) {
				case 10:
					dto.setB_fieldName("질문");
					break;
				case 20:
					dto.setB_fieldName("잡담");
					break;
				case 30:
					dto.setB_fieldName("비밀글");
					break;
				case 40:
					dto.setB_fieldName("나도몰라");
					break;
				default:
					break;
				}
			}
			System.out.println("articlesList" + articlesList);
			session.setAttribute("articlesList", articlesList);
			
			userInfo = dao.userInfo(id);
			session.setAttribute("userInfo", userInfo);
			
			//로그인 성공(메인페이지로 이동)
			out.print("<script>");
			out.print("alert('로그인성공, 메인페이지로 이동합니다.');");
			out.print("location.href = '/teampro/teamCheck/main'");
			out.print("</script>");
			
			//로그인 버튼, 회원가입 버튼 사라지고 로그아웃 버튼 생성
			
		} else if(result == 0){
			//비밀번호 불일치(로그인페이지로 이동)
			out.print("<script>");
			out.print("alert('비밀번호가 틀렸습니다.');");
			out.print("location.href = '/teampro/Signinup/signIn.jsp'");
			out.print("</script>");
			
		} else if(result == -1) {
			//아이디가 존재하지 않음(로그인페이지로 이동)
			out.print("<script>");
			out.print("alert('존재하지 않는 아이디입니다.');");
			out.print("location.href = '/teampro/Signinup/signIn.jsp'");
			out.print("</script>");
			
		} else if(result == 99) {
			//관리자 로그인
			HttpSession session = request.getSession();
			SignUpInDTO userInfo = new SignUpInDTO();
			//로그인이 되면 lasttime을 업데이트해준다
			dao.lastTime(id);
			
			userInfo = dao.userInfo(id);
			session.setAttribute("userInfo", userInfo);
			
			out.print("<script>");
			out.print("alert('관리자로 접속합니다,');");
			// 아래 내용 수정(관리자 페이지로)
			out.print("location.href = '/teampro/Signinup/adminMain.jsp'");
			out.print("</script>");
			
		} else {
			//db오류(로그인페이지로 이동)
			out.print("<script>");
			out.print("alert('DB오류 발생.');");
			out.print("location.href = 'Main.jsp'");
			out.print("</script>");
		}
		
		
	}

}
