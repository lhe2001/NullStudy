package mainServlet;

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

import TeamInfo.TeamInfoDAO;
import TeamInfo.TeamInfoDTO;
import board.BoardDTO;
import board.BoardService;
import signupin.SignUpInDTO;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		System.out.println("post");
		
		BoardService service = new BoardService();
		BoardDTO dto;
		
		
		//allteamList 가져오기
		TeamInfoDAO tdao = new TeamInfoDAO();
		List<TeamInfoDTO> allTeamList = tdao.allTeamList();
		session.setAttribute("allTeamList", allTeamList);
		
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
		response.sendRedirect("/teampro/Main.jsp");
	}

}
