package kr.or.ddit.admin.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.admin.dao.MemberManagementDAO;
import kr.or.ddit.admin.service.ReportManagementService;
import kr.or.ddit.common.CheckMember;
import kr.or.ddit.common.vo.MemberVO;
import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.community.coop.vo.CooBoardVO;
import kr.or.ddit.community.free.vo.FreeBoardVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author 심민경
 * @since 2022. 8. 5.
 * @version 1.0
 * @param <T>
 * @see javax.servlet.http.HttpServlet
 * <pre>
 * [[개정이력(Modification Information)]]
 * 수정일                      수정자               수정내용
 * --------     --------    ----------------------
 * 2022. 8. 5.      심민경           최초작성
 * Copyright (c) 2022 by DDIT All right reserved
 * </pre>
 */
@Slf4j
@Controller
@RequestMapping("/admin/report")
public class ReportListController {
	
	@Inject
	private ReportManagementService service;
	@Inject
	private MemberManagementDAO memberDAO;
	@Inject
	private CheckMember check;
	
	@GetMapping
	public String getReportHTML(){
		String viewName = null;
		MemberVO checkAdmin = check.checkAdmin();
		
		if(checkAdmin != null) {
			
			viewName = "admin/reportList";
		} else {
			viewName = "redirect:/login";
		}
		
		return viewName;
		
	}
	
	@ResponseBody
	@GetMapping(value="member", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public PagingVO<MemberVO> getMemList(
			@RequestParam(name="memPage", required=false, defaultValue="1") int currentPage
			, Model model
			, HttpServletResponse resp
			) throws IOException {
		
		PagingVO<MemberVO> pagingVO = null;
		MemberVO checkAdmin = check.checkAdmin();
		
		if(checkAdmin != null) {
			pagingVO = new PagingVO<>(8, 5);
			pagingVO.setCurrentPage(currentPage);
			service.retrieveMemberReportList(pagingVO);
		} else {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
		
		return pagingVO;
	}
	
	@ResponseBody
	@GetMapping(value="exp", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public PagingVO<MemberVO> getExpBoardList(
			@RequestParam(name="exPage", required=false, defaultValue="1") int currentPage
			, Model model
			, HttpServletResponse resp
			) throws IOException {
		
		PagingVO<MemberVO> pagingVO = null;
		MemberVO checkAdmin = check.checkAdmin();
		
		if(checkAdmin != null) {
			pagingVO = new PagingVO<>(8, 5);
			pagingVO.setCurrentPage(currentPage);
			service.retrieveExpertReportList(pagingVO);
		} else {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
		
		return pagingVO;
		
	}
	
	@ResponseBody
	@GetMapping(value="coo", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public PagingVO<CooBoardVO> getCooBoardList(
			@RequestParam(name="cooPage", required=false, defaultValue="1") int currentPage
			, Model model
			, HttpServletResponse resp
			) throws IOException {
		
		PagingVO<CooBoardVO> pagingVO = null;
		MemberVO checkAdmin = check.checkAdmin();
		
		if(checkAdmin != null) {
			pagingVO = new PagingVO<>(8, 5);
			pagingVO.setCurrentPage(currentPage);
			service.retrieveCooBoardReportList(pagingVO);
		} else {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
		
		return pagingVO;
	}
	
	@ResponseBody
	@GetMapping(value="free", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public PagingVO<FreeBoardVO> getFreeBoardList(
			@RequestParam(name="freePage", required=false, defaultValue="1") int currentPage
			, Model model
			, HttpServletResponse resp
			) throws IOException {
		
		PagingVO<FreeBoardVO> pagingVO = null;
		MemberVO checkAdmin = check.checkAdmin();
		
		if(checkAdmin != null) {
			pagingVO = new PagingVO<>(8, 5);
			pagingVO.setCurrentPage(currentPage);
			service.retrieveFreeBoardReportList(pagingVO);
		} else {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
		
		return pagingVO;
		
	}
	
}
