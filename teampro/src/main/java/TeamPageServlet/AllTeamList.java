package TeamPageServlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import JoinDTO.MyTeamListDTO;
import TMemberInfo.TMemberInfoDAO;
import TeamInfo.TeamInfoDAO;
import TeamInfo.TeamInfoDTO;

@WebServlet("/allteam")
public class AllTeamList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charget=utf-8");
		System.out.println("allteam왓음");
		
		TeamInfoDAO dao = new TeamInfoDAO();
		TeamInfoDTO dto = new TeamInfoDTO();
		
		HttpSession session = request.getSession();
		
		//allteamList 가져오기
		List<TeamInfoDTO> allTeamList = dao.allTeamList();
		session.setAttribute("allTeamList", allTeamList);
		
		response.sendRedirect("/teampro/teamPage/AllTeamList.jsp");
	}

}
