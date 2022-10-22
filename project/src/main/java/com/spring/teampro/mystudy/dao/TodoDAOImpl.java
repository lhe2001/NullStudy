package com.spring.teampro.mystudy.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.teampro.mystudy.dto.TodoDTO;

@Repository
public class TodoDAOImpl implements TodoDAO {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public int insertTodo(TodoDTO dto) {
		int count = sqlSession.insert("mapper.mystudy.insertTodo", dto);
		return count;
	}

	@Override
	public List<TodoDTO> AllListTodo(int userkey) {
		List<TodoDTO> list = sqlSession.selectList("mapper.mystudy.todoAllList", userkey);
		return list;
	}

	@Override
	public int delTodo(int m_td_key) {
		int count = sqlSession.insert("mapper.mystudy.deleteTodo", m_td_key);
		return count;
	}

	@Override
	public int CountTodo(int userkey) {
		int count = sqlSession.selectOne("mapper.mystudy.todoCount", userkey);
		return count;
	}

}
