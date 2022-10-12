package signupin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String pw = request.getParameter("pw");
		String rePw= request.getParameter("re_pw");
		
		SignUpInDTO dto = new SignUpInDTO();
		dto.setId(request.getParameter("id"));
		dto.setPw(request.getParameter("pw"));
		dto.setName(request.getParameter("name"));
		dto.setNickName(request.getParameter("nickname"));
		dto.setSex(request.getParameter("sex"));
		dto.setEmail(request.getParameter("email"));
				
		SignUpInDAO dao = new SignUpInDAO();
		int resultIdCheck = dao.idCheck(dto);		//-1:사용불가 1:사용가능 -2:DB오류
		int resultPwCheck = dao.pwCheck(pw, rePw);	//-1:비번,확인값 불일치, 1:비번확인값 일치
		int resultEmailCheck = dao.emailCheck(dto);	//-1:사용불가 1:사용가능 -2:DB오류
		
		//이메일 중복도 체크
		// 아이디 비번 이메일 다 boolean으로 잡고 곱연산해서 true일때 진행하도록?할것인가
		
		// 아이디가 중복이 아니라면
		if(resultIdCheck==1) {
			if(resultPwCheck==1) {
				if(resultEmailCheck==1) {
					if(!(dto.getSex().equals("none"))) {
						int resultAdd = dao.addMember(dto);
						if (resultAdd == 1) {
							//가입성공시 로그인 화면으로
							out.print("<script>");
							out.print("alert('가입성공, 로그인페이지로 이동합니다.');");
							out.print("location.href = '/teampro/Signinup/signIn.jsp'");
							out.print("</script>");
							
						} else {
							//가입 실패시 다시 회원가입페이지로
							out.print("<script>");
							out.print("alert('가입실패, 회원가입페이지로 이동합니다.');");
							out.print("history.back()");
							out.print("</script>");
						}						
					}else {
						//성별선택 체크
						out.print("<script>");
						out.print("alert('성별을 선택해주세요');");
						out.print("history.back()");
						out.print("</script>");
					}
				
				}else if(resultEmailCheck==-1) {
					//이메일 사용불가
					out.print("<script>");
					out.print("alert('중복된 이메일입니다.');");
					out.print("history.back()");
					out.print("</script>");
				}else {
					//이메일 db오류
					out.print("<script>");
					out.print("alert('가입실패, 회원가입페이지로 이동합니다.');");
					out.print("history.back()");
					out.print("</script>");
				}
				
			}else {
				//비번값 불일치
				out.print("<script>");
				out.print("alert('비밀번호, 비밀번호확인값 불일치, 회원가입페이지로 이동합니다.');");
				out.print("history.back()");
				out.print("</script>");
			}
			
		}else if(resultIdCheck==-1) {
			//아이디 사용불가, 사용중이 아이디 있음
			out.print("<script>");
			out.print("alert('동일한 아이디 존재.');");
			out.print("history.back()");
			out.print("</script>");
		}else {
			//db오류
			out.print("<script>");
			out.print("alert('가입실패, 회원가입페이지로 이동합니다.');");
			out.print("history.back()");
			out.print("</script>");
		}
		
	}

}
