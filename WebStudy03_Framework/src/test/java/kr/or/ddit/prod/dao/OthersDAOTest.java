package kr.or.ddit.prod.dao;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.vo.BuyerVO;

public class OthersDAOTest {

	OthersDAO dao = new OthersDAOImpl();

	@Test
	public void testSelectLprodList() {
		List<Map<String,Object>> lprodList = dao.selectLprodList();
		assertNotNull(lprodList);
		System.out.println(lprodList);
	}

	@Test
	public void testSelectBuyerList() {
		List<BuyerVO> buyerList = dao.selectBuyerList();
		assertNotNull(buyerList);
		System.out.println(buyerList);
	}

}
