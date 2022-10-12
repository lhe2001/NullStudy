package TeamPageServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import JoinDTO.MemberUserDTO;
import JoinDTO.MyTeamListDTO;
import TMemberInfo.TMemberInfoDAO;
import TMemberInfo.TMemberInfoDTO;
import TeamInfo.TeamInfoDAO;
import TeamInfo.TeamInfoDTO;
import signupin.SignUpInDTO;

@WebServlet("/teamCheck/*")
public class TeamCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		System.out.println("teamcheck post");
		HttpSession session = request.getSession();
		
		String action = request.getPathInfo();
		System.out.println("action"+action);
		
		//세션에서 내정보를 가져온뒤
		SignUpInDTO userInfo = (SignUpInDTO)session.getAttribute("userInfo");
		System.out.println("userInfo"+userInfo);
		
		//allteamList 가져오기
		TeamInfoDAO dao = new TeamInfoDAO();
		List<TeamInfoDTO> allTeamList = dao.allTeamList();
		session.setAttribute("allTeamList", allTeamList);
		
		if(userInfo != null) {
			System.out.println("userInfo null 아님");
			//Userkey 를 통해 내가 Teammember에 속해 있는지를 보자.
			TMemberInfoDAO tmdao = new TMemberInfoDAO();
			boolean result = tmdao.includeTeam(userInfo.getUserKey());
			
			//팀이 있다면,,
			if(result){
				System.out.println("팀있어용!");
				//내가 멤버인 team이름 & key들을 쭉 가져와서 session 에 바인딩해준다!!
				List<MyTeamListDTO> myteamList = tmdao.myteamList(userInfo.getUserKey());
				session.setAttribute("myteamList", myteamList);
				
			}else {
				String msg = "속한팀이 없습니다 ㅠㅠ";
				session.setAttribute("msg", msg);
			}
			
			//action에 따라 갈라지기 
			if(action.equals("/main")) {
				response.sendRedirect("/teampro/Main2.jsp");
				
			}else if(action.equals("/teamList")) {
				response.sendRedirect("/teampro/teamPage/MyTeamList.jsp");
			}
			
		}else {
			out.print("다시 로그인 하세요"
					+ "<br><a href='/teampro/Signinup/signIn.jsp'>다시 로그인하기</a>");
			System.out.println(54321);
			session.invalidate();
		}
			
		
	}
}


