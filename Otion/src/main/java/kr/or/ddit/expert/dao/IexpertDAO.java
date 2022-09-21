package kr.or.ddit.expert.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.admin.vo.RevenueVO;
import kr.or.ddit.common.comcode.vo.ComCodeVO;
import kr.or.ddit.common.vo.ExpertPaymentVO;
import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.expert.vo.EProdVO;
import kr.or.ddit.expert.vo.EprodRefundVO;
import kr.or.ddit.expert.vo.ExpFormVO;
import kr.or.ddit.expert.vo.ExpertVO;
import kr.or.ddit.expert.vo.MyExpertVO;

/**
 * 
 * @author 박채록
 * @since 2022. 8. 9.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 9.      박채록       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * 
 */

@Mapper
public interface IexpertDAO {
	
	/**
	 * 전문가 자신의 상품을 리스트를 조회 
	 * @return
	 */
	public List<EProdVO> selectMyeprodList(PagingVO<EProdVO> pagingVO);
	/**
	 * 상품이 전체 수가 얼만있는지 조회
	 * @param pagingVO
	 * @return
	 */
	public int selectTotalprodRecord(PagingVO<EProdVO> pagingVO);
	/**
	 * 전문가가 상품을 추가 
	 * @param eProdVO
	 * @return
	 */
	public int insertEprod(EProdVO eprod);
	
	/**
	 * 전문가프로필수정
	 * @param expert
	 * @return
	 */
	public int updateProfile(ExpertVO expert);
	
	/**
	 * 전문가의 상품을 수정
	 * @param eprod
	 * @return
	 */
	public int updateProd(EProdVO eprod);
	
	/**
	 * 전문가의 상품을 삭제
	 * @param eprodNum
	 * @return
	 */
	public int deleteProd(EProdVO eprod);
	
	/**
	 * 전문가해지
	 * @param expert
	 * @return
	 */
	public int deleteExpert(ExpertVO expert);
	
	/**
	 * 프로필이미지업로드
	 * @param expert
	 * @return
	 */
	public int updateProfileImg(ExpertVO expert);
	
	
	/**
	 * 전문가가가 보는 구매상품리스트
	 * @param myExpert
	 * @return
	 */
	public List<MyExpertVO> selectRequestList(PagingVO<MyExpertVO> pagingVO);
	
	/**
	 * 요청수 
	 * @return
	 */
	public int selectTotalRequest(PagingVO<MyExpertVO> pagingVO);
	
	/**
	 * 전문가진행상태를 업데이트 
	 * @param myExpert
	 * @return
	 */
	public int updateProgress(MyExpertVO myExpert);
	
	/**
	 * 전문가수입리스트 
	 * @param revenue
	 * @return
	 */
	public List<ExpertPaymentVO> selectExpertRevenue(String expertId);
	
	/**
	 * 취소 사유 리스트 
	 * @param comCode
	 * @return
	 */
	public List<ComCodeVO> selectRefundReason();
	
	/**
	 * 전문가가 취소하여 환불 
	 * @return
	 */
	public int insertRefund(EprodRefundVO refund);
	
	/**
	 * 전문가의 아이디 가져옴 
	 * @return
	 */
	public String selectExpertId();
	
	/**
	 * 전문가 롤 삭제 
	 * @return
	 */
	public int deleteExpertRole(String expertId);
	/**
	 * 전문가탈퇴 상품 N 처리 
	 * @return
	 */
	public int updateDelExpPord(String expertId);
	
	public List<ExpertPaymentVO> selectMonthIncome(ExpertPaymentVO epay);
	
	public List<ExpertPaymentVO> selectRevenueForExcel(ExpertPaymentVO epay);
	
	public RevenueVO revenueEincomeList (RevenueVO revenue);
	
	
}
