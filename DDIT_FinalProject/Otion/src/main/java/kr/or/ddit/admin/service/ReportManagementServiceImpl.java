package kr.or.ddit.admin.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.admin.dao.ReportManagementDAO;
import kr.or.ddit.common.dao.NewsDAO;
import kr.or.ddit.common.login.vo.MemberPrincipal;
import kr.or.ddit.common.vo.MemberVO;
import kr.or.ddit.common.vo.NewsVO;
import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.common.vo.ReportVO;
import kr.or.ddit.community.coop.vo.CooBoardVO;
import kr.or.ddit.community.free.vo.FreeBoardVO;
import kr.or.ddit.enumpkg.ComCode;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.expert.dao.IexpertDAO;
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
public class ReportManagementServiceImpl implements ReportManagementService {
	
	@Inject
	private ReportManagementDAO repDAO;
	@Inject
	IexpertDAO iexpertDAO;
	@Inject
	private NewsDAO newsDAO;
	
	@Override
	public void retrieveMemberReportList(PagingVO<MemberVO> pagingVO) {
		pagingVO.setTotalRecord(repDAO.selectTotalMemRecord());
		List<MemberVO> memReportList = repDAO.selectMemberReportList(pagingVO);
		pagingVO.setDataList(memReportList);
	}
	
	@Override
	public void retrieveExpertReportList(PagingVO<MemberVO> pagingVO) {
		pagingVO.setTotalRecord(repDAO.selectTotalExpRecord());
		List<MemberVO> expReportList = repDAO.selectExpertReportList(pagingVO);
		pagingVO.setDataList(expReportList);
		
	}

	@Override
	public void retrieveCooBoardReportList(PagingVO<CooBoardVO> pagingVO) {
		pagingVO.setTotalRecord(repDAO.selectTotalCooRecord());
		List<CooBoardVO> cooReportList = repDAO.selectCooBoardReportList(pagingVO);
		pagingVO.setDataList(cooReportList);
	}

	@Override
	public void retrieveFreeBoardReportList(PagingVO<FreeBoardVO> pagingVO) {
		pagingVO.setTotalRecord(repDAO.selectTotalFreeRecord());
		List<FreeBoardVO> freeReportList = repDAO.selectFreeBoardReportList(pagingVO);
		pagingVO.setDataList(freeReportList);
	}

	@Override
	public void retrieveReport(Map<String, Object> map, PagingVO<ReportVO> pagingVO) {
		List<ReportVO> reportList = repDAO.selectReport(map);
		log.info("reportList.size() :{}", reportList.size());
		pagingVO.setTotalRecord(reportList.size());
		
		pagingVO.setDataList(reportList);
	}
	
	@Transactional
	@Override
	public ServiceResult reportProcess(Map<String, String> map, String targetClass) {
		int cnt = 0;
		
		ServiceResult result = null;
		NewsVO newsVO = new NewsVO();
		String adminId = ((MemberPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getRealMember().getMemId();
		if(StringUtils.equals(targetClass, "member")) {
			cnt = repDAO.insertBlackMember(map);
			
		} else if(StringUtils.equals(targetClass, "expert")) {
			cnt = repDAO.updateExpDel(map);
			repDAO.deleteExpertRole(map);
			
			newsVO = setNews(map.get("target"), ComCode.NEW03.toString(), adminId);
			
		} else if(StringUtils.equals(targetClass, "coo")) {
			cnt = repDAO.updateCooBlind(map);
			newsVO = setNews(repDAO.selectCooWriterId(map), ComCode.NEW04.toString(), adminId);
			
		} else if(StringUtils.equals(targetClass, "free")) {
			cnt = repDAO.updateFreeBlind(map);
			newsVO = setNews(repDAO.selectFreeWriterId(map), ComCode.NEW04.toString(), adminId);
			
		}
		
		
		if(cnt > 0) {
			result = ServiceResult.OK;
			if(!StringUtils.equals(targetClass, "member")) {
				newsDAO.insertNews(newsVO);
			}
		} else {
			result = ServiceResult.FAIL;
		}
		
		return result;
	}
	
	public NewsVO setNews(String receiverId, String newsMsgCode, String newsId) {
		NewsVO newsVO = new NewsVO();
		newsVO.setReceiverId(receiverId);
		newsVO.setNewsMsgCode(newsMsgCode);
		newsVO.setNewsId(newsId);
		return newsVO;
	}
}
