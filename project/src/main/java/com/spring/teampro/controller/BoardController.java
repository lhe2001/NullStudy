package com.spring.teampro.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.teampro.board.dao.BoardDAO;
import com.spring.teampro.board.dto.BoardDTO;
import com.spring.teampro.board.dto.CommentDTO;
import com.spring.teampro.board.dto.PageDTO;
import com.spring.teampro.board.service.BoardService;
import com.spring.teampro.signupin.dto.SignUpInDTO;

import freemarker.core.CollectionAndSequence;

@Controller("boardController")
public class BoardController {
	@Autowired
	private BoardService boardService;

	@Autowired
	private BoardDAO boardDAO;

	@Autowired
	HttpSession session;

	private static String image = "C:\\image_file";

	// 전체 리스트 조회
	@RequestMapping(value = "/board/listArticles.do", method = RequestMethod.GET)
	public String listArticles(HttpServletRequest request, Model model, @ModelAttribute BoardDTO boardDTO) {
		HttpSession session = request.getSession();
		SignUpInDTO adminCheck = (SignUpInDTO) session.getAttribute("userInfo");
		String userId = adminCheck.getId();
		if ("admin".equals(userId)) {
			System.out.println("전체 리스트 조회");
			// 페이징

			// 페이징 초기값
			// 첫 페이지 값 1 , 보여줄 페이지수 10 으로 초기화
			int pageNum = 1;
			int amount = 10;

			// 페이지번호를 클릭하는 경우
			if (request.getParameter("pageNum") != null && request.getParameter("amount") != null) {
				pageNum = Integer.parseInt(request.getParameter("pageNum"));
				amount = Integer.parseInt(request.getParameter("amount"));
			}

			// 댓글 갯수 가져오기
			List<CommentDTO> list = new ArrayList<CommentDTO>();
			list = boardService.getComment();
			CommentDTO commentDTO = new CommentDTO();
			int totalCount = boardService.getPage();
			PageDTO pageDTO = new PageDTO(pageNum, amount, totalCount);

			// 공지글 조회 메소드
			List<CommentDTO> noticeList = boardService.getNoticeList();
			System.out.println("noticeList === " + noticeList);

			// 전체 리스트 가져오기
			List<BoardDTO> articlesList = boardService.getListArticles(pageNum, amount);

			session = request.getSession();
			SignUpInDTO userInfo = (SignUpInDTO) session.getAttribute("userInfo");

			for (int j = 0; j < list.size(); j++) {
				commentDTO = list.get(j);
				for (int i = 0; i < articlesList.size(); i++) {
					boardDTO = articlesList.get(i);
					if (boardDTO.getB_key() == commentDTO.getB_key()) {
						boardDTO.setComment_cnt(commentDTO.getCount_com());
					}
					switch (boardDTO.getB_field()) {
					case 10:
						boardDTO.setB_fieldName("질문");
						break;
					case 20:
						boardDTO.setB_fieldName("잡담");
						break;
					case 30:
						boardDTO.setB_fieldName("비밀글");
						break;
					case 40:
						boardDTO.setB_fieldName("유우머");
						break;
					case 50:
						boardDTO.setB_fieldName("공지");
						break;
					default:
						break;
					}
				}
			}
			model.addAttribute("articlesList", articlesList);
			model.addAttribute("noticeList", noticeList);
			model.addAttribute("userInfo", userInfo);
			model.addAttribute("pageDTO", pageDTO);
			return "listArticles(admin)";

		} else {
			System.out.println("전체 리스트 조회");
			// 페이징

			// 페이징 초기값
			// 1. 화면전환 시에 조회하는 페이지번호 and 화면에 그려질 데이터개수 2개를 전달받음
			// 첫 페이지 경우
			int pageNum = 1;
			int amount = 10;

			// 페이지번호를 클릭하는 경우
			if (request.getParameter("pageNum") != null && request.getParameter("amount") != null) {
				pageNum = Integer.parseInt(request.getParameter("pageNum"));
				amount = Integer.parseInt(request.getParameter("amount"));
			}

			// 댓글 갯수 가져오기
			List<CommentDTO> list = new ArrayList<CommentDTO>();
			list = boardService.getComment();
			CommentDTO commentDTO = new CommentDTO();
			int totalCount = boardService.getPage();
			PageDTO pageDTO = new PageDTO(pageNum, amount, totalCount);
			// 공지글 조회 메소드
			List<CommentDTO> noticeList = boardService.getNoticeList();

			// 전체 리스트 가져오기
			List<BoardDTO> articlesList = boardService.getListArticles(pageNum, amount);
			session = request.getSession();
			SignUpInDTO userInfo = (SignUpInDTO) session.getAttribute("userInfo");

			for (int j = 0; j < list.size(); j++) {
				commentDTO = list.get(j);
				for (int i = 0; i < articlesList.size(); i++) {
					boardDTO = articlesList.get(i);
					if (boardDTO.getB_key() == commentDTO.getB_key()) {
						boardDTO.setComment_cnt(commentDTO.getCount_com());
					}
					switch (boardDTO.getB_field()) {
					case 10:
						boardDTO.setB_fieldName("질문");
						break;
					case 20:
						boardDTO.setB_fieldName("잡담");
						break;
					case 30:
						boardDTO.setB_fieldName("비밀글");
						break;
					case 40:
						boardDTO.setB_fieldName("유우머");
						break;
					case 50:
						boardDTO.setB_fieldName("공지");
						break;
					default:
						break;
					}
				}
			}
			model.addAttribute("noticeList", noticeList);
			model.addAttribute("articlesList", articlesList);
			model.addAttribute("userInfo", userInfo);
			model.addAttribute("pageDTO", pageDTO);
			return "listArticles";
		}
	}

	// 글쓰기 창으로 이동
	@RequestMapping(value = "/board/articleForm.do", method = RequestMethod.GET)
	public String goArticleForm(HttpServletRequest request, HttpServletResponse response, Model model) {

		HttpSession session = request.getSession();
		SignUpInDTO userInfo = (SignUpInDTO) session.getAttribute("userInfo");

		model.addAttribute("userInfo", userInfo);

		System.out.println("글쓰기 창으로 이동");

		return "articleForm";
	}

	// 글쓰기
	@RequestMapping(value = "/board/addArticle.do", method = RequestMethod.POST)
	public void addArticle(MultipartHttpServletRequest multipartRequest, HttpServletResponse response, Model model)
			throws Exception {
		// 스크립트 한글설정
		response.setContentType("text/html; charset=utf-8");
		// 파라미터값을 map형태로 저장
		Map<String, Object> map = new HashMap<String, Object>();
		Enumeration params = multipartRequest.getParameterNames();
		while (params.hasMoreElements()) {
			String name = (String) params.nextElement();
			String value = multipartRequest.getParameter(name);
			map.put(name, value);
			System.out.print(name + " : " + multipartRequest.getParameter(name) + "     ");
			// 비밀글일 경우
			if (multipartRequest.getParameter("b_field").equals("30")) {
				int b_articlePwd = Integer.parseInt(multipartRequest.getParameter("b_articlePwd"));
				if (b_articlePwd != -1) {
					map.put("b_articlePwd", b_articlePwd);
				}
			}
		}
		// 이미지 파일 이름 저장과 동시에 가져오기
		String b_imageFile = upload(multipartRequest);
		session = multipartRequest.getSession();
		SignUpInDTO userInfo = (SignUpInDTO) session.getAttribute("userInfo");
		map.put("b_parentNo", 0);
		map.put("b_imageFile", b_imageFile);
		map.put("userKey", userInfo.getUserKey());

		try {
			int b_articleNo = boardService.getAddArticle(map);
			// 파일을 첨부 했을 경우
			if (b_imageFile != null && b_imageFile.trim().length() != 0) {
				// temp폴더에 임시로 파일 저장
				File srcFile = new File(image + "\\" + "temp" + "\\" + b_imageFile);
				// 자바 폴더 생성
				File destDir = new File(image + "\\" + b_articleNo);
				System.out.println("destDir ==> " + destDir);
				destDir.mkdirs();

				FileUtils.moveFileToDirectory(srcFile, destDir, true);
			}
			model.addAttribute("userInfo", userInfo);

			// 새글 등록했을 때 alert 창 호출
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("  alert('새로운 글이 등록 되었습니다!!!'); ");
			out.print(" location.href =  '");
			out.print(multipartRequest.getContextPath());
			out.print("/board/listArticles.do'");
			out.print("</script>");

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("글쓰기 완료");
	}

	// 게시글 상세보기
	@RequestMapping(value = "/board/viewArticle.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String viewArticle(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("b_articleNo") int b_articleNo, Model model) {

		HttpSession session = request.getSession();
		SignUpInDTO userInfo = (SignUpInDTO) session.getAttribute("userInfo");
		String userId = userInfo.getId();
		if ("admin".equals(userId)) {

			model.addAttribute("userInfo", userInfo);
			BoardDTO dto = new BoardDTO();
			// 댓글 리스트를 여기서 보내준다..
			List<CommentDTO> list = boardService.getCommentList();

			// 조회수
			dto = boardService.getViewArticle(b_articleNo);
			int view = (dto.getB_view() + 1);
			dto.setB_articleNo(b_articleNo);
			dto.setB_view(view);
			// 조회수 업데이트
			boardService.getView(dto);
			model.addAttribute("view", dto);
			model.addAttribute("comment", list);
			return "viewArticle(admin)";
		} else {

			model.addAttribute("userInfo", userInfo);
			BoardDTO dto = new BoardDTO();
			// 댓글 리스트를 여기서 보내준다..
			List<CommentDTO> list = boardService.getCommentList();

			// 조회수
			dto = boardService.getViewArticle(b_articleNo);
			int view = (dto.getB_view() + 1);
			dto.setB_articleNo(b_articleNo);
			dto.setB_view(view);
			// 조회수 업데이트
			boardService.getView(dto);
			model.addAttribute("view", dto);
			model.addAttribute("comment", list);
			return "viewArticle";
		}
	}

	// 게시글 수정하기
	@RequestMapping(value = "/board/updateArticle.do", method = RequestMethod.POST)
	public void updateArticle(MultipartHttpServletRequest multipartRequest, HttpServletResponse response, Model model)
			throws Exception {
		System.out.println("게시글 수정하기");
		// 스크립트 한글설정
		response.setContentType("text/html; charset=utf-8");
		// 파라미터값 출력
		Map<String, Object> map = new HashMap<String, Object>();
		Enumeration params = multipartRequest.getParameterNames();
		while (params.hasMoreElements()) {
			String name = (String) params.nextElement();
			String value = multipartRequest.getParameter(name);
			map.put(name, value);
			System.out.print(name + " : " + multipartRequest.getParameter(name) + "     ");
			// 비밀글일 경우
			if (multipartRequest.getParameter("b_field").equals("30")) {
				int b_articlePwd = Integer.parseInt(multipartRequest.getParameter("b_articlePwd"));
				if (b_articlePwd != -1) {
					map.put("b_articlePwd", b_articlePwd);
				}
			}
		}
		// 이미지 파일 이름 가져오기
		String b_imageFile = upload(multipartRequest);
		session = multipartRequest.getSession();
		SignUpInDTO userInfo = (SignUpInDTO) session.getAttribute("userInfo");
		map.put("b_parentNo", 0);
		map.put("b_imageFile", b_imageFile);
		map.put("userKey", userInfo.getUserKey());

		String b_articleNo = (String) map.get("b_articleNo");

		try {
			boardService.getUpdateArticle(map);
			// 파일을 첨부 했을 경우
			if (b_imageFile != null && b_imageFile.trim().length() != 0) {
				// temp폴더에 임시로 파일 저장
				File srcFile = new File(image + "\\" + "temp" + "\\" + b_imageFile);
				// 자바 폴더 생성
				File destDir = new File(image + "\\" + b_articleNo);
				destDir.mkdirs();

				FileUtils.moveFileToDirectory(srcFile, destDir, true);

				String originalFileName = (String) map.get("originalFileName");
				File oldFile = new File(image + "\\" + b_articleNo + "\\" + originalFileName);
				oldFile.delete();
			}
			model.addAttribute("userInfo", userInfo);
			// 새글 등록했을 때 alert 창 호출
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("  alert('글이 수정 되었습니다!!!'); ");
			out.print(" location.href =  '");
			out.print(multipartRequest.getContextPath());
			out.print("/board/listArticles.do'");
			out.print("</script>");

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("글 수정 완료");
	}

	// 게시글 삭제하기
	@RequestMapping(value = "/board/deleteArticle.do", method = RequestMethod.GET)
	public void deleteArticle(HttpServletRequest request, HttpServletResponse response, Model model) {
		System.out.println("게시글 삭제하기");
		session = request.getSession();
		SignUpInDTO userInfo = (SignUpInDTO) session.getAttribute("userInfo");
		// 스크립트 한글설정
		response.setContentType("text/html; charset=utf-8");
		int b_articleNo = Integer.parseInt(request.getParameter("b_articleNo"));
		model.addAttribute("userInfo", userInfo);
		try {
			boardService.getDeleteArticle(b_articleNo);
			// 자바 폴더 생성
			File destDir = new File(image + "\\" + b_articleNo);
			FileUtils.deleteDirectory(destDir);

			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.println(" alert('글을 삭제 하였습니다.'); ");
			out.print(" window.location.href ='");
			out.print(request.getContextPath());
			out.print("/board/listArticles.do'; ");
			out.print(" </script> ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 답글 쓰기 창으로 이동
	@RequestMapping(value = "/board/replyForm.do", method = RequestMethod.GET)
	public String replyForm(HttpServletRequest request, Model model) {

		HttpSession session = request.getSession();
		SignUpInDTO adminCheck = (SignUpInDTO) session.getAttribute("userInfo");
		String userId = adminCheck.getId();
		if ("admin".equals(userId)) {
			System.out.println("답글 쓰기 창으로 이동");
			int b_articleNo = Integer.parseInt(request.getParameter("b_articleNo"));
			int b_field = Integer.parseInt(request.getParameter("b_field"));
			model.addAttribute("b_articleNo", b_articleNo);
			model.addAttribute("b_field", b_field);
			SignUpInDTO userInfo = (SignUpInDTO) session.getAttribute("userInfo");
			model.addAttribute("userInfo", userInfo);
			return "replyForm(admin)";
		} else {

			System.out.println("답글 쓰기 창으로 이동");
			int b_articleNo = Integer.parseInt(request.getParameter("b_articleNo"));
			int b_field = Integer.parseInt(request.getParameter("b_field"));
			model.addAttribute("b_articleNo", b_articleNo);
			model.addAttribute("b_field", b_field);
			SignUpInDTO userInfo = (SignUpInDTO) session.getAttribute("userInfo");
			model.addAttribute("userInfo", userInfo);
			return "replyForm";
		}
	}

	// 답글 쓰기 완료
	@RequestMapping(value = "/board/addReply.do", method = RequestMethod.POST)
	public void addReply(MultipartHttpServletRequest multipartRequest, HttpServletResponse response, Model model)
			throws Exception {
		System.out.println("답글 쓰기 완료");
		// 스크립트 한글설정
		response.setContentType("text/html; charset=utf-8");
		// 파라미터값 출력
		Map<String, Object> map = new HashMap<String, Object>();
		Enumeration params = multipartRequest.getParameterNames();
		while (params.hasMoreElements()) {
			String name = (String) params.nextElement();
			String value = multipartRequest.getParameter(name);
			map.put(name, value);
		}
		// 비밀글일 경우
		if (multipartRequest.getParameter("b_field").equals("30")) {
			int b_articlePwd = Integer.parseInt(multipartRequest.getParameter("b_articlePwd"));
			if (b_articlePwd != -1) {
				map.put("b_articlePwd", b_articlePwd);
			}
		}
		int b_field = Integer.parseInt(multipartRequest.getParameter("b_field"));
		System.out.println("addReply의 b_field  = " + b_field);
		String b_imageFile = upload(multipartRequest);
		System.out.println("b_imageFile = " + b_imageFile);
		int b_articleNo = Integer.parseInt(multipartRequest.getParameter("b_parentNo"));
		System.out.println("addReply 의 b_articleNo " + b_articleNo);
		session = multipartRequest.getSession();
		SignUpInDTO userInfo = (SignUpInDTO) session.getAttribute("userInfo");
//			model.addAttribute("userKey", userInfo.getUserKey());
		map.put("b_parentNo", b_articleNo);
		map.put("b_imageFile", b_imageFile);
		map.put("userKey", userInfo.getUserKey());

		try {
			b_articleNo = boardService.getAddReply(map);
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
			out.print(multipartRequest.getContextPath());
			out.print("/board/viewArticle.do?b_articleNo=");
			out.print(b_articleNo);
			out.print("';");
			out.print("</script>");

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("답글쓰기 완료");
	}

	// Left(field) select ajax
	@RequestMapping(value = "/board/selectArticle.do", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map selectArticle(HttpServletRequest request, HttpServletResponse response,
			@RequestBody BoardDTO boardDTO, Model model) {
		System.out.println("셀렉스 박스 아작스 기능 작동");
		response.setContentType("text/html; charset=utf-8");
		// 페이징 초기값
		// 1. 화면전환 시에 조회하는 페이지번호 and 화면에 그려질 데이터개수 2개를 전달받음
		// 첫 페이지 경우
		int pageNum = 1;
		int amount = 10;

		// 페이지번호를 클릭하는 경우
		if (boardDTO.getPageNum() != 0 && boardDTO.getAmount() != 0) {
			pageNum = boardDTO.getPageNum();
			amount = boardDTO.getAmount();
		}

		int totalCount2 = boardService.getPage();
		System.out.println("totalCount2 ====> " + totalCount2);

		session = request.getSession();
		SignUpInDTO userInfo = (SignUpInDTO) session.getAttribute("userInfo");
		model.addAttribute("userInfo", userInfo);
		// 0 : 전체 , 10 : 질문 , 20: 잡담, 30: 비밀글, 40: 유우머
		int field2 = boardDTO.getB_field2();
		System.out.println("field2 ---> " + field2);
		List<BoardDTO> searchList = null;
		PageDTO pdto = null;
		System.out.println("pageNum ---> " + pageNum);
		System.out.println("amount ---> " + amount);
		// field 값 1 : 제목, 2: 내용, 3:글 작성자, 4: 전체 근데 굳이 if 안걸고 셋팅해도 노상관
		boardDTO.setB_field2(field2);
//					boardDTO.setPageNum(pageNum);
//					boardDTO.setAmount(amount);
		boardDTO.setTotalCount(totalCount2);
		int totalCount = boardService.getSelectCount(boardDTO);
		System.out.println("셀렉트 박스 아작스 totalCount --->" + totalCount);
		pdto = new PageDTO(pageNum, amount, totalCount);
		searchList = boardService.getAllSearch(boardDTO, pageNum, amount);

		for (int i = 0; i < searchList.size(); i++) {
			boardDTO = searchList.get(i);
			switch (boardDTO.getB_field()) {
			case 10:
				boardDTO.setB_fieldName("질문");
				break;
			case 20:
				boardDTO.setB_fieldName("잡담");
				break;
			case 30:
				boardDTO.setB_fieldName("비밀글");
				break;
			case 40:
				boardDTO.setB_fieldName("유우머");
				break;
			case 50:
				boardDTO.setB_fieldName("공지");
				break;
			default:
				break;
			}
		}

		Map map = new HashMap();
		map.put("searchList", searchList);
		map.put("pageDTO", pdto);
		System.out.println("셀렉트 박스 서치 완료");
		return map;
	}

	// Search Ajax
	@RequestMapping(value = "/board/searchArticle.do", method = { RequestMethod.POST, RequestMethod.GET })
	public @ResponseBody Map searchArticle(HttpServletRequest request, HttpServletResponse response,
			@RequestBody BoardDTO boardDTO, Model model) {
		System.out.println("셀렉트 박스 서치 아작스 기능 작동");

		response.setContentType("text/html; charset=utf-8");
		// 페이징
		int pageNum = 1;
		int amount = 10;

		// 페이지번호를 클릭하는 경우
		if (boardDTO.getPageNum() != 0 && boardDTO.getAmount() != 0) {
			pageNum = boardDTO.getPageNum();
		}
		amount = boardDTO.getAmount();
		int totalCount2 = boardService.getPage();
		session = request.getSession();
		SignUpInDTO userInfo = (SignUpInDTO) session.getAttribute("userInfo");
		model.addAttribute("userInfo", userInfo);
		// 0 : 전체 , 10 : 질문 , 20: 잡담, 30: 비밀글, 40:나도몰라
		int field2 = boardDTO.getB_field2();
		// field 값 1 : 제목, 2: 내용, 3:글 작성자, 4: 전체 근데 굳이 if 안걸고 셋팅해도 노상관
		int field = boardDTO.getB_field();
		String search_bar = boardDTO.getSearch_bar();
		List<BoardDTO> searchList = null;
		PageDTO pdto = null;
		System.out.println("pageNum ---> " + pageNum);
		System.out.println("amount ---> " + amount);
		System.out.println("totalCount2 ---> " + totalCount2);
		boardDTO.setSearch_bar(search_bar);
		boardDTO.setSearch_field(field);
		boardDTO.setB_field2(field2);
		boardDTO.setTotalCount(totalCount2);
		int totalCount = boardService.getSelectCount(boardDTO);
		System.out.println("셀렉트 박스 아작스 totalCount --->" + totalCount);
		pdto = new PageDTO(pageNum, amount, totalCount);
		searchList = boardService.getAllSearch(boardDTO, pageNum, amount);
		for (int i = 0; i < searchList.size(); i++) {
			boardDTO = searchList.get(i);
			switch (boardDTO.getB_field()) {
			case 10:
				boardDTO.setB_fieldName("질문");
				break;
			case 20:
				boardDTO.setB_fieldName("잡담");
				break;
			case 30:
				boardDTO.setB_fieldName("비밀글");
				break;
			case 40:
				boardDTO.setB_fieldName("유우머");
				break;
			case 50:
				boardDTO.setB_fieldName("공지");
				break;
			default:
				break;
			}
		}

		Map map = new HashMap();
		map.put("searchList", searchList);
		map.put("pageDTO", pdto);
		return map;
	}

	// Right(amount) Select Ajax
	@RequestMapping(value = "/board/selectAmount.do", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody Map selectAmount(HttpServletRequest request, HttpServletResponse response,
			@RequestBody BoardDTO boardDTO, Model model) {
		System.out.println("셀렉트 박스 Amount Ajax");
		response.setContentType("text/html; charset=utf-8");
		// 페이징
		int pageNum = 1;
		int amount = 10;

		// 페이지번호를 클릭하는 경우
		if (boardDTO.getPageNum() != 0 && boardDTO.getAmount() != 0) {
			pageNum = boardDTO.getPageNum();
		}
		amount = boardDTO.getAmount();

		int totalCount = boardService.getPage();
		System.out.println("amount : totalcount == " + totalCount);
		session = request.getSession();
		SignUpInDTO userInfo = (SignUpInDTO) session.getAttribute("userInfo");
		model.addAttribute("userInfo", userInfo);
		// 0 : 전체 , 10 : 질문 , 20: 잡담, 30: 비밀글, 40:나도몰라
		int field2 = boardDTO.getB_field2();
		// field 값 1 : 제목, 2: 내용, 3:글 작성자, 4: 전체 근데 굳이 if 안걸고 셋팅해도 노상관
		int field = boardDTO.getB_field();
		String search_bar = boardDTO.getSearch_bar();
		List<BoardDTO> searchList = null;
		PageDTO pdto = null;
		System.out.println("pageNum ---> " + pageNum);
		System.out.println("amount ---> " + amount);
		System.out.println("totalCount ---> " + totalCount);
		pdto = new PageDTO(pageNum, amount, totalCount);
		boardDTO.setSearch_bar(search_bar);
		boardDTO.setSearch_field(field);
		boardDTO.setB_field2(field2);
		searchList = boardService.getAllSearch(boardDTO, pageNum, amount);
		System.out.println("amount searchList.size() == " + searchList.size());
		for (int i = 0; i < searchList.size(); i++) {
			boardDTO = searchList.get(i);
			switch (boardDTO.getB_field()) {
			case 10:
				boardDTO.setB_fieldName("질문");
				break;
			case 20:
				boardDTO.setB_fieldName("잡담");
				break;
			case 30:
				boardDTO.setB_fieldName("비밀글");
				break;
			case 40:
				boardDTO.setB_fieldName("유우머");
				break;
			case 50:
				boardDTO.setB_fieldName("공지");
				break;
			default:
				break;
			}
		}

		Map map = new HashMap();
		map.put("searchList", searchList);
		map.put("pageDTO", pdto);
		return map;
	}

	// 말머리에서 가져오는 리스트 목록
	@RequestMapping(value = "/board/selectField.do", method = RequestMethod.POST)
	public String selectField(HttpServletRequest request, Model model, @RequestParam("list_sel") int list_sel) {
		System.out.println("말머리 선택 완료");
		session = request.getSession();
		SignUpInDTO userInfo = (SignUpInDTO) session.getAttribute("userInfo");
		BoardDTO dto = new BoardDTO();
		dto.setList_sel(list_sel);
		dto.setB_field(list_sel);
		List<BoardDTO> list = boardService.getSelectViewArticle(dto);
		// (0 일경우 전체 글 출력, 10 = 질문, 20 = 잡담, 30 = 비밀글, 40 = 유우머, 50 = 공지)
		for (int j = 0; j < list.size(); j++) {
			dto = list.get(j);
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
				dto.setB_fieldName("유우머");
				break;
			case 50:
				dto.setB_fieldName("공지");
				break;
			default:
				break;
			}
			model.addAttribute("articlesList", list);
			model.addAttribute("userInfo", userInfo);
		}
		return "listArticles";
	}

	// 비밀글 클릭->비밀번호 조회하러 가기
	@RequestMapping(value = "/board/password.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String password(HttpServletRequest request, @RequestParam("b_articleNo") int b_articleNo, Model model) {
		System.out.println("비밀글 조회하러 가기");
		session = request.getSession();
		SignUpInDTO userInfo = (SignUpInDTO) session.getAttribute("userInfo");
		BoardDTO dto = new BoardDTO();
		dto.setB_articleNo(b_articleNo);
		dto = boardService.getSecretView(b_articleNo);
		model.addAttribute("view", dto);
		model.addAttribute("userInfo", userInfo);
		return "password";
	}

	// 비밀글 비밀번호 입력해서 조회
	@RequestMapping(value = "/board/checkPw.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String getPw(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("b_articleNo") int b_articleNo, @RequestParam("pw") int b_articlePwd, Model model)
			throws IOException {
		// 스크립트 한글설정
		int pw = boardService.getPwd(b_articleNo);
		System.out.println("pw = " + pw);
		System.out.println("b_articlePwd ====== " + b_articlePwd);
		if (b_articlePwd == pw) {
			System.out.println("비밀글 조회 성공");
			model.addAttribute("b_articleNo", b_articleNo);
			return "forward:/board/viewArticle.do";
		} else {
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('비밀번호가 틀립니다!!');");
			out.print("</script>");
		}
		return "redirect:/board/listArticles.do";

	}

	// 파일 저장 이름 가져오는 메소드
	private String upload(MultipartHttpServletRequest multipartRequest) throws Exception {

		String b_imageFile = null;

		Iterator<String> fileNames = multipartRequest.getFileNames();

		while (fileNames.hasNext()) {
			String fileName = fileNames.next();
			MultipartFile mFile = multipartRequest.getFile(fileName);
			b_imageFile = mFile.getOriginalFilename();
			File file = new File(image + "\\temp\\" + fileName);
			System.out.println("file ===> " + file);
			if (mFile.getSize() != 0) {
				if (!file.exists()) {
					file.getParentFile().mkdirs();
					mFile.transferTo(new File(image + "\\temp\\" + b_imageFile));
					System.out.println("mFile ====> " + mFile);
				}
			}
		}
		return b_imageFile;
	}
}