package MyStudyServlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import signupin.SignUpInDAO;
import signupin.SignUpInDTO;

/**
 * Servlet implementation class modServlet
 */
@WebServlet("/mod")
public class modServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charget=utf-8");
		System.out.println("Mod doPost실행");
		
		HttpSession session = request.getSession();
		
		
		SignUpInDTO userInfo = (SignUpInDTO)session.getAttribute("userInfo");
		int userkey = userInfo.getUserKey();
			
			//수정한 값을 가져와서
			String id =userInfo.getId();
			String pw = request.getParameter("pw");
			String nickname = request.getParameter("nickname");
			String email = request.getParameter("email");
			String intro = request.getParameter("intro");
			
			
			userInfo.setPw(pw);
			userInfo.setNickName(nickname);
			userInfo.setEmail(email);
			userInfo.setIntro(intro);

			System.out.println(id+pw+nickname+email+intro);
			
			SignUpInDAO udao = new SignUpInDAO();
			udao.modUserInfo(userInfo);
			
			RequestDispatcher dispatch = request.getRequestDispatcher("/mypage");
			dispatch.forward(request, response);
			
		
	}
	

	
	
}
