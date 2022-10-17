package com.spring.teampro.signupin.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.teampro.signupin.dto.SignUpInDTO;

@Repository
public class SignUpInDAOImpl implements SignUpInDAO {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	//모든 회원의 정보(DTO)를 list에 담아주는 메소드 
	public List<SignUpInDTO> listMembers() {
		List list = sqlSession.selectList("mapper.member.listMembers");
		return list;
	}

	@Override
	// 회원가입시 아이디 중복 체크하는 메소드 
	public int idCheck(SignUpInDTO dto) {
		int result = sqlSession.selectOne("mapper.member.idCheck", dto);
		if (result==1) {
			// 중복된 아이디가 존재한다 
			return -1;
		} else {
			// 사용가능한 아이디
			return 1;			
		}
	}

	@Override
	// 회원가입시 이메일 중복 체크하는 메소드 
	public int emailCheck(SignUpInDTO dto) {
		int result = sqlSession.selectOne("mapper.member.emailCheck", dto);
		if(result == 1) {
			// 중복된 이메일이 존재한다.
			return -1;
		} else {
			// 사용 가능한 이메일
			return 1;			
		}
	}

	@Override
	//회원가입시 비밀번호, 비밀번호 확인값 일치하는지 체크하는 메소드 
	public int pwCheck(String pw, String rePw) {
		if(pw.equals(rePw)) {
			//비번 ,비번확인 값 같음
			return 1; 
		} else {
			//비번, 비번확인 값 다름
			return -1;
		}
	}

	@Override
	//회원가입 시 입력된 데이터를 DB로 전달하는 메소드 
	public int addMember(SignUpInDTO dto) {
		int result = 0;
		result = sqlSession.insert("mapper.member.addMember", dto);
		if(result != 0) {
//			sqlSession.commit();
			return 1; // 가입성공 메세지 > signin페이지로 이동 
		} else {
			return -1; // 가입실패 메세지 > signup페이지로 이동 
		}
	}

	@Override
	//로그인 메소드 
	public int signIn(String id, String pw) {
		
		if("admin".equals(id)) {
			String checkPw=sqlSession.selectOne("mapper.member.signIn", id);
			if(pw.equals(checkPw)) {
				//관리자 로그인 성공 
				return 99;
			}else {
				//비밀번호 불일치 
				return 0;
			}
			
		} else {
			String checkPw=sqlSession.selectOne("mapper.member.signIn", id);
			if(pw.equals(checkPw)) {
				// 로그인 성공 
				return 1;
			}else {
				// 비밀번호 불일치 
				return 0;
			}
		}
	}

	@Override
	//로그인 성공시 session에 담아줄 개인 DTO 생성하는 메소드 
	public SignUpInDTO userInfo(String id) {
		return (SignUpInDTO)sqlSession.selectOne("mapper.member.userInfo", id);
		
	}

	@Override
	// 로그인할 경우 유저 DB에 로그인한 시간을 업데이트하는 메소드 
	public void lastTime(String id) {
		sqlSession.update("mapper.member.updateLastTime", id);
	}

	//-------------------관리자용 메소드------------------- 
	
	@Override
	public List<SignUpInDTO> searchMember(String Keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delMember(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void modMember(SignUpInDTO dto) {
		// TODO Auto-generated method stub

	}

	@Override
	public SignUpInDTO modUserInfo(SignUpInDTO udto) {
		// TODO Auto-generated method stub
		return null;
	}

}
