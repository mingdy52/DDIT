package kr.or.ddit.prod.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.vo.BuyerVO;

/**
 * 상품 분류 선택 UI 와 거래처 선택 UI 를 동적 완성하기 위해 사용함.
 *
 */
@Mapper
public interface OthersDAO {
	public List<Map<String, Object>> selectLprodList();
	public List<BuyerVO> selectBuyerList();
}
