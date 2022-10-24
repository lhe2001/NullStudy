package com.spring.teampro.board.service;

import java.util.List;

import com.spring.teampro.board.dto.CommentDTO;

public interface CommentService {
	// 댓글 추가
	public int getAddComment(CommentDTO commentDTO);
	
	// 댓글 추가 조회
	public List<CommentDTO> getSelectComment(CommentDTO commentDTO);
	
	// 댓글 삭제
	public void getDeleteComment(CommentDTO commentDTO);
	
	// 댓글 수정
	public void getUpdateComment(CommentDTO commentDTO);
}
