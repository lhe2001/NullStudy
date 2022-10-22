package com.spring.teampro.mystudy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.teampro.mystudy.dao.TodoDAO;
import com.spring.teampro.mystudy.dto.TodoDTO;

@Service
public class TodoServiceImpl implements TodoService {

	@Autowired
	TodoDAO todoDAO;
	
	@Override
	public List<TodoDTO> AllListTodo(int userkey) {
		List<TodoDTO> list = todoDAO.AllListTodo(userkey);

		return list;
	}

	@Override
	public int insertTodo(TodoDTO dto) {
		return todoDAO.insertTodo(dto);
	}


	@Override
	public int delTodo(int m_td_key) {
		return todoDAO.delTodo(m_td_key);
	}

	@Override
	public int CountTodo(int userkey) {
		return todoDAO.CountTodo(userkey);
	}

}
