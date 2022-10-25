package com.spring.teampro.search.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.teampro.search.dto.SearchDTO;

@Repository
public class Count {

	@Autowired
	SqlSession sqlSession;
	
	public int searchBoardSelectCount(SearchDTO dto) {
		System.out.println("Count searchBoardSelectCount 실행");
			
		int searchBoardSelectCount = sqlSession.selectOne("mapper.search.searchBoardSelectCount", dto);
		
		return searchBoardSelectCount;
	}
	
	public int b_titleSearchBoardSelectCount(SearchDTO dto) {
		System.out.println("Count b_titleSearchBoardSelectCount 실행");
			
		int b_titleSearchBoardSelectCount = 
				sqlSession.selectOne("mapper.search.b_titleSearchBoardSelectCount", dto);
		
		return b_titleSearchBoardSelectCount;
	}
	
	public int nickNameSearchBoardSelectCount(SearchDTO dto) {
		System.out.println("Count nickNameSearchBoardSelectCount 실행");
			
		int nickNameSearchBoardSelectCount = 
				sqlSession.selectOne("mapper.search.nickNameSearchBoardSelectCount", dto);
		
		return nickNameSearchBoardSelectCount;
	}
	
	public int b_contentSearchBoardSelectCount(SearchDTO dto) {
		System.out.println("Count b_contentSearchBoardSelectCount 실행");
			
		int b_contentSearchBoardSelectCount = 
				sqlSession.selectOne("mapper.search.b_contentSearchBoardSelectCount", dto);
		
		return b_contentSearchBoardSelectCount;
	}
	
	public int t_nameSearchTeamSelectCount(SearchDTO dto) {
		System.out.println("Count t_nameSearchTeamSelectCount 실행");
			
		int t_nameSearchTeamSelectCount = 
				sqlSession.selectOne("mapper.search.t_nameSearchTeamSelectCount", dto);
		
		return t_nameSearchTeamSelectCount;
	}
	
	public int nickNameSearchUserSelectCount(SearchDTO dto) {
		System.out.println("Count nickNameSearchUserSelectCount 실행");
			
		int nickNameSearchUserSelectCount = 
				sqlSession.selectOne("mapper.search.nickNameSearchUserSelectCount", dto);
		
		return nickNameSearchUserSelectCount;
	}
	
	
}
