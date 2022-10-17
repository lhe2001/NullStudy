package com.spring.teampro.search.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.teampro.search.dao.UserDAO;
import com.spring.teampro.search.dto.UserDTO;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDAO;
	
	@Override
	public List<UserDTO> getSearchUserSelectList() {
		
		return userDAO.searchUserSelectList();
		
	}
	
	@Override
	public List<UserDTO> getNickNameSearchUserSelectList() {
		
		return userDAO.nickNameSearchUserSelectList();
		
	}
	
}
