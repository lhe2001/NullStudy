package TeamPageServlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import JoinDTO.MyTeamListDTO;
import TMemberInfo.TMemberInfoDAO;
import TMemberInfo.TMemberInfoDTO;
import TeamBoard.TBoardDAO;
import TeamBoard.TBoardDTO;
import TeamInfo.TeamInfoDAO;
import TeamInfo.TeamInfoDTO;
import signupin.SignUpInDTO;

@WebServlet("/teamInfo/*")
public class TeamInfoCRUDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		int teamkey;
		
		try {
			teamkey = Integer.parseInt(request.getParameter("teamkey"));
			session.setAttribute("teamkey",teamkey);
		} catch (Exception e) {
			teamkey = (int) session.getAttribute("teamkey");
		}
		SignUpInDTO userInfo = (SignUpInDTO) session.getAttribute("userInfo");
		
		String url="";
		String action = request.getPathInfo();
		
		if(action.equals("/reviseTeamInfo")) {
			System.out.println("reviseTeamInfo");
			TeamInfoDAO dao = new TeamInfoDAO();
			TeamInfoDTO tdto = new TeamInfoDTO();
			
			String teamIntro = request.getParameter("teamHi");
			int teamField = Integer.parseInt(request.getParameter("field"));
			
			tdto.setTeamKey(teamkey);
			tdto.setTeamIntro(teamIntro);
			tdto.setTeamField(teamField);
			
			dao.reviseTeamInfo(tdto);
			//url="/teampro/teamdetail";
			response.sendRedirect("/teampro/teamdetail");

		}else if(action.equals("/leaderMemo")) {
			System.out.println("leaderMemo");
			TeamInfoDAO dao = new TeamInfoDAO();
			TeamInfoDTO tdto = new TeamInfoDTO();
			
			String leaderMemo = request.getParameter("leWrite");
			
			tdto.setTeamKey(teamkey);
			tdto.setTeamMemo(leaderMemo);
			
			dao.reviseLeaderMemo(tdto);
			//url="/teampro/teamdetail";
			response.sendRedirect("/teampro/teamdetail");
			
		}else if(action.equals("/addBoard")) {
			System.out.println("addBoard");
			TBoardDTO dto = new TBoardDTO();
			TBoardDAO dao = new TBoardDAO();
			
			String boardWrite = request.getParameter("boardWrite");
			
			 dto.setT_key(teamkey);
			 dto.setTb_memo(boardWrite);
			 dto.setUserKey(userInfo.getUserKey());
			
			 dao.addBoard(dto);
			 //url="/teampro/teamdetail";
			 response.sendRedirect("/teampro/teamdetail");
			 
		}else if(action.equals("/delBoard")) {
			System.out.println("delBoard");
			TBoardDTO dto = new TBoardDTO();
			TBoardDAO dao = new TBoardDAO();
			
			int tbkey = Integer.parseInt(request.getParameter("tbkey"));
			dao.delBoard(tbkey);
			
			//url="/teampro/teamdetail";
			response.sendRedirect("/teampro/teamdetail");
		}else if(action.equals("/addMember")) {
			TMemberInfoDAO dao = new TMemberInfoDAO();
			TMemberInfoDTO dto = new TMemberInfoDTO();
			
			//이미 가입되어 있는가??
			List<MyTeamListDTO> list = dao.myteamList(userInfo.getUserKey());
			boolean newMem = true;
			
			for(int i=0; i<list.size();i++) {
				MyTeamListDTO mdto = list.get(i);
				int tk = mdto.getTeamkey();
				
				if(tk == teamkey) {
					newMem = false;
				}
			}
			if(newMem) {
				System.out.println("addMember");
				dto.setId_key(userInfo.getUserKey());
				dto.setT_key(teamkey);
				
				dao.addMember(dto);
				
				//내가 멤버인 team이름 & key들을 쭉 가져와서 session 에 바인딩해준다!!
				TMemberInfoDAO tmdao = new TMemberInfoDAO();
				List<MyTeamListDTO> myteamList = tmdao.myteamList(userInfo.getUserKey());
				session.setAttribute("myteamList", myteamList);
				
				response.sendRedirect("/teampro/teamdetail");
			}else {
				out.print("<script>"
						+ "alert('이미 가입한 곳입니다.');"
						+ "location.href='/teampro/teamdetail'"
						+ "</script>");
			}
		}else if(action.equals("/delMember")) {
			System.out.println("delMember");
			TMemberInfoDAO dao = new TMemberInfoDAO();
			TMemberInfoDTO dto = new TMemberInfoDTO();
			
			int tmkey = Integer.parseInt(request.getParameter("tmkey"));
			
			dao.delMember(tmkey,teamkey);
			
			response.sendRedirect("/teampro/teamdetail");
		}
	}

}
