package kr.or.ddit.buyer.service;

import java.util.List;

import kr.or.ddit.buyer.vo.BuyerVO;
import kr.or.ddit.common.vo.PagingVO;

public interface BuyerService {
	
	public List<BuyerVO> findAll(PagingVO<BuyerVO> pagingVO);
	
	public BuyerVO findBuyer(String buyerId);
	
	public void createBuyer();
	
	public void modifyBuyer();
	
	public void removeBuyer();

	

}
