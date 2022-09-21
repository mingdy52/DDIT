package kr.or.ddit.common.login.vo;


import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import kr.or.ddit.common.vo.MemberVO;

public class MemberPrincipal extends User{
	
	private MemberVO realMember;
	
	public MemberPrincipal(MemberVO realMember) {
		super(realMember.getMemId(), realMember.getMemPass(), AuthorityUtils.createAuthorityList(realMember.getRoleListArray()));
		// TODO Auto-generated constructor stub
		this.realMember = realMember;
	}

	public MemberVO getRealMember() {
		return realMember;
	}
}
