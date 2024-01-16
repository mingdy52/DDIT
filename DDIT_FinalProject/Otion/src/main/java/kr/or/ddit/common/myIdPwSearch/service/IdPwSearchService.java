package kr.or.ddit.common.myIdPwSearch.service;

import kr.or.ddit.common.vo.MemberVO;

public interface IdPwSearchService {
	public MemberVO getMemberId(MemberVO member);
	
	public MemberVO getMemberPass(MemberVO member);
	
	public void modifyMemberPass(MemberVO member);
	
	public void sendMail(MemberVO member);
}
