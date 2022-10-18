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
		logger.info("MemoServiceImpl >>  selectPagingList실행"+memoDTO);
		return memoDAO.selectPagingList(memoDTO);
	}

	@Override
	public int selectlistCount(int userkey) {
		logger.info("MemoServiceImpl >>  selectlistCount실행, userkey"+userkey);
		return memoDAO.selectlistCount(userkey);
	}
	
//	@Override
//	public int getAddmemo(MemoDTO memoDTO) {
//		System.out.println("MemoServiceImpl >> getAddmemo 실행");
//		
//		return memoDAO.memo_add(memoDTO);
//	}
//
//	@Override
//	public MemoDTO getMemoOne(int idx) {
//		System.out.println("MemoServiceImpl >> getMemoOne 실행");
//
//		return memoDAO.memo_beforeUpdate(idx);
//	}
//
//	@Override
//	public int getModmemo(MemoDTO memoDTO) {
//		System.out.println("MemoServiceImpl >> getModmemo 실행");
//
//		return memoDAO.memo_update(memoDTO);
//	}
//
//	@Override
//	public int getDelmemo(int idx) {
//		System.out.println("MemoServiceImpl >> getDelmemo 실행");
//		
//		return memoDAO.memo_delete(idx);
//	}
//
//	@Override
//	public int getMemoTotal(MemoDTO memoDTO) {
//		System.out.println("MemoServiceImpl >> getDelmemo 실행");
//		
//		return memoDAO.listCount(memoDTO);
//	}
//
//	@Override
//	public List getPagingList(MemoDTO memoDTO) {
//		System.out.println("MemoServiceImpl >> getPagingList 실행");
//		
//		return memoDAO.memoPaging_view(memoDTO);
//	}

}
