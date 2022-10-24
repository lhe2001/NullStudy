package com.spring.teampro.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.teampro.board.dao.CommentDAO;
import com.spring.teampro.board.dto.CommentDTO;

@Service("commentService")
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentDAO commentDAO;
	// 댓글 추가
	public int getAddComment(CommentDTO commentDTO) {
		int result = -1;
		result = commentDAO.addComment(commentDTO);
		return result;
	}
	// 댓글 추가 조회
	public List<CommentDTO> getSelectComment(CommentDTO commentDTO){
		List<CommentDTO> list = commentDAO.selectComment(commentDTO);
		return list;
	}
	
	// 댓글 삭제
	public void getDeleteComment(CommentDTO commentDTO) {
		commentDAO.deleteComment(commentDTO);
	}
	
	// 댓글 수정
	public void getUpdateComment(CommentDTO commentDTO) {
		commentDAO.modifyComment(commentDTO);
	}
}
