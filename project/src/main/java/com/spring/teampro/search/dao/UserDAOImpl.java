package com.spring.teampro.search.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.spring.teampro.search.dto.UserDTO;

public class UserDAOImpl implements UserDAO {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<UserDTO> searchUserSelectList() {
		System.out.println("TeamDAOImpl t_nameSearchTeamSelectList 실행");
		
		List<UserDTO> SearchUserSelectList = sqlSession.selectList("mapper.search.SearchUserSelectList");
		
		return SearchUserSelectList;
	}
	
	@Override
	public List<UserDTO> nickNameSearchUserSelectList() {
		System.out.println("TeamDAOImpl nickNameSearchUserSelectList 실행");
		
		List<UserDTO> nickNameSearchUserSelectList = sqlSession.selectList("mapper.search.nickNameSearchUserSelectList");
		
		return nickNameSearchUserSelectList;
	}

}
