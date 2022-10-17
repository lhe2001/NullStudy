package com.spring.teampro.signupin.dao;

import java.util.List;

import com.spring.teampro.signupin.dto.SignUpInDTO;

public interface SignUpInDAO {

	public List<SignUpInDTO> listMembers();
	
	public int idCheck(SignUpInDTO dto);
	
	public int emailCheck(SignUpInDTO dto);
	
	public int pwCheck(String pw, String rePw);
	
	public int addMember(SignUpInDTO dto);
	
	public int signIn(String id, String pw);
	
	public SignUpInDTO userInfo(String id);
	
	public void lastTime(String id);
	
	public List<SignUpInDTO> searchMember(String Keyword);
	
	public void delMember(String id);
	
	public void modMember(SignUpInDTO dto);
	
	public SignUpInDTO modUserInfo(SignUpInDTO udto);
}
