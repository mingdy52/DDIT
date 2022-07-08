package kr.or.ddit.member.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

/**
 * TDD(Test Driven Development : 테스트 주도형 개발) 개발 방법론
 *
 * EDD(Event Driven Development : 이벤트 주도형 개발)
 *
 */
public class MemberServiceTest {
	
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
