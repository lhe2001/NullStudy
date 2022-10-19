package com.spring.teampro.mystudy.dao;

import java.util.List;

import com.spring.teampro.mystudy.dto.MemoDTO;

public interface MemoDAO {
	
	public List<MemoDTO> selectPagingList(MemoDTO memoDTO);
	
	public int selectlistCount(int userkey);
	
	public int insertNewMemo(MemoDTO memoDTO);
	
	public int deleteMemo(int m_memo_key);
	
	public MemoDTO selectOneMemo(int m_memo_key);
	
	public int updateMemo(MemoDTO memoDTO);
}
