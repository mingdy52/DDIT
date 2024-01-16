package kr.or.ddit.prod.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.BuyerVO;

/**
 * 상품 분류 선택 UI 와 거래처 선택 UI 를 동적 완성하기 위해 사용함.
 * @author 306-16
 *
 */
public interface OthersDAO {
	
	public List<Map<String, Object>> selectLprodList(); // vo 없이 맵 사용해서 lprod 가져오기
	public List<BuyerVO> selectBuyerList();
	
}

//비지니스를 위해 존해하는 UI가 아님. 비지니스 로직 필요 없음.