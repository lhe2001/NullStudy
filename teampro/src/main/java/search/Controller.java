package search;

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

import signupin.SignUpInDTO;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ctr.doPost 실행");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		// 한글 안깨지게

		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		SignUpInDTO userInfo = new SignUpInDTO();
		userInfo = (SignUpInDTO)session.getAttribute("userInfo");
		if(userInfo == null) {
			System.out.println("로그인 세션이 없음");
			out.print("<script>");					
			out.print("window.onload = function() {");					
			out.print("		alert('검색하려면 로그인하세요');");
			out.print("		location.href = '/teampro/Main.jsp'");
			out.print("}");
			out.print("</script>");
			
		} else {
			System.out.println("로그인 세션이 있음");
			
//-------------------------------equals 해서 null 일때--------------------------------------//		
		
			String selectValue = request.getParameter("selectValue");
			String mainSearchH = request.getParameter("mainSearchH");
			System.out.println(selectValue);
			if (mainSearchH == null) {
					mainSearchH = "null";	
			}
			if (selectValue == null && mainSearchH.equals("mainSearchH")) {
				System.out.println("검색기준 선택 안함");
				out.print("<script>");					
				out.print("window.onload = function() {");					
				out.print("		alert('검색기준을 선택하세요');");
				out.print("		location.href = '/teampro/Main.jsp'");
				out.print("}");
				out.print("</script>");
			}
			
			String searchBoardHidden = request.getParameter("searchBoardHidden");
			if (searchBoardHidden == null) {
				searchBoardHidden = "null";
			}
			String searchUserHidden = request.getParameter("searchUserHidden");
			if (searchUserHidden == null) {
				searchUserHidden = "null";
			}
			String searchTeamHidden = request.getParameter("searchTeamHidden");
			if (searchTeamHidden == null) {
				searchTeamHidden = "null";
			}
			if (selectValue == null) {
				selectValue = "null";
			}
			System.out.println("select의 option value 값 잡고 : " + selectValue + " = null이면 옵션 선택안한것");
			// select의 option value 값 잡고 출력

			// 어느 jsp의 search 인지 판단하기위해 만든 if문의 equals가 NullException 오류를 피하기 위해 만듬
			
			String search = request.getParameter("q");
			
//------------------------------------ctr의 searchBoard--------------------------------------//	
			
			if (selectValue.equals("boardSearch") || searchBoardHidden.equals("searchBoardHidden")) {
				System.out.println("ctr의 searchBoard 입장");
				// main에서 게시물 검색(boardSearch)을 선택했다면 if문 실행(searchBoard)
			
				
				BoardDAO dao = new BoardDAO();
				List<BoardVO> list = new ArrayList<BoardVO>();
				list = dao.listBoardAll(search, selectValue);
				if (list.size() == 0) {
					System.out.println("listBoardAll()실행 결과 ' .size = 0 '");
					System.out.println("listBoardAll() 에서 listBoards() 대체");
					list = dao.listBoards();
					
					String size_0 = "listBoards";
					request.setAttribute("size_0", size_0);
					// null 이면 조회 된게 없어 모든글 보여주기' 라는 뜻
					// list.size() == 0 이라서 dao.listBoards();로 바꿨다면 jsp 처리를 위한 Attribute	
				}
				// dao 메소드 listBoardAll() 로 input 입력 값, select의 option value 값 보내고
				// 변수로 받아서
				request.setAttribute("list", list);
				// setAttribute로 jsp로 보낼 준비
				RequestDispatcher dispatch = request.getRequestDispatcher("/search/searchBoard.jsp");
				dispatch.forward(request, response);
				// jsp로 전달
			
//-------------------------------------ctr의 searchUser--------------------------------------//			
		
			} else if (selectValue.equals("userSearch")  || searchUserHidden.equals("searchUserHidden")) {
				System.out.println("ctr의 searchUser 입장");
				
				UserDAO dao = new UserDAO();
				List<UserVO> list2 = new ArrayList<UserVO>();
				list2 = dao.listUsers(search);
				if (list2.size() == 0) {
					System.out.println("listUsers()실행 결과 ' .size = 0 '");
					System.out.println("listUsers() 에서 listAllUsers() 대체");
					list2 = dao.listAllUsers();
					
					String size_0 = "listUsers";
					request.setAttribute("size_0", size_0);
					// null 이면 조회 된게 없어 모든글 보여주기' 라는 뜻
					// list.size() == 0 이라서 dao.listUsers();로 바꿨다면 jsp 처리를 위한 Attribute	
				}
				
				request.setAttribute("list2", list2);

				RequestDispatcher dispatch = request.getRequestDispatcher("/search/searchUser.jsp");
				dispatch.forward(request, response);
	
//--------------------------------------ctr의 searchTeam--------------------------------------//	
			
			} else if (selectValue.equals("teamSearch") || searchTeamHidden.equals("searchTeamHidden")) {
				System.out.println("ctr의 searchTeam 입장");
				
				TeamDAO dao = new TeamDAO();
				List<TeamVO> list3 = new ArrayList<TeamVO>();
				list3 = dao.listTeams(search);
				if (list3.size() == 0) {
					System.out.println("listUsers()실행 결과 ' .size = 0 '");
					System.out.println("listUsers() 에서 listAllUsers() 대체");
					list3 = dao.listAllTeams();
					
					String size_0 = "listTeams";
					request.setAttribute("size_0", size_0);
					// null 이면 조회 된게 없어 모든글 보여주기' 라는 뜻
					// list.size() == 0 이라서 dao.listUsers();로 바꿨다면 jsp 처리를 위한 Attribute	
				}
				System.out.println("list3" + list3);
				request.setAttribute("list3", list3);
	
				RequestDispatcher dispatch = request.getRequestDispatcher("/search/searchTeam.jsp");
				dispatch.forward(request, response);

//---------------------------ctr의 각 search들의 if문 통과을 못했으면 여기-----------------------------//	
				
			} else {
				System.out.println("무슨 값을 받아서 각 search로 이동하는 if문 통과을 못했는지 밑에 값 확인");
				System.out.println("input hidden = searchBoardHidden value값 : "+ searchBoardHidden);
				System.out.println("input hidden = searchUserHidden value값 : "+ searchUserHidden);
				System.out.println("input hidden = searchTeamHidden value값 : "+ searchTeamHidden);
				System.out.println("input hidden = selectValue value값 : "+ selectValue + " = null이면 옵션 선택안한것");
			} 
		
		} // 최초 else문 
		
	} //doPost

} // class
