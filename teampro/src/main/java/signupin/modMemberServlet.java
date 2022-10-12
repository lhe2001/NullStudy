package signupin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/modmember")
public class modMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String nickName = request.getParameter("nickName");
		String sex = request.getParameter("sex");
		String email = request.getParameter("email");
		
		SignUpInDTO dto = new SignUpInDTO();
		dto.setId(id);
		dto.setPw(pw);
		dto.setNickName(nickName);
		dto.setSex(sex);
		dto.setEmail(email);
		
		SignUpInDAO dao = new SignUpInDAO();
		
		dao.modMember(dto);
		
		response.sendRedirect("/teampro/Signinup/adminMain.jsp");
		
		
	}

}
