package com.spring.teampro.signupin.service;

import java.util.List;

import com.spring.teampro.board.dto.BoardDTO;
import com.spring.teampro.signupin.dto.AdminDTO;
import com.spring.teampro.signupin.dto.SignUpInDTO;

public interface SignUpInService {
	
	public int getIdCheck(SignUpInDTO dto);
	
	public int getEmailCheck(SignUpInDTO dto);
	
	public int getPwCheck(SignUpInDTO dto);
	
	public int doAddMember(SignUpInDTO dto);
	
	public int doSignIn(String id, String pw);
	
	public SignUpInDTO getUserInfo(String id);
	
	public void updateLastTime(String id);
	
	public List<SignUpInDTO> getMemberList();	
	
	public List<SignUpInDTO> getListById(String keyword);
	
	public List<SignUpInDTO> getListByName(String keyword);
	
	public List<SignUpInDTO> getListByBoth(String keyword);
	
	public List<AdminDTO> getStudyList();

	public List<AdminDTO> getListByTname(String keyword);

	public List<AdminDTO> getListByTleader(String keyword);
	
	public List<AdminDTO> getListByTinfo(String keyword);
	
	public void doModMember(SignUpInDTO dto);
	
	public void doDelMember(String id);
	
	public List<BoardDTO> getTopArticles();
	
	
}
