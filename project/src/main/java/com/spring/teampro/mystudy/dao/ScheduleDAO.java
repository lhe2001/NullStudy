package com.spring.teampro.mystudy.dao;

import java.util.List;

import com.spring.teampro.mystudy.dto.ScheduleDTO;

public interface ScheduleDAO {
	
	public boolean insertCal(ScheduleDTO dto);
	
	public List<ScheduleDTO> calBoardList(ScheduleDTO dto);
	
	public ScheduleDTO calDetail(int schedule_key);
	
	public boolean calUpdate(ScheduleDTO dto);
	
	public boolean calMuldel(String[] schedule_key);
	
	public int calCount(ScheduleDTO dto);
	
	public List<ScheduleDTO> calViewList(ScheduleDTO dto);
}
