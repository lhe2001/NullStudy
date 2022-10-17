package com.spring.teampro.signupin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.teampro.signupin.dao.SignUpInDAO;
import com.spring.teampro.signupin.dto.SignUpInDTO;;

@Service
public class SignUpInServiceImpl implements SignUpInService {

	@Autowired
	SignUpInDAO signUpInDAO;
	
	@Override
	public int getIdCheck(SignUpInDTO dto) {
		return signUpInDAO.idCheck(dto);
	}
	
	@Override
	public int getEmailCheck(SignUpInDTO dto) {
		return signUpInDAO.emailCheck(dto);
	}
	
	@Override
	public int getPwCheck(SignUpInDTO dto) {
		String pw = dto.getPw();
		String rePw = dto.getRePw();
		return signUpInDAO.pwCheck(pw, rePw);
	}
	
	@Override
	public int doAddMember(SignUpInDTO dto) {
		return signUpInDAO.addMember(dto);
	}
	
	@Override
	public int doSignIn(String id, String pw) {
		//return 99:관리자 0:비번불일치 1:로그인 성공 
		return signUpInDAO.signIn(id, pw);
	}
	
	@Override
	public SignUpInDTO getUserInfo(String id) {
		return signUpInDAO.userInfo(id);
	}
	
	@Override
	public void updateLastTime(String id) {
		signUpInDAO.lastTime(id);
	}

	
	
	

}
