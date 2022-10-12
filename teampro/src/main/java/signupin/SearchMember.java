package signupin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/searchMember")
public class SearchMember extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String key = request.getParameter("keyWord");
		System.out.println("search멤버 정실행");
		System.out.println("관리자가 입력한 키값 : " +key);
		List searchList = new ArrayList();
		
		if(key == "") {
			SignUpInDAO dao = new SignUpInDAO();
			searchList = dao.listMembers();
			request.setAttribute("searchList", searchList);
			RequestDispatcher req = request.getRequestDispatcher("/Signinup/adminMain.jsp");
			req.forward(request, response);
		} else {
			System.out.println("key 있을때 ");
			SignUpInDAO dao = new SignUpInDAO();
			searchList = dao.searchMember(key);
			request.setAttribute("searchList", searchList);
			RequestDispatcher req = request.getRequestDispatcher("/Signinup/adminMain.jsp");
			req.forward(request, response);
		}
		
	}

}
