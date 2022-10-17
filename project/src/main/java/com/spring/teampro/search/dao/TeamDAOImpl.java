package com.spring.teampro.search.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.teampro.search.dto.TeamDTO;

@Repository
public class TeamDAOImpl implements TeamDAO {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<TeamDTO> searchTeamSelectList() {
		System.out.println("TeamDAOImpl SearchTeamSelectList 실행");
		
		List<TeamDTO> SearchTeamSelectList = sqlSession.selectList("mapper.search.SearchTeamSelectList");
		
		return SearchTeamSelectList;
	} // 모든 팀 조회
	
	@Override
	public List<TeamDTO> t_nameSearchTeamSelectList() {
		System.out.println("TeamDAOImpl t_nameSearchTeamSelectList 실행");
		
		List<TeamDTO> t_nameSearchTeamSelectList = sqlSession.selectList("mapper.search.t_nameSearchTeamSelectList");
		
		return t_nameSearchTeamSelectList;
	} // 팀 이름 조회

}
