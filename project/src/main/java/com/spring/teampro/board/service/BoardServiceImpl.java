package com.spring.teampro.board.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.teampro.board.dao.BoardDAO;
import com.spring.teampro.board.dao.CommentDAO;
import com.spring.teampro.board.dto.BoardDTO;
import com.spring.teampro.board.dto.CommentDTO;
import com.spring.teampro.board.dto.PageDTO;

@Service("boardService")
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardDAO boardDAO;
	
	@Autowired
	private CommentDAO commentDAO;
	
	// 게시글 전체를 조회할 수 있는 메소드 호출
	@Override
	public List<BoardDTO> getListArticles(int pageNum, int amount) {
		// dao클래스에 selectAllArticles() 메소드를 게시글 전체를 조회할 수 있게 짜면 된다.
		List<BoardDTO> list = boardDAO.selectAllArticles(pageNum, amount);
		// list를 리턴해주면 끝!
		return list;
	}

	// 게시글을 추가 할 수 있는 메소드 호출
	@Override
	public int getAddArticle(Map map) throws Exception {
		return boardDAO.addNewArticle(map);
	}

	// 게시글을 상세보기로 볼 수 있는 메소드 호출
	@Override
	public BoardDTO getViewArticle(int b_articleNo) {
		BoardDTO dto = new BoardDTO();
		dto = boardDAO.selectViewArticle(b_articleNo);
		return dto;
	}

	// 게시글 수정 메소드
	@Override
	public void getUpdateArticle(Map map) {
		boardDAO.modifyArticle(map);
	}

	// 게시글 삭제 메소드
	@Override
	public List<Integer> getDeleteArticle(int b_articleNo) {
		List<Integer> list = boardDAO.selectRemovedArticle(b_articleNo);
		boardDAO.deleteArticle(b_articleNo);
		return list;
	}

	// 게시글 답글 추가 메소드
	@Override
	public int getAddReply(Map map) {
		return boardDAO.addNewArticle(map);
	}


	// 게시글 전체로 검색하는 메소드
//	@Override
//	public List<BoardDTO> getAllSearch(BoardDTO dto) {
//		List<BoardDTO> list = boardDAO.searchAllArticle(dto);
//		return list;
//	}
	
	// 게시글 전체로 검색하는 메소드(ajax)
		@Override
		public List<BoardDTO> getAllSearch(BoardDTO dto,int pageNum, int amount) {
			System.out.println("service dtofield : " + dto.getB_field());
			System.out.println("service search_bar : " + dto.getSearch_bar());
			System.out.println("service b_field2 : " + dto.getB_field2());
			List<BoardDTO> list = boardDAO.searchAllArticle(dto, pageNum, amount);
			System.out.println("service list = " + list.size());
			return list;
		}

	// 말머리로 리스트를 보여줄 메소드
	@Override
	public List<BoardDTO> getSelectViewArticle(BoardDTO dto) {
		List<BoardDTO> list = boardDAO.wiewAllArticle(dto);
		return list;
	}
	// 비밀글을 상세보기로 볼 수 있는 메소드 호출
	@Override
	public BoardDTO getViewSecret(int b_articleNo) {
		BoardDTO dto = new BoardDTO();
		dto= boardDAO.searchPw(b_articleNo);
		System.out.println("service" + dto);
		return dto;
	}
	
	// 비밀번호를 가져오자
	@Override
	public int getPwd(int b_articleNo) {
		return boardDAO.getPw(b_articleNo);
	}
	@Override
	// 조회수 추가 메소드
	public void getView(BoardDTO dto) {
		boardDAO.view(dto);
	}
	
	// 페이징 메소드
	@Override
	public List<PageDTO> getPaging(int pageNum, int amount) {
//		Map map = new HashMap();
//		map.put("pageNum",pageNum);
//		map.put("amount",amount);
		System.out.println("service pageNum = " + pageNum);
		System.out.println("service amount = " + amount);
		List<PageDTO> list = boardDAO.paging(pageNum, amount);
		return list;
	}
	
	// 페이지 수 가져오는 메소드
	@Override
	public int getPage() {
		int count = boardDAO.totalCount();
		System.out.println("service count ---> " + count);
		return count;
	}
	
	// 댓글 조회 리스트 가져오는 메소드
	@Override
	public List<CommentDTO> getCommentList(){
		List<CommentDTO> list = new ArrayList<CommentDTO>();
		list = commentDAO.selectAllComment();
		return list;
	}
	@Override
	// 게시풀의 댓글 수 가져오는 메소드
	public List<CommentDTO> getComment() {
		List<CommentDTO> list = new ArrayList<CommentDTO>();
		list = boardDAO.getCommentCount();
		return list;
	}
	// 공지글만 가져오기
	@Override
	public List<CommentDTO> getNoticeList() {
		List<CommentDTO> list = boardDAO.getNotice();
		return list;
	}
	
	// 셀렉트박스 메소드(ajax)
	@Override
	public List<BoardDTO> getSearch(BoardDTO dto,int pageNum, int amount) {
		System.out.println("service dtofield : " + dto.getB_field());
		System.out.println("service search_bar : " + dto.getSearch_bar());
		System.out.println("service b_field2 : " + dto.getB_field2());
		List<BoardDTO> list = boardDAO.searchAllArticle(dto, pageNum, amount);
		System.out.println("service list = " + list.size());
		return list;
	}
	// 셀렉트 박스 총게시글 가져오기
	@Override
		public int getSelectCount(BoardDTO dto){
		int result = -1;
		result  = boardDAO.getSelectCount(dto);
		return result;
	}
	
	// 셀렉트박스 페이지 갯수 메소드(ajax)
		@Override
		public List<BoardDTO> getAmount(BoardDTO dto,int pageNum, int amount) {
			System.out.println("service dtofield : " + dto.getB_field());
			System.out.println("service search_bar : " + dto.getSearch_bar());
			System.out.println("service b_field2 : " + dto.getB_field2());
			List<BoardDTO> list = boardDAO.searchAllArticle(dto, pageNum, amount);
			System.out.println("service list = " + list.size());
			return list;
		}
	 
}
