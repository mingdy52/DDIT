package kr.or.ddit.expert.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ddit.common.comcode.vo.ComCodeVO;
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
 * @since 2022. 8. 5.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                          수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 5.      박채록       최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Mapper
public interface ExpertDAO {
	/**
	 * 전문가 상품 페이징 처리를 위한 레코드 수 조회 
	 * @param pagingVO
	 * @return
	 */
	public int selectTotalRecord(PagingVO<EProdVO> pagingVO);

	/**
	 * 전문가 리스트 페이징 처리를 위한 레코드수조회
	 * @param pagingVO
	 * @return
	 */
	public int selectTotalexpertRecord(PagingVO<ExpertVO> ExpagingVO);
	/**
	 * 전문가상품목록을 조회
	 * @param pagingVO
	 * @return
	 */
	public List<EProdVO> selectEprodList(PagingVO<EProdVO> pagingVO);
	
	/**
	 * 전문가 목록을 조회
	 * @param epagingVO
	 * @return
	 */
	public List<ExpertVO> selectExpertList(PagingVO<ExpertVO> ExpagingVO);
	
	/**
	 * 전문가상품 상세조회
	 * @param eprodNum
	 * @return
	 */
	public EProdVO selectEprod(String eprodNum);
	
	/**
	 * 전문가상세조회
	 * @param expertId
	 * @return
	 */
	public ExpertVO selectExpert(String expertId);
	
	/**
	 * 회원이 전문가신청 
	 * @param expForm
	 * @return
	 */

	int insertExpertForm(ExpFormVO expForm);
	
	public String selectExpNum();
	
	/**
	 * 회원이 MyExpert 내역 추가
	 * @param myExpert
	 * @return
	 */
	int insertMyexpert(MyExpertVO myExpert);
	/**
	 * 회원이 전문가 상품을 구매 
	 * @param myExpert
	 * @return
	 */
	int insertExpertPay(ExpertPaymentVO expertPayment);
	/**
	 * 회원이 구매한 상품을 조회 
	 * @param myExpert
	 * @return
	 */
	
	String selectExpertpayNum();

	
	
	public int selectTotalMyexpert(PagingVO<MyExpertVO> pagingVO);
	/**
	 * 회원이 이용내역조회
	 * @param myExpert
	 * @return
	 */
	
	
	public List<MyExpertVO> selectMyexpert(PagingVO<MyExpertVO> pagingVO);
	
	/**
	 * 구매상품 상세내역
	 * @param myEprod
	 * @return
	 */
	public List<MyExpDetailVO> myExpDetailList(String myEprod);
	
	/**
	 * 회원의 요청정보 insert
	 * @param myExpDetail
	 * @return
	 */
	public int insertMyexpDetail (MyExpDetailVO myExpDetail);
	
	/**
	 * 리뷰작성가능한리스트
	 * @param myEprod
	 * @return
	 */
	public List<MyExpertVO> writableReviewList(String buyerId);
	
	/**
	 * 리뷰작성
	 * @param review
	 * @return
	 */
	public int insertMyreview(ReviewVO review);
	
	/**
	 * 후기를 작성한 여부 수정
	 * @param reviewYn
	 * @return
	 */
	public int updateReviewYn(String reviewYn);
	
	/**
	 * 작성한 리뷰 
	 * @param reviewWriter
	 * @return
	 */
	public List<ReviewVO> selectmyreviewList(String reviewWriter);
	
	/**
	 * 리뷰상세조회
	 * @param reviewNum
	 * @return
	 */
	public ReviewVO selectmyreview(String reviewNum);
	
	/**
	 * 해당리뷰 
	 * @param reviewNum
	 * @return
	 */
	public int updateMyreview(ReviewVO myreview);
	
	/**
	 * 회원의 위시리스트 목록 
	 * @return
	 */
	public List<ExpWishVO> selectmyWishList(PagingVO<ExpWishVO> pagingVO);

	/**
	 * 위시리스트 갯수
	 * @param pagingVO
	 * @return
	 */
	public int selectTotalWish(PagingVO<ExpWishVO> pagingVO);
	
	/**
	 * 위리리스트에 추가 
	 * @param wish
	 * @return
	 */
	
	public int insertWish(ExpWishVO wish);
	
	/**
	 * 위시리스트 삭제 
	 * @param wish
	 * @return
	 */
	public int deleteWish(ExpWishVO wish);
	
	/**
	 * 해당 상품에 위시리스트에있는지 확인 
	 * @param wish
	 * @return
	 */
	public ExpWishVO selectWishYN(ExpWishVO wish);
	/**
	 * 상품에대한 리뷰리스트 
	 * @param eprodNum
	 * @return
	 */
	public List<ReviewVO> selectEprodReviewList(String eprodNum);
	
	/**
	 * 리뷰삭제 
	 * @param reviewNum
	 * @return
	 */
	public int deleteReview(String reviewNum);
	
	/**
	 * eprod에 대한 expertId 
	 * @param myEprod
	 * @return
	 */
	public String getExpertId(String myEprod);
	
	public String getBuyerId(String myEprod);
	
	public String selectRefundWhy(String myEprod);
	
	public String getRVBuyerId(MyExpertVO myexpert);
	
	public List<ComCodeVO> selectReportList(); 
	
	public void insertReportExp(ReportVO report);
	
	public void updateRepExpCount(ExpertVO expert);
	
	public int selectTotalExpForm(PagingVO<ExpFormVO> pagingVO);
	
	public List<ExpFormVO> selectmyExpFormList(PagingVO<ExpFormVO> pagingVO);
	
	public String selectprodName(String myEprod);
}
