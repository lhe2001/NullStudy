package com.spring.teampro.mystudy.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.teampro.mystudy.dto.MemoDTO;

@Repository
public class MemoDAOImpl implements MemoDAO {

	private static final Logger logger = LoggerFactory.getLogger(MemoDAOImpl.class);
	
	@Autowired
	SqlSession sqlSession;

	@Override
	public List<MemoDTO> selectPagingList(MemoDTO memoDTO) {
		logger.info("MemoDAOImpl >>  selectPagingList실행");
		List<MemoDTO> list = sqlSession.selectList("mapper.mystudy.Paging_list", memoDTO);
		return list;
	}

	@Override
	public int selectlistCount(int userkey) {
		logger.info("MemoDAOImpl >>  selectlistCount실행");
		return sqlSession.selectOne("mapper.mystudy.Paging_allCnt", userkey);
	}

	@Override
	public int insertNewMemo(MemoDTO memoDTO) {
		logger.info("MemoDAOImpl >>  insertNewMemo실행");
		return sqlSession.insert("mapper.mystudy.insertMemo", memoDTO);
	}

	@Override
	public int deleteMemo(int m_memo_key) {
		logger.info("MemoDAOImpl >>  deleteMemo실행");
		return sqlSession.delete("mapper.mystudy.deleteMemo", m_memo_key);
	}

	@Override
	public MemoDTO selectOneMemo(int m_memo_key) {
		logger.info("MemoDAOImpl >>  selectOneMemo실행");
		return sqlSession.selectOne("mapper.mystudy.selectOneMemo", m_memo_key);
	}

	@Override
	public int updateMemo(MemoDTO memoDTO) {
		logger.info("MemoDAOImpl >>  updateMemo실행");
		return sqlSession.update("mapper.mystudy.updateMemo", memoDTO);
	}
	
}
