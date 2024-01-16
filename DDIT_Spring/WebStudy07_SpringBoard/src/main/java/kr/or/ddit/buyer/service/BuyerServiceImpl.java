package kr.or.ddit.buyer.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.buyer.dao.BuyerDAO;
import kr.or.ddit.buyer.vo.BuyerVO;
import kr.or.ddit.common.vo.PagingVO;

@Service
public class BuyerServiceImpl implements BuyerService {
	
	@Inject
	BuyerDAO buyerDAO;
	
	@Override
	public List<BuyerVO> findAll(PagingVO<BuyerVO> pagingVO) {
		pagingVO.setTotalRecord(buyerDAO.totalRecord());
		List<BuyerVO> buyerList = buyerDAO.selectBuyerList(pagingVO);
		return buyerList;
	}


	@Override
	public BuyerVO findBuyer(String buyerId) {
		BuyerVO buyer = buyerDAO.selectBuyer(buyerId);
		if(buyer == null) {
			throw new RuntimeException(String.format("$s 거래처 없음", buyerId));
		}
		return buyer;
	}

	@Override
	public void createBuyer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyBuyer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeBuyer() {
		// TODO Auto-generated method stub
		
	}




}
