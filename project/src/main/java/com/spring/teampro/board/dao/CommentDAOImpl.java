package com.spring.teampro.board.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.teampro.board.dto.CommentDTO;

@Repository("commentDAO")
public class CommentDAOImpl implements CommentDAO {
	@Autowired
	SqlSession sqlSession;	
	
	public int addComment(CommentDTO dto) {
		int result = -1;
		result = sqlSession.insert("mapper.board.addComment", dto);
		return result;
	}
}
