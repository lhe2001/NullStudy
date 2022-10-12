package MyStudyServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import attendPack.AttendDAO;
import attendPack.AttendDTO;
import signupin.SignUpInDTO;

/**
 * Servlet implementation class AttendServlet
 */
@WebServlet("/add")
public class AttendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		SignUpInDTO userInfo = (SignUpInDTO)session.getAttribute("userInfo");
		int userkey = userInfo.getUserKey();
		
		System.out.println("Attend doPost 시작");
			
		AttendDAO atdao = new AttendDAO();
		
		//출석 있는지 체크
		boolean result = atdao.isAttend(userkey);
			
		if(result) {
			System.out.println("출석이미있음");
			out.print("<script>"
	                  + "alert('출석 있습니다');"
	                  + "history.back()"
	                  + "</script>");
		} else {
			AttendDAO adao = new AttendDAO();
			adao.updateAttend(userkey);
			System.out.println("출석 디비에 넣기");

			response.sendRedirect("/teampro/mypage");
		}
			
	}

}
