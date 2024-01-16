package kr.or.ddit.buyer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.buyer.vo.BuyerVO;
import kr.or.ddit.common.vo.PagingVO;

@Mapper
public interface BuyerDAO {
	
	public List<BuyerVO> selectBuyerList(PagingVO<BuyerVO> pagingVO);
	
	public int totalRecord();
	
	public BuyerVO selectBuyer(String buyerId);
	
	public void insertBuyer();
	
	public void updateBuyer();
	
	public void deleteBuyer();
	
	
}
