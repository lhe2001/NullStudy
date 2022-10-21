package com.spring.teampro.mystudy.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.teampro.mystudy.dao.MemoDAO;
import com.spring.teampro.mystudy.dao.MemoDAOImpl;
import com.spring.teampro.mystudy.dto.MemoDTO;

@Service
public class MemoServiceImpl implements MemoService {
	
	private static final Logger logger = LoggerFactory.getLogger(MemoDAOImpl.class);

	@Autowired
	MemoDAO memoDAO;

	@Override
	public List<MemoDTO> selectPagingList(MemoDTO memoDTO) {
		logger.info("MemoServiceImpl >>  selectPagingList실행");
		return memoDAO.selectPagingList(memoDTO);
	}

	@Override
	public int selectlistCount(int userkey) {
		logger.info("MemoServiceImpl >>  selectlistCount실행");
		return memoDAO.selectlistCount(userkey);
	}

	@Override
	public int insertNewMemo(MemoDTO memoDTO) {
		logger.info("MemoServiceImpl >>  insertNewMemo실행");
		return memoDAO.insertNewMemo(memoDTO);
	}

	@Override
	public int deleteMemo(int m_memo_key) {
		logger.info("MemoServiceImpl >>  deleteMemo실행");
		return memoDAO.deleteMemo(m_memo_key);
	}

	@Override
	public MemoDTO selectOneMemo(int m_memo_key) {
		logger.info("MemoServiceImpl >>  selectOneMemo실행");
		return memoDAO.selectOneMemo(m_memo_key);
	}

	@Override
	public int updateMemo(MemoDTO memoDTO) {
		logger.info("MemoServiceImpl >>  updateMemo실행");
		return memoDAO.updateMemo(memoDTO);
	}
	
}
