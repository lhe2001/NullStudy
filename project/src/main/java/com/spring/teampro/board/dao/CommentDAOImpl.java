package com.spring.teampro.board.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.teampro.board.dto.CommentDTO;

@Repository("commentDAO")
public class CommentDAOImpl implements CommentDAO {
	@Autowired
	SqlSession sqlSession;	
	
	// 댓글 추가 메소드
	public int addComment(CommentDTO dto) {
		int result = -1;
		result = sqlSession.insert("mapper.board.addComment", dto);
		return result;
	}
	// 추가한 댓글 조회 메소드
	public List<CommentDTO> selectComment(CommentDTO dto){
		List<CommentDTO> list = new ArrayList<CommentDTO>();
		list = sqlSession.selectList("mapper.board.selectComment", dto);
		return list;
	}
	// 댓글 삭제 메소드
	public void deleteComment(CommentDTO dto){
		sqlSession.delete("mapper.board.deleteComment",dto);
	}
	
	// 댓글 수정 메소드
	public void modifyComment(CommentDTO dto) {
		sqlSession.update("mapper.board.modifyComment",dto);
	}
	
	public List<CommentDTO> selectAllComment(){
		List<CommentDTO> list = new ArrayList<CommentDTO>();
		list = sqlSession.selectList("mapper.board.selectAllComment");
		return list;
	}
}
