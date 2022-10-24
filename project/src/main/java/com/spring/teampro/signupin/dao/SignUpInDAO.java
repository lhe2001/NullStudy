package com.spring.teampro.signupin.dao;

import java.util.List;

import com.spring.teampro.board.dto.BoardDTO;
import com.spring.teampro.signupin.dto.AdminDTO;
import com.spring.teampro.signupin.dto.SignUpInDTO;

public interface SignUpInDAO {

	
	public int idCheck(SignUpInDTO dto);
	
	public int emailCheck(SignUpInDTO dto);
	
	public int pwCheck(String pw, String rePw);
	
	public int addMember(SignUpInDTO dto);
	
	public int signIn(String id, String pw);
	
	public SignUpInDTO userInfo(String id);
	
	public void lastTime(String id);
	
	public void delMember(String id);
	
	public void modMember(SignUpInDTO dto);
	
	public SignUpInDTO modUserInfo(SignUpInDTO udto);
	
	public List<SignUpInDTO> listMembers();
	
	public List<SignUpInDTO> listById(String keyword);
	
	public List<SignUpInDTO> listByName(String keyword);
	
	public List<SignUpInDTO> listByBoth(String keyword);
	
	public List<AdminDTO> listTeams();

	public List<AdminDTO> listByTname(String keyword);
	
	public List<AdminDTO> listByTleader(String keyword);
	
	public List<AdminDTO> listByTinfo(String keyword);
	
	public List<BoardDTO> getTopArticles();
	
	
	
}
