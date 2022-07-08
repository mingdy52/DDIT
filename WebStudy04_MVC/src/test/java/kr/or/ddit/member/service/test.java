package kr.or.ddit.member.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

public class test {

	MemberService service = new MemberServiceImpl();
	
	@Test
	public void testCreateMember() {
		MemberVO member = new MemberVO();
		member.setMemId("a001");
		member.setMemPass("java");
		member.setMemName("신규");
		member.setMemBir("1999-01-01");
		member.setMemZip("000-000");
		member.setMemAdd1("대전");
		member.setMemAdd2("오류");
		member.setMemHp("010");
		member.setMemMail("aa@");
		
		ServiceResult result = service.createMember(member);
		assertEquals(ServiceResult.PKDUPLICATED, result);
	}

	@Test
	public void testRetrieveMemberList() {
		PagingVO<MemberVO> pagingVO = new PagingVO<>();
		pagingVO.setCurrentPage(10);
		List<MemberVO> memberList = service.retrieveMemberList(pagingVO);
		assertNotNull(memberList);
	}

}
