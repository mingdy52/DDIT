package kr.or.ddit.admin.service;

import java.util.Map;

import kr.or.ddit.common.vo.ExpertPaymentVO;
import kr.or.ddit.common.vo.ExpertRefundVO;
import kr.or.ddit.common.vo.MemberVO;
import kr.or.ddit.common.vo.NewsVO;
import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.common.vo.ReportVO;
import kr.or.ddit.common.vo.WorkPaymentVO;
import kr.or.ddit.common.vo.WorkRefundVO;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.expert.vo.ExpFormVO;

/**
 * 
 * @author 심민경
 * @since 2022. 8. 16.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 16. 심민경               최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
public interface MemberManagementService {
	
	/**
	 * 회원 목록 조회
	 * 
	 */
	public void retrieveRoleMemberList(Map<String, Object> map, PagingVO<MemberVO> pagingVO);
	
	/**
	 * 블랙 리스트 조회
	 * @param map
	 * @param pagingVO
	 */
	public void retrieveBlackList(Map<String, Object> map, PagingVO<MemberVO> pagingVO);
	/**
	 * 회원 상세 조회
	 * @param memId 조회할 회원 아이디
	 * @return
	 */
	public MemberVO retrieveMember(String memId);
	
	/**
	 * 회원 신고 목록 조회
	 * 
	 */
	public void retrieveReportList(Map<String, Object> map, PagingVO<ReportVO> repPagingVO);
	
	/**
	 * 회원 협업툴 결제 목록 조회
	 * 
	 */
	public void retrieveWorkPayList(Map<String, Object> map, PagingVO<WorkPaymentVO> workPagingVO);
	
	/**
	 * 회원 전문가 상품 결제 목록 조회
	 * 
	 */
	public void retrieveExpertPayList(Map<String, Object> map, PagingVO<ExpertPaymentVO> expPagingVO);	
	
	/**
	 * 회원 협업툴 환불 목록 조회
	 * 
	 */
	public void retrieveWorkRefundList(Map<String, Object> map, PagingVO<WorkRefundVO> workRefPagingVO);
	
	/**
	 * 회원 전문가 상품 환불 목록 조회
	 * 
	 */
	public void retrieveExpertRefundList(Map<String, Object> map, PagingVO<ExpertRefundVO> expRefPagingVO);
	
	/**
	 * 전문가 신청서 목록 조회
	 * 
	 */
	public void retrieveExpertFormList(Map<String, Object> map, PagingVO<ExpFormVO> pagingVO);
	
	/**
	 * 전문가 신청서 상세 조회
	 * @param exFormNum 조회할 전문가 신청서 번호
	 * @return
	 */
	public ExpFormVO retrieveExpertForm(String exFormNum);
	
	/**
	 * 전문가 신청 승인 결과 반영
	 * @param newsVO 승인 결과에 따른 반영 정보
	 * @param result 승인 결과
	 * @return 
	 */
	public ServiceResult approveResultExpert(NewsVO newsVO, String result);

	

}
