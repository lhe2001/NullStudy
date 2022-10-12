package board;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.eclipse.jdt.internal.compiler.ast.SingleMemberAnnotation;

import JoinDTO.MemberUserDTO;
import signupin.SignUpInDTO;

/* 
Controller에서 TODO
	1. request.getPathInfo() 로 action(url)값을 가져오고
		action의 값이 없거나(null) 글 목록을 보여줘야 한다면 ListArticles.jsp로 쏴줘야 하니
		action 의 값이 = ListArticles.do 일 것이다.
		그럼 DAO나 Service(나의 경우)에 있는 listArticles() <--글목록 조회 메소드를 호출하고
		바인딩 해서 dispatch로 ListArticles.jsp 포워딩 한다.
		그럼 ListArticles.jsp 에서 getAttribute를 하던지 해서 출력하면 된다.
		
	2. action 값이 글쓰기 창으로 가야 한다면 articleForm.jsp 로 쏴줘야 하니 
		action의 값이 articleForm.do 일 것이댜.
		그럼 DAO나 Service(나의 경우)에 있는 
		파일을 첨부 할 경우 먼저 file 이름을 map에 저장한다(upload())메소드 먼저 실행(파일업로드에서)
		upload() 메소드를 이용해 글쓰기 창의 정보를 map에 key:value로 저장하고
		글쓰기 창에서 글쓰기를 누르면 action값이 addArticle.do로 지정하고
		지정한 action값이 addArticle.do로 오면 맵에 정보를 담아서 listArticles.do로
		디스패치 방식으로 전달하면 된다.
		
	3. 글 상세보기 action = viewArticle.do
	
	4. 글 수정하기 action = updateArticle.do
		
 */
@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// 필드 영역 service,dto 초기화 시켜 놓기 귀찮으면 그냥 필요할때 인스턴스화 해서 쓰면 된다.
	BoardService service;
	BoardDTO dto;
	// 파일 경로를 변수로 지정해서 변수로 사용하자. 난 D드라이브에 image_file 폴더에 저장할꺼야
	private static String image = "C:\\image_file";
	// 세션 초기화
	HttpSession session;

	// 생성자 그냥 냅둘게요
	public BoardController() {
		super();
	}
	/*
	 * init 서블릿 최초 생성시 BoardService, BoardDTO 객체 생성 아니면 doGet에서 만들어서 써도 됨!!
	 */
	public void init(ServletConfig config) throws ServletException {
		service = new BoardService();
		dto = new BoardDTO();
		System.out.println("Board init 메소드 실행!!");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		BoardService service = new BoardService();
		
		// 포스트로 와도 실행되니 doGet에다가 적겠습니다.
		// 혹시 모르니 인코딩 부터 하겠습니다.
		Enumeration params = request.getParameterNames();
		while (params.hasMoreElements()) {
			String name = (String) params.nextElement();
			System.out.print(name + " : " + request.getParameter(name) + "     ");
		}
		// forward로 넘겨줄 좌표를 초기화해서 변수에 담아서 전달하자.
		String url = "";
		// getPathInfo()로 가져온 url값을 action에 담자.
		String action = request.getPathInfo();
		System.out.println("action = " + action);
		
		BoardDAO dao = new BoardDAO();
		session=request.getSession();
		SignUpInDTO userInfo = (SignUpInDTO) session.getAttribute("userInfo");

		try {
			// 빈 리스트 객체(BoardVO타입)를 하나 만들자.
			List<BoardDTO> articlesList = new ArrayList<BoardDTO>();
			// 게시판 메인(게시글 출력)
			if (action == null) {
				System.out.println("action = null");
				articlesList = service.listArticles();
				// 바인딩 하자!!
				request.setAttribute("articlesList", articlesList);
				request.setAttribute("userInfo", userInfo);
				// url 셋팅
				url = "/board1/listArticles.jsp";
				// 게시판 메인(게시글 출력)
			} else if (action.equals("/listArticles.do")) {
				System.out.println("게시판 메인 페이지 action = listArticles.do");
				articlesList = service.listArticles();
				for (int i = 0; i < articlesList.size(); i++) {
					BoardDTO dto = articlesList.get(i);
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
				request.setAttribute("userInfo", userInfo);
				request.setAttribute("articlesList", articlesList);
				url = "/board1/listArticles.jsp";
			} else if (action.equals("/articleForm.do")) {
				// 글쓰기 창으로 이동 action = articleForm.do
				System.out.println("글쓰기 창으로 이동 action = listArticles.do");
				url = "/board1/articleForm.jsp";
			} else if (action.equals("/addArticle.do")) {
				System.out.println("글쓰기 완료 action = listArticles.do");
				// 글쓰기 창에서 가져온 데이터(map에 담긴)를 셋팅해서 조회창으로 다시 쏴줘
				// 글쓰기 창에선 제목이랑 내용 파일명만 맵에 담았어
				// upload() 실행!!(파일 업로드 하려고)
				int b_articleNo = 0;
				request.setAttribute("userInfo", userInfo);
				BoardDTO dto = new BoardDTO();
				
				Map<String, String> map = upload(request, response);
				String b_title = map.get("b_title");
				String b_writer = map.get("b_writer");
				String b_content = map.get("b_content");
				String b_imageFile = map.get("b_imageFile");
				//int b_parentNo =Integer.parseInt(map.get("b_parentNo"));
				int b_field = Integer.parseInt(map.get("a_field"));
				// dto에 담아주자
				// 세션에서 가져온 userKey로 셋팅
				dto.setB_parentNo(0);
				dto.setUserkey(userInfo.getUserKey());
				dto.setB_field(b_field);
				dto.setB_writer(b_writer);
				dto.setB_title(b_title);
				dto.setB_content(b_content);
				dto.setB_imageFile(b_imageFile);
				// dao의 멤버추가 메소드에 담는다
				b_articleNo = service.addArticle(dto);

				// 파일을 첨부 했을 경우
				if (b_imageFile != null && b_imageFile.trim().length() != 0) {
					// temp폴더에 임시로 파일 저장
					File srcFile = new File(image + "\\" + "temp" + "\\" + b_imageFile);
					// 자바 폴더 생성
					File destDir = new File(image + "\\" + b_articleNo);
					destDir.mkdirs();

					FileUtils.moveFileToDirectory(srcFile, destDir, true);
				}
				request.setAttribute("userInfo", userInfo);
				// 새글 등록했을 때 alert 창 호출
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("  alert('새로운 글이 등록 되었습니다!!!'); ");
				out.print(" location.href =  '");
				out.print(request.getContextPath());
				out.print("/board/listArticles.do'");
				out.print("</script>");
				return;
			} else if (action.equals("/viewArticle.do")) {
				System.out.println("상세보기 페이지로 이동 action = viewArticle.do");
				// 상세 보기 실행
				int b_articleNo = Integer.parseInt(request.getParameter("b_articleNo").trim());
				dto = service.viewArticle(b_articleNo);
				// dto를 set해준다
				request.setAttribute("userInfo", userInfo);
				request.setAttribute("view", dto);
				url = "/board1/viewArticle.jsp";
			} else if (action.equals("/updateArticle.do")) {
				System.out.println("수정하기 실행 action = updateArticle.do");
				// 수정 하기 실행
				Map<String, String> map = upload(request, response);
				int b_articleNo = Integer.parseInt(map.get("b_articleNo").trim());
				System.out.println("b_articleNo = " + b_articleNo);
				String b_title = map.get("b_title");
				String b_content = map.get("b_content");
				String b_imageFile = map.get("b_imageFile");
				System.out.println("b_title" + b_title);
				System.out.println("b_content" + b_content);
				System.out.println("b_imageFile" + b_imageFile);
				// dto에 담아주자
				dto.setB_articleNo(b_articleNo);
				dto.setB_title(b_title);
				dto.setB_content(b_content);
				dto.setB_imageFile(b_imageFile);
				// 글을 수정하는 메소드 실행
				service.updateArticle(dto);

				// 파일을 첨부 했을 경우
				if (b_imageFile != null && b_imageFile.trim().length() != 0) {
					// viewArticle에서 히든값으로 원래 파일 이름을 넘겨받을 수 있는데 그걸 변수에 담아서 사용
					String oriFileName = map.get("oriFileName");

					// temp폴더에 임시로 파일 저장
					File srcFile = new File(image + "\\" + "temp" + "\\" + b_imageFile);
					// 자바 폴더 생성
					File destDir = new File(image + "\\" + b_articleNo);
					destDir.mkdirs();

					FileUtils.moveFileToDirectory(srcFile, destDir, true);

					// 기존 oriFile 삭제
					File oldFile = new File(image + "\\" + b_articleNo + "\\" + oriFileName);
					oldFile.delete();
				}
				// 새글 등록했을 때 alert 창 호출
				request.setAttribute("userInfo", userInfo);
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("  alert('글이 수정 되었습니다!!!'); ");
				out.print(" location.href =  '");
				out.print(request.getContextPath());
				out.print("/board/viewArticle.do?b_articleNo= ");
				out.print(b_articleNo + "';");
				out.print("</script>");
				return;

				// 삭제 요청이 들어 왔을때!
			} else if (action.equals("/deleteArticle.do")) {
				System.out.println("삭제하기 실행 action = deleteArticle.do");
				int b_articleNo = Integer.parseInt(request.getParameter("b_articleNo").trim());
				request.setAttribute("userInfo", userInfo);
				List<Integer> list = service.deleteArticle(b_articleNo);
				// 향상된 for문으로 리스트에 있는 글 번호에 따른 파일 폴더 삭제
				for (int articleNo : list) {
					File imgDir = new File(image + "\\" + b_articleNo);
					if (imgDir.exists()) {
						FileUtils.deleteDirectory(imgDir);
					}
				}
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.println(" alert('글을 삭제 하였습니다.'); ");
				out.print(" window.location.href ='");
				out.print(request.getContextPath());
				out.print("/board/listArticles.do'; ");
				out.print(" </script> ");
				return;

				// 답글 쓰기를 클릭했을때 답글을 작성할 수 있는 창으로 이동!!
			} else if (action.equals("/replyForm.do")) {
				System.out.println("답글쓰게 페이지로 이동 action = replyForm.do");
				// 부모 글 번호를 session에 저장하고 쏴준다.
				int b_articleNo = Integer.parseInt(request.getParameter("b_articleNo"));
				session = request.getSession();
				request.setAttribute("userInfo", userInfo);
				session.setAttribute("b_articleNo", b_articleNo);
				url = "/board1/replyForm.jsp";

				// 답글 작성 페이지에서 답글 적용 요청
			} else if (action.equals("/addReply.do")) {
				System.out.println("답글 작성 완료 action = addReply.do");
				session = request.getSession();
				request.setAttribute("userInfo", userInfo);
				int b_articleNo = (Integer) session.getAttribute("b_articleNo");
				session.removeAttribute("b_articleNo");
				Map<String, String> map = upload(request, response);
				String b_title = map.get("b_title");
				String b_content = map.get("b_content");
				String b_imageFile = map.get("b_imageFile");
				
				// dto에 담아주자
				dto.setUserkey(userInfo.getUserKey());
				dto.setB_parentNo(b_articleNo);
				dto.setB_title(b_title);
				dto.setB_content(b_content);
				dto.setB_imageFile(b_imageFile);
				// dao의 멤버추가 메소드에 담는다
				b_articleNo = service.addReply(dto);

				// 파일을 첨부 했을 경우
				if (b_imageFile != null && b_imageFile.trim().length() != 0) {
					// temp폴더에 임시로 파일 저장
					File srcFile = new File(image + "\\" + "temp" + "\\" + b_imageFile);
					// 자바 폴더 생성
					File destDir = new File(image + "\\" + b_articleNo);
					destDir.mkdirs();

					FileUtils.moveFileToDirectory(srcFile, destDir, true);
				}
				// 새글 등록했을 때 alert 창 호출
				PrintWriter out = response.getWriter();
				out.print("<script>");
				out.print("  alert('답글이 등록 되었습니다!!!'); ");
				out.print(" location.href =  '");
				out.print(request.getContextPath());
				out.print("/board/viewArticle.do?b_articleNo=");
				out.print(b_articleNo);
				out.print("';");
				out.print("</script>");
				return;

				// 셀렉트 박스 서치 기능
			} else if (action.equals("/searchArticle.do")) {
				System.out.println("서치 기능 작동 action = searchArticle.do");
				String search_bar = request.getParameter("search_bar");
				BoardDTO dto = new BoardDTO();
				String field = request.getParameter("field");
				session = request.getSession();
				request.setAttribute("userInfo", userInfo);
				int setSearch_field = Integer.parseInt(field);
				// setSearch_field(1=제목 , 2=내용, 3= 글작성자, 4=전체)
				
				if (setSearch_field == 1 || setSearch_field == 2 || setSearch_field == 3 || setSearch_field == 4) {
					dto.setSearch_field(setSearch_field);
					dto.setSearch_bar(search_bar);
					List<BoardDTO> searchList = service.allSearch(dto);
					request.getParameter("field");
					request.setAttribute("searchList", searchList);
//						url = "/board1/searchForm.jsp";
					request.setAttribute("articlesList", searchList);
					// url 셋팅
					url = "/board1/listArticles.jsp";
				}
				// 글머리 선택
			} else if (action.equals("/selectField.do")) {
				System.out.println("글머리 선택 action = selectField.do");
				int list_sel = Integer.parseInt(request.getParameter("list_sel"));
				BoardDTO dto = new BoardDTO();
				session = request.getSession();
				request.setAttribute("userInfo", userInfo);
				dto.setList_sel(list_sel);
				List<BoardDTO> allList = service.selectViewArticle(dto);
				request.setAttribute("articlesList", allList);
				for (int i = 0; i < allList.size(); i++) {
					dto = allList.get(i);
					// (0 일경우 전체 글 출력, 10 = 질문, 20 = 잡담, 30 = 비밀글, 40 = 나도몰라)
					switch (dto.getB_field()) {
					case 0:
						System.out.println("articlesList------->" + articlesList);
						articlesList = service.listArticles();
						for (int j = 0; j < articlesList.size(); j++) {
							dto = articlesList.get(j);
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
						request.setAttribute("articlesList", articlesList);
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
				// url 셋팅
				url = "/board1/listArticles.jsp";
			} else {
				url = "/board1/urlempty.jsp";
			}
			// dispatch로 쏴버려
			RequestDispatcher dispatch = request.getRequestDispatcher(url);
			dispatch.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// 먼저 파일 업로드시 맵에 파일명이 담기는 메소드를 만든다.(그냥 fileUpload파일 배껴온다)
	// 수업 시간에 파일쪽 먼저 실행해야 한다고 한거 같음..
	// 여긴 개인적으로 공부 해야 될 것 같습니다만.....
	private Map<String, String> upload(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 맵 객체 초기화ㅑ
		Map<String, String> map = new HashMap<String, String>();
		request.setCharacterEncoding("utf-8");
		String encoding = "utf-8";
		File currentDirPath = new File(image); // 경로를 image로 변수에 담아서 사용한다

		// 세팅
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(currentDirPath);
		factory.setSizeThreshold(192 * 192);

		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			// request로 받은 form의 내용을 드디어 사용 단계
			List items = upload.parseRequest(request);
			for (int i = 0; i < items.size(); i++) {
				FileItem fileItem = (FileItem) items.get(i);
				
				if (fileItem.isFormField()) {
					// isFormField는 file이 아니고 input등 form 요소인지 확인
					System.out.println(fileItem.getFieldName() + "=" + fileItem.getString(encoding));
					// ex) request.getParameter("param1") --> uploadForm.jsp 에서 매개변수값(인풋파라미터)
					// articleForm.jsp에서 인풋값이 제목 내용 파일이면 맵에 담으면 되는거??
					// 그럼 글쓰기 창에서 인풋에 입력한 값이 syso에 있는 값이네???
					// 그럼 그값을 map에 담으면 되겠네?? fileItem.getFieldName() ---> input name??
					// fileItem.getString(encoding) --> input value??
					// 그럼 name 값으로 value를 가져오면 되겄지??
					map.put(fileItem.getFieldName(), fileItem.getString(encoding));

				} else {
					// 여긴 file 영역
					System.out.println("param:" + fileItem.getFieldName());
					System.out.println("file name:" + fileItem.getName());
					System.out.println("file size:" + fileItem.getSize() + "bytes");
					// 업로드한 파일이 있는 경우
					if (fileItem.getSize() > 0) {
						// 파일명 확보 아이디어
						int idx = fileItem.getName().lastIndexOf("\\");
						if (idx == -1) {
							idx = fileItem.getName().lastIndexOf("/");
						}
						// 파일명 확보
						String fileName = fileItem.getName().substring(idx + 1);
						// 파일명 중복 회피 아이디어
						long timestamp = System.currentTimeMillis();
						fileName = timestamp + "_" + fileName;
						// 파일명도 map에 담아야합니다...
						map.put(fileItem.getFieldName(), fileName);

						// 파일 저장
						File uploadFile = new File(currentDirPath + "\\temp\\" + fileName);
						fileItem.write(uploadFile);
					} // end if
				} // end if
			} // end for
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}