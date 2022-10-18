package com.spring.teampro.team.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.teampro.team.dto.TeamInfoDTO;

@Repository
public class TeamDAOImpl implements TeamDAO {

	private static final Logger logger = LoggerFactory.getLogger(TeamDAOImpl.class);
	
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List getMyTeamList(int userkey) {
		
		return sqlSession.selectList("mapper.team.myTeamList",userkey);
	}
	
	@Override
	public TeamInfoDTO getTeamInfo(int t_key) {
		
		return sqlSession.selectOne("mapper.team.teamInfo",t_key);
	}
	
	@Override
	public List getTeamMemberInfo(int t_key) {
		
		return sqlSession.selectList("mapper.team.teamMemberInfo",t_key);
	}
	
	
	
	
}
