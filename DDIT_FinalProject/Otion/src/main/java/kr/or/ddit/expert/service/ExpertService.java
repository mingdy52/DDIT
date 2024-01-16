package kr.or.ddit.expert.service;

import java.util.List;

import kr.or.ddit.common.vo.ExpertPaymentVO;
import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.common.vo.ReportVO;
import kr.or.ddit.expert.vo.EProdVO;
import kr.or.ddit.expert.vo.EprodRefundVO;
import kr.or.ddit.expert.vo.ExpFormVO;
import kr.or.ddit.expert.vo.ExpWishVO;
import kr.or.ddit.expert.vo.ExpertVO;
import kr.or.ddit.expert.vo.MyExpDetailVO;
import kr.or.ddit.expert.vo.MyExpertVO;
import kr.or.ddit.expert.vo.ReviewVO;
/**
 * 
 * @author 박채록
 * @since 2022. 8. 17.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 17.      박채록       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
public interface ExpertService {
	/**
	 * 전문가상품목록 조회
	 * @return
	 */
	public List<EProdVO> retrieveEProdList(PagingVO<EProdVO> pagingVO);
	
	/**
	 * 전문가 목록 조회
	 * @param ExpagingVO
	 * @return
	 */
	public List<ExpertVO> retrieveExpertList(PagingVO<ExpertVO> ExpagingVO);
	
	/**
	 * 전문가상품상세조회
	 * @param eprod
	 * @return
	 */
	public EProdVO retrieveEprod(String eprodNum);
	/**
	 * 전문가상세조회
	 * @param expertId
	 * @return
	 */
	public ExpertVO retrieveExpert(String expertId);
	/**
	 * 회원이 전문가신청 
	 * @return
	 */
	public int resistExpert(ExpFormVO expForm);
	
	/**
	 * 회원이 전문가의 상품을 구매
	 * @param myExpert
	 * @return
	 */
	public int purchaseEprod(ExpertPaymentVO expertPayment);
	
	/**
	 * myexpert에 구매내역추가
	 * @param myExpert
	 * @return
	 */
	public int insertMyexpert(MyExpertVO myExpert);
	
	/**
	 * 회원이 구매한 상품을 조회 
	 * @param myExpert
	 * @return
	 */
	public  List<MyExpertVO> retrieveMyexpert(PagingVO<MyExpertVO> pagingVO);
	
	/**
	 * 회원이 이용상세내역 
	 * @param myEprod
	 * @return
	 */
	public List<MyExpDetailVO> retrieveDetailMyexp(String myEprod);
	
	/**
	 * 회원이 요청글 작성
	 * @param myExpDetail
	 * @return
	 */
	public int createMyexpDetail(MyExpDetailVO myExpDetail);
	
	/**
	 * 리뷰작성가능한 마이익스퍼트내역
	 * @param buyerId
	 * @return
	 */
	public List<MyExpertVO> retrieveWritableRieview(String buyerId);
	
	/**
	 * 회원이 리뷰를 작성
	 * @param review
	 * @return
	 */
	public int writeMyreview(ReviewVO review);
	
	/**
	 * 내가 작성한 리뷰 리스트 
	 * @param reviewWriter
	 * @return
	 */
	public List<ReviewVO> retrieveMyreviewList(String reviewWriter);
	
	/**
	 * 리뷰상세조회
	 * @param reviewNum
	 * @return
	 */
	public ReviewVO retrieveMyreivew(String reviewNum);
	
	/**
	 * 리뷰수정
	 * @param reviewNum
	 * @return
	 */
	public int modifyMyreview(ReviewVO myreview);
	
	
	/**
	 * 회원이 가지고있는 위시리스트 조회 
	 * @param wish
	 * @return
	 */
	public List<ExpWishVO> retrieveMyWishList(PagingVO<ExpWishVO> pagingVO);
	/**
	 * 위시리스트에 추가 
	 * @param wish
	 * @return
	 */
	public int createWish(ExpWishVO wish);
	
	/**
	 * 위시리스트에서 삭제
	 * @param wish
	 * @return
	 */
	
	public int deleteWish(ExpWishVO wish);
	/**
	 * 해당상품이 위시리스트에있는지확인
	 * @param wish
	 * @return
	 */
	public boolean WishYn(ExpWishVO wish);
	/**
	 * 상품에대한 리뷰리스트 
	 * @param eprodNum
	 * @return
	 */
	public List<ReviewVO> retrieveEprodRVList(String eprodNum);
	
	/**
	 * 리뷰삭제 
	 * @param reviewNum
	 * @return
	 */
	public int deleteReview(String reviewNum);
	
	public String getExpertId(String myEprod);
	
	public String getBuyerId(String myEprod);
	
	public String retrieveRefundWhy(String myEprod);
	
	public String retrieveRVBuyerId(MyExpertVO myexpert);
	
	/**
	 * 전문가신고
	 * @param report
	 */
	public void createReportExpert(ReportVO report);
	
	public List<ExpFormVO> retrieveExpFormList(PagingVO<ExpFormVO> pagingVO);
	
	public String selectEprodName(String myEprod);

}
