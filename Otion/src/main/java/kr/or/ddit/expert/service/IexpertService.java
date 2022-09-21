package kr.or.ddit.expert.service;

import java.util.List;

import kr.or.ddit.admin.vo.RevenueVO;
import kr.or.ddit.common.comcode.vo.ComCodeVO;
import kr.or.ddit.common.vo.ExpertPaymentVO;
import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.expert.vo.EProdVO;
import kr.or.ddit.expert.vo.EprodRefundVO;
import kr.or.ddit.expert.vo.ExpertVO;
import kr.or.ddit.expert.vo.MyExpertVO;

public interface IexpertService {
	
	/**
	 * 전문가가 등록한 상품 목록 조회
	 * @param pagingVO
	 * @return
	 */
	public List<EProdVO> retrieveRegisteredEprodList(PagingVO<EProdVO> pagingVO);
	
	/**
	 * 전문가가 상품을 등록
	 * @param eprod
	 * @return
	 */
	public int resistEprod(EProdVO eprod);
	/**
	 * 전문가의 상품을 수정
	 * @param eprod
	 * @return
	 */
	public int modifyEprod(EProdVO eprod);
	
	/**
	 * 전문가의 상품을 삭제
	 * @param eprodNum
	 * @return
	 */
	public int deleteEprod(EProdVO eprod);
	
	
	/**
	 * 전문가의 프로필을 수정
	 * @param expert
	 * @return
	 */
	public int modifyProfile(ExpertVO expert);
	
	/**
	 * 전문가탈퇴
	 * @param expert
	 * @return
	 */
	public int deleteExpert(ExpertVO expert);
	
	/**
	 * 프로필이미지 수정
	 * @param expert
	 * @return
	 */
	public int modifyProfileImg(ExpertVO expert);
	
	/**
	 * 마이익스퍼트 상품 진행상황 수정 
	 * @param processCode
	 * @return
	 */
	public int modifyProcessState(MyExpertVO myExpert);
	
	/**
	 * 요청내역 
	 * @param myExpert
	 * @return
	 */
	public List<MyExpertVO> retrieveReqeustList(PagingVO<MyExpertVO> myExpert);
	
	public List<ExpertPaymentVO> retrieveExpertRevenue(String expertId);
	
	public List<ComCodeVO> retrieveRefundReason();
	
	public int refundEprod(EprodRefundVO refund);
	
	public int deleteExpertRole(String expertId);
	
	public int modifyDelExpProd(String expertId);
	/**
	 * 전문가 소득 월별 조회.
	 * @return
	 */
	public List<ExpertPaymentVO> retrieveMonthinCome(ExpertPaymentVO epay);
	
	public RevenueVO retrieveIncomeList(RevenueVO revenue);
}
