package kr.or.ddit.common.login.dao;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.common.vo.MemberVO;

@Mapper
public interface LoginDAO {
	
	public MemberVO login(String username);

	public String blackView(String username);
}
