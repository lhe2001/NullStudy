package TeamPageServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import JoinDTO.MemberUserDTO;
import JoinDTO.MyTeamListDTO;
import TMemberInfo.TMemberInfoDAO;
import TeamInfo.TeamInfoDAO;
import TeamInfo.TeamInfoDTO;
import signupin.SignUpInDTO;

@WebServlet("/teamcd/*")
public class TeamCDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		HttpSession session = request.getSession();
		
		int userkey = -1;
		
		try {
			SignUpInDTO userInfo = (SignUpInDTO)session.getAttribute("userInfo");
			userkey = userInfo.getUserKey();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(userkey);
		if(userkey != -1) {
			PrintWriter out = response.getWriter();
			
			TeamInfoDAO dao = new TeamInfoDAO();
			TeamInfoDTO tdto = new TeamInfoDTO();
			
			String url="";
			String action = request.getPathInfo();
			
				
			if(action.equals("/addTeam")) {
					System.out.println("addTeam이동");
					
					String teamName = request.getParameter("teamName").trim();
					String teamIntro = request.getParameter("teamIntro");
					int field = Integer.parseInt(request.getParameter("field"));
					
					//중복체크
					boolean result = dao.isExistTeam(teamName);
					
					if(result) {
						out.print("<script>"
				                  + "alert('중복된 이름입니다.');"
				                  + "history.back()"
				                  + "</script>");
					
					}else {
						tdto.setTeamName(teamName);
						tdto.setTeamIntro(teamIntro);
						tdto.setTeamField(field);
						tdto.setTeamLeader(userkey);
						
						
						//팀추가
						dao.newTeam(tdto);
						url="/teampro/allteam";
						
						//myteam에도 추가 
						SignUpInDTO userInfo = (SignUpInDTO)session.getAttribute("userInfo");
						System.out.println("userInfo:"+userInfo);
						
						TMemberInfoDAO tmdao = new TMemberInfoDAO();
						List<MyTeamListDTO> myteamList = tmdao.myteamList(userInfo.getUserKey());
						session.setAttribute("myteamList", myteamList);
						
						response.sendRedirect("/teampro/allteam");
					}
					
			}else if(action.equals("/delTeam")) {
				System.out.println("delTeam");
				List<MemberUserDTO> memberList = (List) session.getAttribute("memberList");
				
				
					int teamkey = (int) session.getAttribute("teamkey");
					
					tdto.setTeamLeader(userkey);
					tdto.setTeamKey(teamkey);
					
					dao.delTeam(tdto);
					url="/teampro/teamCheck/teamList";
					
					response.sendRedirect(url);
			}
		}else {
			response.sendRedirect("/teampro/Signinup/signin.jsp");
		}
	}

}
