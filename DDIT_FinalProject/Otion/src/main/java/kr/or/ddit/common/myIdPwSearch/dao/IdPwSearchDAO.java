package kr.or.ddit.common.myIdPwSearch.dao;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.common.vo.MemberVO;

@Mapper
public interface IdPwSearchDAO {
	
	public MemberVO getMemberId(MemberVO member);
	
	public MemberVO getMemberPass(MemberVO member);

	public void updateMemberPass(MemberVO member);
}
