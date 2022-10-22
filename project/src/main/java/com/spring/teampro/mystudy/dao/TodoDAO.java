package com.spring.teampro.mystudy.dao;

import java.util.List;

import com.spring.teampro.mystudy.dto.TodoDTO;


public interface TodoDAO {
	
	public int insertTodo(TodoDTO dto);
	
	public List<TodoDTO> AllListTodo(int userkey);
	
	public int delTodo(int m_td_key);
	
	public int CountTodo(int userkey);
	

}
