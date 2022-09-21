package kr.or.ddit.expert.dao;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.Test;

import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.expert.vo.EProdVO;

public class EProdDAOTest {
	@Inject
	ExpertDAO edao;
	
	@Test
	public void testSelectTotalRecord() {
		PagingVO<EProdVO> pagingVO = new PagingVO<>();
		int cnt = edao.selectTotalRecord(pagingVO);
		assertNotEquals(0, cnt);
	}

	@Test
	public void testSelectEprodList() {
		fail("Not yet implemented");
	}

}
