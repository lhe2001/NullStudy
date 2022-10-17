package com.spring.teampro.signupin.service;

import com.spring.teampro.signupin.dto.SignUpInDTO;

public interface SignUpInService {
	
	public int getIdCheck(SignUpInDTO dto);
	
	public int getEmailCheck(SignUpInDTO dto);
	
	public int getPwCheck(SignUpInDTO dto);
	
	public int doAddMember(SignUpInDTO dto);
	
	public int doSignIn(String id, String pw);
	
	public SignUpInDTO getUserInfo(String id);
	
	public void updateLastTime(String id);
	
	
}
