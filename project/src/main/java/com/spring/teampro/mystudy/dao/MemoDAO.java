package com.spring.teampro.mystudy.dao;

import java.util.List;

import com.spring.teampro.mystudy.dto.MemoDTO;

public interface MemoDAO {
	
	public List<MemoDTO> selectPagingList(int userkey);
	
	public int selectlistCount(int userkey);
//	
//	public int memo_add(MemoDTO memoDTO);
//	
//	public MemoDTO memo_beforeUpdate(int midx);
//	
//	public int memo_update(MemoDTO memoDTO);
//	
//	public int memo_delete(int mdix);
}
