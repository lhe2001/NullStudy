package com.spring.teampro.mystudy.service;

import java.util.List;

import com.spring.teampro.mystudy.dto.ScheduleDTO;

public interface ScheduleService {
	
	int addNewSchedule(ScheduleDTO scheduleDTO);
	
	List<ScheduleDTO> getMonthList(ScheduleDTO scheduleDTO);
}
