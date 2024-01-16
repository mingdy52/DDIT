package kr.or.ddit.admin.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.admin.dao.MemberManagementDAO;
import kr.or.ddit.admin.service.MemberManagementService;
import kr.or.ddit.common.CheckMember;
import kr.or.ddit.common.vo.ExpertPaymentVO;
import kr.or.ddit.common.vo.ExpertRefundVO;
import kr.or.ddit.common.vo.MemberVO;
import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.common.vo.ReportVO;
import kr.or.ddit.common.vo.WorkPaymentVO;
import kr.or.ddit.common.vo.WorkRefundVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author 심민경
 * @since 2022. 8. 5.
 * @version 1.0
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 5.  심민경               최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */

@Slf4j
@Controller
@RequestMapping("/admin/member/{who}")
public class MemberViewController {

	@Inject
	MemberManagementService service;
	@Inject
	private MemberManagementDAO memberDAO;
	@Inject
	private CheckMember check;
	
	// 회원 상세 정보
	@GetMapping
	public String memInfoView(
			@PathVariable String who
				, Model model){
		
		String viewName = null;
		MemberVO checkAdmin = check.checkAdmin();
		
		if(checkAdmin != null) {
			MemberVO memberVO = service.retrieveMember(who);
        	model.addAttribute("memberVO", memberVO);
			
			viewName = "admin/memberView";
		} else {
			viewName = "redirect:/login";
		}
		
		return viewName;
		
	}
	
	// 회원 신고정보
	@ResponseBody
	@RequestMapping(value="report", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public PagingVO<ReportVO> repView(@PathVariable String who
							, @RequestParam(name="repPage", required=false, defaultValue="1") int repPage
							, HttpServletResponse resp
							) throws IOException {
		
		PagingVO<ReportVO> repPagingVO = null;
		MemberVO checkAdmin = check.checkAdmin();
		
		if(checkAdmin != null) {
			repPagingVO = new PagingVO<>(8, 5);
        	repPagingVO.setCurrentPage(repPage);
        	Map<String, Object> repMap = commonMapLogic(who, repPagingVO);
        	service.retrieveReportList(repMap, repPagingVO);
		} else {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
		
		return  repPagingVO;
		
	}
	
	// 회원 결제 정보
	@ResponseBody
	@RequestMapping(value="payment", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Map<String,Object> payView(
			@PathVariable String who
			, @RequestParam(name="expPayPage", required=false, defaultValue="1") int expPayPage
			, @RequestParam(name="workPayPage", required=false, defaultValue="1") int workPayPage
			, HttpServletResponse resp
			) throws IOException {

			Map<String,Object> resultMap = null;
			MemberVO checkAdmin = check.checkAdmin();
			
			if(checkAdmin != null) {
				PagingVO<ExpertPaymentVO> expPagingVO = new PagingVO<>(8, 5);
            	expPagingVO.setCurrentPage(expPayPage);
            	
            	PagingVO<WorkPaymentVO> workPagingVO = new PagingVO<>(8, 5);
            	workPagingVO.setCurrentPage(workPayPage);
            	
            	Map<String, Object> expMap = commonMapLogic(who, expPagingVO);
            	Map<String, Object> workMap = commonMapLogic(who, workPagingVO);
            	
            	service.retrieveExpertPayList(expMap, expPagingVO);
            	service.retrieveWorkPayList(workMap, workPagingVO);
            	
            	resultMap = new HashMap<String, Object>();
            	resultMap.put("expPagingVO", expPagingVO);
            	resultMap.put("workPagingVO", workPagingVO);
			} else {
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			}

		return  resultMap;
		
	}
	
	// 회원 환불정보
	@ResponseBody
	@RequestMapping(value="refund", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Map<String,Object> refundView(
			@PathVariable String who
			, @RequestParam(name="expRefPage", required=false, defaultValue="1") int expRefPage
			, @RequestParam(name="workRefPage", required=false, defaultValue="1") int workRefPage
			, HttpServletResponse resp
			) throws IOException {

			Map<String,Object> resultMap = null;
			MemberVO checkAdmin = check.checkAdmin();
			
			if(checkAdmin != null) {
				PagingVO<ExpertRefundVO> expRefPagingVO = new PagingVO<>(8, 5);
        		expRefPagingVO.setCurrentPage(expRefPage);
        		
        		PagingVO<WorkRefundVO> workRefPagingVO = new PagingVO<>(8, 5);
        		workRefPagingVO.setCurrentPage(workRefPage);
        		
        		Map<String, Object> expRefMap = commonMapLogic(who, expRefPagingVO);
        		Map<String, Object> workRefMap = commonMapLogic(who, workRefPagingVO);
        		
        		service.retrieveExpertRefundList(expRefMap, expRefPagingVO);
        		service.retrieveWorkRefundList(workRefMap, workRefPagingVO);
            	
            	resultMap = new HashMap<String, Object>();
            	resultMap.put("expRefPagingVO", expRefPagingVO);
        		resultMap.put("workRefPagingVO", workRefPagingVO);
			} else {
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			}

		return  resultMap;
		
	}
	
	public Map<String,Object> commonMapLogic(String who, PagingVO<? extends Object> pagingVO) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("memId", who);
		map.put("pagingVO", pagingVO);
		
		return map;
	}
	
}

