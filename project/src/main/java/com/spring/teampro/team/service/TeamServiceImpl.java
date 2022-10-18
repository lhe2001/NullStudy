package com.spring.teampro.team.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.teampro.team.dao.TeamDAO;
import com.spring.teampro.team.dto.TeamInfoDTO;

@Service
public class TeamServiceImpl implements TeamService {
	
	private static final Logger logger = LoggerFactory.getLogger(TeamServiceImpl.class);

	@Autowired
	TeamDAO dao;
	
	@Override
	public List getMyTeamList(int userkey) {
		List list = dao.getMyTeamList(userkey);
		logger.info("list"+list);
		
		return list;
	}
	
	@Override
	public TeamInfoDTO getTeamInfo(int t_key) {
		
		return dao.getTeamInfo(t_key);
	}

	
	
}
