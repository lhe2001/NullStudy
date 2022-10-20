package com.spring.teampro.mystudy.service;

import java.util.List;

import com.spring.teampro.mystudy.dto.MemoDTO;

public interface MemoService {
	
	List<MemoDTO> selectPagingList(MemoDTO memoDTO);
	
	int selectlistCount(int userkey);
	
//	int getAddmemo(MemoDTO memoDTO);
//	
//	MemoDTO getMemoOne(int idx);
//	
//	int getModmemo(MemoDTO memoDTO);
//	
//	int getDelmemo(int idx);
}
