package com.spring.teampro.team.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.teampro.team.dao.TeamDAO;
import com.spring.teampro.team.dto.TeamInfoDTO;
import com.spring.teampro.team.dto.TeamMemberDTO;

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

	@Override
	public List getTeamMemberInfo(int t_key) {
		
		List list = dao.getTeamMemberInfo(t_key);
		
		for(int i=0; i<list.size(); i++) {
			TeamMemberDTO memberDTO = (TeamMemberDTO) list.get(i);
			Date lastTime = memberDTO.getLastTime();
			SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd hh:mm a");
			String time = format.format(lastTime);
			memberDTO.setLastTime2(time);
		}
		return list;
	}

}
