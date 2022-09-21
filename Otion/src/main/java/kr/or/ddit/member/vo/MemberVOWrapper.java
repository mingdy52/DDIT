package kr.or.ddit.member.vo;

import java.security.Principal;

import kr.or.ddit.common.vo.MemberVO;

public class MemberVOWrapper implements Principal{

	private MemberVO member;
	
	public MemberVOWrapper(MemberVO member) {
		// TODO Auto-generated constructor stub
		this.member = member;
	}
	
	@Override
	public String getName() {

		return member.getMemId();
	}
	
	public MemberVO getRealMember() {
		return member;
	}
}
