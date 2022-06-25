package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.vo.MemberVO;

public class MemberServiceImpl implements MemberService {

	MemberDAO memberDAO = new MemberDAOImpl();
	
	@Override
	public List<MemberVO> retrieveMemberList() {
		List<MemberVO> memberList = memberDAO.selectMemberList();
		return memberList;
	}

	@Override
	public ServiceResult createMember(MemberVO member) {
		ServiceResult result = null;
		
		if(memberDAO.selectMemberForAuth(member) == null) {
			int rowcnt = memberDAO.insertMember(member);
			if(rowcnt > 0) {
				result = ServiceResult.OK;
				
			} else {
				result = ServiceResult.FAIL;
				
			}
		} else {
			result = ServiceResult.PKDUPLICATED;
		}
		
		return result;
	}
	
	
	
}
