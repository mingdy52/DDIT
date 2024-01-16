package kr.or.ddit.member.dao;

import java.util.List;

import kr.or.ddit.vo.MemberVO;

/**
 * 
 * 회원관리(CRUD) 및 인증구조를 위한 Persistence Layer
 *
 */
public interface MemberDAO {
	
	public MemberVO selectMemberForAuth(MemberVO inputData);
	
	/**
	 * 회원 정보 등록
	 * @param member
	 * @return 등록한 레코드 수 > 0 :  성공, else : 실패
	 */
	public int insertMember(MemberVO member);
	
	/**
	 * 회원 목록 조회
	 * @return 존재하지 않는 경우, size()==0
	 */
	public List<MemberVO> selectMemberList();
	
//	selectMember
//	updateMember
//	deleteMember
	
}
