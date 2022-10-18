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
	public List<MemoDTO> selectPagingList(int userkey) {
		logger.info("MemoDAOImpl >>  selectPagingList실행, userkey"+userkey);
		return sqlSession.selectList("mapper.mystudy.Paging_list", userkey);
	}

	@Override
	public int selectlistCount(int userkey) {
		logger.info("MemoDAOImpl >>  selectlistCount실행, userkey"+userkey);
		return sqlSession.selectOne("mapper.mystudy.Paging_allCnt", userkey);
	}
	

//
//	@Override
//	public int memo_add(MemoDTO memoDTO) {
//		
//		System.out.println("MemoDAOImpl >> memo_add 실행");
//		
//		return sqlSession.insert("mapper.mystudy.insertMemo", memoDTO);
//	}
//
//	@Override
//	public MemoDTO memo_beforeUpdate(int midx) {
//		System.out.println("MemoDAOImpl >> memo_beforeUpdate 실행");
//
//		return sqlSession.selectOne("mapper.mystudy.selectMemoByIdx", midx);
//	}
//
//	@Override
//	public int memo_update(MemoDTO memoDTO) {
//		System.out.println("MemoDAOImpl >> memo_update 실행");
//		
//		return sqlSession.update("mapper.mystudy.updateMemo", memoDTO);
//	}
//
//	@Override
//	public int memo_delete(int mdix) {
//		System.out.println("MemoDAOImpl >> memo_delete 실행");
//
//		return sqlSession.delete("mapper.mystudy.deleteMemo", mdix);
//	}



}
