package com.spring.teampro.mystudy.service;

import java.util.List;

import com.spring.teampro.mystudy.dto.TodoDTO;

public interface TodoService {
	
	public boolean insertTodo(TodoDTO dto);
	
	public List<TodoDTO> AllListTodo(int userkey);
	
	public boolean delTodo(int m_td_key);
	
	public int CountTodo(int userkey);
	
}
