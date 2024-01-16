package kr.or.ddit.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import kr.or.ddit.admin.dao.MemberManagementDAO;
import kr.or.ddit.common.dao.NewsDAO;
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
import kr.or.ddit.expert.vo.ExpertVO;
import lombok.extern.slf4j.Slf4j;

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
@Slf4j
@Service
public class MemberManagementServiceImpl implements MemberManagementService {
	
	@Inject
	private MemberManagementDAO memberDAO;
	
	
	@Inject
	private NewsDAO newsDAO;
	
	@Override
	public void retrieveRoleMemberList(Map<String, Object> map, PagingVO<MemberVO> pagingVO) {
		pagingVO.setTotalRecord(memberDAO.selectTotalRoleRecord(map));
		List<MemberVO> roleMemberList = memberDAO.selectRoleMemberList(map);
		pagingVO.setDataList(roleMemberList);
	}
	
	@Override
	public void retrieveBlackList(Map<String, Object> map, PagingVO<MemberVO> pagingVO) {
		pagingVO.setTotalRecord(memberDAO.selectTotalBlackRecord(map));
		List<MemberVO> blackList = memberDAO.selectBlackList(map);
		pagingVO.setDataList(blackList);
	}
	
	@Override
	public MemberVO retrieveMember(String memId) {
		MemberVO memberVO = memberDAO.selectMember(memId);
		if(memberVO == null) {
			throw new RuntimeException(String.format("%s 아이디를 가진 회원 없음.", memId));
		}
		log.info("memberVO: {}", memberVO.getRoleList());
		return memberVO;
	}

	
	@Override
	public void retrieveReportList(Map<String, Object> map, PagingVO<ReportVO> repPagingVO) {
		repPagingVO.setTotalRecord(memberDAO.selectTotalReportRecord(map));
		List<ReportVO> reportList = memberDAO.selectReportList(map);
		repPagingVO.setDataList(reportList);
	}


	@Override
	public void retrieveWorkPayList(Map<String, Object> map, PagingVO<WorkPaymentVO> workPagingVO) {
		workPagingVO.setTotalRecord(memberDAO.selectTotalWorkPayRecord(map));
		List<WorkPaymentVO> workList = memberDAO.selectWorkPayList(map);
		workPagingVO.setDataList(workList);
	}


	@Override
	public void retrieveExpertPayList(Map<String, Object> map, PagingVO<ExpertPaymentVO> expPagingVO) {
		expPagingVO.setTotalRecord(memberDAO.selectTotalExpertPayRecord(map));
		List<ExpertPaymentVO> expList = memberDAO.selectExpertPayList(map);
		expPagingVO.setDataList(expList);
	}


	@Override
	public void retrieveWorkRefundList(Map<String, Object> map, PagingVO<WorkRefundVO> workRefPagingVO) {
		workRefPagingVO.setTotalRecord(memberDAO.selectTotalWorkRefundRecord(map));
		List<WorkRefundVO> workRefList = memberDAO.selectWorkRefundList(map);
		workRefPagingVO.setDataList(workRefList);
	}


	@Override
	public void retrieveExpertRefundList(Map<String, Object> map, PagingVO<ExpertRefundVO> expRefPagingVO) {
		expRefPagingVO.setTotalRecord(memberDAO.selectTotalExpertPayRecord(map));
		List<ExpertRefundVO> expRefList = memberDAO.selectExpertRefundList(map);
		expRefPagingVO.setDataList(expRefList);
	}



	@Override
	public void retrieveExpertFormList(Map<String, Object> map, PagingVO<ExpFormVO> pagingVO) {
		pagingVO.setTotalRecord(memberDAO.selectTotalExpertFormRecord(map));
		List<ExpFormVO> expFormList = memberDAO.selectExpertFormList(map);
		pagingVO.setDataList(expFormList);
	}



	@Override
	public ExpFormVO retrieveExpertForm(String exFormNum) {
		ExpFormVO expFormVO = memberDAO.selectExpertForm(exFormNum);
		if(expFormVO == null) {
			throw new RuntimeException(String.format("%s 번 신청글 없음.", exFormNum));
		}
		
		return expFormVO;
	}

	@Override
	public ServiceResult approveResultExpert(NewsVO newsVO, String exFormNum) {
		
		String apprCode = null;
		ServiceResult result = null;
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("exFormNum", exFormNum);
		ExpFormVO expForm = memberDAO.selectExpertForm(exFormNum);
		
		if(StringUtils.equals(newsVO.getResult(), "approve")) {		
			// 1. approve 일 때
			// 	role assingment 에 전문가 역할 인서트
			int assingCnt = memberDAO.insertMemberRoleExpert(newsVO.getReceiverId());
			
			if(assingCnt > 0) {
				// 신청폼 승인 상태 업데이트
				apprCode = "A03";
				map.put("apprCode", apprCode);
				
				ExpertVO expert = new ExpertVO();
				expert.setExpertId(expForm.getApplicantId());
				expert.setProfileImg("/Otion/resources/images/noImg.png");
				expert.setPresentation(expForm.getExFormContent());
				expert.setAssume("이력을 작성해주세요.");
				
				int updateMemberExpert = memberDAO.updateMemberExpert(map);
				int insertExpert = memberDAO.insertExpert(expert);
				
				if(updateMemberExpert > 0 && insertExpert > 0) {
					// 전문가 승인되면 알림 보내주기
					newsDAO.insertNews(newsVO);
					result = ServiceResult.OK;
				}
			} else {
				result = ServiceResult.FAIL;
			}
			
		} else if(StringUtils.equals(newsVO.getResult(), "hold")) {
			// 2. hold 일 때
			// 신청폼 승인 상태 업데이트
			apprCode = "A02";
			map.put("apprCode", apprCode);
			int holdExpert = memberDAO.updateMemberExpert(map);
			
			if(holdExpert > 0) {
				//  알림 인서트
				newsDAO.insertNews(newsVO);
				result = ServiceResult.OK;
			} else {
				result = ServiceResult.FAIL;
			}
			
		} else if(StringUtils.equals(newsVO.getResult(), "refuse")) {
			// 3. refuse 일 때
			// 신청폼 승인 상태 업데이트
			apprCode = "A04";
			map.put("apprCode", apprCode);
			int refuseExpert = memberDAO.updateMemberExpert(map);
			//  알림 인서트
			
			if(refuseExpert > 0) {
				//  알림 인서트
				newsDAO.insertNews(newsVO);
				result = ServiceResult.OK;
			} else {
				result = ServiceResult.FAIL;
			}
			
		}
		
		return result;
	}
}
