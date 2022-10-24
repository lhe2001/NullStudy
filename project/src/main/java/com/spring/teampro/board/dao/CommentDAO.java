package com.spring.teampro.board.dao;

import java.util.List;

import com.spring.teampro.board.dto.CommentDTO;

public interface CommentDAO {
	// 댓글 추가 메소드
	public int addComment(CommentDTO dto);
	
	// 추가한 댓글 조회 메소드
	public List<CommentDTO> selectComment(CommentDTO dto);
	
	// 댓글 삭제 메소드
	public void deleteComment(CommentDTO dto);
	
	// 댓글 수정 메소드
	public void modifyComment(CommentDTO dto);
	
	public List<CommentDTO> selectAllComment();
}
