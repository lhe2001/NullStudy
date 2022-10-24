package com.spring.teampro.mystudy.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	//일정추가하기
	public boolean insertCal(ScheduleDTO dto) {
		int count = sqlSession.insert("mapper.mystudy.insertCal", dto);
		return count>0?true:false;
	}
	
	//일정목록조회하기
	public List<ScheduleDTO> calBoardList(ScheduleDTO dto){
		List<ScheduleDTO> list = sqlSession.selectList("mapper.mystudy.calBoardList", dto);
		return list;
	}
	
	//일정상세보기
	public ScheduleDTO calDetail(int schedule_key) {
		ScheduleDTO dto = sqlSession.selectOne("mapper.mystudy.calDetail", schedule_key);
		return dto;
	}
	
	//일정수정하기
	public boolean calUpdate(ScheduleDTO dto) {
		int count = sqlSession.update("mapper.mystudy.calUpdate", dto);
		return count>0?true:false;
	}
	//일정삭제하기
	public boolean calMuldel(String[] m_schedule_key) {
		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("m_schedule_keys", m_schedule_key);
		int count = sqlSession.delete("mapper.mystudy.calMuldel", map);
//		logger.info("map count: "+count);
		return count>0?true:false;
	}
	
	//일정의 개수 조회
	public int calCount(ScheduleDTO dto) {
		logger.info("카운트에서 날짜"+dto.getM_schedule_date());
		int count = sqlSession.selectOne("mapper.mystudy.calCount", dto);
		return count;
	}
	
	//달력에 존재하는 일정조회하기(최대 3개)
	public List<ScheduleDTO> calViewList(ScheduleDTO dto){
		return sqlSession.selectList("mapper.mystudy.calViewList", dto);
	}


}
