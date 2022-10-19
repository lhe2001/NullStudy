package com.spring.teampro.mystudy.service;

import java.util.List;

import com.spring.teampro.mystudy.dto.MemoDTO;

public interface MemoService {
	
	List<MemoDTO> selectPagingList(MemoDTO memoDTO);
	
	int selectlistCount(int userkey);
	
	int insertNewMemo(MemoDTO memoDTO);
	
	int deleteMemo(int m_memo_key);
	
	MemoDTO selectOneMemo(int m_memo_key);
	
	int updateMemo(MemoDTO memoDTO);
	
}
