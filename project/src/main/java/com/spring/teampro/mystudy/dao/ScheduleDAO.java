package com.spring.teampro.mystudy.dao;

import java.util.List;

import com.spring.teampro.mystudy.dto.ScheduleDTO;

public interface ScheduleDAO {
	
	public int insertSchedule(ScheduleDTO scheduleDTO);
	
	public List<ScheduleDTO> selectAllSchedule(int userkey);
}
