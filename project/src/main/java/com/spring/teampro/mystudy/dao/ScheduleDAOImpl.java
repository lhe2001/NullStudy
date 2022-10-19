package com.spring.teampro.mystudy.dao;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.teampro.mystudy.dto.ScheduleDTO;

@Repository
public class ScheduleDAOImpl implements ScheduleDAO {

	private static final Logger logger = LoggerFactory.getLogger(ScheduleDAOImpl.class);

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public int insertSchedule(ScheduleDTO scheduleDTO) {
		logger.info("ScheduleDAOImpl >>  insertSchedule 실행");
		
		int i = sqlSession.insert("mapper.mystudy.insertSchedule", scheduleDTO);
		logger.info("일정dao insert >>"+i);
		return i;
		
//		return sqlSession.insert("mapper.mystudy.insertSchedule", scheduleDTO);
	}

}
