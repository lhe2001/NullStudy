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
	
	public int countMethod(List list) {
		int count = 0;
		for (int i = 0; i < list.size(); i++) {
			count =+ 1;
		}
		
		return count;
	}
	
	public int boardAllCount(SearchDTO dto) {
		System.out.println("Count boardAllCount 실행");
			
		List<SearchDTO> boardAllCount = sqlSession.selectList("mapper.search.boardAllCount", dto);
		int count = countMethod(boardAllCount);
		
		return count;
	}
	
	public int searchBoardSelectCount(SearchDTO dto) {
		System.out.println("Count searchBoardSelectCount 실행");
			
		List<SearchDTO> searchBoardSelectCount = sqlSession.selectList("mapper.search.searchBoardSelectCount", dto);
		int count = countMethod(searchBoardSelectCount);
		
		return count;
	}
	
	public int b_titleSearchBoardSelectCount(SearchDTO dto) {
		System.out.println("Count b_titleSearchBoardSelectCount 실행");
			
		List<SearchDTO> b_titleSearchBoardSelectCount = sqlSession.selectList("mapper.search.b_titleSearchBoardSelectCount", dto);
		int count = countMethod(b_titleSearchBoardSelectCount);
		
		return count;
	}
	
	public int nickNameSearchBoardSelectCount(SearchDTO dto) {
		System.out.println("Count nickNameSearchBoardSelectCount 실행");
			
		List<SearchDTO> nickNameSearchBoardSelectCount = sqlSession.selectList("mapper.search.nickNameSearchBoardSelectCount", dto);
		int count = countMethod(nickNameSearchBoardSelectCount);
		
		return count;
	}
	
	public int b_contentSearchBoardSelectCount(SearchDTO dto) {
		System.out.println("Count b_contentSearchBoardSelectCount 실행");
			
		List<SearchDTO> b_contentSearchBoardSelectCount = sqlSession.selectList("mapper.search.b_contentSearchBoardSelectCount", dto);
		int count = countMethod(b_contentSearchBoardSelectCount);
		
		return count;
	}
	
	public int teamAllCount(SearchDTO dto) {
		System.out.println("Count teamAllCount 실행");
			
		List<SearchDTO> teamAllCount = sqlSession.selectList("mapper.search.teamAllCount", dto);
		int count = countMethod(teamAllCount);
		
		return count;
	}
	
	public int t_nameSearchTeamSelectCount(SearchDTO dto) {
		System.out.println("Count t_nameSearchTeamSelectCount 실행");
			
		List<SearchDTO> t_nameSearchTeamSelectCount = sqlSession.selectList("mapper.search.t_nameSearchTeamSelectCount", dto);
		int count = countMethod(t_nameSearchTeamSelectCount);
		
		return count;
	}
	
	public int userAllCount(SearchDTO dto) {
		System.out.println("Count userAllCount 실행");
			
		List<SearchDTO> userAllCount = sqlSession.selectList("mapper.search.userAllCount", dto);
		int count = countMethod(userAllCount);
		
		return count;
	}
	
	public int nickNameSearchUserSelectCount(SearchDTO dto) {
		System.out.println("Count nickNameSearchUserSelectCount 실행");
			
		List<SearchDTO> nickNameSearchUserSelectCount = sqlSession.selectList("mapper.search.nickNameSearchUserSelectCount", dto);
		int count = countMethod(nickNameSearchUserSelectCount);
		
		return count;
	}
	
	
}
