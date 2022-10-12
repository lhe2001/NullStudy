package TeamPageServlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import JoinDTO.MemberUserDTO;
import TMemberInfo.TMemberInfoDAO;
import TMemberInfo.TMemberInfoDTO;
import TeamBoard.TBoardDAO;
import TeamBoard.TBoardDTO;
import TeamInfo.TeamInfoDAO;
import TeamInfo.TeamInfoDTO;
import attendPack.AttendDAO;
import attendPack.AttendDTO;
import attendPack.CalDTO;
import signupin.SignUpInDTO;

@WebServlet("/teamdetail")
public class TeamDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charget=utf-8");
		HttpSession session = request.getSession();
		
		int teamkey;
		SignUpInDTO userInfo = (SignUpInDTO)session.getAttribute("userInfo");
		int userkey = userInfo.getUserKey();
		
		try {
			teamkey = Integer.parseInt(request.getParameter("teamkey"));
			session.setAttribute("teamkey",teamkey);
		} catch (Exception e) {
			teamkey = (int) session.getAttribute("teamkey");
		}
			
		//해당 팀 Info + 리더 닉네임+ 리더 아이디
		TeamInfoDAO tdao= new TeamInfoDAO();
		TeamInfoDTO teamInfo = tdao.viewInfo(teamkey);
		request.setAttribute("teamInfo", teamInfo);
		int leader = teamInfo.getTeamLeader();
		
		//해당 팀멤버 Info 
		TMemberInfoDAO tmdao = new TMemberInfoDAO();
		List<MemberUserDTO> memberList = tmdao.viewMemberInfo(teamkey);
		request.setAttribute("memberList", memberList);
		
		
		//출석
		
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month =cal.get(Calendar.MONTH)+1;
		int day = cal.get(Calendar.DATE);
				
		AttendDAO adao = new AttendDAO();
		
		List AttnSize = new ArrayList();
		//멤버를 돌면서 userkey를 가져온다.
		for(int i=0; i<memberList.size();i++) {
			MemberUserDTO dto = memberList.get(i);
			int memUserKey = dto.getuserKey();
			//user 마다 cdto 생성하여서 is Month로 list 를 뽑는다.
			CalDTO cdto = new CalDTO();
			cdto.setUserkey(memUserKey);
			cdto.setMonth(month);
			cdto.setYear(year);
			List<AttendDTO> memAttd = adao.isMonth(cdto);
			
			double count = (double)memAttd.size();
			AttnSize.add(count);
		}
		
		List PercentList = new ArrayList();
		//출석률을 구해보자!!!!!
		for(int j=0; j<AttnSize.size();j++) {
			double count = (double) AttnSize.get(j);
			double percent = (count/30)*100 ;
			double Userpercent = Math.round(percent*10)/10.0;
			PercentList.add(Userpercent);
		}
		
		request.setAttribute("PercentList", PercentList);
		
				
		//팀 보드 정보 + 닉네임 + 아이디
		TBoardDAO tbdao = new TBoardDAO();
		List<TBoardDTO> boardList = tbdao.viewBoard(teamkey);
		request.setAttribute("boardList", boardList);
		
		if(userkey == leader ) {
			RequestDispatcher dispatch = request.getRequestDispatcher("/teamPage/TeamInfo.jsp");
			dispatch.forward(request, response);
		}else {
			RequestDispatcher dispatch = request.getRequestDispatcher("/teamPage/TeamInfoM.jsp");
			dispatch.forward(request, response);
		}
		
		
	}

}
