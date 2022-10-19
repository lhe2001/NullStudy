package com.spring.teampro.mystudy.dao;

import java.util.List;

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

	@Override
	public List<ScheduleDTO> selectAllSchedule(int userkey) {
		logger.info("ScheduleDAOImpl >>  selectAllSchedule 실행");
		
		List list = sqlSession.selectList("mapper.mystudy.selectAllScheduleList", userkey);
		logger.info("리스트 사이즈: "+list.size());
		return list;
	}

}
