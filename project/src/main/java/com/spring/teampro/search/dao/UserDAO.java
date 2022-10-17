package com.spring.teampro.search.dao;

import java.util.List;

import com.spring.teampro.search.dto.UserDTO;

public interface UserDAO {

	List<UserDTO> searchUserSelectList();
	
	List<UserDTO> nickNameSearchUserSelectList();
	
}
