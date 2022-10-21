package com.spring.teampro.mystudy.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.teampro.mystudy.dao.ScheduleDAO;
import com.spring.teampro.mystudy.dto.ScheduleDTO;

@Service
public class ScheduleServiceImpl implements ScheduleService {

	private static final Logger logger = LoggerFactory.getLogger(ScheduleServiceImpl.class);
	
	@Autowired
	ScheduleDAO scheduleDAO;
	
	@Override
	public boolean insertCal(ScheduleDTO dto) {
		return scheduleDAO.insertCal(dto);
	}

	@Override
	public List<ScheduleDTO> calBoardList(ScheduleDTO dto) {
		return scheduleDAO.calBoardList(dto);
	}

	@Override
	public ScheduleDTO calDetail(int schedule_key) {
		return scheduleDAO.calDetail(schedule_key);
	}

	@Override
	public boolean calUpdate(ScheduleDTO dto) {
		return scheduleDAO.calUpdate(dto);
	}

	@Override
	public boolean calMuldel(String[] schedule_key) {
		return scheduleDAO.calMuldel(schedule_key);
	}

	@Override
	public int calCount(ScheduleDTO dto) {
		return scheduleDAO.calCount(dto);
	}
	
	@Override
	public List<ScheduleDTO> calViewList(ScheduleDTO dto){
		return scheduleDAO.calViewList(dto);
	}

}
