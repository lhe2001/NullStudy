package com.spring.teampro.board.dao;

import java.util.List;
import java.util.Map;

import com.spring.teampro.board.dto.BoardDTO;
import com.spring.teampro.board.dto.CommentDTO;
import com.spring.teampro.board.dto.PageDTO;


public interface BoardDAO {
	
	// 전체 글 조회
	public List<BoardDTO> selectAllArticles(int pageNum, int amount);
	
	// addNewArticle() 메소드 작성 <Board 에 글 추가>
	public int addNewArticle(Map map);
	
	// viewArticle() 메소드 작성 <글 상세 목록 view>
	// 여기서 수정 해야 할 것은 글 내용에 어떤 내용들을 보여줄지 생각...
	public BoardDTO selectViewArticle(int b_articleNo);
	
	// 글 수정하는 메소드
	public void modifyArticle(Map map);
	
	// 삭제하는 메소드
	public void deleteArticle(int b_articleNo);
	
	// 삭제할 글 번호 가져오는 메소드
	public List<Integer> selectRemovedArticle(int b_articleNo);
	
	// 서치하는 메소드
//	public List<BoardDTO> searchAllArticle(BoardDTO dto);
	
	// 서치하는 메소드(ajax)
	public List<BoardDTO> searchAllArticle(BoardDTO dto,int pageNum, int amount);
	
	// 말머리에서 선택했을때 리스트 출력시킬 메소드
	public List<BoardDTO> wiewAllArticle(BoardDTO dto);

	// 비밀글 조회 메소드
	public BoardDTO searchPw(int b_articleNo);
	
	// 비밀번호 찾는 메소드
	public int getPw(int b_articleNo);
	
	// 조회수 추가 메소드
	public void view(BoardDTO dto);
	
	// 페이징 메소드
	public List<PageDTO> paging(int pageNum, int amount);
	
	// 총 페이지 수 출력 메소드
	public int totalCount();
	
	// 게시글의 댓글수 가져오는 메소드
	public List<CommentDTO> getCommentCount();
	
	// 공지글만 가져오기
	public List<CommentDTO> getNotice();
}

