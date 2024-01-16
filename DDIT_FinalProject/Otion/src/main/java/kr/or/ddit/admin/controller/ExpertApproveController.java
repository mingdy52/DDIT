package kr.or.ddit.admin.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.admin.dao.MemberManagementDAO;
import kr.or.ddit.admin.service.MemberManagementService;
import kr.or.ddit.common.CheckMember;
import kr.or.ddit.common.attach.dao.AttatchDAO;
import kr.or.ddit.common.vo.AttatchVO;
import kr.or.ddit.common.vo.MemberVO;
import kr.or.ddit.common.vo.NewsVO;
import kr.or.ddit.common.vo.PagingVO;
import kr.or.ddit.common.vo.SimpleSearchCondition;
import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.expert.vo.ExpFormVO;
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
@Controller
@RequestMapping("/admin/approve")
public class ExpertApproveController {
	
	@Inject
	private MemberManagementService service;
	@Inject
	private MemberManagementDAO memberDAO;
	@Inject
	private CheckMember check;
	
	@Inject
	private AttatchDAO attatchDAO;
	
	@ModelAttribute("news")
	public NewsVO news() {
		return new NewsVO();
	}
	
	// 전문가 신청서 목록
	@GetMapping
	public String getExpertApprove(
			@RequestParam(name="page", required=false, defaultValue="1") int currentPage
			, @RequestParam(name="apprCode", required=false, defaultValue="A01") String apprCode
			, @RequestParam(value="searchWord", required=false) String searchWord
			, Model model
			) {
		
		String viewName = null;
		MemberVO checkAdmin = check.checkAdmin();
		
		if(checkAdmin != null) {
			SimpleSearchCondition searchVO = new SimpleSearchCondition();
			searchVO.setSearchWord(searchWord);
			
			PagingVO<ExpFormVO> pagingVO = new PagingVO<>(8, 5);
			pagingVO.setCurrentPage(currentPage);
			pagingVO.setSimpleCondition(searchVO);
			
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("apprCode", apprCode);
			map.put("pagingVO", pagingVO);
			
			service.retrieveExpertFormList(map, pagingVO);
			model.addAttribute("pagingVO", pagingVO);
			model.addAttribute("apprCode", apprCode);
			
			viewName = "admin/expertApproveList";
		} else {
			viewName = "redirect:/login";
		}
		return viewName;
	}
	
	// 전문가 신청서 상세보기
	@GetMapping("{exFormNum}")
	public String getExpertApprove(
			@PathVariable(name="exFormNum", required=false) String exFormNum
			, Model model
			) {
		String viewName = null;
		MemberVO checkAdmin = check.checkAdmin();
		
		if(checkAdmin != null) {
			ExpFormVO expFormVO = service.retrieveExpertForm(exFormNum);
        	AttatchVO attatchVO = attatchDAO.selectDownload(expFormVO.getExFormAssume());
        	
        	model.addAttribute("expFormVO", expFormVO);
        	model.addAttribute("attatchVO", attatchVO);
			
			viewName = "admin/expertApproveView";
		} else {
			viewName = "redirect:/login";
		}
		return viewName;
		
	}
	
	// 전문가 승인 처리
	@PostMapping("{exFormNum}")
	public String approveResult(
			@ModelAttribute("news") NewsVO newsVO
			, @RequestParam("exFormNum") String exFormNum
			, RedirectAttributes rd
			) {
		
		String viewName = null;
		MemberVO checkAdmin = check.checkAdmin();
		
		if(checkAdmin != null) {
			String memId=checkAdmin.getMemId(); 
			newsVO.setNewsId(memId);
			ServiceResult result = service.approveResultExpert(newsVO, exFormNum);
			switch (result) {
			case OK:
				rd.addFlashAttribute("message", "완료되었습니다.");
				viewName = "redirect:/admin/approve";
				break;
			case FAIL:
				rd.addFlashAttribute("message", "다시 시도해주세요.");
				viewName = "redirect:/admin/approve/{exFormNum}";
				break;
			}
			
		} else {
			viewName = "redirect:/login";
		}
		return viewName;
		
	}
	
}
