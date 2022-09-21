package kr.or.ddit.admin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.admin.vo.RevenueVO;

@Mapper
public interface RevenueMangementDAO {
	
	/**
	 * 전문가 수수료 매출 조회
	 * @param map 조회할 매출 기간
	 * @return 조회 기간의 전문가 수수료 매출
	 */
	public int selectEprodRevenue(Map<String,Object> map);
	
	/**
	 * 협업툴 매출 조회
	 * @param map 조회할 매출 기간
	 * @return 조회 기간의 협업툴 매출
	 */
	public int selectWorkRevenue(Map<String,Object> map);
	
	/**
	 * 월간 매출 정보
	 * @param map year
	 * @return
	 */
	public RevenueVO selectMonthRevenue(Map<String,Object> map);
	
	/**
	 * 전문가 수수료 기간별 매출
	 * @param map 조회할 기간 정보
	 * @return
	 */
	public List<RevenueVO> selectExpertTermRevenue(Map<String, String> map);
	
	/**
	 * 협업툴 기간별 매출
	 * @param map 조회할 기간 정보
	 * @return
	 */
	public List<RevenueVO> selectProjcetTermRevenue(Map<String, String> map);
	
}

