package kr.or.ddit.prod.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

public class ProdDAOImplTest {
	ProdDAO dao = new ProdDAOImpl();

	@Test
	public void testInsertProd() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectProdList() {
		PagingVO<ProdVO> pagingVO = new PagingVO<>();
		List<ProdVO> prodList = dao.selectProdList(pagingVO);
		
		assertNotNull(prodList);
		assertNotEquals(0, prodList.size());
		System.out.println(prodList);
	}

	
	@Test
	public void testSelectProd() {
		ProdVO prod = dao.selectProd("P101000001");
		
		assertNotNull(prod);
		System.out.println(prod);
		System.out.println(prod.getBuyer());
		System.out.println(prod.getMemberSet());
	}

	@Test
	public void testUpdateProd() {
		fail("Not yet implemented");
	}

}
