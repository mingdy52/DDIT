package kr.or.ddit.member.dao;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import kr.or.ddit.vo.MemberVO;

public class MemberDAOTest {
	
	MemberDAO dao = new MemberDAOImpl();
	
	@Test
	public void testSelectMemberForAuth() {
		MemberVO inputData = new MemberVO();
		inputData.setMemId("a001");
		MemberVO member = dao.selectMemberForAuth(inputData);
		assertNotNull(member);
	}

	@Test(expected=RuntimeException.class)
	public void testInsertMemberThrow() {
		MemberVO member = new MemberVO();
		dao.insertMember(member);
	}
	
	@Test
	public void testInsertMember() {
		MemberVO member = new MemberVO();
		member.setMemId("a002");
		member.setMemPass("java");
		member.setMemName("신규");
		member.setMemBir("1999-01-01");
		member.setMemZip("000-000");
		member.setMemAdd1("대전");
		member.setMemAdd2("오류");
		member.setMemHp("010");
		member.setMemMail("aa@");
		
		int rowcnt = dao.insertMember(member);
		assertEquals(1, rowcnt);
		
	}

	@Test
	public void testSelectMemberList() {
		List<MemberVO> memberList = dao.selectMemberList();
		assertNotNull(memberList);
		assertNotEquals(0, memberList.size());
	}

}
