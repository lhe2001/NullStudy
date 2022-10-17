package com.spring.teampro.search.service;

import java.util.List;

import com.spring.teampro.search.dto.UserDTO;

public interface UserService {

	List<UserDTO> getSearchUserSelectList();

	List<UserDTO> getNickNameSearchUserSelectList();

}
