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
	public int addNewSchedule(ScheduleDTO scheduleDTO) {
		logger.info("ScheduleServiceImpl >>  addNewSchedule 실행");
		return scheduleDAO.insertSchedule(scheduleDTO);
	}

	@Override
	public List<ScheduleDTO> getMonthList(ScheduleDTO scheduleDTO) {
		logger.info("ScheduleServiceImpl >>  getAllScheduleList 실행");
		return scheduleDAO.selectMonthSchedule(scheduleDTO);
	}

}
