package kr.or.ddit.expert.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.common.attach.dao.AttatchDAO;
import kr.or.ddit.common.vo.AttatchVO;
import kr.or.ddit.common.vo.ExpertPaymentVO;
import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.common.vo.ReportVO;
import kr.or.ddit.expert.dao.ExpertDAO;
import kr.or.ddit.expert.vo.EProdVO;
import kr.or.ddit.expert.vo.EprodRefundVO;
import kr.or.ddit.expert.vo.ExpFormVO;
import kr.or.ddit.expert.vo.ExpWishVO;
import kr.or.ddit.expert.vo.ExpertVO;
import kr.or.ddit.expert.vo.MyExpDetailVO;
import kr.or.ddit.expert.vo.MyExpertVO;
import kr.or.ddit.expert.vo.ReviewVO;
import lombok.extern.slf4j.Slf4j;
import sun.util.logging.resources.logging;

@Slf4j
@Service
public class ExpertServiceImpl implements ExpertService {
	@Inject
	ExpertDAO expertDAO;
	
	@Inject
	AttatchDAO attatchDAO;

	@Override
	public List<EProdVO> retrieveEProdList(PagingVO<EProdVO> pagingVO) {
		pagingVO.setTotalRecord(expertDAO.selectTotalRecord(pagingVO));
		List<EProdVO> eprodList = expertDAO.selectEprodList(pagingVO);
		pagingVO.setDataList(eprodList);
		return eprodList;
	}
	
	@Override
	public List<ExpertVO> retrieveExpertList(PagingVO<ExpertVO> ExpagingVO) {
		ExpagingVO.setTotalRecord(expertDAO.selectTotalexpertRecord(ExpagingVO));
		List<ExpertVO> expertList = expertDAO.selectExpertList(ExpagingVO);
		ExpagingVO.setDataList(expertList);
		return expertList;
	}
	

	@Override
	public int resistExpert(ExpFormVO expForm) {
		String expNum = expertDAO.selectExpNum();
		log.info("expNum=========== {}",expNum);
		
		// 첨부파일삽입
		AttatchVO attatch = new AttatchVO(expForm.getExFormFiles());
		//신청서 번호 넣기  --여기가 비어있음
		attatch.setAttatchPlace(expNum);
		//신청자 아이디 넣기 
		attatch.setUploaderId(expForm.getApplicantId());
		
		//해당첨부파일 insert
		if(attatch!=null) {
			attatchDAO.insertAttatch(attatch);
		}
		try {
			attatch.saveTo(new File("D:/finalupload"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//업로드할때 상요한 pk 가져오기
		expForm.setExFormNum(expNum);
		expForm.setExFormAssume(attatch.getAttatchNum());
		//insert expertform
		
		int rowcnt = expertDAO.insertExpertForm(expForm);

		return rowcnt;
	}

	@Override
	public EProdVO retrieveEprod(String eprodNum) {
		EProdVO eprod = expertDAO.selectEprod(eprodNum);
		return eprod;
	}

	@Override
	public ExpertVO retrieveExpert(String expertId) {
		ExpertVO expert = expertDAO.selectExpert(expertId);
		return expert;
	}

	@Override
	public int purchaseEprod(ExpertPaymentVO expertPayment) {
		String eprodNum = expertDAO.selectExpertpayNum();
		log.info("***********eprodNum *************{}",eprodNum);
		expertPayment.setMyEprod(eprodNum);
		int rowcnt = expertDAO.insertExpertPay(expertPayment);
		return rowcnt;
	}

	@Override
	public int insertMyexpert(MyExpertVO myExpert) {
		int rowcnt = expertDAO.insertMyexpert(myExpert);
		return rowcnt;
	}

	@Override
	public List<MyExpertVO> retrieveMyexpert(PagingVO<MyExpertVO> pagingVO) {
		pagingVO.setTotalRecord(expertDAO.selectTotalMyexpert(pagingVO));
		List<MyExpertVO> myexpertList = expertDAO.selectMyexpert(pagingVO);
		pagingVO.setDataList(myexpertList);
		return myexpertList;
	}

	@Override
	public List<MyExpDetailVO> retrieveDetailMyexp(String myEprod) {
		List<MyExpDetailVO> myExpDetailList =  expertDAO.myExpDetailList(myEprod);
		
		return myExpDetailList;
	}

	@Override
	public int createMyexpDetail(MyExpDetailVO myExpDetail) {
		int rowcnt = expertDAO.insertMyexpDetail(myExpDetail);
		return rowcnt;
	}

	public List<MyExpertVO> retrieveWritableRieview(String buyerId){
		List<MyExpertVO> reviewList = expertDAO.writableReviewList(buyerId);
		return reviewList;
	}

	@Override
	public int writeMyreview(ReviewVO review) {
		int rowcnt = expertDAO.insertMyreview(review);
		expertDAO.updateReviewYn(review.getMyEprod());
		return rowcnt;
	}

	@Override
	public List<ReviewVO> retrieveMyreviewList(String reviewWriter) {
		List<ReviewVO> reviewList = expertDAO.selectmyreviewList(reviewWriter);
		return reviewList;
	}

	@Override
	public ReviewVO retrieveMyreivew(String reviewNum) {
		ReviewVO myreview = expertDAO.selectmyreview(reviewNum);
		return myreview;
	}

	@Override
	public int modifyMyreview(ReviewVO myreview) {
		int rowcnt = expertDAO.updateMyreview(myreview);
		return rowcnt;
	}

	@Override
	public int createWish(ExpWishVO wish) {
		int rowcnt = expertDAO.insertWish(wish);
		return rowcnt;
	}

	@Override
	public boolean WishYn(ExpWishVO wish) {
		
		boolean wishchk = false;
		ExpWishVO mywish =  expertDAO.selectWishYN(wish);
		if(mywish!=null) {
			wishchk =true;
		}else {
			wishchk = false;
		}
		return wishchk;
	}

	@Override
	public int deleteWish(ExpWishVO wish) {
		int rowcnt = expertDAO.deleteWish(wish);
		return rowcnt;
	}

	@Override
	public List<ExpWishVO> retrieveMyWishList(PagingVO<ExpWishVO> pagingVO) {
		pagingVO.setTotalRecord(expertDAO.selectTotalWish(pagingVO));
		List<ExpWishVO> mywishList = expertDAO.selectmyWishList(pagingVO);
		pagingVO.setDataList(mywishList);
		return mywishList;
	}

	@Override
	public List<ReviewVO> retrieveEprodRVList(String eprodNum) {
		List<ReviewVO> eprodRVList = expertDAO.selectEprodReviewList(eprodNum);
		return eprodRVList;
	}

	@Override
	public int deleteReview(String reviewNum) {
		int rowcnt = expertDAO.deleteReview(reviewNum);
		return rowcnt;
	}

	@Override
	public String getExpertId(String myEprod) {
		String expertId = expertDAO.getExpertId(myEprod);
		return expertId;
	}

	@Override
	public String getBuyerId(String myEprod) {
		String buyerId = expertDAO.getBuyerId(myEprod);
		return buyerId;
	}

	@Override
	public String retrieveRefundWhy(String myEprod) {
		String refundWhy =  expertDAO.selectRefundWhy(myEprod);
		
		return refundWhy;
	}

	@Override
	public String retrieveRVBuyerId(MyExpertVO myexpert) {
		String RVBuyerId = expertDAO.getRVBuyerId(myexpert);
		
		return RVBuyerId;
	}

	@Override
	public void createReportExpert(ReportVO report) {
		expertDAO.insertReportExp(report);
		ExpertVO expert = expertDAO.selectExpert(report.getTargetId());
		int originAccum = expert.getAccumRep();
		int updateAccum = originAccum + 1;
		expert.setAccumRep(updateAccum);
		expertDAO.updateRepExpCount(expert);
		
	}

	@Override
	public List<ExpFormVO> retrieveExpFormList(PagingVO<ExpFormVO> pagingVO) {
		pagingVO.setTotalRecord(expertDAO.selectTotalExpForm(pagingVO));
		List<ExpFormVO> myexpertFormList = expertDAO.selectmyExpFormList(pagingVO);
		pagingVO.setDataList(myexpertFormList);
		return myexpertFormList;
	}

	@Override
	public String selectEprodName(String myEprod) {
		String myeprod = expertDAO.selectprodName(myEprod);
		return myeprod;
	}
	
	


	
	


}
