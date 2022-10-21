package com.spring.teampro.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.teampro.board.dao.CommentDAO;
import com.spring.teampro.board.dto.CommentDTO;

@Service("commentService")
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentDAO commentDAO;
	
	public int getAddComment(CommentDTO commentDTO) {
		int result = -1;
		result = commentDAO.addComment(commentDTO);
		return result;
	}
}
