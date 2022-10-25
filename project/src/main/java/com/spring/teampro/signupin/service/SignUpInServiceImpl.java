package com.spring.teampro.signupin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.teampro.board.dto.BoardDTO;
import com.spring.teampro.signupin.dao.SignUpInDAO;
import com.spring.teampro.signupin.dto.AdminDTO;
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

	@Override
	public List<SignUpInDTO> getMemberList() {
		return signUpInDAO.listMembers();
	}

	@Override
	public List<SignUpInDTO> getListById(String keyword) {
		return signUpInDAO.listById(keyword);
		
	}

	@Override
	public List<SignUpInDTO> getListByName(String keyword) {
		return signUpInDAO.listByName(keyword);
	}

	@Override
	public List<SignUpInDTO> getListByBoth(String keyword) {
		return signUpInDAO.listByBoth(keyword);
	}

	@Override
	public List<AdminDTO> getStudyList() {
		return signUpInDAO.listTeams();
	}

	@Override
	public List<AdminDTO> getListByTname(String keyword) {
		return signUpInDAO.listByTname(keyword);
	}

	@Override
	public List<AdminDTO> getListByTleader(String keyword) {
		return signUpInDAO.listByTleader(keyword);
	}

	@Override
	public List<AdminDTO> getListByTinfo(String keyword) {
		return signUpInDAO.listByTinfo(keyword);
	}

	@Override
	public void doModMember(SignUpInDTO dto) {
		signUpInDAO.modMember(dto);
	}
	
	@Override
	public void doDelMember(String id) {
		signUpInDAO.delMember(id);
	}

	@Override
	public List<BoardDTO> getTopArticles() {
		List<BoardDTO> list = signUpInDAO.getTopArticles(); 
		
		for(int i=0;i<list.size();i++) {
			BoardDTO dto = list.get(i);
			int b_field = dto.getB_field();
			
			switch(b_field) {
			case 10:
				dto.setB_fieldName("질문");
				break;
			case 20:
				dto.setB_fieldName("잡담");
				break;
			case 30:
				dto.setB_fieldName("비밀글");
				break;
			case 40:
				dto.setB_fieldName("나도몰라");
				break;
			case 50:
				dto.setB_fieldName("공지");
				break;
			}
		}
		return list;
	}

}
