package com.spring.teampro.team.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.teampro.team.dto.MemberRequestDTO;
import com.spring.teampro.team.dto.TeamInfoDTO;
import com.spring.teampro.team.dto.TeamMemberDTO;

@Repository
public class TeamDAOImpl implements TeamDAO {

	private static final Logger logger = LoggerFactory.getLogger(TeamDAOImpl.class);
	
	@Autowired
	SqlSession sqlSession;
	
	//>>>>>>>>>>>>>>>SELECT 단순 가져오기>>>>>>>>>>>>>>
	//나의 팀 리스트 가져오기
	@Override
	public List getMyTeamList(int userkey) {
		
		return sqlSession.selectList("mapper.team.myTeamList",userkey);
	}
	
	//팀정보 가져오기
	@Override
	public TeamInfoDTO getTeamInfo(int t_key) {
		
		return sqlSession.selectOne("mapper.team.teamInfo",t_key);
	}
	
	//전체팀 List 가져오기
	@Override
	public List allTeamList() {
		return sqlSession.selectList("mapper.team.allTeamList");
	}
	
	//팀멤버 정보 가져오기
	@Override
	public List getTeamMemberInfo(int t_key) {
		
		return sqlSession.selectList("mapper.team.teamMemberInfo",t_key);
	}
	
	//가입요청 알람이 있는가? 몇개있는가?
	@Override
	public int anyAlarm(int t_key) {
		return sqlSession.selectOne("mapper.team.anyAlarm",t_key);
	}
	
	//가입요청List를 가져오기
	@Override
	public List getRequestList(int t_key) {
		return sqlSession.selectList("mapper.team.getRequestList",t_key);
	}

	//>>>>>>>>>>>>>>>UPDATE 수정하기>>>>>>>>>>>>>>
	//팀정보 업데이트 하기
	@Override
	public int updateTeamInfo(TeamInfoDTO dto) {
		return sqlSession.update("mapper.team.updateTeamInfo",dto);
	}

	//조장한마디 업데이트 하기
	@Override
	public int updateLMemo(TeamInfoDTO dto) {
		return sqlSession.update("mapper.team.updateLMemo",dto);
	}

	//가입요청을 수락하기
	@Override
	public int acceptMember(MemberRequestDTO dto) {
		
		sqlSession.update("mapper.team.accpetMember",dto);
		return sqlSession.insert("mapper.team.addTeamMember",dto);
	}
	
	//>>>>>>>>>>>>>>>DELETE 삭제하기>>>>>>>>>>>>>>
	//멤버 강퇴하기 
	@Override
	public int removeMember(int tm_key) {
		return sqlSession.delete("mapper.team.removeMember",tm_key);
	}

	//>>>>>>>>>>>>>>>INSERT 추가하기>>>>>>>>>>>>>>
	//가입요청 업데이트
	@Override
	public int memberRequest(MemberRequestDTO dto) {
		return sqlSession.insert("mapper.team.memberRequest",dto);
	}

	

	

	
	
	
	
}
