package kr.or.ddit.expert.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.admin.vo.RevenueVO;
import kr.or.ddit.common.comcode.vo.ComCodeVO;
import kr.or.ddit.common.vo.ExpertPaymentVO;
import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.expert.dao.ExpertDAO;
import kr.or.ddit.expert.dao.IexpertDAO;
import kr.or.ddit.expert.vo.EProdVO;
import kr.or.ddit.expert.vo.EprodRefundVO;
import kr.or.ddit.expert.vo.ExpertVO;
import kr.or.ddit.expert.vo.MyExpertVO;
import lombok.extern.slf4j.Slf4j;
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
@Service
@Slf4j
public class IexpertServiceImpl implements IexpertService{
	
	@Inject
	IexpertDAO iexpertDAO;
	
	
	@Inject
	WebApplicationContext rootcontext;
	
	private File imageFolder;
	
	@PostConstruct 
	public void init() {
		// 상품 이미지 저장 처리(MultipartFile)
		String imageFolderUrl = "/resources/profileImages";
		String iamgeFolderPath = rootcontext.getServletContext().getRealPath(imageFolderUrl);
		log.info("iamgeFolderPath :: {}",iamgeFolderPath);
		imageFolder = new File(iamgeFolderPath);
		if (!imageFolder.exists())
			imageFolder.mkdirs(); // 안만들어져있으면 폴더만들어라.
		log.info("주입전 {} 주입 후 {} 생성 및 확인", rootcontext, imageFolder);

	}
	/**
	 * 전문가 프로필이미지 
	 * @param expert
	 */
	private void processImage(ExpertVO expert) {//하나의 요청에대해서 처리되는 구조 위코드가 실행된 이후기때문에 폴더가 생성된 이후 
		MultipartFile imageFile = expert.getProfileImage();
		if (!imageFile.isEmpty()) {
			File profileImageFile = new File(imageFolder, expert.getProfileImg());
			try {
				imageFile.transferTo(profileImageFile);
			} catch (IllegalStateException | IOException e) {
				throw new RuntimeException(e); 

			} 
		}
	}
	
	/**
	 * 전문가의 상품 리스트를 조회 
	 */
	@Override
	public List<EProdVO> retrieveRegisteredEprodList(PagingVO<EProdVO> pagingVO) {
		pagingVO.setTotalRecord(iexpertDAO.selectTotalprodRecord(pagingVO));
		List<EProdVO> regigsteredList = iexpertDAO.selectMyeprodList(pagingVO);
		pagingVO.setDataList(regigsteredList);
		return regigsteredList;
	}
	/**
	 * 전문가가 상품을 등록 
	 */
	@Override
	public int resistEprod(EProdVO eprod) {
		int rowcnt = iexpertDAO.insertEprod(eprod);
		return rowcnt;
	}
	/**
	 * 전문가가 상품을 수정 
	 */
	@Override
	public int modifyEprod(EProdVO eprod) {
		int rowcnt = iexpertDAO.updateProd(eprod);
		return rowcnt;
	}
	/**
	 * 전문가가 상품을 삭제 
	 */
	@Override
	public int deleteEprod(EProdVO eprod) {
		int rowcnt = iexpertDAO.deleteProd(eprod);
		return rowcnt;
	}

	@Override
	public int modifyProfile(ExpertVO expert) {
//		processImage(expert);
		int rowcnt = iexpertDAO.updateProfile(expert);
		return rowcnt;
	}
	
	@Override
	public int deleteExpert(ExpertVO expert) {
		int rowcnt = iexpertDAO.deleteExpert(expert);
		return rowcnt;
	}
	
	@Override
	public int modifyProfileImg(ExpertVO expert) {
		processImage(expert);
		int rowcnt = iexpertDAO.updateProfileImg(expert);
		return rowcnt;
	}
	
	
	
	@Override
	public int modifyProcessState(MyExpertVO myExpert) {
		int rowcnt =iexpertDAO.updateProgress(myExpert);
		return rowcnt;
	}
	
	/**
	 * 본인의 상품을 구매한 상품list
	 */
	@Override
	public List<MyExpertVO> retrieveReqeustList(PagingVO<MyExpertVO> pagingVO) {
		pagingVO.setTotalRecord(iexpertDAO.selectTotalRequest(pagingVO));
		List<MyExpertVO> retrieveReqeustList = iexpertDAO.selectRequestList(pagingVO);
		pagingVO.setDataList(retrieveReqeustList);
		return retrieveReqeustList;

	}
	@Override
	public List<ExpertPaymentVO> retrieveExpertRevenue(String expertId) {
		List<ExpertPaymentVO> epayList = iexpertDAO.selectExpertRevenue(expertId);
		return epayList;
	}
	@Override
	public List<ComCodeVO> retrieveRefundReason() {
		List<ComCodeVO> refundReason = iexpertDAO.selectRefundReason();
		return refundReason;
	}
	@Override
	public int refundEprod(EprodRefundVO refund) {
		int rowcnt = iexpertDAO.insertRefund(refund);
		return rowcnt;
	}
	@Override
	public int deleteExpertRole(String expertId) {
		int rowcnt = iexpertDAO.deleteExpertRole(expertId);
		return rowcnt;
	}
	@Override
	public int modifyDelExpProd(String expertId) {
		int rowcnt = iexpertDAO.updateDelExpPord(expertId);
		
		return rowcnt;
	}
	@Override
	public List<ExpertPaymentVO> retrieveMonthinCome(ExpertPaymentVO epay) {
		List<ExpertPaymentVO> monthList = iexpertDAO.selectMonthIncome(epay);
		return monthList;
	}
	@Override
	public RevenueVO retrieveIncomeList(RevenueVO revenue) {
		RevenueVO incomeList = iexpertDAO.revenueEincomeList(revenue);
		return incomeList;
	}
	
	

}
